package cn.superfree.be_demo.constant.pojo;

import cn.superfree.be_demo.constant.form.DeviceForm;
import lombok.Data;

@Data
public class DeviceDO {
    private String deviceId;
    private String deviceType;

    public DeviceDO(DeviceForm deviceForm) {
        this.deviceId = deviceForm.getDeviceId();
        this.deviceType = deviceForm.getDeviceType();
    }
}
