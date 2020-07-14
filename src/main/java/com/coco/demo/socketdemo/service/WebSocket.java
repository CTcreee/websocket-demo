package com.coco.demo.socketdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.ErrorManager;



/**
 * @author wangzz
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    public Session session;
    private List<Session> sessionList=new ArrayList<>();
    public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        sessionList.add(session);
        System.out.println("【websocket消息】 有新的连接，总数："+webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("【websocket消息】 连接断开，总数："+webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {

        System.out.println("【websocket消息】 收到客户端发来的消息："+ message);

        // 接受到消息之后在发送到前台
       /* try {
                session.getBasicRemote().sendText("【websocket消息】 收到客户端发来的消息：" + message);
        } catch (IOException e) {
            System.out.println("【websocket消息】发送异常~");
            e.printStackTrace();
        }*/
        for (WebSocket webSocket : webSocketSet) {
            System.out.println("【websocket消息】 广播消息，message=" + message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//这个方法可以不用，消息传输在onMessage里完成
    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            System.out.println("【websocket消息】 发送在线人数，message="+ webSocketSet.size());
            try {
                webSocket.session.getBasicRemote().sendText("sda");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
