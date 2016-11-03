package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbOrderExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("ORDER_NO =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("ORDER_NO <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("ORDER_NO >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_NO >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("ORDER_NO <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ORDER_NO <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("ORDER_NO like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("ORDER_NO not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("ORDER_NO in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("ORDER_NO not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("ORDER_NO between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("ORDER_NO not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("ORDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("ORDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("ORDER_TYPE =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("ORDER_TYPE <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("ORDER_TYPE >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_TYPE >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("ORDER_TYPE <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_TYPE <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("ORDER_TYPE in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("ORDER_TYPE not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_TYPE between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_TYPE not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNull() {
            addCriterion("ORDER_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("ORDER_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(Integer value) {
            addCriterion("ORDER_SOURCE =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(Integer value) {
            addCriterion("ORDER_SOURCE <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(Integer value) {
            addCriterion("ORDER_SOURCE >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_SOURCE >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(Integer value) {
            addCriterion("ORDER_SOURCE <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_SOURCE <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<Integer> values) {
            addCriterion("ORDER_SOURCE in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<Integer> values) {
            addCriterion("ORDER_SOURCE not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_SOURCE between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_SOURCE not between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andPaymentModeIsNull() {
            addCriterion("PAYMENT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPaymentModeIsNotNull() {
            addCriterion("PAYMENT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentModeEqualTo(Integer value) {
            addCriterion("PAYMENT_MODE =", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeNotEqualTo(Integer value) {
            addCriterion("PAYMENT_MODE <>", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeGreaterThan(Integer value) {
            addCriterion("PAYMENT_MODE >", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAYMENT_MODE >=", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeLessThan(Integer value) {
            addCriterion("PAYMENT_MODE <", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeLessThanOrEqualTo(Integer value) {
            addCriterion("PAYMENT_MODE <=", value, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeIn(List<Integer> values) {
            addCriterion("PAYMENT_MODE in", values, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeNotIn(List<Integer> values) {
            addCriterion("PAYMENT_MODE not in", values, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeBetween(Integer value1, Integer value2) {
            addCriterion("PAYMENT_MODE between", value1, value2, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andPaymentModeNotBetween(Integer value1, Integer value2) {
            addCriterion("PAYMENT_MODE not between", value1, value2, "paymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeIsNull() {
            addCriterion("OTHER_PAYMENT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeIsNotNull() {
            addCriterion("OTHER_PAYMENT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeEqualTo(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE =", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeNotEqualTo(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE <>", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeGreaterThan(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE >", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE >=", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeLessThan(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE <", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeLessThanOrEqualTo(Integer value) {
            addCriterion("OTHER_PAYMENT_MODE <=", value, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeIn(List<Integer> values) {
            addCriterion("OTHER_PAYMENT_MODE in", values, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeNotIn(List<Integer> values) {
            addCriterion("OTHER_PAYMENT_MODE not in", values, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeBetween(Integer value1, Integer value2) {
            addCriterion("OTHER_PAYMENT_MODE between", value1, value2, "otherPaymentMode");
            return (Criteria) this;
        }

        public Criteria andOtherPaymentModeNotBetween(Integer value1, Integer value2) {
            addCriterion("OTHER_PAYMENT_MODE not between", value1, value2, "otherPaymentMode");
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

        public Criteria andOrderStatusIsNull() {
            addCriterion("ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("ORDER_STATUS =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("ORDER_STATUS <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("ORDER_STATUS >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("ORDER_STATUS <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("ORDER_STATUS like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("ORDER_STATUS not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("ORDER_STATUS in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("ORDER_STATUS not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("PAY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("PAY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("PAY_STATUS =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("PAY_STATUS <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("PAY_STATUS >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAY_STATUS >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("PAY_STATUS <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("PAY_STATUS <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("PAY_STATUS in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("PAY_STATUS not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("PAY_STATUS between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("PAY_STATUS not between", value1, value2, "payStatus");
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

        public Criteria andTransportModeIsNull() {
            addCriterion("TRANSPORT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andTransportModeIsNotNull() {
            addCriterion("TRANSPORT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andTransportModeEqualTo(Integer value) {
            addCriterion("TRANSPORT_MODE =", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotEqualTo(Integer value) {
            addCriterion("TRANSPORT_MODE <>", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeGreaterThan(Integer value) {
            addCriterion("TRANSPORT_MODE >", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRANSPORT_MODE >=", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeLessThan(Integer value) {
            addCriterion("TRANSPORT_MODE <", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeLessThanOrEqualTo(Integer value) {
            addCriterion("TRANSPORT_MODE <=", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeIn(List<Integer> values) {
            addCriterion("TRANSPORT_MODE in", values, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotIn(List<Integer> values) {
            addCriterion("TRANSPORT_MODE not in", values, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeBetween(Integer value1, Integer value2) {
            addCriterion("TRANSPORT_MODE between", value1, value2, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotBetween(Integer value1, Integer value2) {
            addCriterion("TRANSPORT_MODE not between", value1, value2, "transportMode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("TOTAL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("TOTAL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_AMOUNT <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("TOTAL_AMOUNT in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_AMOUNT not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_AMOUNT between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_AMOUNT not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIsNull() {
            addCriterion("APPOINTMENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIsNotNull() {
            addCriterion("APPOINTMENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeEqualTo(Date value) {
            addCriterion("APPOINTMENT_TIME =", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeNotEqualTo(Date value) {
            addCriterion("APPOINTMENT_TIME <>", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeGreaterThan(Date value) {
            addCriterion("APPOINTMENT_TIME >", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("APPOINTMENT_TIME >=", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeLessThan(Date value) {
            addCriterion("APPOINTMENT_TIME <", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeLessThanOrEqualTo(Date value) {
            addCriterion("APPOINTMENT_TIME <=", value, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIn(List<Date> values) {
            addCriterion("APPOINTMENT_TIME in", values, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeNotIn(List<Date> values) {
            addCriterion("APPOINTMENT_TIME not in", values, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeBetween(Date value1, Date value2) {
            addCriterion("APPOINTMENT_TIME between", value1, value2, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeNotBetween(Date value1, Date value2) {
            addCriterion("APPOINTMENT_TIME not between", value1, value2, "appointmentTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("CREATE_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("CREATE_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("CREATE_USER_ID =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("CREATE_USER_ID <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("CREATE_USER_ID >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATE_USER_ID >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("CREATE_USER_ID <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("CREATE_USER_ID <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("CREATE_USER_ID in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("CREATE_USER_ID not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_USER_ID between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_USER_ID not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdIsNull() {
            addCriterion("DESPATCH_EXPRESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdIsNotNull() {
            addCriterion("DESPATCH_EXPRESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdEqualTo(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID =", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdNotEqualTo(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID <>", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdGreaterThan(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID >", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID >=", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdLessThan(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID <", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("DESPATCH_EXPRESS_ID <=", value, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdIn(List<Integer> values) {
            addCriterion("DESPATCH_EXPRESS_ID in", values, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdNotIn(List<Integer> values) {
            addCriterion("DESPATCH_EXPRESS_ID not in", values, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("DESPATCH_EXPRESS_ID between", value1, value2, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andDespatchExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DESPATCH_EXPRESS_ID not between", value1, value2, "despatchExpressId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdIsNull() {
            addCriterion("EXPRESS_MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdIsNotNull() {
            addCriterion("EXPRESS_MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdEqualTo(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID =", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdNotEqualTo(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID <>", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdGreaterThan(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID >", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID >=", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdLessThan(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID <", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXPRESS_MEMBER_ID <=", value, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdIn(List<Integer> values) {
            addCriterion("EXPRESS_MEMBER_ID in", values, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdNotIn(List<Integer> values) {
            addCriterion("EXPRESS_MEMBER_ID not in", values, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("EXPRESS_MEMBER_ID between", value1, value2, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andExpressMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXPRESS_MEMBER_ID not between", value1, value2, "expressMemberId");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("DISCOUNT_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("DISCOUNT_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_PRICE <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_PRICE in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_PRICE not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_PRICE between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_PRICE not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("MANAGER_ID is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("MANAGER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("MANAGER_ID =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("MANAGER_ID <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("MANAGER_ID >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MANAGER_ID >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("MANAGER_ID <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("MANAGER_ID <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("MANAGER_ID in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("MANAGER_ID not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("MANAGER_ID between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("MANAGER_ID not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("BATCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("BATCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("BATCH_NO like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("BATCH_NO not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
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