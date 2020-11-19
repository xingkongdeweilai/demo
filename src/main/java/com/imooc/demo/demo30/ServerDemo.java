package com.imooc.demo.demo30;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.imooc.demo.demo30.enums.UriEnum;
import com.imooc.demo.demo30.form.GetCircleAreaForm;
import com.imooc.demo.demo30.form.RequestForm;
import com.imooc.demo.demo30.form.SendMessageForm;
import com.imooc.demo.demo30.po.SendMessagePO;
import com.imooc.demo.demo30.vo.CommonVO;
import com.imooc.demo.demo30.vo.ResponseVO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerDemo {

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            pool.execute(new HandleSendMessage());
            pool.execute(new HandleResponse());
            System.out.println("服务启动成功");
            System.out.println(Inet4Address.getLocalHost());
            System.out.println(serverSocket.getLocalPort());
            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new HandleAClient(socket));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private static class HandleAClient implements Runnable {

        private Socket socket;

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(socket.getInputStream());
                System.out.println(Thread.currentThread().getName() + "-->新的客户端接入");
                String userIp = socket.getInetAddress().getHostAddress();
                int userPort = socket.getPort();
                String userName = userIp + ":" + userPort;
                SocketMap.put(userName, socket);
                System.out.println(Thread.currentThread().getName() + "-->hostAddress:" + userIp);
                System.out.println(Thread.currentThread().getName() + "-->port:" + userPort);
                while (true) {
                    Object request = isAlive(in);
                    if (request != null) {
                        System.out.println(Thread.currentThread().getName() + "-->request-->" + request);
                        CommonVO result = handleRequest(request, userName);
                        System.out.println(Thread.currentThread().getName() + "-->response-->" + result);
                        HandleResponse.put(new ResponseVO(userName, result));
                    } else {
                        try {
                            socket.sendUrgentData(0);
                        } catch (IOException e) {
                            break;
                        }
                    }
                }
                SocketMap.remove(userName);
                System.out.println(Thread.currentThread().getName() + "-->客户端已断开连接");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        /**
         * 处理请求
         * @param request
         * @return
         * @throws IOException
         */
        private CommonVO handleRequest(Object request, String userName) {
            CommonVO result;
            if (request instanceof RequestForm){
                RequestForm requestForm = (RequestForm) request;
                switch (UriEnum.getInstance(requestForm.getUri())) {
                    case GET_CIRCLE_AREA:
                        double radius = ((GetCircleAreaForm) requestForm.getRequestBody()).getRadius();
                        result = CommonVO.ok();
                        result.setBody(Math.PI * radius * radius);
                        break;
                    case SEND_MESSAGE:
                        HandleSendMessage.putMessage(SendMessagePO.buildSendMessagePo(((SendMessageForm) requestForm.getRequestBody()), userName));
                        result = CommonVO.ok();
                        break;
                    default :
                        result = CommonVO.errorRequestUri();
                }
            } else {
                result = CommonVO.errorRequestBody();
            }
            return result;
        }

        /**
         * 判断客户端是否还处于连接状态
         * @param in
         * @return
         */
        private Object isAlive(ObjectInputStream in) {
            try {
                return in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                return null;
            }
        }
    }

    private static class HandleSendMessage implements Runnable {

        private static LinkedBlockingQueue<SendMessagePO> blockingQueue = new LinkedBlockingQueue();

        public static void putMessage(SendMessagePO sendMessagePo) {
            try {
                blockingQueue.put(sendMessagePo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    SendMessagePO sendMessagePo = blockingQueue.take();
                    CommonVO commonVO = CommonVO.ok();
                    commonVO.setBody(sendMessagePo);
                    HandleResponse.put(new ResponseVO(sendMessagePo.getMessageTo(), commonVO));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class HandleResponse implements Runnable {

        private static final LinkedBlockingQueue<ResponseVO> BLOCKING_QUEUE = new LinkedBlockingQueue();

        public static void put(ResponseVO responseVO) {
            try {
                BLOCKING_QUEUE.put(responseVO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    ResponseVO responseVO = BLOCKING_QUEUE.take();
                    SocketMap.getObjectOutputStream(responseVO.getReceiveUserName()).writeObject(responseVO.getCommonVO());
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
