package com.icookBackstage._00_init.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static final Map<Integer, Set<Session>> rooms = new ConcurrentHashMap();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") int userId){
        if (!rooms.containsKey(userId)) {
            // 建立房間不存在時，建立房間
            Set<Session> room = new HashSet<>();
            // 新增使用者
            room.add(session);
            rooms.put(userId, room);
            System.out.println(userId + " has connected!");
        } else {
            // 房間已存在，直接新增使用者到相應的房間
            rooms.get(userId).add(session);
            System.out.println(userId + " has connected!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
	@OnClose
    public void onClose(@PathParam("userId") int userId,Session session) throws Exception{
    	rooms.get(userId).remove(session);
        System.out.println(userId + " has disconnected!");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws Exception 
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("userId")int userId) throws Exception{
        broadcast(userId,message);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public static void broadcast(int userId, String msg) throws Exception {
    	System.out.println(userId);
    	System.out.println(rooms);
        for (Session session : rooms.get(userId)) {
                session.getBasicRemote().sendText(msg);
        }
    }
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}