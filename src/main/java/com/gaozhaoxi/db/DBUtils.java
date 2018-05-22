package com.gaozhaoxi.db;

import com.gaozhaoxi.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Leon
 */
public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory=null;
    private static final Class Class_LOCK=DBUtils.class;

    public static SqlSessionFactory initSqlSessionFactory(){
//        synchronized (Class_LOCK){
//            if(sqlSessionFactory==null){
//                PooledDataSource dataSource=new PooledDataSource();
//                dataSource.setDriver("com.mysql.jdbc.Driver");
//                dataSource.setUrl("jdbc:mysql://localhost:3306/mybatisone");
//                dataSource.setUsername("root");
//                dataSource.setPassword("FREEDOM");
//
//                //事务管理
//                TransactionFactory transactionFactory=new JdbcTransactionFactory();
//                Environment environment=new Environment("development",transactionFactory,dataSource);
//                Configuration configuration=new Configuration(environment);
//                configuration.addMapper(UserMapper.class);
//
//                //通过SqlSessionFactoryBuilder来builder一个sqlSessionFactory（数据库连接）
//                sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);
//
//            }
//        }

        //这段代码可以参考mybatis的参考文档
        InputStream is=null;
        String resource = "mybatis-config.xml";
        try{
            is= Resources.getResourceAsStream(resource);
            } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (Class_LOCK){
            if(sqlSessionFactory==null){
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
            }
        }
        return sqlSessionFactory;
    }


    public static SqlSession openSqlSession(){
        if(sqlSessionFactory==null)
            initSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}
