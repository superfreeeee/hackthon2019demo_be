package cn.superfree.be_demo.service;

import cn.superfree.be_demo.constant.form.DeviceForm;
import cn.superfree.be_demo.constant.pojo.DeviceDO;

import java.util.List;

public interface DdpushService {

    List<?> refresh(String username);

    boolean registerDevice(DeviceForm deviceForm);

    boolean bindReceiver(DeviceForm deviceForm);

    boolean push(String deviceId, String message);

    DeviceDO getReceiver();

    List<?> getDevices();
}
