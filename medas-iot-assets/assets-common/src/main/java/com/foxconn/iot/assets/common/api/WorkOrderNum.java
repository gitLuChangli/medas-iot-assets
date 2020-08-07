package com.foxconn.iot.assets.common.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WorkOrderNum {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/** 机器id所占的位数 */
	private final static long workerIdBits = 5L;
	
	/** 数据标识id所占的位数 */
	private final static long datacenterIdBits = 5L;
	
	/** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

	/** 支持的最大数据标识id，结果是31 */
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	
	/** 序列在id中占的位数 */
	private final long sequenceBits = 12L;

	/** 机器ID向左移12位 */
	private final long workerIdShift = sequenceBits;

	/** 数据标识id向左移17位(12+5) */
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	
	/** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	/** 工作机器ID(0~31) */
	private long workerId;

	/** 数据中心ID(0~31) */
	private long datacenterId;

	/** 毫秒内序列(0~4095) */
	private long sequence = 0L;

	/** 上次生成ID的时间截 */
	private long lastTimestamp = -1L;
	
	private static WorkOrderNum worker = new WorkOrderNum(0, 0);
	
	public static void init(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		synchronized (WorkOrderNum.class) {
			if (worker == null) {
				worker = new WorkOrderNum(workerId, datacenterId);
			} else {
				worker.workerId = workerId;
				worker.datacenterId = datacenterId;
			}
		}
	}
	
	public WorkOrderNum(long workerId, long datacenterId) {
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}
	
	// ==============================Methods==========================================
	/**
	 * 获得下一个工单号 (该方法是线程安全的)
	 * 
	 * @return WorkOrderNum
	 */
	public synchronized String nextNum() {
		long timestamp = timeGen();

		// 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		// 如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			// 毫秒内序列溢出
			if (sequence == 0) {
				// 阻塞到下一个毫秒,获得新的时间戳
				timestamp = tilNextMillis(lastTimestamp);
			}
		}
		// 时间戳改变，毫秒内序列重置
		else {
			sequence = 0L;
		}

		// 上次生成ID的时间截
		lastTimestamp = timestamp;

		long num = (datacenterId << datacenterIdShift) //
				| (workerId << workerIdShift) //
				| sequence;
		return String.format("%s%03d", sdf.format(timestamp), num);
	}

	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 * 
	 * @param lastTimestamp 上次生成ID的时间截
	 * @return 当前时间戳
	 */
	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 * 
	 * @return 当前时间(毫秒)
	 */
	protected long timeGen() {
		return System.currentTimeMillis();
	}
	
	public static String getNum() {
		return worker.nextNum();
	}
	
	private static ArrayList<String> indexes = new ArrayList<>();
	
	public static void main(String[] args) {		
		WorkOrderNum.init(1, 1);
		for (int i = 0; i < 1200; i++) {
			new Runnable() {				
				@Override
				public void run() {
					String num = WorkOrderNum.getNum();
					if (indexes.indexOf(num) == -1) {
						indexes.add(num);
						System.out.println(indexes.size() + "0");						
					} else {
						System.out.println("1");						
					}
				}
			}.run();
		}
	}
}
