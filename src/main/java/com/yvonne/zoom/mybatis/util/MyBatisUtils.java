package com.yvonne.zoom.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yvonne Wang
 * @date 2021/3/2923:25
 */
public class MyBatisUtils {
    /**
     * 获得SqlSessionFactory对象
     * @param config
     * @return
     */
    private static SqlSessionFactory getSqlSessionFactory(String config) {
        try {
            InputStream is = Resources.getResourceAsStream(config);
            return new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得SqlSession对象
     * @return
     */
    public static SqlSession getSqlSession() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("SqlMapConfig.xml");
        return sqlSessionFactory.openSession();
    }

    /**
     * 获得SqlSession对象(isAutoCommit判断是否自动提交)
     * @param isAutoCommit
     * @return
     */
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("SqlMapConfig.xml");
        return sqlSessionFactory.openSession(isAutoCommit);
    }

}
