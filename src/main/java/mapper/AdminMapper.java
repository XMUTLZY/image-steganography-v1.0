package mapper;

import pojo.Admin;

import java.util.List;

public interface AdminMapper {
    //修改管理员信息
    void update(Admin admin);
    //查看管理员
    Admin get(Admin admin);
    //查询所有管理员
    List<Admin> list();
    //登录验证
    Admin loginCheck(Admin admin);
}
