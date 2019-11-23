package cn.superfree.be_demo.service;

import cn.superfree.be_demo.constant.form.DeviceForm;
import cn.superfree.be_demo.util.Utils;
import org.ddpush.im.v1.client.appserver.Pusher;
import org.ddpush.im.v1.client.appuser.Message;
import org.ddpush.im.v1.client.appuser.UDPClientBase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class DdpushServiceImp implements DdpushService {

    private Pusher pusher = null;

    private List<Record> records = new ArrayList<>();

    public DdpushServiceImp() {
        connect();
    }

    private final class Record {

        long timestamp;

        byte[] data;

        Record(byte[] data) {
            this.timestamp = System.currentTimeMillis();
            this.data = data;
        }

        @Override
        public String toString() {
            return "{timestamp: " + timestamp + ", data: " + Arrays.toString(data) + "}";
        }
    }

    private class PushServiceEnd extends UDPClientBase {
        PushServiceEnd(byte[] uuid, int appid, String serverAddr, int serverPort) throws Exception {
            super(uuid, appid, serverAddr, serverPort);
        }

        @Override
        public boolean hasNetworkConnection() {
            return true;
        }

        @Override
        public void trySystemSleep() {

        }

        @Override
        public void onPushMessage(Message message) {
            if(message == null){
                return;
            }

            int cmd = message.getCmd();
            byte[] data = message.getData();

            if(data == null || data.length == 0) {
                return;
            } else if(cmd == 16 || cmd == 17 || cmd == 32){
                if(cmd == 16) {  // 0x10 通用推送信息
                    System.out.println("get message from 0x10");
                }
                if(cmd == 17) {  // 0x11 分组推送信息
                    System.out.println("get message from 0x11");
                } else if(cmd == 32) {  // 0x20 自定义推送信息
                    System.out.println("get message from 0x20");
                } else {
                    System.out.println("get message from others");
                }

                Record record = new Record(message.getData());
                records.add(record);
                System.out.println(record);
            } else {
                System.out.println("unknown cmd");
            }
        }
    }


    private String name = "server";
    private int appid = 1;
    private int port = 9966;
    private int pushPort = 9999;
    private int heart = 50;
    private int pushTimesOut = 1000;
    private String addr = "172.20.10.2";


    private void connect() {
        try {
            Properties pros = new Properties();

            byte[] uuid = new byte[0];
            uuid = Utils.md5Byte(name);
//            int appid = Integer.parseInt(pros.getProperty("appid"));
//            String addr = pros.getProperty("serverAddr");
//            int port = Integer.parseInt(pros.getProperty("serverPort"));
//            int heart = Integer.parseInt(pros.getProperty("heartBeat"));
//            int pushPort = Integer.parseInt(pros.getProperty("pushPort"));
//            int pushTimesOut = Integer.parseInt(pros.getProperty("pushTimesOut"));

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

    @Override
    public boolean push(String deviceName, String message) {
        try {
            pusher.push0x20Message(Utils.md5Byte(deviceName), message.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
