package com.xmomen.module.stockdaily.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StockDailyExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockDailyExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStockIdIsNull() {
            addCriterion("stock_id is null");
            return (Criteria) this;
        }

        public Criteria andStockIdIsNotNull() {
            addCriterion("stock_id is not null");
            return (Criteria) this;
        }

        public Criteria andStockIdEqualTo(String value) {
            addCriterion("stock_id =", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotEqualTo(String value) {
            addCriterion("stock_id <>", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThan(String value) {
            addCriterion("stock_id >", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThanOrEqualTo(String value) {
            addCriterion("stock_id >=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThan(String value) {
            addCriterion("stock_id <", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThanOrEqualTo(String value) {
            addCriterion("stock_id <=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLike(String value) {
            addCriterion("stock_id like", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotLike(String value) {
            addCriterion("stock_id not like", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdIn(List<String> values) {
            addCriterion("stock_id in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotIn(List<String> values) {
            addCriterion("stock_id not in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdBetween(String value1, String value2) {
            addCriterion("stock_id between", value1, value2, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotBetween(String value1, String value2) {
            addCriterion("stock_id not between", value1, value2, "stockId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andOldStockNumIsNull() {
            addCriterion("old_stock_num is null");
            return (Criteria) this;
        }

        public Criteria andOldStockNumIsNotNull() {
            addCriterion("old_stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andOldStockNumEqualTo(Integer value) {
            addCriterion("old_stock_num =", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumNotEqualTo(Integer value) {
            addCriterion("old_stock_num <>", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumGreaterThan(Integer value) {
            addCriterion("old_stock_num >", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_stock_num >=", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumLessThan(Integer value) {
            addCriterion("old_stock_num <", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("old_stock_num <=", value, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumIn(List<Integer> values) {
            addCriterion("old_stock_num in", values, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumNotIn(List<Integer> values) {
            addCriterion("old_stock_num not in", values, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumBetween(Integer value1, Integer value2) {
            addCriterion("old_stock_num between", value1, value2, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andOldStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("old_stock_num not between", value1, value2, "oldStockNum");
            return (Criteria) this;
        }

        public Criteria andInNumIsNull() {
            addCriterion("in_num is null");
            return (Criteria) this;
        }

        public Criteria andInNumIsNotNull() {
            addCriterion("in_num is not null");
            return (Criteria) this;
        }

        public Criteria andInNumEqualTo(Integer value) {
            addCriterion("in_num =", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumNotEqualTo(Integer value) {
            addCriterion("in_num <>", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumGreaterThan(Integer value) {
            addCriterion("in_num >", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_num >=", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumLessThan(Integer value) {
            addCriterion("in_num <", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumLessThanOrEqualTo(Integer value) {
            addCriterion("in_num <=", value, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumIn(List<Integer> values) {
            addCriterion("in_num in", values, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumNotIn(List<Integer> values) {
            addCriterion("in_num not in", values, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumBetween(Integer value1, Integer value2) {
            addCriterion("in_num between", value1, value2, "inNum");
            return (Criteria) this;
        }

        public Criteria andInNumNotBetween(Integer value1, Integer value2) {
            addCriterion("in_num not between", value1, value2, "inNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumIsNull() {
            addCriterion("return_in_num is null");
            return (Criteria) this;
        }

        public Criteria andReturnInNumIsNotNull() {
            addCriterion("return_in_num is not null");
            return (Criteria) this;
        }

        public Criteria andReturnInNumEqualTo(Integer value) {
            addCriterion("return_in_num =", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumNotEqualTo(Integer value) {
            addCriterion("return_in_num <>", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumGreaterThan(Integer value) {
            addCriterion("return_in_num >", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_in_num >=", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumLessThan(Integer value) {
            addCriterion("return_in_num <", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumLessThanOrEqualTo(Integer value) {
            addCriterion("return_in_num <=", value, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumIn(List<Integer> values) {
            addCriterion("return_in_num in", values, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumNotIn(List<Integer> values) {
            addCriterion("return_in_num not in", values, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumBetween(Integer value1, Integer value2) {
            addCriterion("return_in_num between", value1, value2, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andReturnInNumNotBetween(Integer value1, Integer value2) {
            addCriterion("return_in_num not between", value1, value2, "returnInNum");
            return (Criteria) this;
        }

        public Criteria andOutNumIsNull() {
            addCriterion("out_num is null");
            return (Criteria) this;
        }

        public Criteria andOutNumIsNotNull() {
            addCriterion("out_num is not null");
            return (Criteria) this;
        }

        public Criteria andOutNumEqualTo(Integer value) {
            addCriterion("out_num =", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotEqualTo(Integer value) {
            addCriterion("out_num <>", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumGreaterThan(Integer value) {
            addCriterion("out_num >", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("out_num >=", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumLessThan(Integer value) {
            addCriterion("out_num <", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumLessThanOrEqualTo(Integer value) {
            addCriterion("out_num <=", value, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumIn(List<Integer> values) {
            addCriterion("out_num in", values, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotIn(List<Integer> values) {
            addCriterion("out_num not in", values, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumBetween(Integer value1, Integer value2) {
            addCriterion("out_num between", value1, value2, "outNum");
            return (Criteria) this;
        }

        public Criteria andOutNumNotBetween(Integer value1, Integer value2) {
            addCriterion("out_num not between", value1, value2, "outNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumIsNull() {
            addCriterion("damaged_num is null");
            return (Criteria) this;
        }

        public Criteria andDamagedNumIsNotNull() {
            addCriterion("damaged_num is not null");
            return (Criteria) this;
        }

        public Criteria andDamagedNumEqualTo(Integer value) {
            addCriterion("damaged_num =", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumNotEqualTo(Integer value) {
            addCriterion("damaged_num <>", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumGreaterThan(Integer value) {
            addCriterion("damaged_num >", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("damaged_num >=", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumLessThan(Integer value) {
            addCriterion("damaged_num <", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumLessThanOrEqualTo(Integer value) {
            addCriterion("damaged_num <=", value, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumIn(List<Integer> values) {
            addCriterion("damaged_num in", values, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumNotIn(List<Integer> values) {
            addCriterion("damaged_num not in", values, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumBetween(Integer value1, Integer value2) {
            addCriterion("damaged_num between", value1, value2, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andDamagedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("damaged_num not between", value1, value2, "damagedNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumIsNull() {
            addCriterion("verification_num is null");
            return (Criteria) this;
        }

        public Criteria andVerificationNumIsNotNull() {
            addCriterion("verification_num is not null");
            return (Criteria) this;
        }

        public Criteria andVerificationNumEqualTo(Integer value) {
            addCriterion("verification_num =", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumNotEqualTo(Integer value) {
            addCriterion("verification_num <>", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumGreaterThan(Integer value) {
            addCriterion("verification_num >", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("verification_num >=", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumLessThan(Integer value) {
            addCriterion("verification_num <", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumLessThanOrEqualTo(Integer value) {
            addCriterion("verification_num <=", value, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumIn(List<Integer> values) {
            addCriterion("verification_num in", values, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumNotIn(List<Integer> values) {
            addCriterion("verification_num not in", values, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumBetween(Integer value1, Integer value2) {
            addCriterion("verification_num between", value1, value2, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andVerificationNumNotBetween(Integer value1, Integer value2) {
            addCriterion("verification_num not between", value1, value2, "verificationNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumIsNull() {
            addCriterion("new_stock_num is null");
            return (Criteria) this;
        }

        public Criteria andNewStockNumIsNotNull() {
            addCriterion("new_stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andNewStockNumEqualTo(Integer value) {
            addCriterion("new_stock_num =", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumNotEqualTo(Integer value) {
            addCriterion("new_stock_num <>", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumGreaterThan(Integer value) {
            addCriterion("new_stock_num >", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_stock_num >=", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumLessThan(Integer value) {
            addCriterion("new_stock_num <", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("new_stock_num <=", value, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumIn(List<Integer> values) {
            addCriterion("new_stock_num in", values, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumNotIn(List<Integer> values) {
            addCriterion("new_stock_num not in", values, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumBetween(Integer value1, Integer value2) {
            addCriterion("new_stock_num between", value1, value2, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andNewStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("new_stock_num not between", value1, value2, "newStockNum");
            return (Criteria) this;
        }

        public Criteria andDailyDateIsNull() {
            addCriterion("daily_date is null");
            return (Criteria) this;
        }

        public Criteria andDailyDateIsNotNull() {
            addCriterion("daily_date is not null");
            return (Criteria) this;
        }

        public Criteria andDailyDateEqualTo(Date value) {
            addCriterionForJDBCDate("daily_date =", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("daily_date <>", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("daily_date >", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("daily_date >=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThan(Date value) {
            addCriterionForJDBCDate("daily_date <", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("daily_date <=", value, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateIn(List<Date> values) {
            addCriterionForJDBCDate("daily_date in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("daily_date not in", values, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("daily_date between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andDailyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("daily_date not between", value1, value2, "dailyDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNull() {
            addCriterion("insert_date is null");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNotNull() {
            addCriterion("insert_date is not null");
            return (Criteria) this;
        }

        public Criteria andInsertDateEqualTo(Date value) {
            addCriterion("insert_date =", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotEqualTo(Date value) {
            addCriterion("insert_date <>", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThan(Date value) {
            addCriterion("insert_date >", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_date >=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThan(Date value) {
            addCriterion("insert_date <", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThanOrEqualTo(Date value) {
            addCriterion("insert_date <=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIn(List<Date> values) {
            addCriterion("insert_date in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotIn(List<Date> values) {
            addCriterion("insert_date not in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateBetween(Date value1, Date value2) {
            addCriterion("insert_date between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotBetween(Date value1, Date value2) {
            addCriterion("insert_date not between", value1, value2, "insertDate");
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