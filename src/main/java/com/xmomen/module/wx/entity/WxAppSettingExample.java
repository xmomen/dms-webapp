package com.xmomen.module.wx.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxAppSettingExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxAppSettingExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPublicUidIsNull() {
            addCriterion("public_uid is null");
            return (Criteria) this;
        }

        public Criteria andPublicUidIsNotNull() {
            addCriterion("public_uid is not null");
            return (Criteria) this;
        }

        public Criteria andPublicUidEqualTo(String value) {
            addCriterion("public_uid =", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidNotEqualTo(String value) {
            addCriterion("public_uid <>", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidGreaterThan(String value) {
            addCriterion("public_uid >", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidGreaterThanOrEqualTo(String value) {
            addCriterion("public_uid >=", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidLessThan(String value) {
            addCriterion("public_uid <", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidLessThanOrEqualTo(String value) {
            addCriterion("public_uid <=", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidLike(String value) {
            addCriterion("public_uid like", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidNotLike(String value) {
            addCriterion("public_uid not like", value, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidIn(List<String> values) {
            addCriterion("public_uid in", values, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidNotIn(List<String> values) {
            addCriterion("public_uid not in", values, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidBetween(String value1, String value2) {
            addCriterion("public_uid between", value1, value2, "publicUid");
            return (Criteria) this;
        }

        public Criteria andPublicUidNotBetween(String value1, String value2) {
            addCriterion("public_uid not between", value1, value2, "publicUid");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountIsNull() {
            addCriterion("is_service_account is null");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountIsNotNull() {
            addCriterion("is_service_account is not null");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountEqualTo(Integer value) {
            addCriterion("is_service_account =", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountNotEqualTo(Integer value) {
            addCriterion("is_service_account <>", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountGreaterThan(Integer value) {
            addCriterion("is_service_account >", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_service_account >=", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountLessThan(Integer value) {
            addCriterion("is_service_account <", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountLessThanOrEqualTo(Integer value) {
            addCriterion("is_service_account <=", value, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountIn(List<Integer> values) {
            addCriterion("is_service_account in", values, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountNotIn(List<Integer> values) {
            addCriterion("is_service_account not in", values, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountBetween(Integer value1, Integer value2) {
            addCriterion("is_service_account between", value1, value2, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andIsServiceAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("is_service_account not between", value1, value2, "isServiceAccount");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNull() {
            addCriterion("application_name is null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNotNull() {
            addCriterion("application_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameEqualTo(String value) {
            addCriterion("application_name =", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotEqualTo(String value) {
            addCriterion("application_name <>", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThan(String value) {
            addCriterion("application_name >", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanOrEqualTo(String value) {
            addCriterion("application_name >=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThan(String value) {
            addCriterion("application_name <", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanOrEqualTo(String value) {
            addCriterion("application_name <=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLike(String value) {
            addCriterion("application_name like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotLike(String value) {
            addCriterion("application_name not like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIn(List<String> values) {
            addCriterion("application_name in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotIn(List<String> values) {
            addCriterion("application_name not in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameBetween(String value1, String value2) {
            addCriterion("application_name between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotBetween(String value1, String value2) {
            addCriterion("application_name not between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameIsNull() {
            addCriterion("custom_assembly_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameIsNotNull() {
            addCriterion("custom_assembly_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameEqualTo(String value) {
            addCriterion("custom_assembly_name =", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameNotEqualTo(String value) {
            addCriterion("custom_assembly_name <>", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameGreaterThan(String value) {
            addCriterion("custom_assembly_name >", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameGreaterThanOrEqualTo(String value) {
            addCriterion("custom_assembly_name >=", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameLessThan(String value) {
            addCriterion("custom_assembly_name <", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameLessThanOrEqualTo(String value) {
            addCriterion("custom_assembly_name <=", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameLike(String value) {
            addCriterion("custom_assembly_name like", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameNotLike(String value) {
            addCriterion("custom_assembly_name not like", value, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameIn(List<String> values) {
            addCriterion("custom_assembly_name in", values, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameNotIn(List<String> values) {
            addCriterion("custom_assembly_name not in", values, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameBetween(String value1, String value2) {
            addCriterion("custom_assembly_name between", value1, value2, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomAssemblyNameNotBetween(String value1, String value2) {
            addCriterion("custom_assembly_name not between", value1, value2, "customAssemblyName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameIsNull() {
            addCriterion("custom_class_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameIsNotNull() {
            addCriterion("custom_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameEqualTo(String value) {
            addCriterion("custom_class_name =", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameNotEqualTo(String value) {
            addCriterion("custom_class_name <>", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameGreaterThan(String value) {
            addCriterion("custom_class_name >", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("custom_class_name >=", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameLessThan(String value) {
            addCriterion("custom_class_name <", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameLessThanOrEqualTo(String value) {
            addCriterion("custom_class_name <=", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameLike(String value) {
            addCriterion("custom_class_name like", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameNotLike(String value) {
            addCriterion("custom_class_name not like", value, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameIn(List<String> values) {
            addCriterion("custom_class_name in", values, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameNotIn(List<String> values) {
            addCriterion("custom_class_name not in", values, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameBetween(String value1, String value2) {
            addCriterion("custom_class_name between", value1, value2, "customClassName");
            return (Criteria) this;
        }

        public Criteria andCustomClassNameNotBetween(String value1, String value2) {
            addCriterion("custom_class_name not between", value1, value2, "customClassName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNull() {
            addCriterion("record_status is null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNotNull() {
            addCriterion("record_status is not null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusEqualTo(Integer value) {
            addCriterion("record_status =", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotEqualTo(Integer value) {
            addCriterion("record_status <>", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThan(Integer value) {
            addCriterion("record_status >", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_status >=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThan(Integer value) {
            addCriterion("record_status <", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThanOrEqualTo(Integer value) {
            addCriterion("record_status <=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIn(List<Integer> values) {
            addCriterion("record_status in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotIn(List<Integer> values) {
            addCriterion("record_status not in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusBetween(Integer value1, Integer value2) {
            addCriterion("record_status between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("record_status not between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("access_token is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("access_token is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("access_token =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("access_token <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("access_token >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("access_token >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("access_token <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("access_token <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("access_token like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("access_token not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("access_token in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("access_token not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("access_token between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("access_token not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andExpiresInIsNull() {
            addCriterion("expires_in is null");
            return (Criteria) this;
        }

        public Criteria andExpiresInIsNotNull() {
            addCriterion("expires_in is not null");
            return (Criteria) this;
        }

        public Criteria andExpiresInEqualTo(Integer value) {
            addCriterion("expires_in =", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotEqualTo(Integer value) {
            addCriterion("expires_in <>", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInGreaterThan(Integer value) {
            addCriterion("expires_in >", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInGreaterThanOrEqualTo(Integer value) {
            addCriterion("expires_in >=", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInLessThan(Integer value) {
            addCriterion("expires_in <", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInLessThanOrEqualTo(Integer value) {
            addCriterion("expires_in <=", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInIn(List<Integer> values) {
            addCriterion("expires_in in", values, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotIn(List<Integer> values) {
            addCriterion("expires_in not in", values, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInBetween(Integer value1, Integer value2) {
            addCriterion("expires_in between", value1, value2, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotBetween(Integer value1, Integer value2) {
            addCriterion("expires_in not between", value1, value2, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeIsNull() {
            addCriterion("last_get_time is null");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeIsNotNull() {
            addCriterion("last_get_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeEqualTo(Date value) {
            addCriterion("last_get_time =", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeNotEqualTo(Date value) {
            addCriterion("last_get_time <>", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeGreaterThan(Date value) {
            addCriterion("last_get_time >", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_get_time >=", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeLessThan(Date value) {
            addCriterion("last_get_time <", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_get_time <=", value, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeIn(List<Date> values) {
            addCriterion("last_get_time in", values, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeNotIn(List<Date> values) {
            addCriterion("last_get_time not in", values, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeBetween(Date value1, Date value2) {
            addCriterion("last_get_time between", value1, value2, "lastGetTime");
            return (Criteria) this;
        }

        public Criteria andLastGetTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_get_time not between", value1, value2, "lastGetTime");
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