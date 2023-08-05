package com.example.openapiinterface.controller;

import com.example.openapiinterface.model.User;
import com.example.openapiinterface.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  名称API
 *
 * @author zuodong
 * @create 2023-08-05 21:15
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accesskey = request.getHeader("accesskey");
        String nonce = request.getHeader("nonce");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");

          // TODO 实际情况是要去数据库中查是否已分配给用户
          if (!accesskey.equals("yupi")) {
            throw new RuntimeException("无权限！");
          }

          if(Long.parseLong(nonce) > 10000) {
              throw new RuntimeException("无权限！");
          }

          //TODO 时间戳和当前时间不能超过5分钟

          //TODO 实际情况是从数据库中拿到 secretKey，可以通过accesskey去查
          String serverSign = SignUtil.genSign(body, "abcdefgh");

        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限！");
        }

        return "POST 用户名是" + user.getUsername();
    }

}
