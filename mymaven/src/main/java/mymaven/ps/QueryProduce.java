package mymaven.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class QueryProduce {

	/**
	 * 主题模式的PS 生成者 1：获得工厂 2：获得实体线程 3：开启 4：获得Sessin 5:创建Tcp消息主题 6：创建生产者 7：发送消息
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
    //1
		try {
    ConnectionFactory  connectionFactory= new ActiveMQConnectionFactory("tcp://192.168.163.128:61616");

	//2	
    Connection connection = connectionFactory.createConnection();
    
    //3
    connection.start();
    
    //4
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    
    //5
     Topic createTopic = session.createTopic("topicModle2Test");
     
    //6
     MessageProducer createProducer = session.createProducer(createTopic);
     
    //7
     TextMessage createTextMessage = session.createTextMessage("topic模式");
     
    //8
    createProducer.send(createTextMessage);
    
    //9
    createProducer.close();
    session.close();
    connection.close();
    
    
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    
		
		
		
	}

}
