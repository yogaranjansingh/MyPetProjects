package ActiveMqExample.ActiveMqExample;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

public class Consumer {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String subject = "YogaQueue";

	public static void main(String[] args) throws JMSException {
		
	BasicConfigurator.configure();
	
	ConnectionFactory conFactory = new ActiveMQConnectionFactory(url);
	Connection con = conFactory.createConnection();
	con.start();
	Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	Destination destination = session.createQueue(subject);
	MessageConsumer consumer = session.createConsumer(destination);
	
	Message message = consumer.receive();
	
	if(message instanceof TextMessage)
	{
		TextMessage text = (TextMessage) message;
		System.out.println("message recieved  "+text.getText());
	}
	con.close();
	}

}
