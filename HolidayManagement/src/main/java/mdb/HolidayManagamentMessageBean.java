package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Message-Driven Bean implementation class for: HolidayManagamentMessageBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/HolidayRequest"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/HolidayRequest")
public class HolidayManagamentMessageBean implements MessageListener {

	@PersistenceContext(unitName="HolidayManagement")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public HolidayManagamentMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	try {
    		MapMessage holidayRequestMsg = (MapMessage)message; 
    		String employeeName = holidayRequestMsg.getString("employeeName");
    		deliverResult(holidayRequestMsg);
    	}
    	catch(JMSException | NamingException e) {
    		e.printStackTrace();
    	}
    	
    }

	private void deliverResult(MapMessage holidayRequestMsg) throws NamingException, JMSException {
		Context jndiContext = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory"); 
		
		Topic resultTopic = (Topic) jndiContext.lookup("java:/jms/HolidayRequestTopic"); 
		Connection connect = connectionFactory.createConnection();
		
		Session session =connect.createSession(false,Session.AUTO_ACKNOWLEDGE);
		
		String employeeName = holidayRequestMsg.getString("employeeName");
		
		MessageProducer sender = session.createProducer(resultTopic);
		TextMessage message = session.createTextMessage();
		
		message.setText(String.format("New Holiday Request Submitted by %s ", employeeName));
		sender.send(message);
		connect.close();
		
	}

}
