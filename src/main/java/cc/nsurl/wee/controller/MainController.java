package cc.nsurl.wee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    /**
     * 跳转应用列表
     * @return URL
     */
    @GetMapping("apps")
    public String apps() {
        return "forward:/apps/";
    }
}
