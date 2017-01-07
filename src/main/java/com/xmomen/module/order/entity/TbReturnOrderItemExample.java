package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class TbReturnOrderItemExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbReturnOrderItemExample() {
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

        public Criteria andReturnOrderIdIsNull() {
            addCriterion("RETURN_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdIsNotNull() {
            addCriterion("RETURN_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdEqualTo(Integer value) {
            addCriterion("RETURN_ORDER_ID =", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotEqualTo(Integer value) {
            addCriterion("RETURN_ORDER_ID <>", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdGreaterThan(Integer value) {
            addCriterion("RETURN_ORDER_ID >", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("RETURN_ORDER_ID >=", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdLessThan(Integer value) {
            addCriterion("RETURN_ORDER_ID <", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("RETURN_ORDER_ID <=", value, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdIn(List<Integer> values) {
            addCriterion("RETURN_ORDER_ID in", values, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotIn(List<Integer> values) {
            addCriterion("RETURN_ORDER_ID not in", values, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("RETURN_ORDER_ID between", value1, value2, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andReturnOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("RETURN_ORDER_ID not between", value1, value2, "returnOrderId");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNull() {
            addCriterion("ITEM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("ITEM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("ITEM_CODE =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("ITEM_CODE <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("ITEM_CODE >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_CODE >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("ITEM_CODE <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("ITEM_CODE <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("ITEM_CODE like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("ITEM_CODE not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("ITEM_CODE in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("ITEM_CODE not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("ITEM_CODE between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("ITEM_CODE not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemNumberIsNull() {
            addCriterion("ITEM_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andItemNumberIsNotNull() {
            addCriterion("ITEM_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumberEqualTo(Integer value) {
            addCriterion("ITEM_NUMBER =", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotEqualTo(Integer value) {
            addCriterion("ITEM_NUMBER <>", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberGreaterThan(Integer value) {
            addCriterion("ITEM_NUMBER >", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUMBER >=", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberLessThan(Integer value) {
            addCriterion("ITEM_NUMBER <", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUMBER <=", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberIn(List<Integer> values) {
            addCriterion("ITEM_NUMBER in", values, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotIn(List<Integer> values) {
            addCriterion("ITEM_NUMBER not in", values, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUMBER between", value1, value2, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUMBER not between", value1, value2, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andIsNeedIsNull() {
            addCriterion("IS_NEED is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedIsNotNull() {
            addCriterion("IS_NEED is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedEqualTo(Integer value) {
            addCriterion("IS_NEED =", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotEqualTo(Integer value) {
            addCriterion("IS_NEED <>", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedGreaterThan(Integer value) {
            addCriterion("IS_NEED >", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_NEED >=", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedLessThan(Integer value) {
            addCriterion("IS_NEED <", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedLessThanOrEqualTo(Integer value) {
            addCriterion("IS_NEED <=", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedIn(List<Integer> values) {
            addCriterion("IS_NEED in", values, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotIn(List<Integer> values) {
            addCriterion("IS_NEED not in", values, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedBetween(Integer value1, Integer value2) {
            addCriterion("IS_NEED between", value1, value2, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_NEED not between", value1, value2, "isNeed");
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