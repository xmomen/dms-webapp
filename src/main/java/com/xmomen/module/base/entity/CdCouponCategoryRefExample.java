package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class CdCouponCategoryRefExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdCouponCategoryRefExample() {
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

        public Criteria andCdCouponCategoryIdIsNull() {
            addCriterion("CD_COUPON_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdIsNotNull() {
            addCriterion("CD_COUPON_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdEqualTo(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID =", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdNotEqualTo(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID <>", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdGreaterThan(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID >", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID >=", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdLessThan(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID <", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_COUPON_CATEGORY_ID <=", value, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdIn(List<Integer> values) {
            addCriterion("CD_COUPON_CATEGORY_ID in", values, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdNotIn(List<Integer> values) {
            addCriterion("CD_COUPON_CATEGORY_ID not in", values, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_COUPON_CATEGORY_ID between", value1, value2, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCouponCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_COUPON_CATEGORY_ID not between", value1, value2, "cdCouponCategoryId");
            return (Criteria) this;
        }

        public Criteria andRefTypeIsNull() {
            addCriterion("REF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRefTypeIsNotNull() {
            addCriterion("REF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRefTypeEqualTo(String value) {
            addCriterion("REF_TYPE =", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotEqualTo(String value) {
            addCriterion("REF_TYPE <>", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeGreaterThan(String value) {
            addCriterion("REF_TYPE >", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REF_TYPE >=", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeLessThan(String value) {
            addCriterion("REF_TYPE <", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeLessThanOrEqualTo(String value) {
            addCriterion("REF_TYPE <=", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeLike(String value) {
            addCriterion("REF_TYPE like", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotLike(String value) {
            addCriterion("REF_TYPE not like", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeIn(List<String> values) {
            addCriterion("REF_TYPE in", values, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotIn(List<String> values) {
            addCriterion("REF_TYPE not in", values, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeBetween(String value1, String value2) {
            addCriterion("REF_TYPE between", value1, value2, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotBetween(String value1, String value2) {
            addCriterion("REF_TYPE not between", value1, value2, "refType");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNull() {
            addCriterion("REF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNotNull() {
            addCriterion("REF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRefNameEqualTo(String value) {
            addCriterion("REF_NAME =", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotEqualTo(String value) {
            addCriterion("REF_NAME <>", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThan(String value) {
            addCriterion("REF_NAME >", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThanOrEqualTo(String value) {
            addCriterion("REF_NAME >=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThan(String value) {
            addCriterion("REF_NAME <", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThanOrEqualTo(String value) {
            addCriterion("REF_NAME <=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLike(String value) {
            addCriterion("REF_NAME like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotLike(String value) {
            addCriterion("REF_NAME not like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameIn(List<String> values) {
            addCriterion("REF_NAME in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotIn(List<String> values) {
            addCriterion("REF_NAME not in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameBetween(String value1, String value2) {
            addCriterion("REF_NAME between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotBetween(String value1, String value2) {
            addCriterion("REF_NAME not between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andRefValueIsNull() {
            addCriterion("REF_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andRefValueIsNotNull() {
            addCriterion("REF_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andRefValueEqualTo(String value) {
            addCriterion("REF_VALUE =", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueNotEqualTo(String value) {
            addCriterion("REF_VALUE <>", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueGreaterThan(String value) {
            addCriterion("REF_VALUE >", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueGreaterThanOrEqualTo(String value) {
            addCriterion("REF_VALUE >=", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueLessThan(String value) {
            addCriterion("REF_VALUE <", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueLessThanOrEqualTo(String value) {
            addCriterion("REF_VALUE <=", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueLike(String value) {
            addCriterion("REF_VALUE like", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueNotLike(String value) {
            addCriterion("REF_VALUE not like", value, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueIn(List<String> values) {
            addCriterion("REF_VALUE in", values, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueNotIn(List<String> values) {
            addCriterion("REF_VALUE not in", values, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueBetween(String value1, String value2) {
            addCriterion("REF_VALUE between", value1, value2, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefValueNotBetween(String value1, String value2) {
            addCriterion("REF_VALUE not between", value1, value2, "refValue");
            return (Criteria) this;
        }

        public Criteria andRefCountIsNull() {
            addCriterion("REF_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefCountIsNotNull() {
            addCriterion("REF_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefCountEqualTo(Integer value) {
            addCriterion("REF_COUNT =", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotEqualTo(Integer value) {
            addCriterion("REF_COUNT <>", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountGreaterThan(Integer value) {
            addCriterion("REF_COUNT >", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("REF_COUNT >=", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountLessThan(Integer value) {
            addCriterion("REF_COUNT <", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountLessThanOrEqualTo(Integer value) {
            addCriterion("REF_COUNT <=", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountIn(List<Integer> values) {
            addCriterion("REF_COUNT in", values, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotIn(List<Integer> values) {
            addCriterion("REF_COUNT not in", values, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountBetween(Integer value1, Integer value2) {
            addCriterion("REF_COUNT between", value1, value2, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotBetween(Integer value1, Integer value2) {
            addCriterion("REF_COUNT not between", value1, value2, "refCount");
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