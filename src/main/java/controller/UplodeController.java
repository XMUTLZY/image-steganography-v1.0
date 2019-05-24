package controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("")
public class UplodeController {
    /*
    * 用户上传图片获取路径到前台
    * */
    @RequestMapping(value = "imageUrl", method = {RequestMethod.POST})
    public Map<String,Object> imageUrl(MultipartFile test1, HttpServletRequest request, HttpServletResponse response) {
        //目标文件夹（服务器）
        //String url = request.getSession().getServletContext().getRealPath("/images");
        //目标文件夹（本地）
        String url = "F:\\study\\jee\\idea_WorkSpace\\VueStudy\\src\\main\\webapp\\images";
        //原始图片名称
        String originalName = test1.getOriginalFilename();
        /**
         * 为了处理出现重名现象, 将原始文件名去掉,
         * 通过UUID算法生成新的文件名
         */
        String uuidName = UUID.randomUUID().toString();
        //uuid名称加上文件的后缀名
        String newFile = uuidName + originalName.substring(originalName.lastIndexOf("."));
        //创建File文件
        File file = new File(url + "/" + newFile);
        //将文件写入指定目录
        try {
            test1.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.toString());
        Map<String,Object> map = new HashMap<>();
        map.put("url",file);
        return map;
    }
}
