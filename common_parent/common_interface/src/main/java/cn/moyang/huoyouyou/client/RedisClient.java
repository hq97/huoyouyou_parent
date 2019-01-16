package cn.moyang.huoyouyou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "HUOYOUYOU-COMMON",fallbackFactory = RedisClientFallbackFactory.class)
public interface RedisClient {
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    void set(@RequestParam("key") String key, @RequestParam("value")String value);
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    String get(@RequestParam("key") String key);
}
