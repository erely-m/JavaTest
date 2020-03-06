package com.erely.netflix.archaius;

import org.junit.Test;

public class ApplicationConfigTest {

    private ApplicationConfig apc = new ApplicationConfig();

    @Test
    public  void getValue() {
        String property = apc.getStringProperty("driver","driver driver");
//        assert(property.equals("com.mysql.cj.jdbc.Driver"));
        System.out.println(property);
    }

    @Test
    public void getDoubleFileValue(){
        String property = apc.getStringProperty("eureka.name","not found");
        System.out.println(property);
    }


}
