package mymaven.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueryComsmer {

	/**
	 * ps主题模式消费者 1:创建工厂 2:获得实体 3：开启 4：获得Session 5：获得主题 6：创建消费者 7：监听主题
	 */
	public static void main(String[] args) {

		try {
		    ConnectionFactory  connectionFactory= new ActiveMQConnectionFactory("tcp://192.168.163.128:61616");

			//2	
		    Connection connection = connectionFactory.createConnection();
		    
		    //3
		    connection.start();
		    
		    //4
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic createTopic = session.createTopic("topicModle2Test");
			
			MessageConsumer consumer = session.createConsumer(createTopic);
			
			//7.监听消息
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage textMessage=(TextMessage)message;
					try {
						System.out.println("接收到消息："+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			System.in.read();
			//9.关闭资源
			consumer.close();
			session.close();
			connection.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
