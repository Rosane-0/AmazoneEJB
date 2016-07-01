package fr.treeptik.amazoneejb.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.jboss.logging.Logger;

import fr.treeptik.amazoneejb.pojo.Article;
import fr.treeptik.amazoneejb.service.ArticleService;

@MessageDriven(activationConfig={
		
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/amazoneQueue")
		
})
public class ArticleMessageListener implements MessageListener {
	
	private Logger logger = Logger.getLogger(ArticleMessageListener.class);
	@EJB
	public ArticleService articleService;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
				Object object = objectMessage.getObject();
				if (object instanceof Article) {
					articleService.add((Article) object);
					logger.info("ARTICLE SEND : " + object.toString());
				}
				else {
					logger.info(object.toString() + " NOT A ARTICLE !");
				}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
