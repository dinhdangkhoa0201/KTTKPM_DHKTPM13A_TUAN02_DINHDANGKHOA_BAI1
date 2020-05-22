package fit.se.publisher;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MyTSubscriber {
	private QueueConnection connection = null;
	private static QueueSession session = null;
	private static Queue queue = null;
	private static QueueReceiver receiver = null;
	
	public MyTSubscriber() throws NamingException, JMSException {
		Context context = new InitialContext();
		QueueConnectionFactory factory =  (QueueConnectionFactory) context.lookup("benhNhan");
		connection = factory.createQueueConnection();
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) context.lookup("MyQueue");
		connection.start();
	}
	
	public static QueueReceiver getQueueReceiver() throws JMSException {
		if(receiver == null) {
			receiver = session.createReceiver(queue);
		}
		return receiver;
	}
}
