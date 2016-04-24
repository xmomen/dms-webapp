package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdActivityExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdActivityExample() {
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

        public Criteria andActivityNameIsNull() {
            addCriterion("ACTIVITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("ACTIVITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("ACTIVITY_NAME =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("ACTIVITY_NAME >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("ACTIVITY_NAME <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("ACTIVITY_NAME like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("ACTIVITY_NAME not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("ACTIVITY_NAME in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("ACTIVITY_NAME not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIsNull() {
            addCriterion("ACTIVITY_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIsNotNull() {
            addCriterion("ACTIVITY_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeEqualTo(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME =", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotEqualTo(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME <>", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeGreaterThan(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME >", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME >=", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeLessThan(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME <", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("ACTIVITY_BEGIN_TIME <=", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIn(List<Date> values) {
            addCriterion("ACTIVITY_BEGIN_TIME in", values, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotIn(List<Date> values) {
            addCriterion("ACTIVITY_BEGIN_TIME not in", values, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeBetween(Date value1, Date value2) {
            addCriterion("ACTIVITY_BEGIN_TIME between", value1, value2, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("ACTIVITY_BEGIN_TIME not between", value1, value2, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNull() {
            addCriterion("ACTIVITY_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNotNull() {
            addCriterion("ACTIVITY_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeEqualTo(Date value) {
            addCriterion("ACTIVITY_END_TIME =", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotEqualTo(Date value) {
            addCriterion("ACTIVITY_END_TIME <>", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThan(Date value) {
            addCriterion("ACTIVITY_END_TIME >", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ACTIVITY_END_TIME >=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThan(Date value) {
            addCriterion("ACTIVITY_END_TIME <", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("ACTIVITY_END_TIME <=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIn(List<Date> values) {
            addCriterion("ACTIVITY_END_TIME in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotIn(List<Date> values) {
            addCriterion("ACTIVITY_END_TIME not in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeBetween(Date value1, Date value2) {
            addCriterion("ACTIVITY_END_TIME between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("ACTIVITY_END_TIME not between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeIsNull() {
            addCriterion("ACTIVITY_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeIsNotNull() {
            addCriterion("ACTIVITY_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeEqualTo(String value) {
            addCriterion("ACTIVITY_DESCRIBE =", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeNotEqualTo(String value) {
            addCriterion("ACTIVITY_DESCRIBE <>", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeGreaterThan(String value) {
            addCriterion("ACTIVITY_DESCRIBE >", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_DESCRIBE >=", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeLessThan(String value) {
            addCriterion("ACTIVITY_DESCRIBE <", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_DESCRIBE <=", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeLike(String value) {
            addCriterion("ACTIVITY_DESCRIBE like", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeNotLike(String value) {
            addCriterion("ACTIVITY_DESCRIBE not like", value, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeIn(List<String> values) {
            addCriterion("ACTIVITY_DESCRIBE in", values, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeNotIn(List<String> values) {
            addCriterion("ACTIVITY_DESCRIBE not in", values, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeBetween(String value1, String value2) {
            addCriterion("ACTIVITY_DESCRIBE between", value1, value2, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityDescribeNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_DESCRIBE not between", value1, value2, "activityDescribe");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNull() {
            addCriterion("ACTIVITY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNotNull() {
            addCriterion("ACTIVITY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeEqualTo(Integer value) {
            addCriterion("ACTIVITY_TYPE =", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotEqualTo(Integer value) {
            addCriterion("ACTIVITY_TYPE <>", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThan(Integer value) {
            addCriterion("ACTIVITY_TYPE >", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_TYPE >=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThan(Integer value) {
            addCriterion("ACTIVITY_TYPE <", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_TYPE <=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIn(List<Integer> values) {
            addCriterion("ACTIVITY_TYPE in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotIn(List<Integer> values) {
            addCriterion("ACTIVITY_TYPE not in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_TYPE between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_TYPE not between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityDayIsNull() {
            addCriterion("ACTIVITY_DAY is null");
            return (Criteria) this;
        }

        public Criteria andActivityDayIsNotNull() {
            addCriterion("ACTIVITY_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDayEqualTo(Integer value) {
            addCriterion("ACTIVITY_DAY =", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayNotEqualTo(Integer value) {
            addCriterion("ACTIVITY_DAY <>", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayGreaterThan(Integer value) {
            addCriterion("ACTIVITY_DAY >", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_DAY >=", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayLessThan(Integer value) {
            addCriterion("ACTIVITY_DAY <", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayLessThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_DAY <=", value, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayIn(List<Integer> values) {
            addCriterion("ACTIVITY_DAY in", values, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayNotIn(List<Integer> values) {
            addCriterion("ACTIVITY_DAY not in", values, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_DAY between", value1, value2, "activityDay");
            return (Criteria) this;
        }

        public Criteria andActivityDayNotBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_DAY not between", value1, value2, "activityDay");
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