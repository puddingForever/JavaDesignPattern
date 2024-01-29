package structural.decorator;

//decorator
public class HtmlEncodedMessage implements Message{

	private Message msg;
	
	@Override
	public String getContent() {
		return null;
	}

}
