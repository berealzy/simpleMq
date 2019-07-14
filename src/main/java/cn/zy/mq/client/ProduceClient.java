package cn.zy.mq.client;

public class ProduceClient {
    public static void main(String[] args) throws Exception
    {
        MqClient mqClient = new MqClient();
        for(int i = 0; i <10; i++)
        {
          mqClient.produce("hello Wrold!" + i);
            Thread.sleep(10000);
        }

    }
}
