package mapper;

import pojo.User_pay;

import java.util.List;

public interface User_payMapper {
    //添加订单信息
    void add(User_pay user_pay);
    //修改订单信息
    void update(User_pay user_pay);
    //查看订单
    User_pay get(User_pay user_pay);
    //查询所有订单
    List<User_pay> list();
    //删除订单
    void delete(User_pay user_pay);
}
