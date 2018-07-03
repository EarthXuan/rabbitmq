package com.ex.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    /**
	 * 获取MQ的连接  
	 * @return
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		//定义一个连接工厂
		ConnectionFactory factory =new ConnectionFactory();
		
		//设置服务地址
		factory.setHost("127.0.0.1");

		//AMQP 5672
		factory.setPort(5672);
		
		//vhost 
		factory.setVirtualHost("/vhost");
		
		//用户名 
		factory.setUsername("earthxuan");
		
		//密码
		factory.setPassword("abcisme123");
		return factory.newConnection();
	}
	
}