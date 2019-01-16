package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.client.PageClient;
import cn.moyang.huoyouyou.util.VelocityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PageController implements PageClient {

    @Override
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public void genStaticPage(@RequestBody Map<String, Object> params) {
        Object model = params.get("model");
        String   templatePath = (String) params.get("templatePath");//获取模板的路径
        String   staticPagePath = (String) params.get("staticPagePath");//获取模板生成的静态页面的位置
        VelocityUtils.staticByTemplate(model,templatePath,staticPagePath);
    }
}
