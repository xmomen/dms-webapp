package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbPackingExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPackingExample() {
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

        public Criteria andPackingNoIsNull() {
            addCriterion("PACKING_NO is null");
            return (Criteria) this;
        }

        public Criteria andPackingNoIsNotNull() {
            addCriterion("PACKING_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPackingNoEqualTo(String value) {
            addCriterion("PACKING_NO =", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoNotEqualTo(String value) {
            addCriterion("PACKING_NO <>", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoGreaterThan(String value) {
            addCriterion("PACKING_NO >", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoGreaterThanOrEqualTo(String value) {
            addCriterion("PACKING_NO >=", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoLessThan(String value) {
            addCriterion("PACKING_NO <", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoLessThanOrEqualTo(String value) {
            addCriterion("PACKING_NO <=", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoLike(String value) {
            addCriterion("PACKING_NO like", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoNotLike(String value) {
            addCriterion("PACKING_NO not like", value, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoIn(List<String> values) {
            addCriterion("PACKING_NO in", values, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoNotIn(List<String> values) {
            addCriterion("PACKING_NO not in", values, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoBetween(String value1, String value2) {
            addCriterion("PACKING_NO between", value1, value2, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingNoNotBetween(String value1, String value2) {
            addCriterion("PACKING_NO not between", value1, value2, "packingNo");
            return (Criteria) this;
        }

        public Criteria andPackingTimeIsNull() {
            addCriterion("PACKING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPackingTimeIsNotNull() {
            addCriterion("PACKING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPackingTimeEqualTo(Date value) {
            addCriterion("PACKING_TIME =", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeNotEqualTo(Date value) {
            addCriterion("PACKING_TIME <>", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeGreaterThan(Date value) {
            addCriterion("PACKING_TIME >", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PACKING_TIME >=", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeLessThan(Date value) {
            addCriterion("PACKING_TIME <", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeLessThanOrEqualTo(Date value) {
            addCriterion("PACKING_TIME <=", value, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeIn(List<Date> values) {
            addCriterion("PACKING_TIME in", values, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeNotIn(List<Date> values) {
            addCriterion("PACKING_TIME not in", values, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeBetween(Date value1, Date value2) {
            addCriterion("PACKING_TIME between", value1, value2, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingTimeNotBetween(Date value1, Date value2) {
            addCriterion("PACKING_TIME not between", value1, value2, "packingTime");
            return (Criteria) this;
        }

        public Criteria andPackingStatusIsNull() {
            addCriterion("PACKING_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPackingStatusIsNotNull() {
            addCriterion("PACKING_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPackingStatusEqualTo(Integer value) {
            addCriterion("PACKING_STATUS =", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusNotEqualTo(Integer value) {
            addCriterion("PACKING_STATUS <>", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusGreaterThan(Integer value) {
            addCriterion("PACKING_STATUS >", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("PACKING_STATUS >=", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusLessThan(Integer value) {
            addCriterion("PACKING_STATUS <", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusLessThanOrEqualTo(Integer value) {
            addCriterion("PACKING_STATUS <=", value, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusIn(List<Integer> values) {
            addCriterion("PACKING_STATUS in", values, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusNotIn(List<Integer> values) {
            addCriterion("PACKING_STATUS not in", values, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusBetween(Integer value1, Integer value2) {
            addCriterion("PACKING_STATUS between", value1, value2, "packingStatus");
            return (Criteria) this;
        }

        public Criteria andPackingStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("PACKING_STATUS not between", value1, value2, "packingStatus");
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