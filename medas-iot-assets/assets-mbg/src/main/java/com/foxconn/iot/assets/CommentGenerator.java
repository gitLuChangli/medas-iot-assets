package com.foxconn.iot.assets;

import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 自定义注释生成器
 */
public class CommentGenerator extends DefaultCommentGenerator {
	private boolean addRemarkComments = false;
	private static final String EXAMPLE_SUFFIX = "Example";
	private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME = "io.swagger.annotations.ApiModelProperty";

	/**
	 * 设置用户配置的参数
	 */
	@Override
	public void addConfigurationProperties(Properties properties) {
		super.addConfigurationProperties(properties);
		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
	}

	/**
	 * 给字段添加注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		// 根据参数和备注信息判断是否添加备注信息
		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
//            addFieldJavaDoc(field, remarks);
			// 数据库中特殊字符需要转义
			if (remarks.contains("\"")) {
				remarks = remarks.replace("\"", "'");
			}
			// 给model的字段添加swagger注解
			field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\")");
			
			String m = field.getName() + " -> " + field.getType().getFullyQualifiedName();
			System.out.println(m);
		}	
		/** 格式化時間 */
		if(field.getType().getFullyQualifiedName().equalsIgnoreCase("java.util.Date")) {
            field.addJavaDocLine("@com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")");
        }
		/** Long -> String 前端整形取值範圍不夠 */
		if (field.getType().getFullyQualifiedName().equalsIgnoreCase("java.lang.Long")) {
			field.addJavaDocLine("@com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)");
		}
	}

	/**
	 * 给model的字段添加注释
	 */
//	private void addFieldJavaDoc(Field field, String remarks) {
//		// 文档注释开始
//		field.addJavaDocLine("/**");
//		// 获取数据库字段的备注信息
//		String[] remarkLines = remarks.split(System.getProperty("line.separator"));
//		for (String remarkLine : remarkLines) {
//			field.addJavaDocLine(" * " + remarkLine);
//		}
//		addJavadocTag(field, false);
//		field.addJavaDocLine(" */");
//	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		super.addJavaFileComment(compilationUnit);
		// 只在model中添加swagger注解类的导入
		if (!compilationUnit.isJavaInterface()
				&& !compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)) {
			compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
		}
	}
}
