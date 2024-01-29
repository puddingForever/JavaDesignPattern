package structural.decorator;

public class Client {

	public static void main(String[] args) {
		Message m = new TextMessage("<FORCE>");
		System.out.println(m.getContent());

		Message decorator = new HtmlEncodedMessage(m);
		System.out.println(decorator.getContent()); //&lt 
		
		
		decorator = new Base64EncodedMessage(decorator);
		System.out.println(decorator.getContent()); //base64 encoded 
	}
}
