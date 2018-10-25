package mymaven.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.stomp.Stomp.Headers.Connect;
import org.apache.log4j.net.SyslogAppender;

public class QueryConsmer {

	/**
	 * 创建消费者 1:获得工厂对象 2：获得实体线程 开启 3：获得Session 4:获得消息队列 5:创建消费者 6:监听消息
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// 1:获得消息工厂
			ConnectionFactory connectionFactroy = new ActiveMQConnectionFactory("tcp://192.168.163.128:61616");

			// 2:获得实体线程
			Connection connection = connectionFactroy.createConnection();

			// 3:开启
			connection.start();

			// 4:获得Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			//5:获得消息列队
			Queue createQueue = session.createQueue("test2tcp");
			
			//6:获得消费者
            MessageConsumer createConsumer = session.createConsumer(createQueue);
            
			//7:监听消息
            createConsumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
                       TextMessage  textmessage = (TextMessage) message;
                       try {
						System.out.println(textmessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
            });
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
