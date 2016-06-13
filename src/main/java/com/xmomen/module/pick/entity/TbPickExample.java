package com.xmomen.module.pick.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbPickExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPickExample() {
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

        public Criteria andPickWeightIsNull() {
            addCriterion("PICK_WEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andPickWeightIsNotNull() {
            addCriterion("PICK_WEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andPickWeightEqualTo(BigDecimal value) {
            addCriterion("PICK_WEIGHT =", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightNotEqualTo(BigDecimal value) {
            addCriterion("PICK_WEIGHT <>", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightGreaterThan(BigDecimal value) {
            addCriterion("PICK_WEIGHT >", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_WEIGHT >=", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightLessThan(BigDecimal value) {
            addCriterion("PICK_WEIGHT <", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_WEIGHT <=", value, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightIn(List<BigDecimal> values) {
            addCriterion("PICK_WEIGHT in", values, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightNotIn(List<BigDecimal> values) {
            addCriterion("PICK_WEIGHT not in", values, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_WEIGHT between", value1, value2, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_WEIGHT not between", value1, value2, "pickWeight");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeIsNull() {
            addCriterion("PICK_PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeIsNotNull() {
            addCriterion("PICK_PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeEqualTo(Integer value) {
            addCriterion("PICK_PAY_TYPE =", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeNotEqualTo(Integer value) {
            addCriterion("PICK_PAY_TYPE <>", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeGreaterThan(Integer value) {
            addCriterion("PICK_PAY_TYPE >", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PICK_PAY_TYPE >=", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeLessThan(Integer value) {
            addCriterion("PICK_PAY_TYPE <", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("PICK_PAY_TYPE <=", value, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeIn(List<Integer> values) {
            addCriterion("PICK_PAY_TYPE in", values, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeNotIn(List<Integer> values) {
            addCriterion("PICK_PAY_TYPE not in", values, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PAY_TYPE between", value1, value2, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PAY_TYPE not between", value1, value2, "pickPayType");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceIsNull() {
            addCriterion("PICK_TOTAL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceIsNotNull() {
            addCriterion("PICK_TOTAL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceEqualTo(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE =", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE <>", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE >", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE >=", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceLessThan(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE <", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_TOTAL_PRICE <=", value, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceIn(List<BigDecimal> values) {
            addCriterion("PICK_TOTAL_PRICE in", values, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("PICK_TOTAL_PRICE not in", values, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_TOTAL_PRICE between", value1, value2, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_TOTAL_PRICE not between", value1, value2, "pickTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceIsNull() {
            addCriterion("PICK_CRAD_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceIsNotNull() {
            addCriterion("PICK_CRAD_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceEqualTo(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE =", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceNotEqualTo(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE <>", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceGreaterThan(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE >", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE >=", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceLessThan(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE <", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_CRAD_PRICE <=", value, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceIn(List<BigDecimal> values) {
            addCriterion("PICK_CRAD_PRICE in", values, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceNotIn(List<BigDecimal> values) {
            addCriterion("PICK_CRAD_PRICE not in", values, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_CRAD_PRICE between", value1, value2, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCradPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_CRAD_PRICE not between", value1, value2, "pickCradPrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceIsNull() {
            addCriterion("PICK_CASE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceIsNotNull() {
            addCriterion("PICK_CASE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceEqualTo(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE =", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceNotEqualTo(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE <>", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceGreaterThan(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE >", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE >=", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceLessThan(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE <", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PICK_CASE_PRICE <=", value, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceIn(List<BigDecimal> values) {
            addCriterion("PICK_CASE_PRICE in", values, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceNotIn(List<BigDecimal> values) {
            addCriterion("PICK_CASE_PRICE not in", values, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_CASE_PRICE between", value1, value2, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickCasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PICK_CASE_PRICE not between", value1, value2, "pickCasePrice");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNull() {
            addCriterion("PICK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNotNull() {
            addCriterion("PICK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPickDateEqualTo(Date value) {
            addCriterion("PICK_DATE =", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotEqualTo(Date value) {
            addCriterion("PICK_DATE <>", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThan(Date value) {
            addCriterion("PICK_DATE >", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThanOrEqualTo(Date value) {
            addCriterion("PICK_DATE >=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThan(Date value) {
            addCriterion("PICK_DATE <", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThanOrEqualTo(Date value) {
            addCriterion("PICK_DATE <=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateIn(List<Date> values) {
            addCriterion("PICK_DATE in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotIn(List<Date> values) {
            addCriterion("PICK_DATE not in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateBetween(Date value1, Date value2) {
            addCriterion("PICK_DATE between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotBetween(Date value1, Date value2) {
            addCriterion("PICK_DATE not between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickPlaceIsNull() {
            addCriterion("PICK_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andPickPlaceIsNotNull() {
            addCriterion("PICK_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andPickPlaceEqualTo(Integer value) {
            addCriterion("PICK_PLACE =", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceNotEqualTo(Integer value) {
            addCriterion("PICK_PLACE <>", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceGreaterThan(Integer value) {
            addCriterion("PICK_PLACE >", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("PICK_PLACE >=", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceLessThan(Integer value) {
            addCriterion("PICK_PLACE <", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceLessThanOrEqualTo(Integer value) {
            addCriterion("PICK_PLACE <=", value, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceIn(List<Integer> values) {
            addCriterion("PICK_PLACE in", values, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceNotIn(List<Integer> values) {
            addCriterion("PICK_PLACE not in", values, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PLACE between", value1, value2, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceNotBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PLACE not between", value1, value2, "pickPlace");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserIsNull() {
            addCriterion("PICK_PLACE_USER is null");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserIsNotNull() {
            addCriterion("PICK_PLACE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserEqualTo(Integer value) {
            addCriterion("PICK_PLACE_USER =", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserNotEqualTo(Integer value) {
            addCriterion("PICK_PLACE_USER <>", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserGreaterThan(Integer value) {
            addCriterion("PICK_PLACE_USER >", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("PICK_PLACE_USER >=", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserLessThan(Integer value) {
            addCriterion("PICK_PLACE_USER <", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserLessThanOrEqualTo(Integer value) {
            addCriterion("PICK_PLACE_USER <=", value, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserIn(List<Integer> values) {
            addCriterion("PICK_PLACE_USER in", values, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserNotIn(List<Integer> values) {
            addCriterion("PICK_PLACE_USER not in", values, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PLACE_USER between", value1, value2, "pickPlaceUser");
            return (Criteria) this;
        }

        public Criteria andPickPlaceUserNotBetween(Integer value1, Integer value2) {
            addCriterion("PICK_PLACE_USER not between", value1, value2, "pickPlaceUser");
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