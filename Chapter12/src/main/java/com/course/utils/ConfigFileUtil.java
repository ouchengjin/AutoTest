package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFileUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName interfaceName){
        String adress = bundle.getString("test.uri");
        String uri="";
        //下面的判断,因为是枚举的比较,也可以用"=="
        if (interfaceName.equals(InterfaceName.LOGIN)){
            uri = bundle.getString("login.uri");
        } else if (interfaceName.equals(InterfaceName.ADDUSER)){
            uri = bundle.getString("addUser.uri");
        }else if (interfaceName.equals(InterfaceName.GETUSERIFO)){
            uri = bundle.getString("getUserInfo.uri");
        }else if (interfaceName.equals(InterfaceName.GETUSERLIST)){
            uri = bundle.getString("getUserList.uri");
        }else if (interfaceName.equals(InterfaceName.UPDATEUSERINFO)){
            uri = bundle.getString("updateUserInfo.uri");
        }
        //最终测试地址
        String url =adress+ uri ;

        return url;
    }


}
