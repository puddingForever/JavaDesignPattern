package structural.facade;

import structural.facade.email.Email;
import structural.facade.email.EmailFacade;
import structural.facade.email.Mailer;
import structural.facade.email.Stationary;
import structural.facade.email.StationaryFactory;
import structural.facade.email.Template;
import structural.facade.email.Template.TemplateType;
import structural.facade.email.TemplateFactory;

public class Client {

	public static void main(String[] args) {
		Order order = new Order("101", 99.99);
		EmailFacade facade = new EmailFacade();
		boolean result = facade.sendOrderEmail(order);
		
		System.out.println("Order Email "+ (result?"sent!":"NOT sent..."));
		
	}
	
//
//	private static boolean sendOrderEmailWithoutFacade(Order order) {
//		Template template = TemplateFactory.createTemplateFor(TemplateType.Email);
//		Stationary stationary = StationaryFactory.createStationary();
//		Email email = Email.getBuilder()
//					  .withTemplate(template)
//					  .withStationary(stationary)
//					  .forObject(order)
//					  .build();
//		Mailer mailer = Mailer.getMailer();
//		return mailer.send(email);
//	}
	
}
