package com.sample;

import com.sample.config.ApplicationContextConfig;
import com.sample.dao.TestDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.sql.SQLException;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
        classes = {ApplicationContextConfig.class }
        ,loader = AnnotationConfigContextLoader.class
)
public class SpringTest {

    @Autowired
    DriverManagerDataSource dataSource;

   /* @Autowired
    private SqlSession sqlSession;
*/
    @Test
    public void dummy() {
    /*    sqlSession.getMapper(TestDao.class);
        // 테스트를 위해 추가
        // ********************************
        TestDao testDao = sqlSession.getMapper(TestDao.class);
        System.out.println(testDao.test());
        // ********************************
*/
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
