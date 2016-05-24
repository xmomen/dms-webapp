package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbPackingRecordExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPackingRecordExample() {
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

        public Criteria andUpcIsNull() {
            addCriterion("UPC is null");
            return (Criteria) this;
        }

        public Criteria andUpcIsNotNull() {
            addCriterion("UPC is not null");
            return (Criteria) this;
        }

        public Criteria andUpcEqualTo(String value) {
            addCriterion("UPC =", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotEqualTo(String value) {
            addCriterion("UPC <>", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcGreaterThan(String value) {
            addCriterion("UPC >", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcGreaterThanOrEqualTo(String value) {
            addCriterion("UPC >=", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLessThan(String value) {
            addCriterion("UPC <", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLessThanOrEqualTo(String value) {
            addCriterion("UPC <=", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLike(String value) {
            addCriterion("UPC like", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotLike(String value) {
            addCriterion("UPC not like", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcIn(List<String> values) {
            addCriterion("UPC in", values, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotIn(List<String> values) {
            addCriterion("UPC not in", values, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcBetween(String value1, String value2) {
            addCriterion("UPC between", value1, value2, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotBetween(String value1, String value2) {
            addCriterion("UPC not between", value1, value2, "upc");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNull() {
            addCriterion("ORDER_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNotNull() {
            addCriterion("ORDER_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdEqualTo(Integer value) {
            addCriterion("ORDER_ITEM_ID =", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotEqualTo(Integer value) {
            addCriterion("ORDER_ITEM_ID <>", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThan(Integer value) {
            addCriterion("ORDER_ITEM_ID >", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_ITEM_ID >=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThan(Integer value) {
            addCriterion("ORDER_ITEM_ID <", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_ITEM_ID <=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIn(List<Integer> values) {
            addCriterion("ORDER_ITEM_ID in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotIn(List<Integer> values) {
            addCriterion("ORDER_ITEM_ID not in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_ITEM_ID between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_ITEM_ID not between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNull() {
            addCriterion("SCAN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNotNull() {
            addCriterion("SCAN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andScanTimeEqualTo(Date value) {
            addCriterion("SCAN_TIME =", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotEqualTo(Date value) {
            addCriterion("SCAN_TIME <>", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThan(Date value) {
            addCriterion("SCAN_TIME >", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SCAN_TIME >=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThan(Date value) {
            addCriterion("SCAN_TIME <", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThanOrEqualTo(Date value) {
            addCriterion("SCAN_TIME <=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeIn(List<Date> values) {
            addCriterion("SCAN_TIME in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotIn(List<Date> values) {
            addCriterion("SCAN_TIME not in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeBetween(Date value1, Date value2) {
            addCriterion("SCAN_TIME between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotBetween(Date value1, Date value2) {
            addCriterion("SCAN_TIME not between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andPackingIdIsNull() {
            addCriterion("PACKING_ID is null");
            return (Criteria) this;
        }

        public Criteria andPackingIdIsNotNull() {
            addCriterion("PACKING_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPackingIdEqualTo(Integer value) {
            addCriterion("PACKING_ID =", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdNotEqualTo(Integer value) {
            addCriterion("PACKING_ID <>", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdGreaterThan(Integer value) {
            addCriterion("PACKING_ID >", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PACKING_ID >=", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdLessThan(Integer value) {
            addCriterion("PACKING_ID <", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdLessThanOrEqualTo(Integer value) {
            addCriterion("PACKING_ID <=", value, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdIn(List<Integer> values) {
            addCriterion("PACKING_ID in", values, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdNotIn(List<Integer> values) {
            addCriterion("PACKING_ID not in", values, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdBetween(Integer value1, Integer value2) {
            addCriterion("PACKING_ID between", value1, value2, "packingId");
            return (Criteria) this;
        }

        public Criteria andPackingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PACKING_ID not between", value1, value2, "packingId");
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