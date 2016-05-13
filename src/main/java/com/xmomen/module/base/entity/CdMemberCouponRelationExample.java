package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class CdMemberCouponRelationExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdMemberCouponRelationExample() {
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

        public Criteria andCdMemberIdIsNull() {
            addCriterion("CD_MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdIsNotNull() {
            addCriterion("CD_MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID =", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID <>", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdGreaterThan(Integer value) {
            addCriterion("CD_MEMBER_ID >", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID >=", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdLessThan(Integer value) {
            addCriterion("CD_MEMBER_ID <", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID <=", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdIn(List<Integer> values) {
            addCriterion("CD_MEMBER_ID in", values, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotIn(List<Integer> values) {
            addCriterion("CD_MEMBER_ID not in", values, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_MEMBER_ID between", value1, value2, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_MEMBER_ID not between", value1, value2, "cdMemberId");
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