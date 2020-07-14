package com.coco.demo.socketdemo.controller;


import com.coco.demo.socketdemo.dao.WebuserMapper;
import com.coco.demo.socketdemo.entity.Webuser;
import com.coco.demo.socketdemo.service.UserService;
import com.coco.demo.socketdemo.service.WebSocket;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wangzz
 */
@Controller
public class SocketController {
    private String UESRNAME;
    @Autowired
    private WebSocket webSocket;

    private Webuser webuser;
    @Lazy
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest req;

    @RequestMapping("/person")
    @ResponseBody
    public String Initial() {
        String res= String.valueOf(webSocket.webSocketSet.size());
        return res;
    }

    @RequestMapping("/user")
    @ResponseBody
    public String GetUser( ) {
        return this.UESRNAME;
    }


    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam("username")String username, @RequestParam("password") String password , Map<String,Object> map,
                          HttpSession httpSession) {
        this.UESRNAME=username;
       /* try {
            String getpassword=userService.selectByusername(username);
            System.out.println(getpassword);;
            if(!getpassword.equals(password)) {
                httpSession.invalidate();
                map.put("msg","用户名或密码错误");
                return "login";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "websocket.html";
        */
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);

        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                req.setAttribute("errorMess", "用户不存在");
            } else if (e instanceof LockedAccountException) {
                req.setAttribute("errorMess", "用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                req.setAttribute("errorMess", "密码错误");
            } else {
                req.setAttribute("errorMess", "用户认证失败");
            }

            map.put("msg","用户名或密码错误");
            return "login.html";
        }
        return "websocket.html";


    }

    @RequestMapping("send")
    public ModelAndView send(String msg) {
        webSocket.sendMessage("msg:{" + msg + "}");
        ModelAndView view=new ModelAndView();
        view.addObject("count",webSocket.webSocketSet.size());
        view.setViewName("redirect:websocket.html");
        return view;
    }
    @RequestMapping("/admin")
    public String admin(String msg) {

        return "admin.html";

    }
    /*public String send(String msg) {
        webSocket.sendMessage("msg:{" + msg + "}");
        return "消息发送成功！消息：{" + msg + "}";
    }*/

    @RequestMapping("close")
    @ResponseBody
    public String close() {
        webSocket.onClose();
        return "关闭连接成功！";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        return  userService.selectByPrimaryKey(1).toString();
    }

}
