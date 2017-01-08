package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbReturnOrderExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbReturnOrderExample() {
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

        public Criteria andReturnTimeIsNull() {
            addCriterion("RETURN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIsNotNull() {
            addCriterion("RETURN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTimeEqualTo(Date value) {
            addCriterion("RETURN_TIME =", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotEqualTo(Date value) {
            addCriterion("RETURN_TIME <>", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThan(Date value) {
            addCriterion("RETURN_TIME >", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RETURN_TIME >=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThan(Date value) {
            addCriterion("RETURN_TIME <", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeLessThanOrEqualTo(Date value) {
            addCriterion("RETURN_TIME <=", value, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeIn(List<Date> values) {
            addCriterion("RETURN_TIME in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotIn(List<Date> values) {
            addCriterion("RETURN_TIME not in", values, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeBetween(Date value1, Date value2) {
            addCriterion("RETURN_TIME between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnTimeNotBetween(Date value1, Date value2) {
            addCriterion("RETURN_TIME not between", value1, value2, "returnTime");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNull() {
            addCriterion("RETURN_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIsNotNull() {
            addCriterion("RETURN_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReturnStatusEqualTo(Integer value) {
            addCriterion("RETURN_STATUS =", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotEqualTo(Integer value) {
            addCriterion("RETURN_STATUS <>", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThan(Integer value) {
            addCriterion("RETURN_STATUS >", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("RETURN_STATUS >=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThan(Integer value) {
            addCriterion("RETURN_STATUS <", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusLessThanOrEqualTo(Integer value) {
            addCriterion("RETURN_STATUS <=", value, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusIn(List<Integer> values) {
            addCriterion("RETURN_STATUS in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotIn(List<Integer> values) {
            addCriterion("RETURN_STATUS not in", values, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusBetween(Integer value1, Integer value2) {
            addCriterion("RETURN_STATUS between", value1, value2, "returnStatus");
            return (Criteria) this;
        }

        public Criteria andReturnStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("RETURN_STATUS not between", value1, value2, "returnStatus");
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

        public Criteria andAuditDateIsNull() {
            addCriterion("AUDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("AUDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("AUDIT_DATE =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("AUDIT_DATE <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("AUDIT_DATE >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("AUDIT_DATE <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("AUDIT_DATE in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("AUDIT_DATE not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE not between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("AUDIT_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("AUDIT_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(Integer value) {
            addCriterion("AUDIT_USER_ID =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(Integer value) {
            addCriterion("AUDIT_USER_ID <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(Integer value) {
            addCriterion("AUDIT_USER_ID >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_USER_ID >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(Integer value) {
            addCriterion("AUDIT_USER_ID <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_USER_ID <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<Integer> values) {
            addCriterion("AUDIT_USER_ID in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<Integer> values) {
            addCriterion("AUDIT_USER_ID not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_USER_ID between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_USER_ID not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateIsNull() {
            addCriterion("TAKE_GOODS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateIsNotNull() {
            addCriterion("TAKE_GOODS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateEqualTo(Date value) {
            addCriterion("TAKE_GOODS_DATE =", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateNotEqualTo(Date value) {
            addCriterion("TAKE_GOODS_DATE <>", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateGreaterThan(Date value) {
            addCriterion("TAKE_GOODS_DATE >", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("TAKE_GOODS_DATE >=", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateLessThan(Date value) {
            addCriterion("TAKE_GOODS_DATE <", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateLessThanOrEqualTo(Date value) {
            addCriterion("TAKE_GOODS_DATE <=", value, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateIn(List<Date> values) {
            addCriterion("TAKE_GOODS_DATE in", values, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateNotIn(List<Date> values) {
            addCriterion("TAKE_GOODS_DATE not in", values, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateBetween(Date value1, Date value2) {
            addCriterion("TAKE_GOODS_DATE between", value1, value2, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsDateNotBetween(Date value1, Date value2) {
            addCriterion("TAKE_GOODS_DATE not between", value1, value2, "takeGoodsDate");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdIsNull() {
            addCriterion("TAKE_GOODS_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdIsNotNull() {
            addCriterion("TAKE_GOODS_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdEqualTo(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID =", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdNotEqualTo(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID <>", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdGreaterThan(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID >", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID >=", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdLessThan(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID <", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("TAKE_GOODS_USER_ID <=", value, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdIn(List<Integer> values) {
            addCriterion("TAKE_GOODS_USER_ID in", values, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdNotIn(List<Integer> values) {
            addCriterion("TAKE_GOODS_USER_ID not in", values, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdBetween(Integer value1, Integer value2) {
            addCriterion("TAKE_GOODS_USER_ID between", value1, value2, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TAKE_GOODS_USER_ID not between", value1, value2, "takeGoodsUserId");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneIsNull() {
            addCriterion("TAKE_GOODS_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneIsNotNull() {
            addCriterion("TAKE_GOODS_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneEqualTo(String value) {
            addCriterion("TAKE_GOODS_PHONE =", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneNotEqualTo(String value) {
            addCriterion("TAKE_GOODS_PHONE <>", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneGreaterThan(String value) {
            addCriterion("TAKE_GOODS_PHONE >", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TAKE_GOODS_PHONE >=", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneLessThan(String value) {
            addCriterion("TAKE_GOODS_PHONE <", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneLessThanOrEqualTo(String value) {
            addCriterion("TAKE_GOODS_PHONE <=", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneLike(String value) {
            addCriterion("TAKE_GOODS_PHONE like", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneNotLike(String value) {
            addCriterion("TAKE_GOODS_PHONE not like", value, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneIn(List<String> values) {
            addCriterion("TAKE_GOODS_PHONE in", values, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneNotIn(List<String> values) {
            addCriterion("TAKE_GOODS_PHONE not in", values, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneBetween(String value1, String value2) {
            addCriterion("TAKE_GOODS_PHONE between", value1, value2, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andTakeGoodsPhoneNotBetween(String value1, String value2) {
            addCriterion("TAKE_GOODS_PHONE not between", value1, value2, "takeGoodsPhone");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountIsNull() {
            addCriterion("RETURN_TOTAL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountIsNotNull() {
            addCriterion("RETURN_TOTAL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountEqualTo(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT =", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT <>", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT >", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT >=", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountLessThan(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT <", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RETURN_TOTAL_AMOUNT <=", value, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountIn(List<BigDecimal> values) {
            addCriterion("RETURN_TOTAL_AMOUNT in", values, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("RETURN_TOTAL_AMOUNT not in", values, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RETURN_TOTAL_AMOUNT between", value1, value2, "returnTotalAmount");
            return (Criteria) this;
        }

        public Criteria andReturnTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RETURN_TOTAL_AMOUNT not between", value1, value2, "returnTotalAmount");
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