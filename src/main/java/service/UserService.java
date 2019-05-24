package service;

import pojo.User;

import java.util.List;

public interface UserService {
    //增加
    int add(User user);
    //删除
    void delete(User user);
    //修改用户
    void update(User user);
    //通过id查询
    User get(User user);
    //查询所有用户
    List<User> list();
    //登录验证
    User loginCheck(User user);
    //用户查询
    List<User> userSearch(User user);
    //修改用户
    void userUpdate(User user);
}
