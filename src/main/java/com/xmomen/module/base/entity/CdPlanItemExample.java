package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class CdPlanItemExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdPlanItemExample() {
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

        public Criteria andCdPlanIdIsNull() {
            addCriterion("CD_PLAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdIsNotNull() {
            addCriterion("CD_PLAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdEqualTo(Integer value) {
            addCriterion("CD_PLAN_ID =", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdNotEqualTo(Integer value) {
            addCriterion("CD_PLAN_ID <>", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdGreaterThan(Integer value) {
            addCriterion("CD_PLAN_ID >", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_PLAN_ID >=", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdLessThan(Integer value) {
            addCriterion("CD_PLAN_ID <", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_PLAN_ID <=", value, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdIn(List<Integer> values) {
            addCriterion("CD_PLAN_ID in", values, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdNotIn(List<Integer> values) {
            addCriterion("CD_PLAN_ID not in", values, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_PLAN_ID between", value1, value2, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_PLAN_ID not between", value1, value2, "cdPlanId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdIsNull() {
            addCriterion("CD_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdItemIdIsNotNull() {
            addCriterion("CD_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdItemIdEqualTo(Integer value) {
            addCriterion("CD_ITEM_ID =", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdNotEqualTo(Integer value) {
            addCriterion("CD_ITEM_ID <>", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdGreaterThan(Integer value) {
            addCriterion("CD_ITEM_ID >", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_ITEM_ID >=", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdLessThan(Integer value) {
            addCriterion("CD_ITEM_ID <", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_ITEM_ID <=", value, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdIn(List<Integer> values) {
            addCriterion("CD_ITEM_ID in", values, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdNotIn(List<Integer> values) {
            addCriterion("CD_ITEM_ID not in", values, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_ITEM_ID between", value1, value2, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCdItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_ITEM_ID not between", value1, value2, "cdItemId");
            return (Criteria) this;
        }

        public Criteria andCountValueIsNull() {
            addCriterion("COUNT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCountValueIsNotNull() {
            addCriterion("COUNT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCountValueEqualTo(Integer value) {
            addCriterion("COUNT_VALUE =", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueNotEqualTo(Integer value) {
            addCriterion("COUNT_VALUE <>", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueGreaterThan(Integer value) {
            addCriterion("COUNT_VALUE >", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUNT_VALUE >=", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueLessThan(Integer value) {
            addCriterion("COUNT_VALUE <", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueLessThanOrEqualTo(Integer value) {
            addCriterion("COUNT_VALUE <=", value, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueIn(List<Integer> values) {
            addCriterion("COUNT_VALUE in", values, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueNotIn(List<Integer> values) {
            addCriterion("COUNT_VALUE not in", values, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueBetween(Integer value1, Integer value2) {
            addCriterion("COUNT_VALUE between", value1, value2, "countValue");
            return (Criteria) this;
        }

        public Criteria andCountValueNotBetween(Integer value1, Integer value2) {
            addCriterion("COUNT_VALUE not between", value1, value2, "countValue");
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