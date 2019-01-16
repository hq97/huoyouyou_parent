package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.client.RedisClient;
import cn.moyang.huoyouyou.util.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient {
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    @Override
    public void set(@RequestParam("key") String key, @RequestParam("value")String value) {
        RedisUtils.INSTANCE.set(key,value);
    }
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    @Override
    public String get(@RequestParam("key") String key) {
        return RedisUtils.INSTANCE.get(key);
    }
}
