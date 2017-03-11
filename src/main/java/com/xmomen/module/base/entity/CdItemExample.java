package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CdItemExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CdItemExample() {
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

        public Criteria andItemCodeIsNull() {
            addCriterion("ITEM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("ITEM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("ITEM_CODE =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("ITEM_CODE <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("ITEM_CODE >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_CODE >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("ITEM_CODE <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("ITEM_CODE <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("ITEM_CODE like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("ITEM_CODE not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("ITEM_CODE in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("ITEM_CODE not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("ITEM_CODE between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("ITEM_CODE not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIsNull() {
            addCriterion("CD_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIsNotNull() {
            addCriterion("CD_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID =", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID <>", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdGreaterThan(Integer value) {
            addCriterion("CD_CATEGORY_ID >", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID >=", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdLessThan(Integer value) {
            addCriterion("CD_CATEGORY_ID <", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CD_CATEGORY_ID <=", value, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdIn(List<Integer> values) {
            addCriterion("CD_CATEGORY_ID in", values, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotIn(List<Integer> values) {
            addCriterion("CD_CATEGORY_ID not in", values, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CD_CATEGORY_ID between", value1, value2, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andCdCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CD_CATEGORY_ID not between", value1, value2, "cdCategoryId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemDescribeIsNull() {
            addCriterion("ITEM_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andItemDescribeIsNotNull() {
            addCriterion("ITEM_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescribeEqualTo(String value) {
            addCriterion("ITEM_DESCRIBE =", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeNotEqualTo(String value) {
            addCriterion("ITEM_DESCRIBE <>", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeGreaterThan(String value) {
            addCriterion("ITEM_DESCRIBE >", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_DESCRIBE >=", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeLessThan(String value) {
            addCriterion("ITEM_DESCRIBE <", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeLessThanOrEqualTo(String value) {
            addCriterion("ITEM_DESCRIBE <=", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeLike(String value) {
            addCriterion("ITEM_DESCRIBE like", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeNotLike(String value) {
            addCriterion("ITEM_DESCRIBE not like", value, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeIn(List<String> values) {
            addCriterion("ITEM_DESCRIBE in", values, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeNotIn(List<String> values) {
            addCriterion("ITEM_DESCRIBE not in", values, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeBetween(String value1, String value2) {
            addCriterion("ITEM_DESCRIBE between", value1, value2, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemDescribeNotBetween(String value1, String value2) {
            addCriterion("ITEM_DESCRIBE not between", value1, value2, "itemDescribe");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("ITEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("ITEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(Integer value) {
            addCriterion("ITEM_TYPE =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(Integer value) {
            addCriterion("ITEM_TYPE <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(Integer value) {
            addCriterion("ITEM_TYPE >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_TYPE >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(Integer value) {
            addCriterion("ITEM_TYPE <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_TYPE <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<Integer> values) {
            addCriterion("ITEM_TYPE in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<Integer> values) {
            addCriterion("ITEM_TYPE not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_TYPE between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_TYPE not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andYieldlyIsNull() {
            addCriterion("YIELDLY is null");
            return (Criteria) this;
        }

        public Criteria andYieldlyIsNotNull() {
            addCriterion("YIELDLY is not null");
            return (Criteria) this;
        }

        public Criteria andYieldlyEqualTo(String value) {
            addCriterion("YIELDLY =", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyNotEqualTo(String value) {
            addCriterion("YIELDLY <>", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyGreaterThan(String value) {
            addCriterion("YIELDLY >", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyGreaterThanOrEqualTo(String value) {
            addCriterion("YIELDLY >=", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyLessThan(String value) {
            addCriterion("YIELDLY <", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyLessThanOrEqualTo(String value) {
            addCriterion("YIELDLY <=", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyLike(String value) {
            addCriterion("YIELDLY like", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyNotLike(String value) {
            addCriterion("YIELDLY not like", value, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyIn(List<String> values) {
            addCriterion("YIELDLY in", values, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyNotIn(List<String> values) {
            addCriterion("YIELDLY not in", values, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyBetween(String value1, String value2) {
            addCriterion("YIELDLY between", value1, value2, "yieldly");
            return (Criteria) this;
        }

        public Criteria andYieldlyNotBetween(String value1, String value2) {
            addCriterion("YIELDLY not between", value1, value2, "yieldly");
            return (Criteria) this;
        }

        public Criteria andSpecIsNull() {
            addCriterion("SPEC is null");
            return (Criteria) this;
        }

        public Criteria andSpecIsNotNull() {
            addCriterion("SPEC is not null");
            return (Criteria) this;
        }

        public Criteria andSpecEqualTo(String value) {
            addCriterion("SPEC =", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotEqualTo(String value) {
            addCriterion("SPEC <>", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThan(String value) {
            addCriterion("SPEC >", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC >=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThan(String value) {
            addCriterion("SPEC <", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThanOrEqualTo(String value) {
            addCriterion("SPEC <=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLike(String value) {
            addCriterion("SPEC like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotLike(String value) {
            addCriterion("SPEC not like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecIn(List<String> values) {
            addCriterion("SPEC in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotIn(List<String> values) {
            addCriterion("SPEC not in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecBetween(String value1, String value2) {
            addCriterion("SPEC between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotBetween(String value1, String value2) {
            addCriterion("SPEC not between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNull() {
            addCriterion("BASE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNotNull() {
            addCriterion("BASE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE =", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE <>", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThan(BigDecimal value) {
            addCriterion("BASE_PRICE >", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE >=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThan(BigDecimal value) {
            addCriterion("BASE_PRICE <", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE <=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceIn(List<BigDecimal> values) {
            addCriterion("BASE_PRICE in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotIn(List<BigDecimal> values) {
            addCriterion("BASE_PRICE not in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASE_PRICE between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASE_PRICE not between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIsNull() {
            addCriterion("MEMBER_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIsNotNull() {
            addCriterion("MEMBER_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPriceEqualTo(BigDecimal value) {
            addCriterion("MEMBER_PRICE =", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotEqualTo(BigDecimal value) {
            addCriterion("MEMBER_PRICE <>", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceGreaterThan(BigDecimal value) {
            addCriterion("MEMBER_PRICE >", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMBER_PRICE >=", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceLessThan(BigDecimal value) {
            addCriterion("MEMBER_PRICE <", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMBER_PRICE <=", value, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceIn(List<BigDecimal> values) {
            addCriterion("MEMBER_PRICE in", values, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotIn(List<BigDecimal> values) {
            addCriterion("MEMBER_PRICE not in", values, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMBER_PRICE between", value1, value2, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andMemberPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMBER_PRICE not between", value1, value2, "memberPrice");
            return (Criteria) this;
        }

        public Criteria andPricingMannerIsNull() {
            addCriterion("PRICING_MANNER is null");
            return (Criteria) this;
        }

        public Criteria andPricingMannerIsNotNull() {
            addCriterion("PRICING_MANNER is not null");
            return (Criteria) this;
        }

        public Criteria andPricingMannerEqualTo(String value) {
            addCriterion("PRICING_MANNER =", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerNotEqualTo(String value) {
            addCriterion("PRICING_MANNER <>", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerGreaterThan(String value) {
            addCriterion("PRICING_MANNER >", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerGreaterThanOrEqualTo(String value) {
            addCriterion("PRICING_MANNER >=", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerLessThan(String value) {
            addCriterion("PRICING_MANNER <", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerLessThanOrEqualTo(String value) {
            addCriterion("PRICING_MANNER <=", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerLike(String value) {
            addCriterion("PRICING_MANNER like", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerNotLike(String value) {
            addCriterion("PRICING_MANNER not like", value, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerIn(List<String> values) {
            addCriterion("PRICING_MANNER in", values, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerNotIn(List<String> values) {
            addCriterion("PRICING_MANNER not in", values, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerBetween(String value1, String value2) {
            addCriterion("PRICING_MANNER between", value1, value2, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andPricingMannerNotBetween(String value1, String value2) {
            addCriterion("PRICING_MANNER not between", value1, value2, "pricingManner");
            return (Criteria) this;
        }

        public Criteria andSellStatusIsNull() {
            addCriterion("SELL_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSellStatusIsNotNull() {
            addCriterion("SELL_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSellStatusEqualTo(Integer value) {
            addCriterion("SELL_STATUS =", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotEqualTo(Integer value) {
            addCriterion("SELL_STATUS <>", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusGreaterThan(Integer value) {
            addCriterion("SELL_STATUS >", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("SELL_STATUS >=", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusLessThan(Integer value) {
            addCriterion("SELL_STATUS <", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusLessThanOrEqualTo(Integer value) {
            addCriterion("SELL_STATUS <=", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusIn(List<Integer> values) {
            addCriterion("SELL_STATUS in", values, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotIn(List<Integer> values) {
            addCriterion("SELL_STATUS not in", values, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusBetween(Integer value1, Integer value2) {
            addCriterion("SELL_STATUS between", value1, value2, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("SELL_STATUS not between", value1, value2, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellUnitIsNull() {
            addCriterion("SELL_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andSellUnitIsNotNull() {
            addCriterion("SELL_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andSellUnitEqualTo(String value) {
            addCriterion("SELL_UNIT =", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitNotEqualTo(String value) {
            addCriterion("SELL_UNIT <>", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitGreaterThan(String value) {
            addCriterion("SELL_UNIT >", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitGreaterThanOrEqualTo(String value) {
            addCriterion("SELL_UNIT >=", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitLessThan(String value) {
            addCriterion("SELL_UNIT <", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitLessThanOrEqualTo(String value) {
            addCriterion("SELL_UNIT <=", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitLike(String value) {
            addCriterion("SELL_UNIT like", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitNotLike(String value) {
            addCriterion("SELL_UNIT not like", value, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitIn(List<String> values) {
            addCriterion("SELL_UNIT in", values, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitNotIn(List<String> values) {
            addCriterion("SELL_UNIT not in", values, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitBetween(String value1, String value2) {
            addCriterion("SELL_UNIT between", value1, value2, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellUnitNotBetween(String value1, String value2) {
            addCriterion("SELL_UNIT not between", value1, value2, "sellUnit");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNull() {
            addCriterion("SELL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNotNull() {
            addCriterion("SELL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSellPriceEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE =", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE <>", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThan(BigDecimal value) {
            addCriterion("SELL_PRICE >", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE >=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThan(BigDecimal value) {
            addCriterion("SELL_PRICE <", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE <=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIn(List<BigDecimal> values) {
            addCriterion("SELL_PRICE in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("SELL_PRICE not in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SELL_PRICE between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SELL_PRICE not between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andIsAuditIsNull() {
            addCriterion("IS_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andIsAuditIsNotNull() {
            addCriterion("IS_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuditEqualTo(Integer value) {
            addCriterion("IS_AUDIT =", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotEqualTo(Integer value) {
            addCriterion("IS_AUDIT <>", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditGreaterThan(Integer value) {
            addCriterion("IS_AUDIT >", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_AUDIT >=", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditLessThan(Integer value) {
            addCriterion("IS_AUDIT <", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditLessThanOrEqualTo(Integer value) {
            addCriterion("IS_AUDIT <=", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditIn(List<Integer> values) {
            addCriterion("IS_AUDIT in", values, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotIn(List<Integer> values) {
            addCriterion("IS_AUDIT not in", values, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditBetween(Integer value1, Integer value2) {
            addCriterion("IS_AUDIT between", value1, value2, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_AUDIT not between", value1, value2, "isAudit");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNull() {
            addCriterion("CREATE_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNotNull() {
            addCriterion("CREATE_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME =", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME <>", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThan(Date value) {
            addCriterion("CREATE_DATE_TIME >", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME >=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThan(Date value) {
            addCriterion("CREATE_DATE_TIME <", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE_TIME <=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIn(List<Date> values) {
            addCriterion("CREATE_DATE_TIME in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_DATE_TIME not in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE_TIME between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE_TIME not between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIsNull() {
            addCriterion("CREATE_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIsNotNull() {
            addCriterion("CREATE_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeEqualTo(String value) {
            addCriterion("CREATE_USER_CODE =", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotEqualTo(String value) {
            addCriterion("CREATE_USER_CODE <>", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeGreaterThan(String value) {
            addCriterion("CREATE_USER_CODE >", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_CODE >=", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLessThan(String value) {
            addCriterion("CREATE_USER_CODE <", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_CODE <=", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeLike(String value) {
            addCriterion("CREATE_USER_CODE like", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotLike(String value) {
            addCriterion("CREATE_USER_CODE not like", value, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeIn(List<String> values) {
            addCriterion("CREATE_USER_CODE in", values, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotIn(List<String> values) {
            addCriterion("CREATE_USER_CODE not in", values, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeBetween(String value1, String value2) {
            addCriterion("CREATE_USER_CODE between", value1, value2, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCreateUserCodeNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER_CODE not between", value1, value2, "createUserCode");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserIsNull() {
            addCriterion("CAIZAI_USER is null");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserIsNotNull() {
            addCriterion("CAIZAI_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserEqualTo(String value) {
            addCriterion("CAIZAI_USER =", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserNotEqualTo(String value) {
            addCriterion("CAIZAI_USER <>", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserGreaterThan(String value) {
            addCriterion("CAIZAI_USER >", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserGreaterThanOrEqualTo(String value) {
            addCriterion("CAIZAI_USER >=", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserLessThan(String value) {
            addCriterion("CAIZAI_USER <", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserLessThanOrEqualTo(String value) {
            addCriterion("CAIZAI_USER <=", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserLike(String value) {
            addCriterion("CAIZAI_USER like", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserNotLike(String value) {
            addCriterion("CAIZAI_USER not like", value, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserIn(List<String> values) {
            addCriterion("CAIZAI_USER in", values, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserNotIn(List<String> values) {
            addCriterion("CAIZAI_USER not in", values, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserBetween(String value1, String value2) {
            addCriterion("CAIZAI_USER between", value1, value2, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andCaizaiUserNotBetween(String value1, String value2) {
            addCriterion("CAIZAI_USER not between", value1, value2, "caizaiUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserIsNull() {
            addCriterion("JIANCE_USER is null");
            return (Criteria) this;
        }

        public Criteria andJianceUserIsNotNull() {
            addCriterion("JIANCE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andJianceUserEqualTo(String value) {
            addCriterion("JIANCE_USER =", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserNotEqualTo(String value) {
            addCriterion("JIANCE_USER <>", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserGreaterThan(String value) {
            addCriterion("JIANCE_USER >", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserGreaterThanOrEqualTo(String value) {
            addCriterion("JIANCE_USER >=", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserLessThan(String value) {
            addCriterion("JIANCE_USER <", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserLessThanOrEqualTo(String value) {
            addCriterion("JIANCE_USER <=", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserLike(String value) {
            addCriterion("JIANCE_USER like", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserNotLike(String value) {
            addCriterion("JIANCE_USER not like", value, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserIn(List<String> values) {
            addCriterion("JIANCE_USER in", values, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserNotIn(List<String> values) {
            addCriterion("JIANCE_USER not in", values, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserBetween(String value1, String value2) {
            addCriterion("JIANCE_USER between", value1, value2, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andJianceUserNotBetween(String value1, String value2) {
            addCriterion("JIANCE_USER not between", value1, value2, "jianceUser");
            return (Criteria) this;
        }

        public Criteria andNongCanLvIsNull() {
            addCriterion("NONG_CAN_LV is null");
            return (Criteria) this;
        }

        public Criteria andNongCanLvIsNotNull() {
            addCriterion("NONG_CAN_LV is not null");
            return (Criteria) this;
        }

        public Criteria andNongCanLvEqualTo(String value) {
            addCriterion("NONG_CAN_LV =", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvNotEqualTo(String value) {
            addCriterion("NONG_CAN_LV <>", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvGreaterThan(String value) {
            addCriterion("NONG_CAN_LV >", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvGreaterThanOrEqualTo(String value) {
            addCriterion("NONG_CAN_LV >=", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvLessThan(String value) {
            addCriterion("NONG_CAN_LV <", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvLessThanOrEqualTo(String value) {
            addCriterion("NONG_CAN_LV <=", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvLike(String value) {
            addCriterion("NONG_CAN_LV like", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvNotLike(String value) {
            addCriterion("NONG_CAN_LV not like", value, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvIn(List<String> values) {
            addCriterion("NONG_CAN_LV in", values, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvNotIn(List<String> values) {
            addCriterion("NONG_CAN_LV not in", values, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvBetween(String value1, String value2) {
            addCriterion("NONG_CAN_LV between", value1, value2, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andNongCanLvNotBetween(String value1, String value2) {
            addCriterion("NONG_CAN_LV not between", value1, value2, "nongCanLv");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenIsNull() {
            addCriterion("YI_YANG_CHEN_FEN is null");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenIsNotNull() {
            addCriterion("YI_YANG_CHEN_FEN is not null");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenEqualTo(String value) {
            addCriterion("YI_YANG_CHEN_FEN =", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenNotEqualTo(String value) {
            addCriterion("YI_YANG_CHEN_FEN <>", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenGreaterThan(String value) {
            addCriterion("YI_YANG_CHEN_FEN >", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenGreaterThanOrEqualTo(String value) {
            addCriterion("YI_YANG_CHEN_FEN >=", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenLessThan(String value) {
            addCriterion("YI_YANG_CHEN_FEN <", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenLessThanOrEqualTo(String value) {
            addCriterion("YI_YANG_CHEN_FEN <=", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenLike(String value) {
            addCriterion("YI_YANG_CHEN_FEN like", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenNotLike(String value) {
            addCriterion("YI_YANG_CHEN_FEN not like", value, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenIn(List<String> values) {
            addCriterion("YI_YANG_CHEN_FEN in", values, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenNotIn(List<String> values) {
            addCriterion("YI_YANG_CHEN_FEN not in", values, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenBetween(String value1, String value2) {
            addCriterion("YI_YANG_CHEN_FEN between", value1, value2, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andYiYangChenFenNotBetween(String value1, String value2) {
            addCriterion("YI_YANG_CHEN_FEN not between", value1, value2, "yiYangChenFen");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiIsNull() {
            addCriterion("BAO_ZHI_QI is null");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiIsNotNull() {
            addCriterion("BAO_ZHI_QI is not null");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiEqualTo(Integer value) {
            addCriterion("BAO_ZHI_QI =", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiNotEqualTo(Integer value) {
            addCriterion("BAO_ZHI_QI <>", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiGreaterThan(Integer value) {
            addCriterion("BAO_ZHI_QI >", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiGreaterThanOrEqualTo(Integer value) {
            addCriterion("BAO_ZHI_QI >=", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiLessThan(Integer value) {
            addCriterion("BAO_ZHI_QI <", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiLessThanOrEqualTo(Integer value) {
            addCriterion("BAO_ZHI_QI <=", value, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiIn(List<Integer> values) {
            addCriterion("BAO_ZHI_QI in", values, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiNotIn(List<Integer> values) {
            addCriterion("BAO_ZHI_QI not in", values, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiBetween(Integer value1, Integer value2) {
            addCriterion("BAO_ZHI_QI between", value1, value2, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andBaoZhiQiNotBetween(Integer value1, Integer value2) {
            addCriterion("BAO_ZHI_QI not between", value1, value2, "baoZhiQi");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunIsNull() {
            addCriterion("SHI_YI_REN_QUN is null");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunIsNotNull() {
            addCriterion("SHI_YI_REN_QUN is not null");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunEqualTo(String value) {
            addCriterion("SHI_YI_REN_QUN =", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunNotEqualTo(String value) {
            addCriterion("SHI_YI_REN_QUN <>", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunGreaterThan(String value) {
            addCriterion("SHI_YI_REN_QUN >", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunGreaterThanOrEqualTo(String value) {
            addCriterion("SHI_YI_REN_QUN >=", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunLessThan(String value) {
            addCriterion("SHI_YI_REN_QUN <", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunLessThanOrEqualTo(String value) {
            addCriterion("SHI_YI_REN_QUN <=", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunLike(String value) {
            addCriterion("SHI_YI_REN_QUN like", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunNotLike(String value) {
            addCriterion("SHI_YI_REN_QUN not like", value, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunIn(List<String> values) {
            addCriterion("SHI_YI_REN_QUN in", values, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunNotIn(List<String> values) {
            addCriterion("SHI_YI_REN_QUN not in", values, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunBetween(String value1, String value2) {
            addCriterion("SHI_YI_REN_QUN between", value1, value2, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andShiYiRenQunNotBetween(String value1, String value2) {
            addCriterion("SHI_YI_REN_QUN not between", value1, value2, "shiYiRenQun");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouIsNull() {
            addCriterion("XIAN_SHI_QIANG_GOU is null");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouIsNotNull() {
            addCriterion("XIAN_SHI_QIANG_GOU is not null");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouEqualTo(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU =", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouNotEqualTo(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU <>", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouGreaterThan(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU >", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouGreaterThanOrEqualTo(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU >=", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouLessThan(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU <", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouLessThanOrEqualTo(Integer value) {
            addCriterion("XIAN_SHI_QIANG_GOU <=", value, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouIn(List<Integer> values) {
            addCriterion("XIAN_SHI_QIANG_GOU in", values, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouNotIn(List<Integer> values) {
            addCriterion("XIAN_SHI_QIANG_GOU not in", values, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouBetween(Integer value1, Integer value2) {
            addCriterion("XIAN_SHI_QIANG_GOU between", value1, value2, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXianShiQiangGouNotBetween(Integer value1, Integer value2) {
            addCriterion("XIAN_SHI_QIANG_GOU not between", value1, value2, "xianShiQiangGou");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianIsNull() {
            addCriterion("XIN_PIN_CHANG_XIAN is null");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianIsNotNull() {
            addCriterion("XIN_PIN_CHANG_XIAN is not null");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianEqualTo(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN =", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianNotEqualTo(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN <>", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianGreaterThan(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN >", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianGreaterThanOrEqualTo(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN >=", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianLessThan(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN <", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianLessThanOrEqualTo(Integer value) {
            addCriterion("XIN_PIN_CHANG_XIAN <=", value, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianIn(List<Integer> values) {
            addCriterion("XIN_PIN_CHANG_XIAN in", values, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianNotIn(List<Integer> values) {
            addCriterion("XIN_PIN_CHANG_XIAN not in", values, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianBetween(Integer value1, Integer value2) {
            addCriterion("XIN_PIN_CHANG_XIAN between", value1, value2, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andXinPinChangXianNotBetween(Integer value1, Integer value2) {
            addCriterion("XIN_PIN_CHANG_XIAN not between", value1, value2, "xinPinChangXian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianIsNull() {
            addCriterion("RE_MAI_TUI_JIAN is null");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianIsNotNull() {
            addCriterion("RE_MAI_TUI_JIAN is not null");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianEqualTo(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN =", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianNotEqualTo(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN <>", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianGreaterThan(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN >", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianGreaterThanOrEqualTo(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN >=", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianLessThan(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN <", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianLessThanOrEqualTo(Integer value) {
            addCriterion("RE_MAI_TUI_JIAN <=", value, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianIn(List<Integer> values) {
            addCriterion("RE_MAI_TUI_JIAN in", values, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianNotIn(List<Integer> values) {
            addCriterion("RE_MAI_TUI_JIAN not in", values, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianBetween(Integer value1, Integer value2) {
            addCriterion("RE_MAI_TUI_JIAN between", value1, value2, "reMaiTuiJian");
            return (Criteria) this;
        }

        public Criteria andReMaiTuiJianNotBetween(Integer value1, Integer value2) {
            addCriterion("RE_MAI_TUI_JIAN not between", value1, value2, "reMaiTuiJian");
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