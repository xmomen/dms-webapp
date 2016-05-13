package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class CdActivityAddressExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdActivityAddressExample() {
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

        public Criteria andConsignmentNameIsNull() {
            addCriterion("CONSIGNMENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameIsNotNull() {
            addCriterion("CONSIGNMENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameEqualTo(String value) {
            addCriterion("CONSIGNMENT_NAME =", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameNotEqualTo(String value) {
            addCriterion("CONSIGNMENT_NAME <>", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameGreaterThan(String value) {
            addCriterion("CONSIGNMENT_NAME >", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_NAME >=", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameLessThan(String value) {
            addCriterion("CONSIGNMENT_NAME <", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_NAME <=", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameLike(String value) {
            addCriterion("CONSIGNMENT_NAME like", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameNotLike(String value) {
            addCriterion("CONSIGNMENT_NAME not like", value, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameIn(List<String> values) {
            addCriterion("CONSIGNMENT_NAME in", values, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameNotIn(List<String> values) {
            addCriterion("CONSIGNMENT_NAME not in", values, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_NAME between", value1, value2, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentNameNotBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_NAME not between", value1, value2, "consignmentName");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneIsNull() {
            addCriterion("CONSIGNMENT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneIsNotNull() {
            addCriterion("CONSIGNMENT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneEqualTo(String value) {
            addCriterion("CONSIGNMENT_PHONE =", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneNotEqualTo(String value) {
            addCriterion("CONSIGNMENT_PHONE <>", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneGreaterThan(String value) {
            addCriterion("CONSIGNMENT_PHONE >", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_PHONE >=", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneLessThan(String value) {
            addCriterion("CONSIGNMENT_PHONE <", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_PHONE <=", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneLike(String value) {
            addCriterion("CONSIGNMENT_PHONE like", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneNotLike(String value) {
            addCriterion("CONSIGNMENT_PHONE not like", value, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneIn(List<String> values) {
            addCriterion("CONSIGNMENT_PHONE in", values, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneNotIn(List<String> values) {
            addCriterion("CONSIGNMENT_PHONE not in", values, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_PHONE between", value1, value2, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentPhoneNotBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_PHONE not between", value1, value2, "consignmentPhone");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressIsNull() {
            addCriterion("CONSIGNMENT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressIsNotNull() {
            addCriterion("CONSIGNMENT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressEqualTo(String value) {
            addCriterion("CONSIGNMENT_ADDRESS =", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressNotEqualTo(String value) {
            addCriterion("CONSIGNMENT_ADDRESS <>", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressGreaterThan(String value) {
            addCriterion("CONSIGNMENT_ADDRESS >", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_ADDRESS >=", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressLessThan(String value) {
            addCriterion("CONSIGNMENT_ADDRESS <", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNMENT_ADDRESS <=", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressLike(String value) {
            addCriterion("CONSIGNMENT_ADDRESS like", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressNotLike(String value) {
            addCriterion("CONSIGNMENT_ADDRESS not like", value, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressIn(List<String> values) {
            addCriterion("CONSIGNMENT_ADDRESS in", values, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressNotIn(List<String> values) {
            addCriterion("CONSIGNMENT_ADDRESS not in", values, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_ADDRESS between", value1, value2, "consignmentAddress");
            return (Criteria) this;
        }

        public Criteria andConsignmentAddressNotBetween(String value1, String value2) {
            addCriterion("CONSIGNMENT_ADDRESS not between", value1, value2, "consignmentAddress");
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