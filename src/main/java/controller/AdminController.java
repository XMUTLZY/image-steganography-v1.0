package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Admin;
import service.AdminService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("")
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    /*
    * 管理员登陆
    * */
    @RequestMapping("adminlogin")
    public String adminlogin(@RequestParam("phone") String phone,
                             @RequestParam("password") String password){
        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setPassword(password);
        //判断是否查询得到该管理员
        if(adminService.loginCheck(admin)!=null){
            return "true";
        }else{
            return "false";
        }
    }
    /*
    * 获取手机号对应的姓名
    * */
    @RequestMapping(value = "getName",produces = { "application/json;charset=UTF-8" })
    public String getName(@RequestParam("phone") String phone){
        Admin admin = new Admin();
        admin.setPhone(phone);
        String name = adminService.get(admin).getName();
        return name;
    }
    /*
    * 获取所有管理员
    * */
    @RequestMapping(value = "adminList",produces = { "application/json;charset=UTF-8" })
    public String adminList(){
        List<Admin> list = new ArrayList<>();
        list = adminService.list();
        //使用fastjson 对 对象数组进行转换
        String jsonlist = JSONObject.toJSONString(list);
        jsonlist = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+jsonlist+"}";
        return jsonlist;
    }
}
