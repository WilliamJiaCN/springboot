package com.architect.dao;

import com.architect.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jiawenxiong
 * @date 2018-8-25
 */
@Mapper
public interface UserDao {
    int insert(User user);

    int insertBatch(List<User> list);

    int insertSelective(User user);

    User getByPrimaryKey(User user);
}
