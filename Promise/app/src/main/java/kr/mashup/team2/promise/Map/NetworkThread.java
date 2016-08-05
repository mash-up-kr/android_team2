package kr.mashup.team2.promise.Map;

import android.os.Handler;
import android.os.Message;

public class NetworkThread extends Thread{

    Handler handler;

    public NetworkThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {

        Message msg = new Message();
        msg.obj = "";

        handler.sendMessage(msg);
    }
}
