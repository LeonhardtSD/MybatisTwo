package com.gaozhaoxi.mapper;

import com.gaozhaoxi.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author Leon
 */
public interface UserMapper {
    public User getUser(Long id);

    public int insertUser(User user);

    public int deleteUser(Long id);

}
