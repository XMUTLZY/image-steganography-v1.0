package service.impl;

import mapper.User_payMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User_pay;
import service.UserPayService;

import java.util.List;

@Service
public class UserPayServiceImpl implements UserPayService {
    @Autowired
    User_payMapper user_payMapper;

    @Override
    public void add(User_pay user_pay) {
        user_payMapper.add(user_pay);
    }

    @Override
    public void update(User_pay user_pay) {
       user_payMapper.update(user_pay);
    }

    @Override
    public User_pay get(User_pay user_pay) {
        return user_payMapper.get(user_pay);
    }

    @Override
    public List<User_pay> list() {
        return user_payMapper.list();
    }

    @Override
    public void delete(User_pay user_pay) {
        user_payMapper.delete(user_pay);
    }
}
