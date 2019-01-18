package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.util.AjaxResult;
import cn.moyang.huoyouyou.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * 文件上传下载删除的controller
 * 暂时上传和下载只给外部页面之间调用,所有不用写feign
 */
@RestController
public class FastDfsController {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AjaxResult upload(@RequestParam("file") MultipartFile file) {
        try {
            //System.out.println(file.getOriginalFilename());//获得上传文件的完整路径名称
            String originalFilename = file.getOriginalFilename();
            System.out.println(file.getName());
            System.out.println(file.getSize());
            System.out.println(file.getContentType());//文件类型
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            System.out.println(extName);//文件的后缀名称
            System.out.println(file.getBytes());
            String filePath = FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(false).setMessage("上传失败");
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/filePath", method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestParam(value = "filePath") String filePath) {
        //System.out.println(filePath);//   /group1/M00/00/01/wKgrilxAVxuAfAAUAArlDOnW3jc020.jpg
        String pathTmp = filePath.substring(1);//   去掉路径前的 /
       // System.out.println(pathTmp);//    group1/M00/00/01/wKgrilxAVxuAfAAUAArlDOnW3jc020.jpg
        String groupName = pathTmp.substring(0, pathTmp.indexOf("/"));
        //System.out.println(groupName);  //   group1
        String remotePath = pathTmp.substring(pathTmp.indexOf("/")+1);
       // System.out.println(remotePath);  //   M00/00/01/wKgrilxAVxuAfAAUAArlDOnW3jc020.jpg
        FastDfsApiOpr.delete(groupName, remotePath);
        return AjaxResult.me();

    }


}
