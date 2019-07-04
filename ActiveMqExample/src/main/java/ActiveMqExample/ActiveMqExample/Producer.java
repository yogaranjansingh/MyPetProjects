package ActiveMqExample.ActiveMqExample;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

class Producer {
	
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "YogaQueue";

	public Producer() throws NamingException, JMSException {
		
		InitialContext jndi = new InitialContext();
		
	//	ConnectionFactory conFactory = (ConnectionFactory) jndi.lookup("connnectionFactory");
		ConnectionFactory conFactory = new ActiveMQConnectionFactory(url);
		
		Connection con;
		
		con = conFactory.createConnection();
		
		try {
			con.start();
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(subject);
			TextMessage text = session.createTextMessage("Hello Yoga");
			MessageProducer producer = session.createProducer(destination);
			producer.send(text);
			System.out.println("Message sent   " +text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			BasicConfigurator.configure();
			new Producer();
		} catch (Exception e) {
		}
		
	}
	
	

}
