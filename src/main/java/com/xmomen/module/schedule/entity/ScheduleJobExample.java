package com.xmomen.module.schedule.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;

import java.util.ArrayList;
import java.util.List;

public class ScheduleJobExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleJobExample() {
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

        public Criteria andJobNameIsNull() {
            addCriterion("JOB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("JOB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("JOB_NAME =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("JOB_NAME <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("JOB_NAME >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_NAME >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("JOB_NAME <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_NAME <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("JOB_NAME like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("JOB_NAME not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("JOB_NAME in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("JOB_NAME not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("JOB_NAME between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("JOB_NAME not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNull() {
            addCriterion("JOB_GROUP is null");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNotNull() {
            addCriterion("JOB_GROUP is not null");
            return (Criteria) this;
        }

        public Criteria andJobGroupEqualTo(String value) {
            addCriterion("JOB_GROUP =", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotEqualTo(String value) {
            addCriterion("JOB_GROUP <>", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThan(String value) {
            addCriterion("JOB_GROUP >", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_GROUP >=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThan(String value) {
            addCriterion("JOB_GROUP <", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThanOrEqualTo(String value) {
            addCriterion("JOB_GROUP <=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLike(String value) {
            addCriterion("JOB_GROUP like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotLike(String value) {
            addCriterion("JOB_GROUP not like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupIn(List<String> values) {
            addCriterion("JOB_GROUP in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotIn(List<String> values) {
            addCriterion("JOB_GROUP not in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupBetween(String value1, String value2) {
            addCriterion("JOB_GROUP between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotBetween(String value1, String value2) {
            addCriterion("JOB_GROUP not between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNull() {
            addCriterion("JOB_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNotNull() {
            addCriterion("JOB_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andJobStatusEqualTo(Integer value) {
            addCriterion("JOB_STATUS =", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotEqualTo(Integer value) {
            addCriterion("JOB_STATUS <>", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThan(Integer value) {
            addCriterion("JOB_STATUS >", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("JOB_STATUS >=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThan(Integer value) {
            addCriterion("JOB_STATUS <", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThanOrEqualTo(Integer value) {
            addCriterion("JOB_STATUS <=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusIn(List<Integer> values) {
            addCriterion("JOB_STATUS in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotIn(List<Integer> values) {
            addCriterion("JOB_STATUS not in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusBetween(Integer value1, Integer value2) {
            addCriterion("JOB_STATUS between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("JOB_STATUS not between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIsNull() {
            addCriterion("JOB_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIsNotNull() {
            addCriterion("JOB_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionEqualTo(String value) {
            addCriterion("JOB_DESCRIPTION =", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotEqualTo(String value) {
            addCriterion("JOB_DESCRIPTION <>", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionGreaterThan(String value) {
            addCriterion("JOB_DESCRIPTION >", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_DESCRIPTION >=", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLessThan(String value) {
            addCriterion("JOB_DESCRIPTION <", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLessThanOrEqualTo(String value) {
            addCriterion("JOB_DESCRIPTION <=", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLike(String value) {
            addCriterion("JOB_DESCRIPTION like", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotLike(String value) {
            addCriterion("JOB_DESCRIPTION not like", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIn(List<String> values) {
            addCriterion("JOB_DESCRIPTION in", values, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotIn(List<String> values) {
            addCriterion("JOB_DESCRIPTION not in", values, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionBetween(String value1, String value2) {
            addCriterion("JOB_DESCRIPTION between", value1, value2, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotBetween(String value1, String value2) {
            addCriterion("JOB_DESCRIPTION not between", value1, value2, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNull() {
            addCriterion("CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNotNull() {
            addCriterion("CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionEqualTo(String value) {
            addCriterion("CRON_EXPRESSION =", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <>", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThan(String value) {
            addCriterion("CRON_EXPRESSION >", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION >=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThan(String value) {
            addCriterion("CRON_EXPRESSION <", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLike(String value) {
            addCriterion("CRON_EXPRESSION like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotLike(String value) {
            addCriterion("CRON_EXPRESSION not like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIn(List<String> values) {
            addCriterion("CRON_EXPRESSION in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotIn(List<String> values) {
            addCriterion("CRON_EXPRESSION not in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION not between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andTriggerIdIsNull() {
            addCriterion("TRIGGER_ID is null");
            return (Criteria) this;
        }

        public Criteria andTriggerIdIsNotNull() {
            addCriterion("TRIGGER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerIdEqualTo(String value) {
            addCriterion("TRIGGER_ID =", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdNotEqualTo(String value) {
            addCriterion("TRIGGER_ID <>", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdGreaterThan(String value) {
            addCriterion("TRIGGER_ID >", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRIGGER_ID >=", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdLessThan(String value) {
            addCriterion("TRIGGER_ID <", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdLessThanOrEqualTo(String value) {
            addCriterion("TRIGGER_ID <=", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdLike(String value) {
            addCriterion("TRIGGER_ID like", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdNotLike(String value) {
            addCriterion("TRIGGER_ID not like", value, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdIn(List<String> values) {
            addCriterion("TRIGGER_ID in", values, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdNotIn(List<String> values) {
            addCriterion("TRIGGER_ID not in", values, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdBetween(String value1, String value2) {
            addCriterion("TRIGGER_ID between", value1, value2, "triggerId");
            return (Criteria) this;
        }

        public Criteria andTriggerIdNotBetween(String value1, String value2) {
            addCriterion("TRIGGER_ID not between", value1, value2, "triggerId");
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