package cn.superfree.be_demo.controller;

import cn.superfree.be_demo.constant.Response;
import cn.superfree.be_demo.service.DdpushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cam")
public class DdpushController {

    @Autowired
    DdpushService ddpushService;

    @GetMapping("/hello")
    public Response hello() {
        return Response.ok("hello", null);
    }
}
