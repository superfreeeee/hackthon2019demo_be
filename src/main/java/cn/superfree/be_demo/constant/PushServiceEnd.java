package cn.superfree.be_demo.constant;

import org.ddpush.im.v1.client.appuser.Message;
import org.ddpush.im.v1.client.appuser.UDPClientBase;

import java.util.Arrays;

public class PushServiceEnd extends UDPClientBase {
    public PushServiceEnd(byte[] uuid, int appid, String serverAddr, int serverPort) throws Exception {
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

        if(message.getData() == null || message.getData().length == 0){
            return;
        }
        if(message.getCmd() == 16){
            System.out.println("Get Message");
        }// 0x10 通用推送信息

        if(message.getCmd() == 17){
            System.out.println(Arrays.toString(message.getData()));
        }// 0x11 分组推送信息

        if(message.getCmd() == 32){// 0x20 自定义推送信息
            System.out.println(Arrays.toString(message.getData()));
        }
    }
}
