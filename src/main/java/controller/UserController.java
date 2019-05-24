package controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;
    /*
    * 手机验证码验证
    * */
    @RequestMapping("codeJudge")
    public String codeJudge(@RequestParam("phone") String phone){
        //随机生成4位数的验证码
        int code = (int)(1000 + Math.random()*8999);
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn/");
        //在头文件中设置转码
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair[] data = {new NameValuePair("Uid","2410267884@qq.com"),
                new NameValuePair("Key","d41d8cd98f00b204e980"),
                new NameValuePair("smsMob",phone),
        new NameValuePair("smsText","（图像隐写在线服务平台-用户注册）您收到的手机验证码为"+code)};
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        //判断验证码是否成功发送，如果正确发送则将手机号和验证码保存在数据库中
        if(result.equals("1")){
            //验证码成功发送并返回状态码1
            User user = new User();
            user.setPhone(phone);
            user.setCode(code);
            System.out.println(user); //打印返回消息状态
            //判断本地数据库中是否存有该手机号信息
            if(userService.get(user)==null){
                //没有该手机号信息，则存入
                userService.add(user);
            }else {
                //存在该手机号信息，则修改验证码
                userService.update(user);
            }
        }else{
            //验证码发送失败
        }
        return result;
    }
    /*
    * 判断该手机号是否被注册
    * */
    @RequestMapping("isRegister")
    public String isRegister(@RequestParam("phone") String phone){
        User user = new User();
        user.setPhone(phone);
        if(userService.get(user)!=null){
            return "true";
        }else{
            return "false";
        }
    }
    /*
    * 用户账号注册
    * */
    @RequestMapping("userregister")
    public String userregister(@RequestParam("phone") String phone,
                              @RequestParam("code") int code,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name){
        User user = new User();
        user.setPhone(phone);
        user.setCode(code);
        user.setPassword(password);
        user.setName(name);
        //获取该手机号对应的验证码，与前端输入的验证码进行验证
        if(userService.get(user).getCode()==code){
            //输入的验证码正确
            userService.update(user);
            return "codeTrue";
        }else{
            //输入的验证码错误
            return "codeFalse";
        }
    }
    /*
    * 用户登录
    * */
    @RequestMapping("userlogin")
    public String userlogin(@RequestParam("phone") String phone,
                             @RequestParam("password") String password){
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        if(userService.loginCheck(user)!=null){
            return "true";
        }
        return "false";
    }
    /*
    * 用户列表
    * */
    @RequestMapping(value = "userList",produces = { "application/json;charset=UTF-8" })
    public String userList(){
        List<User> list = new ArrayList<>();
        list = userService.list();
        //使用fastjson 对 对象数组进行转换
        String jsonlist = JSONObject.toJSONString(list);
        jsonlist = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+jsonlist+"}";
        return jsonlist;
    }
    /*
    * 管理员查询用户信息
    * */
    @RequestMapping(value = "getUser",produces = { "application/json;charset=UTF-8" })
    public String getUser(@RequestParam("email") String email,
                        @RequestParam("name") String name,
                        @RequestParam("phone") String phone){
        String name1 = null;
        //解决乱码问题
        try {
            name1 = new String(name.getBytes("ISO8859_1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name1);
        user.setPhone(phone);
        System.out.println(email+""+name1+""+phone);
        List<User> list = new ArrayList<>();
        list = userService.userSearch(user);
        //使用fastjson 对 对象数组进行转换
        String jsonlist = JSONObject.toJSONString(list);
        jsonlist = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+jsonlist+"}";
        return jsonlist;
    }
    /*
    * 管理员添加用户
    * */
    @RequestMapping("subUser")
    public String subUser(@RequestParam("phone") String phone,
                          @RequestParam("password") String password,
                          @RequestParam("name") String name){
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        userService.add(user);
        return "true";
    }
    /*
    * 管理员删除用户
    * */
    @RequestMapping("deleteUser")
    public void deleteUser(@RequestParam("id") int id){
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }
    /*
    * 根据phone显示用户信息
    * */
    @RequestMapping(value = "showUser",produces = { "application/json;charset=UTF-8" })
    public String showUser(@RequestParam("phone") String phone){
        User user = new User();
        user.setPhone(phone);
        User u = userService.get(user);
        String json = JSONObject.toJSONString(u);
        return json;
    }
    /*
    * 更新用户列表
    * */
    @RequestMapping(value = "updateUser",produces = { "application/json;charset=UTF-8" })
    public void updateUser(@RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("city") String city,
                           @RequestParam("email") String email,
                           @RequestParam("company") String company,
                           @RequestParam("career") String career){
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setCity(city);
        user.setEmail(email);
        user.setCompany(company);
        user.setCareer(career);
        userService.userUpdate(user);
    }
}
