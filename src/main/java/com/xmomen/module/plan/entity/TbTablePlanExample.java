package com.xmomen.module.plan.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TbTablePlanExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTablePlanExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andAuditStatusIsNull() {
            addCriterion("AUDIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("AUDIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("AUDIT_STATUS =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("AUDIT_STATUS <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("AUDIT_STATUS >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_STATUS >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("AUDIT_STATUS <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_STATUS <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("AUDIT_STATUS in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("AUDIT_STATUS not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_STATUS between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_STATUS not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andIsStopIsNull() {
            addCriterion("IS_STOP is null");
            return (Criteria) this;
        }

        public Criteria andIsStopIsNotNull() {
            addCriterion("IS_STOP is not null");
            return (Criteria) this;
        }

        public Criteria andIsStopEqualTo(Integer value) {
            addCriterion("IS_STOP =", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopNotEqualTo(Integer value) {
            addCriterion("IS_STOP <>", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopGreaterThan(Integer value) {
            addCriterion("IS_STOP >", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_STOP >=", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopLessThan(Integer value) {
            addCriterion("IS_STOP <", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopLessThanOrEqualTo(Integer value) {
            addCriterion("IS_STOP <=", value, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopIn(List<Integer> values) {
            addCriterion("IS_STOP in", values, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopNotIn(List<Integer> values) {
            addCriterion("IS_STOP not in", values, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopBetween(Integer value1, Integer value2) {
            addCriterion("IS_STOP between", value1, value2, "isStop");
            return (Criteria) this;
        }

        public Criteria andIsStopNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_STOP not between", value1, value2, "isStop");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdIsNull() {
            addCriterion("CD_MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdIsNotNull() {
            addCriterion("CD_MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID =", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID <>", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdGreaterThan(Integer value) {
            addCriterion("CD_MEMBER_ID >", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID >=", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdLessThan(Integer value) {
            addCriterion("CD_MEMBER_ID <", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_MEMBER_ID <=", value, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdIn(List<Integer> values) {
            addCriterion("CD_MEMBER_ID in", values, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotIn(List<Integer> values) {
            addCriterion("CD_MEMBER_ID not in", values, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_MEMBER_ID between", value1, value2, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andCdMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_MEMBER_ID not between", value1, value2, "cdMemberId");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIsNull() {
            addCriterion("MEMBER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIsNotNull() {
            addCriterion("MEMBER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCodeEqualTo(String value) {
            addCriterion("MEMBER_CODE =", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotEqualTo(String value) {
            addCriterion("MEMBER_CODE <>", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeGreaterThan(String value) {
            addCriterion("MEMBER_CODE >", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_CODE >=", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeLessThan(String value) {
            addCriterion("MEMBER_CODE <", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_CODE <=", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeLike(String value) {
            addCriterion("MEMBER_CODE like", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotLike(String value) {
            addCriterion("MEMBER_CODE not like", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIn(List<String> values) {
            addCriterion("MEMBER_CODE in", values, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotIn(List<String> values) {
            addCriterion("MEMBER_CODE not in", values, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeBetween(String value1, String value2) {
            addCriterion("MEMBER_CODE between", value1, value2, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotBetween(String value1, String value2) {
            addCriterion("MEMBER_CODE not between", value1, value2, "memberCode");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIsNull() {
            addCriterion("COUPON_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIsNotNull() {
            addCriterion("COUPON_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNumberEqualTo(String value) {
            addCriterion("COUPON_NUMBER =", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotEqualTo(String value) {
            addCriterion("COUPON_NUMBER <>", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberGreaterThan(String value) {
            addCriterion("COUPON_NUMBER >", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_NUMBER >=", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLessThan(String value) {
            addCriterion("COUPON_NUMBER <", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLessThanOrEqualTo(String value) {
            addCriterion("COUPON_NUMBER <=", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberLike(String value) {
            addCriterion("COUPON_NUMBER like", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotLike(String value) {
            addCriterion("COUPON_NUMBER not like", value, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberIn(List<String> values) {
            addCriterion("COUPON_NUMBER in", values, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotIn(List<String> values) {
            addCriterion("COUPON_NUMBER not in", values, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberBetween(String value1, String value2) {
            addCriterion("COUPON_NUMBER between", value1, value2, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andCouponNumberNotBetween(String value1, String value2) {
            addCriterion("COUPON_NUMBER not between", value1, value2, "couponNumber");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneIsNull() {
            addCriterion("CONSIGNEE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneIsNotNull() {
            addCriterion("CONSIGNEE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneEqualTo(String value) {
            addCriterion("CONSIGNEE_PHONE =", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneNotEqualTo(String value) {
            addCriterion("CONSIGNEE_PHONE <>", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneGreaterThan(String value) {
            addCriterion("CONSIGNEE_PHONE >", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_PHONE >=", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneLessThan(String value) {
            addCriterion("CONSIGNEE_PHONE <", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_PHONE <=", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneLike(String value) {
            addCriterion("CONSIGNEE_PHONE like", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneNotLike(String value) {
            addCriterion("CONSIGNEE_PHONE not like", value, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneIn(List<String> values) {
            addCriterion("CONSIGNEE_PHONE in", values, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneNotIn(List<String> values) {
            addCriterion("CONSIGNEE_PHONE not in", values, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_PHONE between", value1, value2, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneePhoneNotBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_PHONE not between", value1, value2, "consigneePhone");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameIsNull() {
            addCriterion("CONSIGNEE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameIsNotNull() {
            addCriterion("CONSIGNEE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameEqualTo(String value) {
            addCriterion("CONSIGNEE_NAME =", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameNotEqualTo(String value) {
            addCriterion("CONSIGNEE_NAME <>", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameGreaterThan(String value) {
            addCriterion("CONSIGNEE_NAME >", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_NAME >=", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameLessThan(String value) {
            addCriterion("CONSIGNEE_NAME <", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_NAME <=", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameLike(String value) {
            addCriterion("CONSIGNEE_NAME like", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameNotLike(String value) {
            addCriterion("CONSIGNEE_NAME not like", value, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameIn(List<String> values) {
            addCriterion("CONSIGNEE_NAME in", values, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameNotIn(List<String> values) {
            addCriterion("CONSIGNEE_NAME not in", values, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_NAME between", value1, value2, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeNameNotBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_NAME not between", value1, value2, "consigneeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIsNull() {
            addCriterion("CONSIGNEE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIsNotNull() {
            addCriterion("CONSIGNEE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressEqualTo(String value) {
            addCriterion("CONSIGNEE_ADDRESS =", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotEqualTo(String value) {
            addCriterion("CONSIGNEE_ADDRESS <>", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressGreaterThan(String value) {
            addCriterion("CONSIGNEE_ADDRESS >", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_ADDRESS >=", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLessThan(String value) {
            addCriterion("CONSIGNEE_ADDRESS <", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLessThanOrEqualTo(String value) {
            addCriterion("CONSIGNEE_ADDRESS <=", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLike(String value) {
            addCriterion("CONSIGNEE_ADDRESS like", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotLike(String value) {
            addCriterion("CONSIGNEE_ADDRESS not like", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIn(List<String> values) {
            addCriterion("CONSIGNEE_ADDRESS in", values, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotIn(List<String> values) {
            addCriterion("CONSIGNEE_ADDRESS not in", values, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_ADDRESS between", value1, value2, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotBetween(String value1, String value2) {
            addCriterion("CONSIGNEE_ADDRESS not between", value1, value2, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("POSTCODE =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("POSTCODE <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("POSTCODE >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("POSTCODE >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("POSTCODE <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("POSTCODE <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("POSTCODE like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("POSTCODE not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("POSTCODE in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("POSTCODE not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("POSTCODE between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("POSTCODE not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueIsNull() {
            addCriterion("TOTAL_SEND_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueIsNotNull() {
            addCriterion("TOTAL_SEND_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueEqualTo(Integer value) {
            addCriterion("TOTAL_SEND_VALUE =", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueNotEqualTo(Integer value) {
            addCriterion("TOTAL_SEND_VALUE <>", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueGreaterThan(Integer value) {
            addCriterion("TOTAL_SEND_VALUE >", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("TOTAL_SEND_VALUE >=", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueLessThan(Integer value) {
            addCriterion("TOTAL_SEND_VALUE <", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueLessThanOrEqualTo(Integer value) {
            addCriterion("TOTAL_SEND_VALUE <=", value, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueIn(List<Integer> values) {
            addCriterion("TOTAL_SEND_VALUE in", values, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueNotIn(List<Integer> values) {
            addCriterion("TOTAL_SEND_VALUE not in", values, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL_SEND_VALUE between", value1, value2, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andTotalSendValueNotBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL_SEND_VALUE not between", value1, value2, "totalSendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueIsNull() {
            addCriterion("SEND_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andSendValueIsNotNull() {
            addCriterion("SEND_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andSendValueEqualTo(Integer value) {
            addCriterion("SEND_VALUE =", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueNotEqualTo(Integer value) {
            addCriterion("SEND_VALUE <>", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueGreaterThan(Integer value) {
            addCriterion("SEND_VALUE >", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEND_VALUE >=", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueLessThan(Integer value) {
            addCriterion("SEND_VALUE <", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueLessThanOrEqualTo(Integer value) {
            addCriterion("SEND_VALUE <=", value, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueIn(List<Integer> values) {
            addCriterion("SEND_VALUE in", values, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueNotIn(List<Integer> values) {
            addCriterion("SEND_VALUE not in", values, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueBetween(Integer value1, Integer value2) {
            addCriterion("SEND_VALUE between", value1, value2, "sendValue");
            return (Criteria) this;
        }

        public Criteria andSendValueNotBetween(Integer value1, Integer value2) {
            addCriterion("SEND_VALUE not between", value1, value2, "sendValue");
            return (Criteria) this;
        }

        public Criteria andLastSendDateIsNull() {
            addCriterion("LAST_SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastSendDateIsNotNull() {
            addCriterion("LAST_SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE =", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE <>", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE >", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE >=", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateLessThan(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE <", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_SEND_DATE <=", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SEND_DATE in", values, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_SEND_DATE not in", values, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SEND_DATE between", value1, value2, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_SEND_DATE not between", value1, value2, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterionForJDBCDate("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BEGIN_TIME not between", value1, value2, "beginTime");
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