package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdPlanExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdPlanExample() {
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

        public Criteria andPlanNameIsNull() {
            addCriterion("PLAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("PLAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("PLAN_NAME =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("PLAN_NAME <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("PLAN_NAME >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_NAME >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("PLAN_NAME <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_NAME <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("PLAN_NAME like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("PLAN_NAME not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("PLAN_NAME in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("PLAN_NAME not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("PLAN_NAME between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_NAME not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("CREATE_USER like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("CREATE_USER not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNull() {
            addCriterion("DELIVERY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNotNull() {
            addCriterion("DELIVERY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeEqualTo(Integer value) {
            addCriterion("DELIVERY_TYPE =", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotEqualTo(Integer value) {
            addCriterion("DELIVERY_TYPE <>", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThan(Integer value) {
            addCriterion("DELIVERY_TYPE >", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_TYPE >=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThan(Integer value) {
            addCriterion("DELIVERY_TYPE <", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_TYPE <=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIn(List<Integer> values) {
            addCriterion("DELIVERY_TYPE in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotIn(List<Integer> values) {
            addCriterion("DELIVERY_TYPE not in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_TYPE between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_TYPE not between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("DELIVERY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("DELIVERY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(String value) {
            addCriterion("DELIVERY_TIME =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(String value) {
            addCriterion("DELIVERY_TIME <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(String value) {
            addCriterion("DELIVERY_TIME >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DELIVERY_TIME >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(String value) {
            addCriterion("DELIVERY_TIME <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(String value) {
            addCriterion("DELIVERY_TIME <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLike(String value) {
            addCriterion("DELIVERY_TIME like", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotLike(String value) {
            addCriterion("DELIVERY_TIME not like", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<String> values) {
            addCriterion("DELIVERY_TIME in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<String> values) {
            addCriterion("DELIVERY_TIME not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(String value1, String value2) {
            addCriterion("DELIVERY_TIME between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(String value1, String value2) {
            addCriterion("DELIVERY_TIME not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNull() {
            addCriterion("DELIVER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNotNull() {
            addCriterion("DELIVER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountEqualTo(Integer value) {
            addCriterion("DELIVER_COUNT =", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotEqualTo(Integer value) {
            addCriterion("DELIVER_COUNT <>", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThan(Integer value) {
            addCriterion("DELIVER_COUNT >", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DELIVER_COUNT >=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThan(Integer value) {
            addCriterion("DELIVER_COUNT <", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThanOrEqualTo(Integer value) {
            addCriterion("DELIVER_COUNT <=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIn(List<Integer> values) {
            addCriterion("DELIVER_COUNT in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotIn(List<Integer> values) {
            addCriterion("DELIVER_COUNT not in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountBetween(Integer value1, Integer value2) {
            addCriterion("DELIVER_COUNT between", value1, value2, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotBetween(Integer value1, Integer value2) {
            addCriterion("DELIVER_COUNT not between", value1, value2, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("PRICE =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("PRICE <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("PRICE >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("PRICE <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("PRICE in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("PRICE not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andIsRandomIsNull() {
            addCriterion("IS_RANDOM is null");
            return (Criteria) this;
        }

        public Criteria andIsRandomIsNotNull() {
            addCriterion("IS_RANDOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsRandomEqualTo(Integer value) {
            addCriterion("IS_RANDOM =", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotEqualTo(Integer value) {
            addCriterion("IS_RANDOM <>", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThan(Integer value) {
            addCriterion("IS_RANDOM >", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_RANDOM >=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThan(Integer value) {
            addCriterion("IS_RANDOM <", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThanOrEqualTo(Integer value) {
            addCriterion("IS_RANDOM <=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomIn(List<Integer> values) {
            addCriterion("IS_RANDOM in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotIn(List<Integer> values) {
            addCriterion("IS_RANDOM not in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomBetween(Integer value1, Integer value2) {
            addCriterion("IS_RANDOM between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_RANDOM not between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIsNull() {
            addCriterion("CD_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIsNotNull() {
            addCriterion("CD_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID =", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID <>", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdGreaterThan(Integer value) {
            addCriterion("CD_CATEGORY_ID >", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID >=", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdLessThan(Integer value) {
            addCriterion("CD_CATEGORY_ID <", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID <=", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIn(List<Integer> values) {
            addCriterion("CD_CATEGORY_ID in", values, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotIn(List<Integer> values) {
            addCriterion("CD_CATEGORY_ID not in", values, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_CATEGORY_ID between", value1, value2, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_CATEGORY_ID not between", value1, value2, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andRandomNumIsNull() {
            addCriterion("RANDOM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRandomNumIsNotNull() {
            addCriterion("RANDOM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRandomNumEqualTo(Integer value) {
            addCriterion("RANDOM_NUM =", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumNotEqualTo(Integer value) {
            addCriterion("RANDOM_NUM <>", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumGreaterThan(Integer value) {
            addCriterion("RANDOM_NUM >", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("RANDOM_NUM >=", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumLessThan(Integer value) {
            addCriterion("RANDOM_NUM <", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumLessThanOrEqualTo(Integer value) {
            addCriterion("RANDOM_NUM <=", value, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumIn(List<Integer> values) {
            addCriterion("RANDOM_NUM in", values, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumNotIn(List<Integer> values) {
            addCriterion("RANDOM_NUM not in", values, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumBetween(Integer value1, Integer value2) {
            addCriterion("RANDOM_NUM between", value1, value2, "randomNum");
            return (Criteria) this;
        }

        public Criteria andRandomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("RANDOM_NUM not between", value1, value2, "randomNum");
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