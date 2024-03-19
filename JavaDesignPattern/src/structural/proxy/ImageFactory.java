package structural.proxy;

//이미지인스턴스를 만드는 팩토리 
public class ImageFactory {
	//실제 객체가 아닌, 프록시 인스턴스를 전달함 
	
	public static Image getImage(String name) {
		return new ImageProxy(name);
	}
	
}
