package mymaven.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQXAConnectionFactory;

public class QueueProduce {

	/**
	 * 1：获得ActiveMq工厂对象 2：获得实体对象 3：获得Session 4：创建消息列队 5：发送消息
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// 1:获得ActiveMq工厂对象(JMS)
		try {
			ConnectionFactory connectionFactory = new ActiveMQXAConnectionFactory("tcp://192.168.163.128:61616");
			// 2: 获得实例
			Connection createConnection = connectionFactory.createConnection();

			createConnection.start();

			// 3:获得核心对象Session，参数1：是否开启事务 ，参数2：消息确认模式
			Session session = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// 4:Session创建消息队列
			Queue createQueue = session.createQueue("test2tcp");

			// 5:创造消息生成者
			MessageProducer createProducer = session.createProducer(createQueue);

			// 6:创建此消息
			TextMessage createTextMessage = session.createTextMessage("这里是tcp activeMQ");

			// 7:发送消息
			createProducer.send(createTextMessage);

			// 8:关流
			createProducer.close();
			session.close();
			createConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
