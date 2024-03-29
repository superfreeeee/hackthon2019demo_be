package cn.superfree.be_demo.controller;

import cn.superfree.be_demo.constant.form.DeviceForm;
import cn.superfree.be_demo.constant.form.LoginForm;
import cn.superfree.be_demo.constant.Response;
import cn.superfree.be_demo.constant.pojo.DeviceDO;
import cn.superfree.be_demo.constant.pojo.UserVO;
import cn.superfree.be_demo.service.DdpushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cam")
public class DdpushController {

    private static final String username = "superfree";

    private static final String password = "123456";

    @Autowired
    DdpushService ddpushService;

    @GetMapping("/hello")
    public Response hello() {
        return Response.ok("hello", null);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginForm loginForm) {
        if(!loginForm.getUsername().equals(username) || !loginForm.getPassword().equals(password)) {
            return Response.error("login fail");
        } else {
            UserVO userVO = new UserVO();
            userVO.setUsername(loginForm.getUsername());
            userVO.setReceiver(ddpushService.getReceiver());

            return Response.ok("login success", userVO);
        }
    }

    @GetMapping("/refresh")
    public Response refresh() {
        List<?> result = ddpushService.refresh();
        return Response.ok("refresh success", result);
    }

    @PostMapping("/bind")
    public Response bind(@RequestBody DeviceForm deviceForm) {
        boolean registerResult = ddpushService.bindReceiver(deviceForm);
        if (registerResult) {
            return Response.error("bind fail");
        }
        return Response.ok("bind success", null);
    }

    @PostMapping("/register")
    public Response register(@RequestBody DeviceForm deviceForm) {
        boolean registerResult = ddpushService.registerDevice(deviceForm);
        if (registerResult) {
            return Response.error("register fail");
        }
        return Response.ok("register success", null);
    }


    @GetMapping("/push")
    public Response push() {
        ddpushService.push("chu", "hahaha");
        return Response.ok("push message", null);
    }

    @GetMapping("/receiver")
    public Response receiver() {
        DeviceDO deviceDO = ddpushService.getReceiver();
        return Response.ok("receiver got", deviceDO);
    }
    @GetMapping("/devices")
    public Response devices() {
        List<?> deviceDOS = ddpushService.getDevices();
        return Response.ok("devices got", deviceDOS);
    }
}
