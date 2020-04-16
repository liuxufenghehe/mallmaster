package com.lxf.mall.common.util;

/**
 * 获取浏览器信息
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/3/13 14:40
 */
public class BrowerTypeUtil {


    /**
     * 根据header信息解析浏览器类型
     * @param agent
     * @return
     */
    public static String getBrowserName(String agent) {
        agent = agent.toLowerCase();
        if(agent.indexOf("msie 7")>0){
            return "ie7";
        }else if(agent.indexOf("msie 8")>0){
            return "ie8";
        }else if(agent.indexOf("msie 9")>0){
            return "ie9";
        }else if(agent.indexOf("msie 10")>0){
            return "ie10";
        }else if(agent.indexOf("msie")>0){
            return "ie";
        }else if(agent.indexOf("opera")>0){
            return "opera";
        }else if(agent.indexOf("opera")>0){
            return "opera";
        }else if(agent.indexOf("firefox")>0){
            return "firefox";
        }else if(agent.indexOf("webkit")>0){
            return "webkit";
        }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
            return "ie11";
        }else{
            return "Others";
        }
    }

}
