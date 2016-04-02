package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class SysDictionaryExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDictionaryExample() {
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

        public Criteria andDictionaryCodeIsNull() {
            addCriterion("DICTIONARY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeIsNotNull() {
            addCriterion("DICTIONARY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeEqualTo(String value) {
            addCriterion("DICTIONARY_CODE =", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeNotEqualTo(String value) {
            addCriterion("DICTIONARY_CODE <>", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeGreaterThan(String value) {
            addCriterion("DICTIONARY_CODE >", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_CODE >=", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeLessThan(String value) {
            addCriterion("DICTIONARY_CODE <", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeLessThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_CODE <=", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeLike(String value) {
            addCriterion("DICTIONARY_CODE like", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeNotLike(String value) {
            addCriterion("DICTIONARY_CODE not like", value, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeIn(List<String> values) {
            addCriterion("DICTIONARY_CODE in", values, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeNotIn(List<String> values) {
            addCriterion("DICTIONARY_CODE not in", values, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeBetween(String value1, String value2) {
            addCriterion("DICTIONARY_CODE between", value1, value2, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryCodeNotBetween(String value1, String value2) {
            addCriterion("DICTIONARY_CODE not between", value1, value2, "dictionaryCode");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIsNull() {
            addCriterion("DICTIONARY_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIsNotNull() {
            addCriterion("DICTIONARY_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescEqualTo(String value) {
            addCriterion("DICTIONARY_DESC =", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotEqualTo(String value) {
            addCriterion("DICTIONARY_DESC <>", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescGreaterThan(String value) {
            addCriterion("DICTIONARY_DESC >", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescGreaterThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_DESC >=", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLessThan(String value) {
            addCriterion("DICTIONARY_DESC <", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLessThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_DESC <=", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLike(String value) {
            addCriterion("DICTIONARY_DESC like", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotLike(String value) {
            addCriterion("DICTIONARY_DESC not like", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIn(List<String> values) {
            addCriterion("DICTIONARY_DESC in", values, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotIn(List<String> values) {
            addCriterion("DICTIONARY_DESC not in", values, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescBetween(String value1, String value2) {
            addCriterion("DICTIONARY_DESC between", value1, value2, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotBetween(String value1, String value2) {
            addCriterion("DICTIONARY_DESC not between", value1, value2, "dictionaryDesc");
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