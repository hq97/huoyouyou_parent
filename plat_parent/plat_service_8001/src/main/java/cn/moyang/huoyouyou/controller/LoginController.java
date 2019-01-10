package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.domain.Employee;
import cn.moyang.huoyouyou.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody Employee employee){//  //以后传递对象前太都要用json对象{},[],后台通过(@RequestBody 接受数据，使用 @RequestParam 接受参数
        if ("admin".equals(employee.getName()) && "admin".equals(employee.getPassword()))
            return AjaxResult.me();
        return AjaxResult.me().setSuccess(false).setMessage("用户名或则密码错误！");
    }
}
