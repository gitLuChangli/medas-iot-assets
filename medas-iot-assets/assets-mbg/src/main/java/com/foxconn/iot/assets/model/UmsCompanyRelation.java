package com.foxconn.iot.assets.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class UmsCompanyRelation implements Serializable {
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "上级部门编号")
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    private Long ancestor;

    @ApiModelProperty(value = "部门编号")
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    private Long descendant;

    @ApiModelProperty(value = "部门之间层级深度")
    private Integer depth;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAncestor() {
        return ancestor;
    }

    public void setAncestor(Long ancestor) {
        this.ancestor = ancestor;
    }

    public Long getDescendant() {
        return descendant;
    }

    public void setDescendant(Long descendant) {
        this.descendant = descendant;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ancestor=").append(ancestor);
        sb.append(", descendant=").append(descendant);
        sb.append(", depth=").append(depth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}