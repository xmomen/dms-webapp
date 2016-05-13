package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class CdMemberExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdMemberExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("PHONE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("PHONE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("PHONE_NUMBER =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("PHONE_NUMBER <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("PHONE_NUMBER >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NUMBER >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("PHONE_NUMBER <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NUMBER <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("PHONE_NUMBER like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("PHONE_NUMBER not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("PHONE_NUMBER in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("PHONE_NUMBER not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("PHONE_NUMBER between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("PHONE_NUMBER not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIsNull() {
            addCriterion("CD_COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIsNotNull() {
            addCriterion("CD_COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID =", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID <>", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdGreaterThan(Integer value) {
            addCriterion("CD_COMPANY_ID >", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID >=", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdLessThan(Integer value) {
            addCriterion("CD_COMPANY_ID <", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_COMPANY_ID <=", value, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdIn(List<Integer> values) {
            addCriterion("CD_COMPANY_ID in", values, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotIn(List<Integer> values) {
            addCriterion("CD_COMPANY_ID not in", values, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_COMPANY_ID between", value1, value2, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_COMPANY_ID not between", value1, value2, "cdCompanyId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIsNull() {
            addCriterion("CD_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIsNotNull() {
            addCriterion("CD_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdUserIdEqualTo(Integer value) {
            addCriterion("CD_USER_ID =", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotEqualTo(Integer value) {
            addCriterion("CD_USER_ID <>", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdGreaterThan(Integer value) {
            addCriterion("CD_USER_ID >", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_USER_ID >=", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdLessThan(Integer value) {
            addCriterion("CD_USER_ID <", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_USER_ID <=", value, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdIn(List<Integer> values) {
            addCriterion("CD_USER_ID in", values, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotIn(List<Integer> values) {
            addCriterion("CD_USER_ID not in", values, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_USER_ID between", value1, value2, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andCdUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_USER_ID not between", value1, value2, "cdUserId");
            return (Criteria) this;
        }

        public Criteria andSpareNameIsNull() {
            addCriterion("SPARE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpareNameIsNotNull() {
            addCriterion("SPARE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpareNameEqualTo(String value) {
            addCriterion("SPARE_NAME =", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameNotEqualTo(String value) {
            addCriterion("SPARE_NAME <>", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameGreaterThan(String value) {
            addCriterion("SPARE_NAME >", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_NAME >=", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameLessThan(String value) {
            addCriterion("SPARE_NAME <", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameLessThanOrEqualTo(String value) {
            addCriterion("SPARE_NAME <=", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameLike(String value) {
            addCriterion("SPARE_NAME like", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameNotLike(String value) {
            addCriterion("SPARE_NAME not like", value, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameIn(List<String> values) {
            addCriterion("SPARE_NAME in", values, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameNotIn(List<String> values) {
            addCriterion("SPARE_NAME not in", values, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameBetween(String value1, String value2) {
            addCriterion("SPARE_NAME between", value1, value2, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareNameNotBetween(String value1, String value2) {
            addCriterion("SPARE_NAME not between", value1, value2, "spareName");
            return (Criteria) this;
        }

        public Criteria andSpareName2IsNull() {
            addCriterion("SPARE_NAME2 is null");
            return (Criteria) this;
        }

        public Criteria andSpareName2IsNotNull() {
            addCriterion("SPARE_NAME2 is not null");
            return (Criteria) this;
        }

        public Criteria andSpareName2EqualTo(String value) {
            addCriterion("SPARE_NAME2 =", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2NotEqualTo(String value) {
            addCriterion("SPARE_NAME2 <>", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2GreaterThan(String value) {
            addCriterion("SPARE_NAME2 >", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2GreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_NAME2 >=", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2LessThan(String value) {
            addCriterion("SPARE_NAME2 <", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2LessThanOrEqualTo(String value) {
            addCriterion("SPARE_NAME2 <=", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2Like(String value) {
            addCriterion("SPARE_NAME2 like", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2NotLike(String value) {
            addCriterion("SPARE_NAME2 not like", value, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2In(List<String> values) {
            addCriterion("SPARE_NAME2 in", values, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2NotIn(List<String> values) {
            addCriterion("SPARE_NAME2 not in", values, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2Between(String value1, String value2) {
            addCriterion("SPARE_NAME2 between", value1, value2, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareName2NotBetween(String value1, String value2) {
            addCriterion("SPARE_NAME2 not between", value1, value2, "spareName2");
            return (Criteria) this;
        }

        public Criteria andSpareTelIsNull() {
            addCriterion("SPARE_TEL is null");
            return (Criteria) this;
        }

        public Criteria andSpareTelIsNotNull() {
            addCriterion("SPARE_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andSpareTelEqualTo(String value) {
            addCriterion("SPARE_TEL =", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelNotEqualTo(String value) {
            addCriterion("SPARE_TEL <>", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelGreaterThan(String value) {
            addCriterion("SPARE_TEL >", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelGreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_TEL >=", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelLessThan(String value) {
            addCriterion("SPARE_TEL <", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelLessThanOrEqualTo(String value) {
            addCriterion("SPARE_TEL <=", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelLike(String value) {
            addCriterion("SPARE_TEL like", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelNotLike(String value) {
            addCriterion("SPARE_TEL not like", value, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelIn(List<String> values) {
            addCriterion("SPARE_TEL in", values, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelNotIn(List<String> values) {
            addCriterion("SPARE_TEL not in", values, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelBetween(String value1, String value2) {
            addCriterion("SPARE_TEL between", value1, value2, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTelNotBetween(String value1, String value2) {
            addCriterion("SPARE_TEL not between", value1, value2, "spareTel");
            return (Criteria) this;
        }

        public Criteria andSpareTel2IsNull() {
            addCriterion("SPARE_TEL2 is null");
            return (Criteria) this;
        }

        public Criteria andSpareTel2IsNotNull() {
            addCriterion("SPARE_TEL2 is not null");
            return (Criteria) this;
        }

        public Criteria andSpareTel2EqualTo(String value) {
            addCriterion("SPARE_TEL2 =", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2NotEqualTo(String value) {
            addCriterion("SPARE_TEL2 <>", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2GreaterThan(String value) {
            addCriterion("SPARE_TEL2 >", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2GreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_TEL2 >=", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2LessThan(String value) {
            addCriterion("SPARE_TEL2 <", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2LessThanOrEqualTo(String value) {
            addCriterion("SPARE_TEL2 <=", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2Like(String value) {
            addCriterion("SPARE_TEL2 like", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2NotLike(String value) {
            addCriterion("SPARE_TEL2 not like", value, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2In(List<String> values) {
            addCriterion("SPARE_TEL2 in", values, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2NotIn(List<String> values) {
            addCriterion("SPARE_TEL2 not in", values, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2Between(String value1, String value2) {
            addCriterion("SPARE_TEL2 between", value1, value2, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andSpareTel2NotBetween(String value1, String value2) {
            addCriterion("SPARE_TEL2 not between", value1, value2, "spareTel2");
            return (Criteria) this;
        }

        public Criteria andTelNumberIsNull() {
            addCriterion("TEL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTelNumberIsNotNull() {
            addCriterion("TEL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTelNumberEqualTo(String value) {
            addCriterion("TEL_NUMBER =", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotEqualTo(String value) {
            addCriterion("TEL_NUMBER <>", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberGreaterThan(String value) {
            addCriterion("TEL_NUMBER >", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TEL_NUMBER >=", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLessThan(String value) {
            addCriterion("TEL_NUMBER <", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLessThanOrEqualTo(String value) {
            addCriterion("TEL_NUMBER <=", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLike(String value) {
            addCriterion("TEL_NUMBER like", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotLike(String value) {
            addCriterion("TEL_NUMBER not like", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberIn(List<String> values) {
            addCriterion("TEL_NUMBER in", values, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotIn(List<String> values) {
            addCriterion("TEL_NUMBER not in", values, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberBetween(String value1, String value2) {
            addCriterion("TEL_NUMBER between", value1, value2, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotBetween(String value1, String value2) {
            addCriterion("TEL_NUMBER not between", value1, value2, "telNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIsNull() {
            addCriterion("OFFICE_TEL is null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIsNotNull() {
            addCriterion("OFFICE_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelEqualTo(String value) {
            addCriterion("OFFICE_TEL =", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotEqualTo(String value) {
            addCriterion("OFFICE_TEL <>", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelGreaterThan(String value) {
            addCriterion("OFFICE_TEL >", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICE_TEL >=", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLessThan(String value) {
            addCriterion("OFFICE_TEL <", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLessThanOrEqualTo(String value) {
            addCriterion("OFFICE_TEL <=", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLike(String value) {
            addCriterion("OFFICE_TEL like", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotLike(String value) {
            addCriterion("OFFICE_TEL not like", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIn(List<String> values) {
            addCriterion("OFFICE_TEL in", values, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotIn(List<String> values) {
            addCriterion("OFFICE_TEL not in", values, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelBetween(String value1, String value2) {
            addCriterion("OFFICE_TEL between", value1, value2, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotBetween(String value1, String value2) {
            addCriterion("OFFICE_TEL not between", value1, value2, "officeTel");
            return (Criteria) this;
        }

        public Criteria andSpareAddressIsNull() {
            addCriterion("SPARE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andSpareAddressIsNotNull() {
            addCriterion("SPARE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andSpareAddressEqualTo(String value) {
            addCriterion("SPARE_ADDRESS =", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressNotEqualTo(String value) {
            addCriterion("SPARE_ADDRESS <>", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressGreaterThan(String value) {
            addCriterion("SPARE_ADDRESS >", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressGreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_ADDRESS >=", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressLessThan(String value) {
            addCriterion("SPARE_ADDRESS <", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressLessThanOrEqualTo(String value) {
            addCriterion("SPARE_ADDRESS <=", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressLike(String value) {
            addCriterion("SPARE_ADDRESS like", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressNotLike(String value) {
            addCriterion("SPARE_ADDRESS not like", value, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressIn(List<String> values) {
            addCriterion("SPARE_ADDRESS in", values, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressNotIn(List<String> values) {
            addCriterion("SPARE_ADDRESS not in", values, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressBetween(String value1, String value2) {
            addCriterion("SPARE_ADDRESS between", value1, value2, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddressNotBetween(String value1, String value2) {
            addCriterion("SPARE_ADDRESS not between", value1, value2, "spareAddress");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2IsNull() {
            addCriterion("SPARE_ADDRESS2 is null");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2IsNotNull() {
            addCriterion("SPARE_ADDRESS2 is not null");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2EqualTo(String value) {
            addCriterion("SPARE_ADDRESS2 =", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2NotEqualTo(String value) {
            addCriterion("SPARE_ADDRESS2 <>", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2GreaterThan(String value) {
            addCriterion("SPARE_ADDRESS2 >", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2GreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_ADDRESS2 >=", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2LessThan(String value) {
            addCriterion("SPARE_ADDRESS2 <", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2LessThanOrEqualTo(String value) {
            addCriterion("SPARE_ADDRESS2 <=", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2Like(String value) {
            addCriterion("SPARE_ADDRESS2 like", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2NotLike(String value) {
            addCriterion("SPARE_ADDRESS2 not like", value, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2In(List<String> values) {
            addCriterion("SPARE_ADDRESS2 in", values, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2NotIn(List<String> values) {
            addCriterion("SPARE_ADDRESS2 not in", values, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2Between(String value1, String value2) {
            addCriterion("SPARE_ADDRESS2 between", value1, value2, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andSpareAddress2NotBetween(String value1, String value2) {
            addCriterion("SPARE_ADDRESS2 not between", value1, value2, "spareAddress2");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIsNull() {
            addCriterion("MEMBER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIsNotNull() {
            addCriterion("MEMBER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeEqualTo(Integer value) {
            addCriterion("MEMBER_TYPE =", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotEqualTo(Integer value) {
            addCriterion("MEMBER_TYPE <>", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThan(Integer value) {
            addCriterion("MEMBER_TYPE >", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MEMBER_TYPE >=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThan(Integer value) {
            addCriterion("MEMBER_TYPE <", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThanOrEqualTo(Integer value) {
            addCriterion("MEMBER_TYPE <=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIn(List<Integer> values) {
            addCriterion("MEMBER_TYPE in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotIn(List<Integer> values) {
            addCriterion("MEMBER_TYPE not in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeBetween(Integer value1, Integer value2) {
            addCriterion("MEMBER_TYPE between", value1, value2, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("MEMBER_TYPE not between", value1, value2, "memberType");
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