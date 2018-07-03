package com.ex.workfair;

import com.ex.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection= ConnectionUtils.getConnection();
        final Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        int prefetchCount=1;
        channel.basicQos(prefetchCount);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                /*super.handleDelivery(consumerTag, envelope, properties, body);*/
                String msg=new String(body,"utf-8");
                System.out.println(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[2] done");
                    //手动回执
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //自动消息应答
        boolean autoAck=false;
        //公平分发fairDispatch;关闭自动应答ack,改成手动
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
