package cn.superfree.be_demo.service;

import cn.superfree.be_demo.constant.PushServiceEnd;
import cn.superfree.be_demo.constant.form.DeviceForm;
import cn.superfree.be_demo.util.Utils;
import org.ddpush.im.v1.client.appserver.Pusher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class DdpushServiceImp implements DdpushService {

    Pusher pusher = null;

    public DdpushServiceImp() {
        connect();
    }

    private void connect() {
        try {
            Properties pros = new Properties();
            pros.load(Class.class.getClassLoader().getResourceAsStream("ddpush.properties"));

            byte[] uuid = new byte[0];
            uuid = Utils.md5Byte(pros.getProperty("uuid"));
            int appid = Integer.parseInt(pros.getProperty("appid"));
            String addr = pros.getProperty("serverAddr");
            int port = Integer.parseInt(pros.getProperty("serverPort"));
            int heart = Integer.parseInt(pros.getProperty("heartBeat"));
            int pushPort = Integer.parseInt(pros.getProperty("pushPort"));
            int pushTimesOut = Integer.parseInt(pros.getProperty("pushTimesOut"));

            PushServiceEnd pushServiceEnd = new PushServiceEnd(uuid, appid, addr, port);
            pushServiceEnd.setHeartbeatInterval(heart);
            pushServiceEnd.start();

            pusher = new Pusher(addr, pushPort, pushTimesOut);

            System.out.println("connection success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection fail");
        }
    }

    @Override
    public List<?> refresh(String username) {
        if(pusher == null) {
            connect();
        }
        return null;
    }

    @Override
    public boolean register(DeviceForm deviceForm) {
        return false;
    }
}
