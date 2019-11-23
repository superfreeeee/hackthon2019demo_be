package cn.superfree.be_demo.service;

import cn.superfree.be_demo.constant.form.DeviceForm;

import java.util.List;

public interface DdpushService {

    List<?> refresh(String username);

    boolean register(DeviceForm deviceForm);

    
}
