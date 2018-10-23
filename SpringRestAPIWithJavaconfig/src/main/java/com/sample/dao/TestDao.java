package com.sample.dao;

import com.sample.dto.TestDto;
import org.apache.ibatis.annotations.Select;

public interface TestDao {
    @Select("select * from test_tb where id ='1'")
    TestDto test();
}


