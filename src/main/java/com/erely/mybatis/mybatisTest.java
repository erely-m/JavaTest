package com.erely.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class mybatisTest {

    public static void main(String[] args) throws IOException {

//        InputStream inputStream = Resources.getResourceAsStream("classpath:mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileReader("E:\\QHD\\JavaTest\\src\\main\\resources\\mybatis-config.xml"));
        System.out.println(sqlSessionFactory.getConfiguration().getVariables().get("aaa"));
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestDao td = session.getMapper(TestDao.class);
            Test t = td.find("ttt");
            System.out.println(t.getValue());
        }

    }
}
