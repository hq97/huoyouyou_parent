package cn.moyang.huoyouyou.util;

import java.io.Serializable;

//ajax请求响应对象类
public class AjaxResult {
    private boolean success =true;
    private String message ="操作成功";

    private Object resultObj ; //返回一个对象

    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this; //实现链式编程
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }


    //AjaxResult.me()成功
    //AjaxResult.me().setMessage()成功
    //AjaxResult.me().setSuccess(false),setMessage("失败");
    public static AjaxResult me(){
        return new AjaxResult();
    }
}
