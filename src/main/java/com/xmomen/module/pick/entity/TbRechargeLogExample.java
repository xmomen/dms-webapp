package com.xmomen.module.pick.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbRechargeLogExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbRechargeLogExample() {
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

        public Criteria andCouponNoIsNull() {
            addCriterion("COUPON_NO is null");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNotNull() {
            addCriterion("COUPON_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNoEqualTo(String value) {
            addCriterion("COUPON_NO =", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotEqualTo(String value) {
            addCriterion("COUPON_NO <>", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThan(String value) {
            addCriterion("COUPON_NO >", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_NO >=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThan(String value) {
            addCriterion("COUPON_NO <", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThanOrEqualTo(String value) {
            addCriterion("COUPON_NO <=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLike(String value) {
            addCriterion("COUPON_NO like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotLike(String value) {
            addCriterion("COUPON_NO not like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoIn(List<String> values) {
            addCriterion("COUPON_NO in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotIn(List<String> values) {
            addCriterion("COUPON_NO not in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoBetween(String value1, String value2) {
            addCriterion("COUPON_NO between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotBetween(String value1, String value2) {
            addCriterion("COUPON_NO not between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andRechargePriceIsNull() {
            addCriterion("RECHARGE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andRechargePriceIsNotNull() {
            addCriterion("RECHARGE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andRechargePriceEqualTo(BigDecimal value) {
            addCriterion("RECHARGE_PRICE =", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceNotEqualTo(BigDecimal value) {
            addCriterion("RECHARGE_PRICE <>", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceGreaterThan(BigDecimal value) {
            addCriterion("RECHARGE_PRICE >", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RECHARGE_PRICE >=", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceLessThan(BigDecimal value) {
            addCriterion("RECHARGE_PRICE <", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RECHARGE_PRICE <=", value, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceIn(List<BigDecimal> values) {
            addCriterion("RECHARGE_PRICE in", values, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceNotIn(List<BigDecimal> values) {
            addCriterion("RECHARGE_PRICE not in", values, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECHARGE_PRICE between", value1, value2, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECHARGE_PRICE not between", value1, value2, "rechargePrice");
            return (Criteria) this;
        }

        public Criteria andRechargeDateIsNull() {
            addCriterion("RECHARGE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRechargeDateIsNotNull() {
            addCriterion("RECHARGE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeDateEqualTo(Date value) {
            addCriterion("RECHARGE_DATE =", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateNotEqualTo(Date value) {
            addCriterion("RECHARGE_DATE <>", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateGreaterThan(Date value) {
            addCriterion("RECHARGE_DATE >", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("RECHARGE_DATE >=", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateLessThan(Date value) {
            addCriterion("RECHARGE_DATE <", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateLessThanOrEqualTo(Date value) {
            addCriterion("RECHARGE_DATE <=", value, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateIn(List<Date> values) {
            addCriterion("RECHARGE_DATE in", values, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateNotIn(List<Date> values) {
            addCriterion("RECHARGE_DATE not in", values, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateBetween(Date value1, Date value2) {
            addCriterion("RECHARGE_DATE between", value1, value2, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeDateNotBetween(Date value1, Date value2) {
            addCriterion("RECHARGE_DATE not between", value1, value2, "rechargeDate");
            return (Criteria) this;
        }

        public Criteria andRechargeUserIsNull() {
            addCriterion("RECHARGE_USER is null");
            return (Criteria) this;
        }

        public Criteria andRechargeUserIsNotNull() {
            addCriterion("RECHARGE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeUserEqualTo(Integer value) {
            addCriterion("RECHARGE_USER =", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserNotEqualTo(Integer value) {
            addCriterion("RECHARGE_USER <>", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserGreaterThan(Integer value) {
            addCriterion("RECHARGE_USER >", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECHARGE_USER >=", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserLessThan(Integer value) {
            addCriterion("RECHARGE_USER <", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserLessThanOrEqualTo(Integer value) {
            addCriterion("RECHARGE_USER <=", value, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserIn(List<Integer> values) {
            addCriterion("RECHARGE_USER in", values, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserNotIn(List<Integer> values) {
            addCriterion("RECHARGE_USER not in", values, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserBetween(Integer value1, Integer value2) {
            addCriterion("RECHARGE_USER between", value1, value2, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargeUserNotBetween(Integer value1, Integer value2) {
            addCriterion("RECHARGE_USER not between", value1, value2, "rechargeUser");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceIsNull() {
            addCriterion("RECHARGE_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceIsNotNull() {
            addCriterion("RECHARGE_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceEqualTo(String value) {
            addCriterion("RECHARGE_PLACE =", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotEqualTo(String value) {
            addCriterion("RECHARGE_PLACE <>", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceGreaterThan(String value) {
            addCriterion("RECHARGE_PLACE >", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("RECHARGE_PLACE >=", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceLessThan(String value) {
            addCriterion("RECHARGE_PLACE <", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceLessThanOrEqualTo(String value) {
            addCriterion("RECHARGE_PLACE <=", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceLike(String value) {
            addCriterion("RECHARGE_PLACE like", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotLike(String value) {
            addCriterion("RECHARGE_PLACE not like", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceIn(List<String> values) {
            addCriterion("RECHARGE_PLACE in", values, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotIn(List<String> values) {
            addCriterion("RECHARGE_PLACE not in", values, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceBetween(String value1, String value2) {
            addCriterion("RECHARGE_PLACE between", value1, value2, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotBetween(String value1, String value2) {
            addCriterion("RECHARGE_PLACE not between", value1, value2, "rechargePlace");
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