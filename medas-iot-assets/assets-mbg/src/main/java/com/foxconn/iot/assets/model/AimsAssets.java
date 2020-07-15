package com.foxconn.iot.assets.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class AimsAssets implements Serializable {
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "部門編號")
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    private Long companyId;

    @ApiModelProperty(value = "管制編號")
    private String gzhNum;

    @ApiModelProperty(value = "財產編號")
    private String cchNum;

    @ApiModelProperty(value = "設備名稱")
    private String shbName;

    @ApiModelProperty(value = "設備品牌")
    private String shbBrand;

    @ApiModelProperty(value = "設備參數")
    private String shbSpec;

    @ApiModelProperty(value = "費用類型")
    private String fyType;

    @ApiModelProperty(value = "單位")
    private String unit;

    @ApiModelProperty(value = "單價")
    private String price;

    @ApiModelProperty(value = "廠商代碼")
    private String chshCode;

    @ApiModelProperty(value = "廠商名稱")
    private String chshName;

    @ApiModelProperty(value = "需求部門")
    private String xqDepartment;

    @ApiModelProperty(value = "設備面積")
    private String sheArea;

    @ApiModelProperty(value = "設備重量")
    private String shbWeight;

    @ApiModelProperty(value = "設備銘牌SN")
    private String shbSn;

    @ApiModelProperty(value = "所在專案")
    private String project;

    @ApiModelProperty(value = "專案段別")
    private String segment;

    @ApiModelProperty(value = "工站名稱")
    private String gzhName;

    @ApiModelProperty(value = "設備狀態")
    private String shbStatus;

    @ApiModelProperty(value = "樓棟")
    private String building;

    @ApiModelProperty(value = "樓層")
    private String floor;

    @ApiModelProperty(value = "線體")
    private String xianti;

    @ApiModelProperty(value = "PO單號")
    private String poNum;

    @ApiModelProperty(value = "領用工號")
    private String lyEmp;

    @ApiModelProperty(value = "領用姓名")
    private String lyEmpName;

    @ApiModelProperty(value = "到貨時間")
    private String dhTime;

    @ApiModelProperty(value = "校驗時間")
    private String jyTime;

    @ApiModelProperty(value = "校驗週期")
    private String yjCycle;

    @ApiModelProperty(value = "備註")
    private String note;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getGzhNum() {
        return gzhNum;
    }

    public void setGzhNum(String gzhNum) {
        this.gzhNum = gzhNum;
    }

    public String getCchNum() {
        return cchNum;
    }

    public void setCchNum(String cchNum) {
        this.cchNum = cchNum;
    }

    public String getShbName() {
        return shbName;
    }

    public void setShbName(String shbName) {
        this.shbName = shbName;
    }

    public String getShbBrand() {
        return shbBrand;
    }

    public void setShbBrand(String shbBrand) {
        this.shbBrand = shbBrand;
    }

    public String getShbSpec() {
        return shbSpec;
    }

    public void setShbSpec(String shbSpec) {
        this.shbSpec = shbSpec;
    }

    public String getFyType() {
        return fyType;
    }

    public void setFyType(String fyType) {
        this.fyType = fyType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChshCode() {
        return chshCode;
    }

    public void setChshCode(String chshCode) {
        this.chshCode = chshCode;
    }

    public String getChshName() {
        return chshName;
    }

    public void setChshName(String chshName) {
        this.chshName = chshName;
    }

    public String getXqDepartment() {
        return xqDepartment;
    }

    public void setXqDepartment(String xqDepartment) {
        this.xqDepartment = xqDepartment;
    }

    public String getSheArea() {
        return sheArea;
    }

    public void setSheArea(String sheArea) {
        this.sheArea = sheArea;
    }

    public String getShbWeight() {
        return shbWeight;
    }

    public void setShbWeight(String shbWeight) {
        this.shbWeight = shbWeight;
    }

    public String getShbSn() {
        return shbSn;
    }

    public void setShbSn(String shbSn) {
        this.shbSn = shbSn;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getGzhName() {
        return gzhName;
    }

    public void setGzhName(String gzhName) {
        this.gzhName = gzhName;
    }

    public String getShbStatus() {
        return shbStatus;
    }

    public void setShbStatus(String shbStatus) {
        this.shbStatus = shbStatus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getXianti() {
        return xianti;
    }

    public void setXianti(String xianti) {
        this.xianti = xianti;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getLyEmp() {
        return lyEmp;
    }

    public void setLyEmp(String lyEmp) {
        this.lyEmp = lyEmp;
    }

    public String getLyEmpName() {
        return lyEmpName;
    }

    public void setLyEmpName(String lyEmpName) {
        this.lyEmpName = lyEmpName;
    }

    public String getDhTime() {
        return dhTime;
    }

    public void setDhTime(String dhTime) {
        this.dhTime = dhTime;
    }

    public String getJyTime() {
        return jyTime;
    }

    public void setJyTime(String jyTime) {
        this.jyTime = jyTime;
    }

    public String getYjCycle() {
        return yjCycle;
    }

    public void setYjCycle(String yjCycle) {
        this.yjCycle = yjCycle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", gzhNum=").append(gzhNum);
        sb.append(", cchNum=").append(cchNum);
        sb.append(", shbName=").append(shbName);
        sb.append(", shbBrand=").append(shbBrand);
        sb.append(", shbSpec=").append(shbSpec);
        sb.append(", fyType=").append(fyType);
        sb.append(", unit=").append(unit);
        sb.append(", price=").append(price);
        sb.append(", chshCode=").append(chshCode);
        sb.append(", chshName=").append(chshName);
        sb.append(", xqDepartment=").append(xqDepartment);
        sb.append(", sheArea=").append(sheArea);
        sb.append(", shbWeight=").append(shbWeight);
        sb.append(", shbSn=").append(shbSn);
        sb.append(", project=").append(project);
        sb.append(", segment=").append(segment);
        sb.append(", gzhName=").append(gzhName);
        sb.append(", shbStatus=").append(shbStatus);
        sb.append(", building=").append(building);
        sb.append(", floor=").append(floor);
        sb.append(", xianti=").append(xianti);
        sb.append(", poNum=").append(poNum);
        sb.append(", lyEmp=").append(lyEmp);
        sb.append(", lyEmpName=").append(lyEmpName);
        sb.append(", dhTime=").append(dhTime);
        sb.append(", jyTime=").append(jyTime);
        sb.append(", yjCycle=").append(yjCycle);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}