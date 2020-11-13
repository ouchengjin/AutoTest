package hello;

import org.springframework.stereotype.Controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.SpringApplication;

/**
 * 10-2 springboo简介及官方demo开发
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "HelloWorld";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class,args);
    }

}
