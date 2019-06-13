package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092900620870";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmP0PeKnpdukFWkTlVWAKzfdn7f71Mj+K6hlbMXrGRygy+aef/tztaCSoQyAbLx73ux1++vAHZOJ3vEVbop31zW1yclV0i/lBh647aDHYskK7mr0kqY9bu7J+qOhgaBFL2VEu9fvkKXAd1F8PEpRpp9EbkMp2Rk567HNuEcW31ZSLdVf+h2L+7ErgWo0E9RaPFGf1gLvLn2/APwQgQilyQeZPaFuz4HXg2Tf1OWX090AWUaaCemuaVHnTPsfEyaGuEz4vZyZYB8Odky8rPXc8SLuSd4am8YdLy/wszhqr9gHZ6SVFYGs6S0927nYK78TDDTuIypoHKVjbChohw+AqlAgMBAAECggEAaF/sAj31GYdooisHLffVqk9nXiky9jCYDrdtI16IaZnLKnlfeQLXNo3uZR7QdXxwWMu/5wYuuux666+dOvB9KMHBgWWdVe0JFpFH3MpaH1yvEpIWg5CvTs4s6DkVDLCOTa1a6Bwhy0lAPVmwRXPLA56kK31DxDREQgG6KIr2mEvlscT0QednV0L60XJyfBUvTtRO5crRVoVGgntpcLsiL7z6lzJjOWdHdJ7Jv0o0lQCZjzJcqPqDZjOPP/4Zq+88lgYGNv0cvEEwu3135MdJfrNfXyFNuQWnZ+WKO5juF6czbZAjOwDwvme+w6SXQGGIABHT5yNqCCHTej7N7WLWeQKBgQDXTiMes9i6JNEq6VNBzXRcU2RWk1u9Otps0s+zBne5O/cinWAkQwctq3V96VvzYD3L61vYTqV+q7NFIK6VfQsrUc/8nduPG37LtHdw5BNy7FgVaho2WAZdqa0f9TL87e7umguGuYoWG1UouhSf/JEo06gMqy5Jb26VgiYseF3CAwKBgQDFq2EUxsFizzhq3h09wCE+qLMjwc1Hj6w7ISgooKw/SpwWGZR2VS6TVmHlev2jjcayKhfmYUKdjR6M2dKhsMEoBq7mOGM4s/aDY31bPJBj5nJQtgVdQMXSjZ3PnKD+R8e3KA4z3y7twP7hVWILRRzojl6Zi6elv6kg7wgSVE50NwKBgBMjzRdzoXaaw0VvTIly/D8g4rCXlPYzfythajld/9KltAiNuPhARSkYLu+siRIPoi64k4edJjUmil9A0oRTg2SsdzLbzaLhLNprBpswi1ISFJqCviXKmXZGGS/YGlpFxJHNe+zUHaRtdZqTQDYgu77HYg8OQKUilNARq9cUNDGFAoGAV5U96KTAHvXN+5v6vNeaY1cAOMGwIRsJpxFj58x5RXnbevdOjrfjcSj/K1y5cXRqK5r3B/7kM0bJ2ILTDw1wKDn92Mf3PrKOcVztuDU3jOwYjqZ37f+7JzXUTJ8Lvl8zoHXBBT7IXlP7GwBn9rJiwrACPmpgl9I6L/t5ShXMxTUCgYBosmVzqVGSb0cS5jAbjbqB9Y3gidTXXoUegGPJFid2OQR1vF4P4Gl7mUYxwK0QEtdzAzZ9mvr9PAtz4QvCIdB5Qh7KXBwvEfKjPuSg+0nf9ZF0+zWFhGCAHYSGtejAXcPrHqByU5SrWIv7/Xbffg+OTXyLKzU5p5WeYyQsxSgqGw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmnCD2WgmT8C2o5GHTNzMHPEPVRuwgpHdJxvB3ActwNcnxmiiafHmxNmmxAH8tLlZp2H6KKhdSJK6vzLBRmAQSrf3ny04NJaGeTutpmXA6YMIJCT7I3Ooc55X1pz10ZO+hILEAEBm9teFSZnM+dfri9CqEfd4NCSKWUfeWRGkhYMXWiIQyjCfH+Ib3hJxLfg+pvMb2QX1hhIw1oiJ7CF0oHwUHGJYVxQeM8Xu039Hrh+lhGwmSnCDv9EyVVWAh0KKXUSM2MrKROJayPEnmXjSa3AZ2GcblkRq49GBby1nBr+9p2iXZC2+CsEOng3pnTEvOk/z3ArHgsYAfDfUwjk3ZwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/VueStudy_war/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/VueStudy_war/userIndex.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

