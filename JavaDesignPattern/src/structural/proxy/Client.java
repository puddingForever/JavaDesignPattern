package structural.proxy;


public class Client {

	public static void main(String[] args) {
		Image img = ImageFactory.getImage("A1.bmp");
		
		img.setLocation(new Point2D(10,10));
		System.out.println("Image location is : " + img.getLocation());
		System.out.println("rendering image now..!!!!");
		//실제 대상객체를 사용 
		img.render();
	}

}
