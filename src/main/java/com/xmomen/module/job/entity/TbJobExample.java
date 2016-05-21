package com.xmomen.module.job.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbJobExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbJobExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIsNull() {
            addCriterion("PURCHASE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIsNotNull() {
            addCriterion("PURCHASE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeEqualTo(String value) {
            addCriterion("PURCHASE_CODE =", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotEqualTo(String value) {
            addCriterion("PURCHASE_CODE <>", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThan(String value) {
            addCriterion("PURCHASE_CODE >", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PURCHASE_CODE >=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThan(String value) {
            addCriterion("PURCHASE_CODE <", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThanOrEqualTo(String value) {
            addCriterion("PURCHASE_CODE <=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLike(String value) {
            addCriterion("PURCHASE_CODE like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotLike(String value) {
            addCriterion("PURCHASE_CODE not like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIn(List<String> values) {
            addCriterion("PURCHASE_CODE in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotIn(List<String> values) {
            addCriterion("PURCHASE_CODE not in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeBetween(String value1, String value2) {
            addCriterion("PURCHASE_CODE between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotBetween(String value1, String value2) {
            addCriterion("PURCHASE_CODE not between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIsNull() {
            addCriterion("PURCHASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIsNotNull() {
            addCriterion("PURCHASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdEqualTo(Integer value) {
            addCriterion("PURCHASE_ID =", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotEqualTo(Integer value) {
            addCriterion("PURCHASE_ID <>", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThan(Integer value) {
            addCriterion("PURCHASE_ID >", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PURCHASE_ID >=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThan(Integer value) {
            addCriterion("PURCHASE_ID <", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("PURCHASE_ID <=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIn(List<Integer> values) {
            addCriterion("PURCHASE_ID in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotIn(List<Integer> values) {
            addCriterion("PURCHASE_ID not in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdBetween(Integer value1, Integer value2) {
            addCriterion("PURCHASE_ID between", value1, value2, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PURCHASE_ID not between", value1, value2, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNull() {
            addCriterion("JOB_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNotNull() {
            addCriterion("JOB_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andJobTypeEqualTo(Integer value) {
            addCriterion("JOB_TYPE =", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotEqualTo(Integer value) {
            addCriterion("JOB_TYPE <>", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThan(Integer value) {
            addCriterion("JOB_TYPE >", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("JOB_TYPE >=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThan(Integer value) {
            addCriterion("JOB_TYPE <", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThanOrEqualTo(Integer value) {
            addCriterion("JOB_TYPE <=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeIn(List<Integer> values) {
            addCriterion("JOB_TYPE in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotIn(List<Integer> values) {
            addCriterion("JOB_TYPE not in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeBetween(Integer value1, Integer value2) {
            addCriterion("JOB_TYPE between", value1, value2, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("JOB_TYPE not between", value1, value2, "jobType");
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

        public Criteria andJobUserIsNull() {
            addCriterion("JOB_USER is null");
            return (Criteria) this;
        }

        public Criteria andJobUserIsNotNull() {
            addCriterion("JOB_USER is not null");
            return (Criteria) this;
        }

        public Criteria andJobUserEqualTo(Integer value) {
            addCriterion("JOB_USER =", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserNotEqualTo(Integer value) {
            addCriterion("JOB_USER <>", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserGreaterThan(Integer value) {
            addCriterion("JOB_USER >", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("JOB_USER >=", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserLessThan(Integer value) {
            addCriterion("JOB_USER <", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserLessThanOrEqualTo(Integer value) {
            addCriterion("JOB_USER <=", value, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserIn(List<Integer> values) {
            addCriterion("JOB_USER in", values, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserNotIn(List<Integer> values) {
            addCriterion("JOB_USER not in", values, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserBetween(Integer value1, Integer value2) {
            addCriterion("JOB_USER between", value1, value2, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobUserNotBetween(Integer value1, Integer value2) {
            addCriterion("JOB_USER not between", value1, value2, "jobUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeIsNull() {
            addCriterion("JOB_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeIsNotNull() {
            addCriterion("JOB_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeEqualTo(Date value) {
            addCriterion("JOB_CREATE_TIME =", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeNotEqualTo(Date value) {
            addCriterion("JOB_CREATE_TIME <>", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeGreaterThan(Date value) {
            addCriterion("JOB_CREATE_TIME >", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("JOB_CREATE_TIME >=", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeLessThan(Date value) {
            addCriterion("JOB_CREATE_TIME <", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("JOB_CREATE_TIME <=", value, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeIn(List<Date> values) {
            addCriterion("JOB_CREATE_TIME in", values, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeNotIn(List<Date> values) {
            addCriterion("JOB_CREATE_TIME not in", values, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeBetween(Date value1, Date value2) {
            addCriterion("JOB_CREATE_TIME between", value1, value2, "jobCreateTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("JOB_CREATE_TIME not between", value1, value2, "jobCreateTime");
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

        public Criteria andJobBeginTimeIsNull() {
            addCriterion("JOB_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeIsNotNull() {
            addCriterion("JOB_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeEqualTo(Date value) {
            addCriterion("JOB_BEGIN_TIME =", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeNotEqualTo(Date value) {
            addCriterion("JOB_BEGIN_TIME <>", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeGreaterThan(Date value) {
            addCriterion("JOB_BEGIN_TIME >", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("JOB_BEGIN_TIME >=", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeLessThan(Date value) {
            addCriterion("JOB_BEGIN_TIME <", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("JOB_BEGIN_TIME <=", value, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeIn(List<Date> values) {
            addCriterion("JOB_BEGIN_TIME in", values, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeNotIn(List<Date> values) {
            addCriterion("JOB_BEGIN_TIME not in", values, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeBetween(Date value1, Date value2) {
            addCriterion("JOB_BEGIN_TIME between", value1, value2, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andJobBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("JOB_BEGIN_TIME not between", value1, value2, "jobBeginTime");
            return (Criteria) this;
        }

        public Criteria andFinishValueIsNull() {
            addCriterion("FINISH_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andFinishValueIsNotNull() {
            addCriterion("FINISH_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andFinishValueEqualTo(Integer value) {
            addCriterion("FINISH_VALUE =", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueNotEqualTo(Integer value) {
            addCriterion("FINISH_VALUE <>", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueGreaterThan(Integer value) {
            addCriterion("FINISH_VALUE >", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("FINISH_VALUE >=", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueLessThan(Integer value) {
            addCriterion("FINISH_VALUE <", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueLessThanOrEqualTo(Integer value) {
            addCriterion("FINISH_VALUE <=", value, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueIn(List<Integer> values) {
            addCriterion("FINISH_VALUE in", values, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueNotIn(List<Integer> values) {
            addCriterion("FINISH_VALUE not in", values, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueBetween(Integer value1, Integer value2) {
            addCriterion("FINISH_VALUE between", value1, value2, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishValueNotBetween(Integer value1, Integer value2) {
            addCriterion("FINISH_VALUE not between", value1, value2, "finishValue");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("FINISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("FINISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("FINISH_TIME =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("FINISH_TIME <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("FINISH_TIME >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FINISH_TIME >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("FINISH_TIME <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("FINISH_TIME <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("FINISH_TIME in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("FINISH_TIME not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("FINISH_TIME between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("FINISH_TIME not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserIsNull() {
            addCriterion("JOB_CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserIsNotNull() {
            addCriterion("JOB_CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserEqualTo(Integer value) {
            addCriterion("JOB_CREATE_USER =", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserNotEqualTo(Integer value) {
            addCriterion("JOB_CREATE_USER <>", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserGreaterThan(Integer value) {
            addCriterion("JOB_CREATE_USER >", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("JOB_CREATE_USER >=", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserLessThan(Integer value) {
            addCriterion("JOB_CREATE_USER <", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserLessThanOrEqualTo(Integer value) {
            addCriterion("JOB_CREATE_USER <=", value, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserIn(List<Integer> values) {
            addCriterion("JOB_CREATE_USER in", values, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserNotIn(List<Integer> values) {
            addCriterion("JOB_CREATE_USER not in", values, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserBetween(Integer value1, Integer value2) {
            addCriterion("JOB_CREATE_USER between", value1, value2, "jobCreateUser");
            return (Criteria) this;
        }

        public Criteria andJobCreateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("JOB_CREATE_USER not between", value1, value2, "jobCreateUser");
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