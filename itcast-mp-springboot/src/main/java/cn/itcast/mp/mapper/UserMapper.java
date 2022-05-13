package cn.itcast.mp.mapper;

import cn.itcast.mp.pojo.User;


import java.util.List;

public interface UserMapper extends MyBaseMapper<User> {
    User findById(Long id);
}
