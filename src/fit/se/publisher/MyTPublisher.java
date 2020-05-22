package fit.se.publisher;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

import fit.se.entities.BenhNhan;

public class MyTPublisher {
	private QueueConnection connection = null;
	private QueueSession session = null;
	private Queue queue = null;
	public MyTPublisher() throws NamingException, JMSException {
		Context context = new InitialContext();
		QueueConnectionFactory factory =  (QueueConnectionFactory) context.lookup("benhNhan");
		connection = factory.createQueueConnection();
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue =  (Queue) context.lookup("MyQueue");
		connection.start();
	}
	
	public void exit() throws JMSException {
		session.close();
		connection.close();
	}
	
	public void sendMessage(BenhNhan benhNhan) throws JMSException {
		Gson gson = new Gson();
		String json = gson.toJson(benhNhan);
		QueueSender sender = session.createSender(queue);
		Message message = session.createTextMessage(json);
		sender.send(message);
	}
}
