package com.architect.mapper;

import com.architect.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wenxiong.jia
 * @since 2018/7/20
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键查询
     * @param id 主键
     * @return 对象
     */
    @Select("SELECT * FROM T_USER WHERE ID = #{id}")
    User queryByPrimaryKey(@Param("id") Long id);

    /**
     * 新增用户
     * @param user 用户
     * @return 数量
     */
    @Insert("INSERT INTO T_USER(NAME, AGE, ADDRESS, PHONE) VALUES(#{name}, #{age}, #{address}, #{phone})")
    int insert(User user);
}
