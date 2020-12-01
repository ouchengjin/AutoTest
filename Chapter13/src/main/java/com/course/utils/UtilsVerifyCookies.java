package com.course.utils;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
@Log4j2
public class UtilsVerifyCookies {

    public static boolean verifyCookies(HttpServletRequest request){
        boolean ret = false;
        Cookie[] cookies = request.getCookies();
        if(!Objects.isNull(cookies)){
            for (Cookie cookie:cookies){
                String name = cookie.getName();
                String value = cookie.getValue();

                if (name.equals("login")&&value.equals("true")){
                    ret = true;
                    log.info("cookies验证通过");
                }
            }

        }else{
            log.info("cookies验证不通过");
        }
        return ret;
    }
}
