package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.User_pay;
import service.UserPayService;

@RestController
@RequestMapping("")
public class UserPayController {
    @Autowired
    UserPayService userPayService;
    /*
    * 添加原始图片及藏入信息到数据库中
    * */
    @RequestMapping("addInfo")
    public String addInfo(@RequestParam("orginalImage") String orginalImage,
                          @RequestParam("phone") String phone,
                          @RequestParam("inputInfo") String inputInfo){
        System.out.println("我可以到这里");
        User_pay user_pay = new User_pay();
        user_pay.setOrginalImage(orginalImage);
        user_pay.setPhone(phone);
        user_pay.setInputInfo(inputInfo);
        System.out.println(user_pay);
        //查看是否存在该订单
        if(userPayService.get(user_pay)!=null){
            //存在该订单，则修改订单信息
            userPayService.update(user_pay);
        }else{
            //不存在该订单，则添加订单信息
            userPayService.add(user_pay);
        }
        return "true";
    }
}
