package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdCouponExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdCouponExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNull() {
            addCriterion("COUPON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("COUPON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(Integer value) {
            addCriterion("COUPON_TYPE =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(Integer value) {
            addCriterion("COUPON_TYPE <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(Integer value) {
            addCriterion("COUPON_TYPE >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUPON_TYPE >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(Integer value) {
            addCriterion("COUPON_TYPE <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(Integer value) {
            addCriterion("COUPON_TYPE <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<Integer> values) {
            addCriterion("COUPON_TYPE in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<Integer> values) {
            addCriterion("COUPON_TYPE not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(Integer value1, Integer value2) {
            addCriterion("COUPON_TYPE between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("COUPON_TYPE not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryIsNull() {
            addCriterion("COUPON_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryIsNotNull() {
            addCriterion("COUPON_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryEqualTo(Integer value) {
            addCriterion("COUPON_CATEGORY =", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryNotEqualTo(Integer value) {
            addCriterion("COUPON_CATEGORY <>", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryGreaterThan(Integer value) {
            addCriterion("COUPON_CATEGORY >", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUPON_CATEGORY >=", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryLessThan(Integer value) {
            addCriterion("COUPON_CATEGORY <", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("COUPON_CATEGORY <=", value, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryIn(List<Integer> values) {
            addCriterion("COUPON_CATEGORY in", values, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryNotIn(List<Integer> values) {
            addCriterion("COUPON_CATEGORY not in", values, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryBetween(Integer value1, Integer value2) {
            addCriterion("COUPON_CATEGORY between", value1, value2, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("COUPON_CATEGORY not between", value1, value2, "couponCategory");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIsNull() {
            addCriterion("COUPON_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIsNotNull() {
            addCriterion("COUPON_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNumberEqualTo(String value) {
            addCriterion("COUPON_NUMBER =", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotEqualTo(String value) {
            addCriterion("COUPON_NUMBER <>", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberGreaterThan(String value) {
            addCriterion("COUPON_NUMBER >", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_NUMBER >=", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLessThan(String value) {
            addCriterion("COUPON_NUMBER <", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLessThanOrEqualTo(String value) {
            addCriterion("COUPON_NUMBER <=", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLike(String value) {
            addCriterion("COUPON_NUMBER like", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotLike(String value) {
            addCriterion("COUPON_NUMBER not like", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIn(List<String> values) {
            addCriterion("COUPON_NUMBER in", values, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotIn(List<String> values) {
            addCriterion("COUPON_NUMBER not in", values, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberBetween(String value1, String value2) {
            addCriterion("COUPON_NUMBER between", value1, value2, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotBetween(String value1, String value2) {
            addCriterion("COUPON_NUMBER not between", value1, value2, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordIsNull() {
            addCriterion("COUPON_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordIsNotNull() {
            addCriterion("COUPON_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordEqualTo(String value) {
            addCriterion("COUPON_PASSWORD =", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordNotEqualTo(String value) {
            addCriterion("COUPON_PASSWORD <>", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordGreaterThan(String value) {
            addCriterion("COUPON_PASSWORD >", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_PASSWORD >=", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordLessThan(String value) {
            addCriterion("COUPON_PASSWORD <", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordLessThanOrEqualTo(String value) {
            addCriterion("COUPON_PASSWORD <=", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordLike(String value) {
            addCriterion("COUPON_PASSWORD like", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordNotLike(String value) {
            addCriterion("COUPON_PASSWORD not like", value, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordIn(List<String> values) {
            addCriterion("COUPON_PASSWORD in", values, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordNotIn(List<String> values) {
            addCriterion("COUPON_PASSWORD not in", values, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordBetween(String value1, String value2) {
            addCriterion("COUPON_PASSWORD between", value1, value2, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponPasswordNotBetween(String value1, String value2) {
            addCriterion("COUPON_PASSWORD not between", value1, value2, "couponPassword");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNull() {
            addCriterion("COUPON_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNotNull() {
            addCriterion("COUPON_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponValueEqualTo(BigDecimal value) {
            addCriterion("COUPON_VALUE =", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotEqualTo(BigDecimal value) {
            addCriterion("COUPON_VALUE <>", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThan(BigDecimal value) {
            addCriterion("COUPON_VALUE >", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COUPON_VALUE >=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThan(BigDecimal value) {
            addCriterion("COUPON_VALUE <", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COUPON_VALUE <=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueIn(List<BigDecimal> values) {
            addCriterion("COUPON_VALUE in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotIn(List<BigDecimal> values) {
            addCriterion("COUPON_VALUE not in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COUPON_VALUE between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COUPON_VALUE not between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andUserPriceIsNull() {
            addCriterion("USER_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andUserPriceIsNotNull() {
            addCriterion("USER_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPriceEqualTo(BigDecimal value) {
            addCriterion("USER_PRICE =", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceNotEqualTo(BigDecimal value) {
            addCriterion("USER_PRICE <>", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceGreaterThan(BigDecimal value) {
            addCriterion("USER_PRICE >", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("USER_PRICE >=", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceLessThan(BigDecimal value) {
            addCriterion("USER_PRICE <", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("USER_PRICE <=", value, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceIn(List<BigDecimal> values) {
            addCriterion("USER_PRICE in", values, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceNotIn(List<BigDecimal> values) {
            addCriterion("USER_PRICE not in", values, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("USER_PRICE between", value1, value2, "userPrice");
            return (Criteria) this;
        }

        public Criteria andUserPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("USER_PRICE not between", value1, value2, "userPrice");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIsNull() {
            addCriterion("CD_COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIsNotNull() {
            addCriterion("CD_COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID =", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID <>", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdGreaterThan(Integer value) {
            addCriterion("CD_COMPANY_ID >", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID >=", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdLessThan(Integer value) {
            addCriterion("CD_COMPANY_ID <", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID <=", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIn(List<Integer> values) {
            addCriterion("CD_COMPANY_ID in", values, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotIn(List<Integer> values) {
            addCriterion("CD_COMPANY_ID not in", values, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_COMPANY_ID between", value1, value2, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_COMPANY_ID not between", value1, value2, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIsNull() {
            addCriterion("CD_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIsNotNull() {
            addCriterion("CD_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdUserIdEqualTo(Integer value) {
            addCriterion("CD_USER_ID =", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotEqualTo(Integer value) {
            addCriterion("CD_USER_ID <>", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdGreaterThan(Integer value) {
            addCriterion("CD_USER_ID >", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_USER_ID >=", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdLessThan(Integer value) {
            addCriterion("CD_USER_ID <", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_USER_ID <=", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIn(List<Integer> values) {
            addCriterion("CD_USER_ID in", values, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotIn(List<Integer> values) {
            addCriterion("CD_USER_ID not in", values, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_USER_ID between", value1, value2, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_USER_ID not between", value1, value2, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCouponDescIsNull() {
            addCriterion("COUPON_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCouponDescIsNotNull() {
            addCriterion("COUPON_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDescEqualTo(String value) {
            addCriterion("COUPON_DESC =", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotEqualTo(String value) {
            addCriterion("COUPON_DESC <>", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescGreaterThan(String value) {
            addCriterion("COUPON_DESC >", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_DESC >=", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLessThan(String value) {
            addCriterion("COUPON_DESC <", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLessThanOrEqualTo(String value) {
            addCriterion("COUPON_DESC <=", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLike(String value) {
            addCriterion("COUPON_DESC like", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotLike(String value) {
            addCriterion("COUPON_DESC not like", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescIn(List<String> values) {
            addCriterion("COUPON_DESC in", values, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotIn(List<String> values) {
            addCriterion("COUPON_DESC not in", values, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescBetween(String value1, String value2) {
            addCriterion("COUPON_DESC between", value1, value2, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotBetween(String value1, String value2) {
            addCriterion("COUPON_DESC not between", value1, value2, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andIsUsedIsNull() {
            addCriterion("IS_USED is null");
            return (Criteria) this;
        }

        public Criteria andIsUsedIsNotNull() {
            addCriterion("IS_USED is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsedEqualTo(Integer value) {
            addCriterion("IS_USED =", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotEqualTo(Integer value) {
            addCriterion("IS_USED <>", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedGreaterThan(Integer value) {
            addCriterion("IS_USED >", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_USED >=", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedLessThan(Integer value) {
            addCriterion("IS_USED <", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedLessThanOrEqualTo(Integer value) {
            addCriterion("IS_USED <=", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedIn(List<Integer> values) {
            addCriterion("IS_USED in", values, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotIn(List<Integer> values) {
            addCriterion("IS_USED not in", values, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedBetween(Integer value1, Integer value2) {
            addCriterion("IS_USED between", value1, value2, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_USED not between", value1, value2, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsefulIsNull() {
            addCriterion("IS_USEFUL is null");
            return (Criteria) this;
        }

        public Criteria andIsUsefulIsNotNull() {
            addCriterion("IS_USEFUL is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsefulEqualTo(Integer value) {
            addCriterion("IS_USEFUL =", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulNotEqualTo(Integer value) {
            addCriterion("IS_USEFUL <>", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulGreaterThan(Integer value) {
            addCriterion("IS_USEFUL >", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_USEFUL >=", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulLessThan(Integer value) {
            addCriterion("IS_USEFUL <", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulLessThanOrEqualTo(Integer value) {
            addCriterion("IS_USEFUL <=", value, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulIn(List<Integer> values) {
            addCriterion("IS_USEFUL in", values, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulNotIn(List<Integer> values) {
            addCriterion("IS_USEFUL not in", values, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulBetween(Integer value1, Integer value2) {
            addCriterion("IS_USEFUL between", value1, value2, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsUsefulNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_USEFUL not between", value1, value2, "isUseful");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNull() {
            addCriterion("IS_GIFT is null");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNotNull() {
            addCriterion("IS_GIFT is not null");
            return (Criteria) this;
        }

        public Criteria andIsGiftEqualTo(Integer value) {
            addCriterion("IS_GIFT =", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotEqualTo(Integer value) {
            addCriterion("IS_GIFT <>", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThan(Integer value) {
            addCriterion("IS_GIFT >", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_GIFT >=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThan(Integer value) {
            addCriterion("IS_GIFT <", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThanOrEqualTo(Integer value) {
            addCriterion("IS_GIFT <=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftIn(List<Integer> values) {
            addCriterion("IS_GIFT in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotIn(List<Integer> values) {
            addCriterion("IS_GIFT not in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftBetween(Integer value1, Integer value2) {
            addCriterion("IS_GIFT between", value1, value2, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_GIFT not between", value1, value2, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNull() {
            addCriterion("IS_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("IS_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(Integer value) {
            addCriterion("IS_SEND =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(Integer value) {
            addCriterion("IS_SEND <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(Integer value) {
            addCriterion("IS_SEND >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SEND >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(Integer value) {
            addCriterion("IS_SEND <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SEND <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<Integer> values) {
            addCriterion("IS_SEND in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<Integer> values) {
            addCriterion("IS_SEND not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(Integer value1, Integer value2) {
            addCriterion("IS_SEND between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SEND not between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("PAYMENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("PAYMENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(Integer value) {
            addCriterion("PAYMENT_TYPE =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(Integer value) {
            addCriterion("PAYMENT_TYPE <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(Integer value) {
            addCriterion("PAYMENT_TYPE >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAYMENT_TYPE >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(Integer value) {
            addCriterion("PAYMENT_TYPE <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("PAYMENT_TYPE <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<Integer> values) {
            addCriterion("PAYMENT_TYPE in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<Integer> values) {
            addCriterion("PAYMENT_TYPE not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(Integer value1, Integer value2) {
            addCriterion("PAYMENT_TYPE between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("PAYMENT_TYPE not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andNotesIsNull() {
            addCriterion("NOTES is null");
            return (Criteria) this;
        }

        public Criteria andNotesIsNotNull() {
            addCriterion("NOTES is not null");
            return (Criteria) this;
        }

        public Criteria andNotesEqualTo(String value) {
            addCriterion("NOTES =", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotEqualTo(String value) {
            addCriterion("NOTES <>", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThan(String value) {
            addCriterion("NOTES >", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThanOrEqualTo(String value) {
            addCriterion("NOTES >=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThan(String value) {
            addCriterion("NOTES <", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThanOrEqualTo(String value) {
            addCriterion("NOTES <=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLike(String value) {
            addCriterion("NOTES like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotLike(String value) {
            addCriterion("NOTES not like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesIn(List<String> values) {
            addCriterion("NOTES in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotIn(List<String> values) {
            addCriterion("NOTES not in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesBetween(String value1, String value2) {
            addCriterion("NOTES between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotBetween(String value1, String value2) {
            addCriterion("NOTES not between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNull() {
            addCriterion("IS_OVER is null");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNotNull() {
            addCriterion("IS_OVER is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverEqualTo(Integer value) {
            addCriterion("IS_OVER =", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotEqualTo(Integer value) {
            addCriterion("IS_OVER <>", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThan(Integer value) {
            addCriterion("IS_OVER >", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_OVER >=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThan(Integer value) {
            addCriterion("IS_OVER <", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThanOrEqualTo(Integer value) {
            addCriterion("IS_OVER <=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverIn(List<Integer> values) {
            addCriterion("IS_OVER in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotIn(List<Integer> values) {
            addCriterion("IS_OVER not in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverBetween(Integer value1, Integer value2) {
            addCriterion("IS_OVER between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_OVER not between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNull() {
            addCriterion("AUDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("AUDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("AUDIT_DATE =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("AUDIT_DATE <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("AUDIT_DATE >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("AUDIT_DATE <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("AUDIT_DATE in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("AUDIT_DATE not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE not between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andBatchIsNull() {
            addCriterion("BATCH is null");
            return (Criteria) this;
        }

        public Criteria andBatchIsNotNull() {
            addCriterion("BATCH is not null");
            return (Criteria) this;
        }

        public Criteria andBatchEqualTo(String value) {
            addCriterion("BATCH =", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotEqualTo(String value) {
            addCriterion("BATCH <>", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThan(String value) {
            addCriterion("BATCH >", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH >=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThan(String value) {
            addCriterion("BATCH <", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThanOrEqualTo(String value) {
            addCriterion("BATCH <=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLike(String value) {
            addCriterion("BATCH like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotLike(String value) {
            addCriterion("BATCH not like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchIn(List<String> values) {
            addCriterion("BATCH in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotIn(List<String> values) {
            addCriterion("BATCH not in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchBetween(String value1, String value2) {
            addCriterion("BATCH between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotBetween(String value1, String value2) {
            addCriterion("BATCH not between", value1, value2, "batch");
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