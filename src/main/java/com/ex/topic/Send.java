package com.ex.topic;

import com.ex.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String EXCHANGE_NAME="test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String msg="hello world";
        channel.basicPublish(EXCHANGE_NAME,"goods.info",null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
