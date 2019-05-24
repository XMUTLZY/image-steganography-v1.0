package service.impl;

import mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Admin;
import service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public void update(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    public Admin get(Admin admin) {
        return adminMapper.get(admin);
    }

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public Admin loginCheck(Admin admin) {
        return adminMapper.loginCheck(admin);
    }
}
