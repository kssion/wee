package cc.nsurl.wee.controller;

import cc.nsurl.wee.model.App;
import cc.nsurl.wee.model.Result;
import cc.nsurl.wee.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/apps")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    /**
     * 应用列表
     * @return mav
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("apps");
        mav.addObject("apps", appService.getApps());
        return mav;
    }

    /**
     * 添加应用
     * @param name 应用名称
     * @param path 部署路径
     * @param type 仓库类型
     * @param repertory 仓库地址
     * @param model 请求对象
     * @return 添加结果
     */
    @PostMapping("app")
    public String app_post(@RequestParam String name, String path, int type, String repertory, Model model) {
        App app = new App(null, name, path, type, repertory);

        String uuid = UUID.randomUUID().toString();
        String hex = DigestUtils.md5DigestAsHex(uuid.getBytes());
        app.setSid(hex.substring(8, 24));

        if (appService.insert(app)) {
            return "forward:apps";
        }
        model.addAttribute("app", app);
        return "forward:add";
    }

    /**
     * 修改应用
     * @param sid 应用id
     * @param name 应用名称
     * @param path 部署路径
     * @param type 仓库类型
     * @param repertory 仓库地址
     * @return 修改结果
     */
    @PutMapping("app")
    public Result app_put(@RequestParam String sid, String name, String path, Integer type, String repertory) {
        App app = appService.selectAppWithSid(sid);
        if (app == null) {
            return Result.error("应用无效");
        }
        if (app.getState() == 1) {
            return Result.error("应用无效");
        }
        if (name != null) {
            app.setName(name);
        }
        if (path != null) {
            app.setPath(path);
        }
        if (type != null) {
            app.setType(type);
        }
        if (repertory != null) {
            app.setRepertory(repertory);
        }
        if (appService.updateApp(app)) {
            return Result.success("ok");
        }
        return Result.error("修改失败");
    }

    @GetMapping("{sid}")
    @ResponseBody
    public Result app_get(@PathVariable String sid) {
        App app = appService.selectAppWithSid(sid);
        return Result.simple(app);
    }

    @GetMapping("json")
    @ResponseBody
    public Result json() {
        return Result.success("ok", appService.getApps());
    }
}
