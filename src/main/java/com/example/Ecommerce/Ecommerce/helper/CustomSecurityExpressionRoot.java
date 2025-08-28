package com.example.Ecommerce.Ecommerce.helper;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final CheckCurrentUserRole roleChecker;
    private Object filterObject;
    private Object returnObject;

    public CustomSecurityExpressionRoot(Authentication authentication, CheckCurrentUserRole roleChecker) {
        super(authentication);
        this.roleChecker = roleChecker;
    }

    public boolean isUser() {
        return roleChecker.isUser();
    }

    public boolean isAdmin() {
        return roleChecker.isAdmin();
    }

    public boolean isModerator() {
        return roleChecker.isModerator();
    }

    public boolean isSuperAdmin() {
        return roleChecker.isSuperAdmin();
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}

