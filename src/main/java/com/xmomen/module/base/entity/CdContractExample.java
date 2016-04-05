package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdContractExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdContractExample() {
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

        public Criteria andScopeIsNull() {
            addCriterion("SCOPE is null");
            return (Criteria) this;
        }

        public Criteria andScopeIsNotNull() {
            addCriterion("SCOPE is not null");
            return (Criteria) this;
        }

        public Criteria andScopeEqualTo(Integer value) {
            addCriterion("SCOPE =", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotEqualTo(Integer value) {
            addCriterion("SCOPE <>", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThan(Integer value) {
            addCriterion("SCOPE >", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCOPE >=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThan(Integer value) {
            addCriterion("SCOPE <", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThanOrEqualTo(Integer value) {
            addCriterion("SCOPE <=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeIn(List<Integer> values) {
            addCriterion("SCOPE in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotIn(List<Integer> values) {
            addCriterion("SCOPE not in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeBetween(Integer value1, Integer value2) {
            addCriterion("SCOPE between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotBetween(Integer value1, Integer value2) {
            addCriterion("SCOPE not between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNull() {
            addCriterion("CONTRACT_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNotNull() {
            addCriterion("CONTRACT_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andContractPriceEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_PRICE =", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_PRICE <>", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThan(BigDecimal value) {
            addCriterion("CONTRACT_PRICE >", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_PRICE >=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThan(BigDecimal value) {
            addCriterion("CONTRACT_PRICE <", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_PRICE <=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceIn(List<BigDecimal> values) {
            addCriterion("CONTRACT_PRICE in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotIn(List<BigDecimal> values) {
            addCriterion("CONTRACT_PRICE not in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONTRACT_PRICE between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONTRACT_PRICE not between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andBenginTimeIsNull() {
            addCriterion("BENGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBenginTimeIsNotNull() {
            addCriterion("BENGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBenginTimeEqualTo(Date value) {
            addCriterion("BENGIN_TIME =", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeNotEqualTo(Date value) {
            addCriterion("BENGIN_TIME <>", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeGreaterThan(Date value) {
            addCriterion("BENGIN_TIME >", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BENGIN_TIME >=", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeLessThan(Date value) {
            addCriterion("BENGIN_TIME <", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeLessThanOrEqualTo(Date value) {
            addCriterion("BENGIN_TIME <=", value, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeIn(List<Date> values) {
            addCriterion("BENGIN_TIME in", values, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeNotIn(List<Date> values) {
            addCriterion("BENGIN_TIME not in", values, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeBetween(Date value1, Date value2) {
            addCriterion("BENGIN_TIME between", value1, value2, "benginTime");
            return (Criteria) this;
        }

        public Criteria andBenginTimeNotBetween(Date value1, Date value2) {
            addCriterion("BENGIN_TIME not between", value1, value2, "benginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andIsAuditorIsNull() {
            addCriterion("IS_AUDITOR is null");
            return (Criteria) this;
        }

        public Criteria andIsAuditorIsNotNull() {
            addCriterion("IS_AUDITOR is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuditorEqualTo(Integer value) {
            addCriterion("IS_AUDITOR =", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorNotEqualTo(Integer value) {
            addCriterion("IS_AUDITOR <>", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorGreaterThan(Integer value) {
            addCriterion("IS_AUDITOR >", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_AUDITOR >=", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorLessThan(Integer value) {
            addCriterion("IS_AUDITOR <", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorLessThanOrEqualTo(Integer value) {
            addCriterion("IS_AUDITOR <=", value, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorIn(List<Integer> values) {
            addCriterion("IS_AUDITOR in", values, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorNotIn(List<Integer> values) {
            addCriterion("IS_AUDITOR not in", values, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorBetween(Integer value1, Integer value2) {
            addCriterion("IS_AUDITOR between", value1, value2, "isAuditor");
            return (Criteria) this;
        }

        public Criteria andIsAuditorNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_AUDITOR not between", value1, value2, "isAuditor");
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