package com.xmomen.module.pick.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class TbExchangeCardLogExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbExchangeCardLogExample() {
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

        public Criteria andOldCouponIdIsNull() {
            addCriterion("OLD_COUPON_ID is null");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdIsNotNull() {
            addCriterion("OLD_COUPON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdEqualTo(Integer value) {
            addCriterion("OLD_COUPON_ID =", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdNotEqualTo(Integer value) {
            addCriterion("OLD_COUPON_ID <>", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdGreaterThan(Integer value) {
            addCriterion("OLD_COUPON_ID >", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("OLD_COUPON_ID >=", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdLessThan(Integer value) {
            addCriterion("OLD_COUPON_ID <", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("OLD_COUPON_ID <=", value, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdIn(List<Integer> values) {
            addCriterion("OLD_COUPON_ID in", values, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdNotIn(List<Integer> values) {
            addCriterion("OLD_COUPON_ID not in", values, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("OLD_COUPON_ID between", value1, value2, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("OLD_COUPON_ID not between", value1, value2, "oldCouponId");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoIsNull() {
            addCriterion("OLD_COUPON_NO is null");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoIsNotNull() {
            addCriterion("OLD_COUPON_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoEqualTo(String value) {
            addCriterion("OLD_COUPON_NO =", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoNotEqualTo(String value) {
            addCriterion("OLD_COUPON_NO <>", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoGreaterThan(String value) {
            addCriterion("OLD_COUPON_NO >", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_COUPON_NO >=", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoLessThan(String value) {
            addCriterion("OLD_COUPON_NO <", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoLessThanOrEqualTo(String value) {
            addCriterion("OLD_COUPON_NO <=", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoLike(String value) {
            addCriterion("OLD_COUPON_NO like", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoNotLike(String value) {
            addCriterion("OLD_COUPON_NO not like", value, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoIn(List<String> values) {
            addCriterion("OLD_COUPON_NO in", values, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoNotIn(List<String> values) {
            addCriterion("OLD_COUPON_NO not in", values, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoBetween(String value1, String value2) {
            addCriterion("OLD_COUPON_NO between", value1, value2, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andOldCouponNoNotBetween(String value1, String value2) {
            addCriterion("OLD_COUPON_NO not between", value1, value2, "oldCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdIsNull() {
            addCriterion("NEW_COUPON_ID is null");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdIsNotNull() {
            addCriterion("NEW_COUPON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdEqualTo(Integer value) {
            addCriterion("NEW_COUPON_ID =", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdNotEqualTo(Integer value) {
            addCriterion("NEW_COUPON_ID <>", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdGreaterThan(Integer value) {
            addCriterion("NEW_COUPON_ID >", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("NEW_COUPON_ID >=", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdLessThan(Integer value) {
            addCriterion("NEW_COUPON_ID <", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("NEW_COUPON_ID <=", value, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdIn(List<Integer> values) {
            addCriterion("NEW_COUPON_ID in", values, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdNotIn(List<Integer> values) {
            addCriterion("NEW_COUPON_ID not in", values, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("NEW_COUPON_ID between", value1, value2, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("NEW_COUPON_ID not between", value1, value2, "newCouponId");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoIsNull() {
            addCriterion("NEW_COUPON_NO is null");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoIsNotNull() {
            addCriterion("NEW_COUPON_NO is not null");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoEqualTo(String value) {
            addCriterion("NEW_COUPON_NO =", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoNotEqualTo(String value) {
            addCriterion("NEW_COUPON_NO <>", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoGreaterThan(String value) {
            addCriterion("NEW_COUPON_NO >", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("NEW_COUPON_NO >=", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoLessThan(String value) {
            addCriterion("NEW_COUPON_NO <", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoLessThanOrEqualTo(String value) {
            addCriterion("NEW_COUPON_NO <=", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoLike(String value) {
            addCriterion("NEW_COUPON_NO like", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoNotLike(String value) {
            addCriterion("NEW_COUPON_NO not like", value, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoIn(List<String> values) {
            addCriterion("NEW_COUPON_NO in", values, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoNotIn(List<String> values) {
            addCriterion("NEW_COUPON_NO not in", values, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoBetween(String value1, String value2) {
            addCriterion("NEW_COUPON_NO between", value1, value2, "newCouponNo");
            return (Criteria) this;
        }

        public Criteria andNewCouponNoNotBetween(String value1, String value2) {
            addCriterion("NEW_COUPON_NO not between", value1, value2, "newCouponNo");
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

        public Criteria andRechargePlaceEqualTo(Integer value) {
            addCriterion("RECHARGE_PLACE =", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotEqualTo(Integer value) {
            addCriterion("RECHARGE_PLACE <>", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceGreaterThan(Integer value) {
            addCriterion("RECHARGE_PLACE >", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECHARGE_PLACE >=", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceLessThan(Integer value) {
            addCriterion("RECHARGE_PLACE <", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceLessThanOrEqualTo(Integer value) {
            addCriterion("RECHARGE_PLACE <=", value, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceIn(List<Integer> values) {
            addCriterion("RECHARGE_PLACE in", values, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotIn(List<Integer> values) {
            addCriterion("RECHARGE_PLACE not in", values, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceBetween(Integer value1, Integer value2) {
            addCriterion("RECHARGE_PLACE between", value1, value2, "rechargePlace");
            return (Criteria) this;
        }

        public Criteria andRechargePlaceNotBetween(Integer value1, Integer value2) {
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