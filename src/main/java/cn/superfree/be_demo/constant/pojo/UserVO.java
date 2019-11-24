package cn.superfree.be_demo.constant.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private String username;

    private DeviceDO receiver;

    private List<DeviceDO> devices;

}
