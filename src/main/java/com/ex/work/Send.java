package com.ex.work;

import com.ex.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME="test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection= ConnectionUtils.getConnection();
        //创建channel
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for(int i=0;i<50;i++){
            String msg="."+i+".";
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("WQ send:"+msg);
            Thread.sleep(i*20);
        }
        channel.close();
        connection.close();
    }
}
