package com.erely.mybatis.type;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestType {

    public static void main(String[] args) throws Exception {
        Field f = ClassA.class.getDeclaredField("map");

        System.out.println(f.getGenericType());  //所属字段声明类型
        System.out.println(f.getGenericType() instanceof ParameterizedType); //声明类型属于ParameterizedType

        ParameterizedType p1 = (ParameterizedType) f.getGenericType();
        System.out.println(p1.getOwnerType());
        System.out.println(p1.getRawType());


        Type type = TypeParameterResolver.resolveFieldType(f, ParameterizedTypeImpl
                .make(SubClassA.class, new Type[]{Long.class}, TestType.class));

        System.out.println(type.getClass());
        ParameterizedType p = (ParameterizedType) type;
        System.out.println(p.getRawType()); //获取
        System.out.println(p.getOwnerType());

        for(Type t : p.getActualTypeArguments()){
            System.out.println(t);
        }

      Method th = TestType.class.getMethod("tttt",String.class,String.class);
        Annotation[][] A = th.getParameterAnnotations();
        System.out.println(A.length);
    }

    public void tttt(@Param("d")
                             @Logger
                             String ss,@Param("ss")String s1){

    }
}
