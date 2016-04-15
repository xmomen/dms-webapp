package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class SysDictionaryParameterExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDictionaryParameterExample() {
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

        public Criteria andSysDictionaryIdIsNull() {
            addCriterion("SYS_DICTIONARY_ID is null");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdIsNotNull() {
            addCriterion("SYS_DICTIONARY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdEqualTo(Integer value) {
            addCriterion("SYS_DICTIONARY_ID =", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdNotEqualTo(Integer value) {
            addCriterion("SYS_DICTIONARY_ID <>", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdGreaterThan(Integer value) {
            addCriterion("SYS_DICTIONARY_ID >", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SYS_DICTIONARY_ID >=", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdLessThan(Integer value) {
            addCriterion("SYS_DICTIONARY_ID <", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdLessThanOrEqualTo(Integer value) {
            addCriterion("SYS_DICTIONARY_ID <=", value, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdIn(List<Integer> values) {
            addCriterion("SYS_DICTIONARY_ID in", values, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdNotIn(List<Integer> values) {
            addCriterion("SYS_DICTIONARY_ID not in", values, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdBetween(Integer value1, Integer value2) {
            addCriterion("SYS_DICTIONARY_ID between", value1, value2, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andSysDictionaryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SYS_DICTIONARY_ID not between", value1, value2, "sysDictionaryId");
            return (Criteria) this;
        }

        public Criteria andShowValueIsNull() {
            addCriterion("SHOW_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andShowValueIsNotNull() {
            addCriterion("SHOW_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andShowValueEqualTo(String value) {
            addCriterion("SHOW_VALUE =", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotEqualTo(String value) {
            addCriterion("SHOW_VALUE <>", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueGreaterThan(String value) {
            addCriterion("SHOW_VALUE >", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_VALUE >=", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLessThan(String value) {
            addCriterion("SHOW_VALUE <", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLessThanOrEqualTo(String value) {
            addCriterion("SHOW_VALUE <=", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLike(String value) {
            addCriterion("SHOW_VALUE like", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotLike(String value) {
            addCriterion("SHOW_VALUE not like", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueIn(List<String> values) {
            addCriterion("SHOW_VALUE in", values, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotIn(List<String> values) {
            addCriterion("SHOW_VALUE not in", values, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueBetween(String value1, String value2) {
            addCriterion("SHOW_VALUE between", value1, value2, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotBetween(String value1, String value2) {
            addCriterion("SHOW_VALUE not between", value1, value2, "showValue");
            return (Criteria) this;
        }

        public Criteria andRealValueIsNull() {
            addCriterion("REAL_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andRealValueIsNotNull() {
            addCriterion("REAL_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andRealValueEqualTo(String value) {
            addCriterion("REAL_VALUE =", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueNotEqualTo(String value) {
            addCriterion("REAL_VALUE <>", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueGreaterThan(String value) {
            addCriterion("REAL_VALUE >", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueGreaterThanOrEqualTo(String value) {
            addCriterion("REAL_VALUE >=", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueLessThan(String value) {
            addCriterion("REAL_VALUE <", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueLessThanOrEqualTo(String value) {
            addCriterion("REAL_VALUE <=", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueLike(String value) {
            addCriterion("REAL_VALUE like", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueNotLike(String value) {
            addCriterion("REAL_VALUE not like", value, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueIn(List<String> values) {
            addCriterion("REAL_VALUE in", values, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueNotIn(List<String> values) {
            addCriterion("REAL_VALUE not in", values, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueBetween(String value1, String value2) {
            addCriterion("REAL_VALUE between", value1, value2, "realValue");
            return (Criteria) this;
        }

        public Criteria andRealValueNotBetween(String value1, String value2) {
            addCriterion("REAL_VALUE not between", value1, value2, "realValue");
            return (Criteria) this;
        }

        public Criteria andSortValueIsNull() {
            addCriterion("SORT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andSortValueIsNotNull() {
            addCriterion("SORT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andSortValueEqualTo(Integer value) {
            addCriterion("SORT_VALUE =", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotEqualTo(Integer value) {
            addCriterion("SORT_VALUE <>", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueGreaterThan(Integer value) {
            addCriterion("SORT_VALUE >", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_VALUE >=", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueLessThan(Integer value) {
            addCriterion("SORT_VALUE <", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_VALUE <=", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueIn(List<Integer> values) {
            addCriterion("SORT_VALUE in", values, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotIn(List<Integer> values) {
            addCriterion("SORT_VALUE not in", values, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueBetween(Integer value1, Integer value2) {
            addCriterion("SORT_VALUE between", value1, value2, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_VALUE not between", value1, value2, "sortValue");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("AVAILABLE is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("AVAILABLE is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Integer value) {
            addCriterion("AVAILABLE =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Integer value) {
            addCriterion("AVAILABLE <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Integer value) {
            addCriterion("AVAILABLE >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("AVAILABLE >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Integer value) {
            addCriterion("AVAILABLE <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("AVAILABLE <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Integer> values) {
            addCriterion("AVAILABLE in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Integer> values) {
            addCriterion("AVAILABLE not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Integer value1, Integer value2) {
            addCriterion("AVAILABLE between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("AVAILABLE not between", value1, value2, "available");
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