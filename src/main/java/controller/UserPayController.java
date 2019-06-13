package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mathworks.toolbox.javabuilder.MWException;
import embedding.Class1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import pojo.User_pay;
import service.UserPayService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class UserPayController {
    @Autowired
    UserPayService userPayService;
    /*
     * 获取所有订单信息
     * */
    @RequestMapping(value = "payList",produces = { "application/json;charset=UTF-8" })
    public String payList(){
        List<User_pay> list = new ArrayList<>();
        list = userPayService.list();
        //使用fastjson 对 对象数组进行转换
        String jsonlist = JSONObject.toJSONString(list);
        jsonlist = "{\"code\":0,\"msg\":\"\",\"count\":"+list.size()+",\"data\":"+jsonlist+"}";
        return jsonlist;
    }
    /*
    * 添加原始图片及藏入信息到数据库中
    * */
    @RequestMapping("addInfo")
    public JSONObject addInfo(@RequestParam("orginalImage") String orginalImage,
                          @RequestParam("phone") String phone,
                          @RequestParam("inputInfo") String inputInfo){
        //分割路径
        String[] split = orginalImage.split("\\\\");
        String[] imageName = split[10].split("\\.");
        User_pay user_pay = new User_pay();
        user_pay.setOrginalImage("/file/"+imageName[0]+".bmp");
        user_pay.setPhone(phone);
        user_pay.setInputInfo(inputInfo);
        /*
        * 生成藏入信息后的图片
        * */
        try {
            callMatlab(orginalImage,inputInfo);
        } catch (MWException e) {
            e.printStackTrace();
        }
        String resultImage1 = "http://localhost:8080/VueStudy_war/images/resultImage1/"+ imageName[0]+"1.bmp";
        String resultImage2 = "http://localhost:8080/VueStudy_war/images/resultImage2/"+ imageName[0]+"2.bmp";
        user_pay.setResultImage1(resultImage1);
        user_pay.setResultImage2(resultImage2);
        //不存在该订单，则添加订单信息
        userPayService.add(user_pay);
        Map<String,String> map = new HashMap<>();
        map.put("result1",resultImage1);
        map.put("result2",resultImage2);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        return json;
    }
    /*
    * 调用matlab图像隐写算法
    * @param image:图像url地址 inputInfo:需要藏入的信息(暂时只支持中文)
    * */
    public void callMatlab(String image,String inputInfo) throws MWException {
        //将中文字符转换成bit（中文使用）
        byte[] b = null;
        try {
            b = inputInfo.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("转换前:"+inputInfo);
        System.out.print("转换后:");
        //定义一个一维数组存放转换后的数据
        int[] bit = new int[b.length*8];
        int t = 0;
        for (int i = 0;i < b.length;i++){
            System.out.print(Integer.toBinaryString(b[i] & 0xff));
            for(int j = 0;j < 8;j++,t++){
                if(j%8==0)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,7);
                if(j%8==1)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,6)%10;
                if(j%8==2)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,5)%10;
                if(j%8==3)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,4)%10;
                if(j%8==4)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,3)%10;
                if(j%8==5)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,2)%10;
                if(j%8==6)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,1)%10;
                if(j%8==7)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))%10;
            }
        }
        //调用matlab隐写算法
        Class1 class1 = new Class1();
        class1.start(image,bit);
    }
    /*
    * 将订单金额存放在数据库中
    * */
    @RequestMapping("setMoney")
    public JSONObject setMoney(@RequestParam("phone") String phone,
                         @RequestParam("orginalImage") String orginalImage,
                         @RequestParam("money") Float money,
                         @RequestParam("time") String time){
        System.out.println(phone+" "+orginalImage+" "+money);
        //查询到该订单
        User_pay user_pay = new User_pay();
        user_pay.setPhone(phone);
        //分割路径
        String[] split = orginalImage.split("\\\\");
        String[] imageName = split[10].split("\\.");
        user_pay.setOrginalImage("/file/"+imageName[0]+".bmp");
        User_pay user_pay1 = userPayService.get(user_pay);

        user_pay1.setMoney(money);
        user_pay1.setPay_time(time);
        userPayService.update(user_pay1);

        Map<String,String> map = new HashMap<>();
        map.put("image1",user_pay1.getResultImage1());
        map.put("image2",user_pay1.getResultImage2());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        return json;
    }
}
