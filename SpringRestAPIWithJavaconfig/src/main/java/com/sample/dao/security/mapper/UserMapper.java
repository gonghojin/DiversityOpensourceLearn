package com.sample.dao.security.mapper;

import com.sample.domain.security.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public User readUser(String username);
    public List<String> readAuthority(String username);
}
