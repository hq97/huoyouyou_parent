package cn.moyang.huoyouyou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "HUOYOUYOU-COMMON",fallbackFactory = PageClientFallbackFactory.class)
public interface PageClient {
    /**
     * 根据特定模板传入特定数据,生成静态页面到特定位置
     *  params
     *          key:
     *              model
     *              tmeplatePath
     *              staticPagePath
     * Map<String,Object>
     *      model ==数据
     *      tmeplatePath==xxx
     *      staticPagePath = xxx
     */

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    void genStaticPage(@RequestBody Map<String,Object> params);
}
