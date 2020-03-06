package com.erely.mybatis;

import java.lang.reflect.ReflectPermission;

public class SecurityTest {
    public static void main(String[] args) {

        SecurityManager securityManager = System.getSecurityManager();
        if (null != securityManager) {
            securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
        }
    }
}
