package cn.zy.mq.broker;


import java.util.concurrent.ArrayBlockingQueue;

public class Broker
{
    public static final int MAX_SIZE = 4;


    private static  ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    public static void produce(String msg)
    {
        if(arrayBlockingQueue.offer(msg))
        {
            System.out.println("msg:" + msg +"is push to the message queue! The queue size is = " + arrayBlockingQueue.size());
        }
        else
        {
            System.out.println("msg:" + msg +" is not push to the queue because of over size!");
        }
    }

    public static String consume()
    {
        String msg = arrayBlockingQueue.poll();
        if(null != msg)
        {
            System.out.println("msg:"+msg +"is consumed!");
        }
        else
        {
            System.out.println("The message queue is empty!");
        }
        return msg;
    }

}
