package structural.decorator;


//decorator
public class HtmlEncodedMessage implements Message{

	private Message msg;
	
	public HtmlEncodedMessage(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public String getContent() { // html encoded 
		return StringEscapeUtils.escapeHtml4(msg.getContent());
	}

}
