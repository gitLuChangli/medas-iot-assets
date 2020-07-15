package com.foxconn.iot.assets.model;

import java.util.ArrayList;
import java.util.List;

public class AimsAssetsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AimsAssetsExample() {
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andGzhNumIsNull() {
            addCriterion("gzh_num is null");
            return (Criteria) this;
        }

        public Criteria andGzhNumIsNotNull() {
            addCriterion("gzh_num is not null");
            return (Criteria) this;
        }

        public Criteria andGzhNumEqualTo(String value) {
            addCriterion("gzh_num =", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumNotEqualTo(String value) {
            addCriterion("gzh_num <>", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumGreaterThan(String value) {
            addCriterion("gzh_num >", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_num >=", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumLessThan(String value) {
            addCriterion("gzh_num <", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumLessThanOrEqualTo(String value) {
            addCriterion("gzh_num <=", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumLike(String value) {
            addCriterion("gzh_num like", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumNotLike(String value) {
            addCriterion("gzh_num not like", value, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumIn(List<String> values) {
            addCriterion("gzh_num in", values, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumNotIn(List<String> values) {
            addCriterion("gzh_num not in", values, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumBetween(String value1, String value2) {
            addCriterion("gzh_num between", value1, value2, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andGzhNumNotBetween(String value1, String value2) {
            addCriterion("gzh_num not between", value1, value2, "gzhNum");
            return (Criteria) this;
        }

        public Criteria andCchNumIsNull() {
            addCriterion("cch_num is null");
            return (Criteria) this;
        }

        public Criteria andCchNumIsNotNull() {
            addCriterion("cch_num is not null");
            return (Criteria) this;
        }

        public Criteria andCchNumEqualTo(String value) {
            addCriterion("cch_num =", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumNotEqualTo(String value) {
            addCriterion("cch_num <>", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumGreaterThan(String value) {
            addCriterion("cch_num >", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumGreaterThanOrEqualTo(String value) {
            addCriterion("cch_num >=", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumLessThan(String value) {
            addCriterion("cch_num <", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumLessThanOrEqualTo(String value) {
            addCriterion("cch_num <=", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumLike(String value) {
            addCriterion("cch_num like", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumNotLike(String value) {
            addCriterion("cch_num not like", value, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumIn(List<String> values) {
            addCriterion("cch_num in", values, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumNotIn(List<String> values) {
            addCriterion("cch_num not in", values, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumBetween(String value1, String value2) {
            addCriterion("cch_num between", value1, value2, "cchNum");
            return (Criteria) this;
        }

        public Criteria andCchNumNotBetween(String value1, String value2) {
            addCriterion("cch_num not between", value1, value2, "cchNum");
            return (Criteria) this;
        }

        public Criteria andShbNameIsNull() {
            addCriterion("shb_name is null");
            return (Criteria) this;
        }

        public Criteria andShbNameIsNotNull() {
            addCriterion("shb_name is not null");
            return (Criteria) this;
        }

        public Criteria andShbNameEqualTo(String value) {
            addCriterion("shb_name =", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameNotEqualTo(String value) {
            addCriterion("shb_name <>", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameGreaterThan(String value) {
            addCriterion("shb_name >", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameGreaterThanOrEqualTo(String value) {
            addCriterion("shb_name >=", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameLessThan(String value) {
            addCriterion("shb_name <", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameLessThanOrEqualTo(String value) {
            addCriterion("shb_name <=", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameLike(String value) {
            addCriterion("shb_name like", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameNotLike(String value) {
            addCriterion("shb_name not like", value, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameIn(List<String> values) {
            addCriterion("shb_name in", values, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameNotIn(List<String> values) {
            addCriterion("shb_name not in", values, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameBetween(String value1, String value2) {
            addCriterion("shb_name between", value1, value2, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbNameNotBetween(String value1, String value2) {
            addCriterion("shb_name not between", value1, value2, "shbName");
            return (Criteria) this;
        }

        public Criteria andShbBrandIsNull() {
            addCriterion("shb_brand is null");
            return (Criteria) this;
        }

        public Criteria andShbBrandIsNotNull() {
            addCriterion("shb_brand is not null");
            return (Criteria) this;
        }

        public Criteria andShbBrandEqualTo(String value) {
            addCriterion("shb_brand =", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandNotEqualTo(String value) {
            addCriterion("shb_brand <>", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandGreaterThan(String value) {
            addCriterion("shb_brand >", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandGreaterThanOrEqualTo(String value) {
            addCriterion("shb_brand >=", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandLessThan(String value) {
            addCriterion("shb_brand <", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandLessThanOrEqualTo(String value) {
            addCriterion("shb_brand <=", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandLike(String value) {
            addCriterion("shb_brand like", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandNotLike(String value) {
            addCriterion("shb_brand not like", value, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandIn(List<String> values) {
            addCriterion("shb_brand in", values, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandNotIn(List<String> values) {
            addCriterion("shb_brand not in", values, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandBetween(String value1, String value2) {
            addCriterion("shb_brand between", value1, value2, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbBrandNotBetween(String value1, String value2) {
            addCriterion("shb_brand not between", value1, value2, "shbBrand");
            return (Criteria) this;
        }

        public Criteria andShbSpecIsNull() {
            addCriterion("shb_spec is null");
            return (Criteria) this;
        }

        public Criteria andShbSpecIsNotNull() {
            addCriterion("shb_spec is not null");
            return (Criteria) this;
        }

        public Criteria andShbSpecEqualTo(String value) {
            addCriterion("shb_spec =", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecNotEqualTo(String value) {
            addCriterion("shb_spec <>", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecGreaterThan(String value) {
            addCriterion("shb_spec >", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecGreaterThanOrEqualTo(String value) {
            addCriterion("shb_spec >=", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecLessThan(String value) {
            addCriterion("shb_spec <", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecLessThanOrEqualTo(String value) {
            addCriterion("shb_spec <=", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecLike(String value) {
            addCriterion("shb_spec like", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecNotLike(String value) {
            addCriterion("shb_spec not like", value, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecIn(List<String> values) {
            addCriterion("shb_spec in", values, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecNotIn(List<String> values) {
            addCriterion("shb_spec not in", values, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecBetween(String value1, String value2) {
            addCriterion("shb_spec between", value1, value2, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andShbSpecNotBetween(String value1, String value2) {
            addCriterion("shb_spec not between", value1, value2, "shbSpec");
            return (Criteria) this;
        }

        public Criteria andFyTypeIsNull() {
            addCriterion("fy_type is null");
            return (Criteria) this;
        }

        public Criteria andFyTypeIsNotNull() {
            addCriterion("fy_type is not null");
            return (Criteria) this;
        }

        public Criteria andFyTypeEqualTo(String value) {
            addCriterion("fy_type =", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeNotEqualTo(String value) {
            addCriterion("fy_type <>", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeGreaterThan(String value) {
            addCriterion("fy_type >", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("fy_type >=", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeLessThan(String value) {
            addCriterion("fy_type <", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeLessThanOrEqualTo(String value) {
            addCriterion("fy_type <=", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeLike(String value) {
            addCriterion("fy_type like", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeNotLike(String value) {
            addCriterion("fy_type not like", value, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeIn(List<String> values) {
            addCriterion("fy_type in", values, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeNotIn(List<String> values) {
            addCriterion("fy_type not in", values, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeBetween(String value1, String value2) {
            addCriterion("fy_type between", value1, value2, "fyType");
            return (Criteria) this;
        }

        public Criteria andFyTypeNotBetween(String value1, String value2) {
            addCriterion("fy_type not between", value1, value2, "fyType");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andChshCodeIsNull() {
            addCriterion("chsh_code is null");
            return (Criteria) this;
        }

        public Criteria andChshCodeIsNotNull() {
            addCriterion("chsh_code is not null");
            return (Criteria) this;
        }

        public Criteria andChshCodeEqualTo(String value) {
            addCriterion("chsh_code =", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeNotEqualTo(String value) {
            addCriterion("chsh_code <>", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeGreaterThan(String value) {
            addCriterion("chsh_code >", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeGreaterThanOrEqualTo(String value) {
            addCriterion("chsh_code >=", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeLessThan(String value) {
            addCriterion("chsh_code <", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeLessThanOrEqualTo(String value) {
            addCriterion("chsh_code <=", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeLike(String value) {
            addCriterion("chsh_code like", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeNotLike(String value) {
            addCriterion("chsh_code not like", value, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeIn(List<String> values) {
            addCriterion("chsh_code in", values, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeNotIn(List<String> values) {
            addCriterion("chsh_code not in", values, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeBetween(String value1, String value2) {
            addCriterion("chsh_code between", value1, value2, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshCodeNotBetween(String value1, String value2) {
            addCriterion("chsh_code not between", value1, value2, "chshCode");
            return (Criteria) this;
        }

        public Criteria andChshNameIsNull() {
            addCriterion("chsh_name is null");
            return (Criteria) this;
        }

        public Criteria andChshNameIsNotNull() {
            addCriterion("chsh_name is not null");
            return (Criteria) this;
        }

        public Criteria andChshNameEqualTo(String value) {
            addCriterion("chsh_name =", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameNotEqualTo(String value) {
            addCriterion("chsh_name <>", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameGreaterThan(String value) {
            addCriterion("chsh_name >", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameGreaterThanOrEqualTo(String value) {
            addCriterion("chsh_name >=", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameLessThan(String value) {
            addCriterion("chsh_name <", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameLessThanOrEqualTo(String value) {
            addCriterion("chsh_name <=", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameLike(String value) {
            addCriterion("chsh_name like", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameNotLike(String value) {
            addCriterion("chsh_name not like", value, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameIn(List<String> values) {
            addCriterion("chsh_name in", values, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameNotIn(List<String> values) {
            addCriterion("chsh_name not in", values, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameBetween(String value1, String value2) {
            addCriterion("chsh_name between", value1, value2, "chshName");
            return (Criteria) this;
        }

        public Criteria andChshNameNotBetween(String value1, String value2) {
            addCriterion("chsh_name not between", value1, value2, "chshName");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentIsNull() {
            addCriterion("xq_department is null");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentIsNotNull() {
            addCriterion("xq_department is not null");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentEqualTo(String value) {
            addCriterion("xq_department =", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentNotEqualTo(String value) {
            addCriterion("xq_department <>", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentGreaterThan(String value) {
            addCriterion("xq_department >", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("xq_department >=", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentLessThan(String value) {
            addCriterion("xq_department <", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentLessThanOrEqualTo(String value) {
            addCriterion("xq_department <=", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentLike(String value) {
            addCriterion("xq_department like", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentNotLike(String value) {
            addCriterion("xq_department not like", value, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentIn(List<String> values) {
            addCriterion("xq_department in", values, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentNotIn(List<String> values) {
            addCriterion("xq_department not in", values, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentBetween(String value1, String value2) {
            addCriterion("xq_department between", value1, value2, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andXqDepartmentNotBetween(String value1, String value2) {
            addCriterion("xq_department not between", value1, value2, "xqDepartment");
            return (Criteria) this;
        }

        public Criteria andSheAreaIsNull() {
            addCriterion("she_area is null");
            return (Criteria) this;
        }

        public Criteria andSheAreaIsNotNull() {
            addCriterion("she_area is not null");
            return (Criteria) this;
        }

        public Criteria andSheAreaEqualTo(String value) {
            addCriterion("she_area =", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaNotEqualTo(String value) {
            addCriterion("she_area <>", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaGreaterThan(String value) {
            addCriterion("she_area >", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaGreaterThanOrEqualTo(String value) {
            addCriterion("she_area >=", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaLessThan(String value) {
            addCriterion("she_area <", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaLessThanOrEqualTo(String value) {
            addCriterion("she_area <=", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaLike(String value) {
            addCriterion("she_area like", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaNotLike(String value) {
            addCriterion("she_area not like", value, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaIn(List<String> values) {
            addCriterion("she_area in", values, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaNotIn(List<String> values) {
            addCriterion("she_area not in", values, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaBetween(String value1, String value2) {
            addCriterion("she_area between", value1, value2, "sheArea");
            return (Criteria) this;
        }

        public Criteria andSheAreaNotBetween(String value1, String value2) {
            addCriterion("she_area not between", value1, value2, "sheArea");
            return (Criteria) this;
        }

        public Criteria andShbWeightIsNull() {
            addCriterion("shb_weight is null");
            return (Criteria) this;
        }

        public Criteria andShbWeightIsNotNull() {
            addCriterion("shb_weight is not null");
            return (Criteria) this;
        }

        public Criteria andShbWeightEqualTo(String value) {
            addCriterion("shb_weight =", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightNotEqualTo(String value) {
            addCriterion("shb_weight <>", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightGreaterThan(String value) {
            addCriterion("shb_weight >", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightGreaterThanOrEqualTo(String value) {
            addCriterion("shb_weight >=", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightLessThan(String value) {
            addCriterion("shb_weight <", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightLessThanOrEqualTo(String value) {
            addCriterion("shb_weight <=", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightLike(String value) {
            addCriterion("shb_weight like", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightNotLike(String value) {
            addCriterion("shb_weight not like", value, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightIn(List<String> values) {
            addCriterion("shb_weight in", values, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightNotIn(List<String> values) {
            addCriterion("shb_weight not in", values, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightBetween(String value1, String value2) {
            addCriterion("shb_weight between", value1, value2, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbWeightNotBetween(String value1, String value2) {
            addCriterion("shb_weight not between", value1, value2, "shbWeight");
            return (Criteria) this;
        }

        public Criteria andShbSnIsNull() {
            addCriterion("shb_sn is null");
            return (Criteria) this;
        }

        public Criteria andShbSnIsNotNull() {
            addCriterion("shb_sn is not null");
            return (Criteria) this;
        }

        public Criteria andShbSnEqualTo(String value) {
            addCriterion("shb_sn =", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnNotEqualTo(String value) {
            addCriterion("shb_sn <>", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnGreaterThan(String value) {
            addCriterion("shb_sn >", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnGreaterThanOrEqualTo(String value) {
            addCriterion("shb_sn >=", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnLessThan(String value) {
            addCriterion("shb_sn <", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnLessThanOrEqualTo(String value) {
            addCriterion("shb_sn <=", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnLike(String value) {
            addCriterion("shb_sn like", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnNotLike(String value) {
            addCriterion("shb_sn not like", value, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnIn(List<String> values) {
            addCriterion("shb_sn in", values, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnNotIn(List<String> values) {
            addCriterion("shb_sn not in", values, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnBetween(String value1, String value2) {
            addCriterion("shb_sn between", value1, value2, "shbSn");
            return (Criteria) this;
        }

        public Criteria andShbSnNotBetween(String value1, String value2) {
            addCriterion("shb_sn not between", value1, value2, "shbSn");
            return (Criteria) this;
        }

        public Criteria andProjectIsNull() {
            addCriterion("project is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("project is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(String value) {
            addCriterion("project =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(String value) {
            addCriterion("project <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(String value) {
            addCriterion("project >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(String value) {
            addCriterion("project >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(String value) {
            addCriterion("project <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(String value) {
            addCriterion("project <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLike(String value) {
            addCriterion("project like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotLike(String value) {
            addCriterion("project not like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<String> values) {
            addCriterion("project in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<String> values) {
            addCriterion("project not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(String value1, String value2) {
            addCriterion("project between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(String value1, String value2) {
            addCriterion("project not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andSegmentIsNull() {
            addCriterion("segment is null");
            return (Criteria) this;
        }

        public Criteria andSegmentIsNotNull() {
            addCriterion("segment is not null");
            return (Criteria) this;
        }

        public Criteria andSegmentEqualTo(String value) {
            addCriterion("segment =", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentNotEqualTo(String value) {
            addCriterion("segment <>", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentGreaterThan(String value) {
            addCriterion("segment >", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentGreaterThanOrEqualTo(String value) {
            addCriterion("segment >=", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentLessThan(String value) {
            addCriterion("segment <", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentLessThanOrEqualTo(String value) {
            addCriterion("segment <=", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentLike(String value) {
            addCriterion("segment like", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentNotLike(String value) {
            addCriterion("segment not like", value, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentIn(List<String> values) {
            addCriterion("segment in", values, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentNotIn(List<String> values) {
            addCriterion("segment not in", values, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentBetween(String value1, String value2) {
            addCriterion("segment between", value1, value2, "segment");
            return (Criteria) this;
        }

        public Criteria andSegmentNotBetween(String value1, String value2) {
            addCriterion("segment not between", value1, value2, "segment");
            return (Criteria) this;
        }

        public Criteria andGzhNameIsNull() {
            addCriterion("gzh_name is null");
            return (Criteria) this;
        }

        public Criteria andGzhNameIsNotNull() {
            addCriterion("gzh_name is not null");
            return (Criteria) this;
        }

        public Criteria andGzhNameEqualTo(String value) {
            addCriterion("gzh_name =", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotEqualTo(String value) {
            addCriterion("gzh_name <>", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameGreaterThan(String value) {
            addCriterion("gzh_name >", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_name >=", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLessThan(String value) {
            addCriterion("gzh_name <", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLessThanOrEqualTo(String value) {
            addCriterion("gzh_name <=", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLike(String value) {
            addCriterion("gzh_name like", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotLike(String value) {
            addCriterion("gzh_name not like", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameIn(List<String> values) {
            addCriterion("gzh_name in", values, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotIn(List<String> values) {
            addCriterion("gzh_name not in", values, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameBetween(String value1, String value2) {
            addCriterion("gzh_name between", value1, value2, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotBetween(String value1, String value2) {
            addCriterion("gzh_name not between", value1, value2, "gzhName");
            return (Criteria) this;
        }

        public Criteria andShbStatusIsNull() {
            addCriterion("shb_status is null");
            return (Criteria) this;
        }

        public Criteria andShbStatusIsNotNull() {
            addCriterion("shb_status is not null");
            return (Criteria) this;
        }

        public Criteria andShbStatusEqualTo(String value) {
            addCriterion("shb_status =", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusNotEqualTo(String value) {
            addCriterion("shb_status <>", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusGreaterThan(String value) {
            addCriterion("shb_status >", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusGreaterThanOrEqualTo(String value) {
            addCriterion("shb_status >=", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusLessThan(String value) {
            addCriterion("shb_status <", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusLessThanOrEqualTo(String value) {
            addCriterion("shb_status <=", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusLike(String value) {
            addCriterion("shb_status like", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusNotLike(String value) {
            addCriterion("shb_status not like", value, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusIn(List<String> values) {
            addCriterion("shb_status in", values, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusNotIn(List<String> values) {
            addCriterion("shb_status not in", values, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusBetween(String value1, String value2) {
            addCriterion("shb_status between", value1, value2, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andShbStatusNotBetween(String value1, String value2) {
            addCriterion("shb_status not between", value1, value2, "shbStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingIsNull() {
            addCriterion("building is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIsNotNull() {
            addCriterion("building is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingEqualTo(String value) {
            addCriterion("building =", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotEqualTo(String value) {
            addCriterion("building <>", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingGreaterThan(String value) {
            addCriterion("building >", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingGreaterThanOrEqualTo(String value) {
            addCriterion("building >=", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLessThan(String value) {
            addCriterion("building <", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLessThanOrEqualTo(String value) {
            addCriterion("building <=", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLike(String value) {
            addCriterion("building like", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotLike(String value) {
            addCriterion("building not like", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingIn(List<String> values) {
            addCriterion("building in", values, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotIn(List<String> values) {
            addCriterion("building not in", values, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingBetween(String value1, String value2) {
            addCriterion("building between", value1, value2, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotBetween(String value1, String value2) {
            addCriterion("building not between", value1, value2, "building");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(String value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(String value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(String value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(String value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(String value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(String value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLike(String value) {
            addCriterion("floor like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotLike(String value) {
            addCriterion("floor not like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<String> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<String> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(String value1, String value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(String value1, String value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andXiantiIsNull() {
            addCriterion("xianti is null");
            return (Criteria) this;
        }

        public Criteria andXiantiIsNotNull() {
            addCriterion("xianti is not null");
            return (Criteria) this;
        }

        public Criteria andXiantiEqualTo(String value) {
            addCriterion("xianti =", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiNotEqualTo(String value) {
            addCriterion("xianti <>", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiGreaterThan(String value) {
            addCriterion("xianti >", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiGreaterThanOrEqualTo(String value) {
            addCriterion("xianti >=", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiLessThan(String value) {
            addCriterion("xianti <", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiLessThanOrEqualTo(String value) {
            addCriterion("xianti <=", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiLike(String value) {
            addCriterion("xianti like", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiNotLike(String value) {
            addCriterion("xianti not like", value, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiIn(List<String> values) {
            addCriterion("xianti in", values, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiNotIn(List<String> values) {
            addCriterion("xianti not in", values, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiBetween(String value1, String value2) {
            addCriterion("xianti between", value1, value2, "xianti");
            return (Criteria) this;
        }

        public Criteria andXiantiNotBetween(String value1, String value2) {
            addCriterion("xianti not between", value1, value2, "xianti");
            return (Criteria) this;
        }

        public Criteria andPoNumIsNull() {
            addCriterion("po_num is null");
            return (Criteria) this;
        }

        public Criteria andPoNumIsNotNull() {
            addCriterion("po_num is not null");
            return (Criteria) this;
        }

        public Criteria andPoNumEqualTo(String value) {
            addCriterion("po_num =", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumNotEqualTo(String value) {
            addCriterion("po_num <>", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumGreaterThan(String value) {
            addCriterion("po_num >", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumGreaterThanOrEqualTo(String value) {
            addCriterion("po_num >=", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumLessThan(String value) {
            addCriterion("po_num <", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumLessThanOrEqualTo(String value) {
            addCriterion("po_num <=", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumLike(String value) {
            addCriterion("po_num like", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumNotLike(String value) {
            addCriterion("po_num not like", value, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumIn(List<String> values) {
            addCriterion("po_num in", values, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumNotIn(List<String> values) {
            addCriterion("po_num not in", values, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumBetween(String value1, String value2) {
            addCriterion("po_num between", value1, value2, "poNum");
            return (Criteria) this;
        }

        public Criteria andPoNumNotBetween(String value1, String value2) {
            addCriterion("po_num not between", value1, value2, "poNum");
            return (Criteria) this;
        }

        public Criteria andLyEmpIsNull() {
            addCriterion("ly_emp is null");
            return (Criteria) this;
        }

        public Criteria andLyEmpIsNotNull() {
            addCriterion("ly_emp is not null");
            return (Criteria) this;
        }

        public Criteria andLyEmpEqualTo(String value) {
            addCriterion("ly_emp =", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpNotEqualTo(String value) {
            addCriterion("ly_emp <>", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpGreaterThan(String value) {
            addCriterion("ly_emp >", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpGreaterThanOrEqualTo(String value) {
            addCriterion("ly_emp >=", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpLessThan(String value) {
            addCriterion("ly_emp <", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpLessThanOrEqualTo(String value) {
            addCriterion("ly_emp <=", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpLike(String value) {
            addCriterion("ly_emp like", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpNotLike(String value) {
            addCriterion("ly_emp not like", value, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpIn(List<String> values) {
            addCriterion("ly_emp in", values, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpNotIn(List<String> values) {
            addCriterion("ly_emp not in", values, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpBetween(String value1, String value2) {
            addCriterion("ly_emp between", value1, value2, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpNotBetween(String value1, String value2) {
            addCriterion("ly_emp not between", value1, value2, "lyEmp");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameIsNull() {
            addCriterion("ly_emp_name is null");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameIsNotNull() {
            addCriterion("ly_emp_name is not null");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameEqualTo(String value) {
            addCriterion("ly_emp_name =", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameNotEqualTo(String value) {
            addCriterion("ly_emp_name <>", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameGreaterThan(String value) {
            addCriterion("ly_emp_name >", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("ly_emp_name >=", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameLessThan(String value) {
            addCriterion("ly_emp_name <", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameLessThanOrEqualTo(String value) {
            addCriterion("ly_emp_name <=", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameLike(String value) {
            addCriterion("ly_emp_name like", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameNotLike(String value) {
            addCriterion("ly_emp_name not like", value, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameIn(List<String> values) {
            addCriterion("ly_emp_name in", values, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameNotIn(List<String> values) {
            addCriterion("ly_emp_name not in", values, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameBetween(String value1, String value2) {
            addCriterion("ly_emp_name between", value1, value2, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andLyEmpNameNotBetween(String value1, String value2) {
            addCriterion("ly_emp_name not between", value1, value2, "lyEmpName");
            return (Criteria) this;
        }

        public Criteria andDhTimeIsNull() {
            addCriterion("dh_time is null");
            return (Criteria) this;
        }

        public Criteria andDhTimeIsNotNull() {
            addCriterion("dh_time is not null");
            return (Criteria) this;
        }

        public Criteria andDhTimeEqualTo(String value) {
            addCriterion("dh_time =", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeNotEqualTo(String value) {
            addCriterion("dh_time <>", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeGreaterThan(String value) {
            addCriterion("dh_time >", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeGreaterThanOrEqualTo(String value) {
            addCriterion("dh_time >=", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeLessThan(String value) {
            addCriterion("dh_time <", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeLessThanOrEqualTo(String value) {
            addCriterion("dh_time <=", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeLike(String value) {
            addCriterion("dh_time like", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeNotLike(String value) {
            addCriterion("dh_time not like", value, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeIn(List<String> values) {
            addCriterion("dh_time in", values, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeNotIn(List<String> values) {
            addCriterion("dh_time not in", values, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeBetween(String value1, String value2) {
            addCriterion("dh_time between", value1, value2, "dhTime");
            return (Criteria) this;
        }

        public Criteria andDhTimeNotBetween(String value1, String value2) {
            addCriterion("dh_time not between", value1, value2, "dhTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeIsNull() {
            addCriterion("jy_time is null");
            return (Criteria) this;
        }

        public Criteria andJyTimeIsNotNull() {
            addCriterion("jy_time is not null");
            return (Criteria) this;
        }

        public Criteria andJyTimeEqualTo(String value) {
            addCriterion("jy_time =", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeNotEqualTo(String value) {
            addCriterion("jy_time <>", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeGreaterThan(String value) {
            addCriterion("jy_time >", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("jy_time >=", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeLessThan(String value) {
            addCriterion("jy_time <", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeLessThanOrEqualTo(String value) {
            addCriterion("jy_time <=", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeLike(String value) {
            addCriterion("jy_time like", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeNotLike(String value) {
            addCriterion("jy_time not like", value, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeIn(List<String> values) {
            addCriterion("jy_time in", values, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeNotIn(List<String> values) {
            addCriterion("jy_time not in", values, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeBetween(String value1, String value2) {
            addCriterion("jy_time between", value1, value2, "jyTime");
            return (Criteria) this;
        }

        public Criteria andJyTimeNotBetween(String value1, String value2) {
            addCriterion("jy_time not between", value1, value2, "jyTime");
            return (Criteria) this;
        }

        public Criteria andYjCycleIsNull() {
            addCriterion("yj_cycle is null");
            return (Criteria) this;
        }

        public Criteria andYjCycleIsNotNull() {
            addCriterion("yj_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andYjCycleEqualTo(String value) {
            addCriterion("yj_cycle =", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleNotEqualTo(String value) {
            addCriterion("yj_cycle <>", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleGreaterThan(String value) {
            addCriterion("yj_cycle >", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleGreaterThanOrEqualTo(String value) {
            addCriterion("yj_cycle >=", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleLessThan(String value) {
            addCriterion("yj_cycle <", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleLessThanOrEqualTo(String value) {
            addCriterion("yj_cycle <=", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleLike(String value) {
            addCriterion("yj_cycle like", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleNotLike(String value) {
            addCriterion("yj_cycle not like", value, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleIn(List<String> values) {
            addCriterion("yj_cycle in", values, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleNotIn(List<String> values) {
            addCriterion("yj_cycle not in", values, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleBetween(String value1, String value2) {
            addCriterion("yj_cycle between", value1, value2, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andYjCycleNotBetween(String value1, String value2) {
            addCriterion("yj_cycle not between", value1, value2, "yjCycle");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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