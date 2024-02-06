package structural.facade.email;

import structural.facade.Order;
import structural.facade.email.Template.TemplateType;

//facade class
//클라이언트가 간편히 사용할 수 있도록 메소드를 구현해둠 
public class EmailFacade {
	
	public boolean sendOrderEmail(Order order) {
		Template template = TemplateFactory.createTemplateFor(TemplateType.Email);
		Stationary stationary = StationaryFactory.createStationary();
		Email email = Email.getBuilder()
					  .withTemplate(template)
					  .withStationary(stationary)
					  .forObject(order)
					  .build();
		Mailer mailer = Mailer.getMailer();
		return mailer.send(email);
	}
	
	
}
