package cn.zy.mq.client;

import cn.zy.mq.broker.BrokerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MqClient {
    public  void produce(String message)
    {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
             out.println(message);
             out.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String consume()
    {
        try(Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream()))
        {
            // 消费一条消息
            out.println("consume");
            out.flush();

            String msg = in.readLine();
            return msg;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }
}
