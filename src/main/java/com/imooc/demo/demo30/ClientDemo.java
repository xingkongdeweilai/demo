package com.imooc.demo.demo30;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.imooc.demo.demo30.enums.UriEnum;
import com.imooc.demo.demo30.form.GetCircleAreaForm;
import com.imooc.demo.demo30.form.RequestForm;
import com.imooc.demo.demo30.form.SendMessageForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author liulijian
 */
public class ClientDemo {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        Socket socket;
        ObjectOutputStream out = null;
        try {
            socket = new Socket("192.168.1.24", 8080);
            pool.execute(new ReceiveMessageListener(socket));
            System.out.println(socket.getLocalPort());
            out = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String uri = scanner.next();

                RequestForm requestForm = new RequestForm();
                requestForm.setUri(uri);
                Object requestBody = null;
                switch (UriEnum.getInstance(uri)) {
                    case GET_CIRCLE_AREA:
                        requestBody = new GetCircleAreaForm(scanner.nextDouble());
                        break;
                    case SEND_MESSAGE:
                        requestBody = new SendMessageForm(scanner.next(), scanner.next());
                        break;
                    default:
                }
                requestForm.setRequestBody(requestBody);
                out.writeObject(requestForm);
                System.out.println("request-->" + requestForm);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static class ReceiveMessageListener implements Runnable {

        private Socket socket;

        public ReceiveMessageListener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    System.out.println(in.readObject());
                }
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
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
    }
}
