package com.foxconn.iot.assets.model;

import java.util.ArrayList;
import java.util.List;

public class UmsCompanyRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsCompanyRelationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAncestorIsNull() {
            addCriterion("ancestor is null");
            return (Criteria) this;
        }

        public Criteria andAncestorIsNotNull() {
            addCriterion("ancestor is not null");
            return (Criteria) this;
        }

        public Criteria andAncestorEqualTo(Long value) {
            addCriterion("ancestor =", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorNotEqualTo(Long value) {
            addCriterion("ancestor <>", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorGreaterThan(Long value) {
            addCriterion("ancestor >", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorGreaterThanOrEqualTo(Long value) {
            addCriterion("ancestor >=", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorLessThan(Long value) {
            addCriterion("ancestor <", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorLessThanOrEqualTo(Long value) {
            addCriterion("ancestor <=", value, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorIn(List<Long> values) {
            addCriterion("ancestor in", values, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorNotIn(List<Long> values) {
            addCriterion("ancestor not in", values, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorBetween(Long value1, Long value2) {
            addCriterion("ancestor between", value1, value2, "ancestor");
            return (Criteria) this;
        }

        public Criteria andAncestorNotBetween(Long value1, Long value2) {
            addCriterion("ancestor not between", value1, value2, "ancestor");
            return (Criteria) this;
        }

        public Criteria andDescendantIsNull() {
            addCriterion("descendant is null");
            return (Criteria) this;
        }

        public Criteria andDescendantIsNotNull() {
            addCriterion("descendant is not null");
            return (Criteria) this;
        }

        public Criteria andDescendantEqualTo(Long value) {
            addCriterion("descendant =", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantNotEqualTo(Long value) {
            addCriterion("descendant <>", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantGreaterThan(Long value) {
            addCriterion("descendant >", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantGreaterThanOrEqualTo(Long value) {
            addCriterion("descendant >=", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantLessThan(Long value) {
            addCriterion("descendant <", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantLessThanOrEqualTo(Long value) {
            addCriterion("descendant <=", value, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantIn(List<Long> values) {
            addCriterion("descendant in", values, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantNotIn(List<Long> values) {
            addCriterion("descendant not in", values, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantBetween(Long value1, Long value2) {
            addCriterion("descendant between", value1, value2, "descendant");
            return (Criteria) this;
        }

        public Criteria andDescendantNotBetween(Long value1, Long value2) {
            addCriterion("descendant not between", value1, value2, "descendant");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("depth is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("depth is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(Integer value) {
            addCriterion("depth =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(Integer value) {
            addCriterion("depth <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(Integer value) {
            addCriterion("depth >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(Integer value) {
            addCriterion("depth >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(Integer value) {
            addCriterion("depth <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(Integer value) {
            addCriterion("depth <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<Integer> values) {
            addCriterion("depth in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<Integer> values) {
            addCriterion("depth not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(Integer value1, Integer value2) {
            addCriterion("depth between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(Integer value1, Integer value2) {
            addCriterion("depth not between", value1, value2, "depth");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}