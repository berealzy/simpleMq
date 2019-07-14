package cn.zy.mq.broker;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable
{
    public static int SERVICE_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream()))
        {
            while(true)
            {
                String str = in.readLine();
                if(null == str)
                {
                    continue;
                }

                System.out.println("receive the original msg is " + str);
                // 消费消息
                if(str.equalsIgnoreCase("consume"))
                {
                    String msg = Broker.consume();
                    if(null == msg)
                    {
                        System.out.println("the msg from Broker.consume() is null!");
                        continue;
                    }
                    out.println(msg);
                    out.flush();
                }
                else
                {
                    // 生产消息
                    Broker.produce(str);
                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(SERVICE_PORT);
        while(true)
        {
            BrokerServer brokerServer = new BrokerServer(serverSocket.accept());
            new Thread(brokerServer).start();
        }
    }


}
