package service.impl;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User get(User user) {
        return userMapper.get(user);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User loginCheck(User user) {
        return userMapper.loginCheck(user);
    }

    @Override
    public List<User> userSearch(User user) {
        return userMapper.userSearch(user);
    }

    @Override
    public void userUpdate(User user) {
        userMapper.userUpdate(user);
    }
}