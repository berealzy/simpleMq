package cn.zy.mq.client;

public class ConsumeClient
{
    public static void main(String[] args) throws  Exception
    {
        MqClient mqClient = new MqClient();
        for(int i = 0; i < 10; i++)
        {
            Thread.sleep(10000);
            String msg = mqClient.consume();
            System.out.println("ConsumeClient consume the msg: " + msg);
        }

    }
}
