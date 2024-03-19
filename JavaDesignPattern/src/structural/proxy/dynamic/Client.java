package structural.proxy.dynamic;

import structural.proxy.Image;
import structural.proxy.Point2D;

public class Client {

	public static void main(String[] args) {
		Image img = ImageFactory.getImage();
		img.setLocation(new Point2D(-10,0));
		
	}
}
