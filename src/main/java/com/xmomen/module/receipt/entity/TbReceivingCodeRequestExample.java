package com.xmomen.module.receipt.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbReceivingCodeRequestExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbReceivingCodeRequestExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNull() {
            addCriterion("request_user is null");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNotNull() {
            addCriterion("request_user is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUserEqualTo(String value) {
            addCriterion("request_user =", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotEqualTo(String value) {
            addCriterion("request_user <>", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThan(String value) {
            addCriterion("request_user >", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThanOrEqualTo(String value) {
            addCriterion("request_user >=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThan(String value) {
            addCriterion("request_user <", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThanOrEqualTo(String value) {
            addCriterion("request_user <=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLike(String value) {
            addCriterion("request_user like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotLike(String value) {
            addCriterion("request_user not like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserIn(List<String> values) {
            addCriterion("request_user in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotIn(List<String> values) {
            addCriterion("request_user not in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserBetween(String value1, String value2) {
            addCriterion("request_user between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotBetween(String value1, String value2) {
            addCriterion("request_user not between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("request_time is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("request_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("request_time =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("request_time <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("request_time >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("request_time >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("request_time <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("request_time <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("request_time in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("request_time not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("request_time between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("request_time not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneIsNull() {
            addCriterion("request_phone is null");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneIsNotNull() {
            addCriterion("request_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneEqualTo(String value) {
            addCriterion("request_phone =", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneNotEqualTo(String value) {
            addCriterion("request_phone <>", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneGreaterThan(String value) {
            addCriterion("request_phone >", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("request_phone >=", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneLessThan(String value) {
            addCriterion("request_phone <", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneLessThanOrEqualTo(String value) {
            addCriterion("request_phone <=", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneLike(String value) {
            addCriterion("request_phone like", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneNotLike(String value) {
            addCriterion("request_phone not like", value, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneIn(List<String> values) {
            addCriterion("request_phone in", values, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneNotIn(List<String> values) {
            addCriterion("request_phone not in", values, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneBetween(String value1, String value2) {
            addCriterion("request_phone between", value1, value2, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestPhoneNotBetween(String value1, String value2) {
            addCriterion("request_phone not between", value1, value2, "requestPhone");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdIsNull() {
            addCriterion("request_express_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdIsNotNull() {
            addCriterion("request_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdEqualTo(Integer value) {
            addCriterion("request_express_id =", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdNotEqualTo(Integer value) {
            addCriterion("request_express_id <>", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdGreaterThan(Integer value) {
            addCriterion("request_express_id >", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("request_express_id >=", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdLessThan(Integer value) {
            addCriterion("request_express_id <", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("request_express_id <=", value, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdIn(List<Integer> values) {
            addCriterion("request_express_id in", values, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdNotIn(List<Integer> values) {
            addCriterion("request_express_id not in", values, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("request_express_id between", value1, value2, "requestExpressId");
            return (Criteria) this;
        }

        public Criteria andRequestExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("request_express_id not between", value1, value2, "requestExpressId");
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