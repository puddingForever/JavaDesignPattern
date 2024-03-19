package structural.proxy.dynamic;

import java.lang.reflect.Proxy;

import structural.proxy.Image;

//Factory to get image obj
public class ImageFactory {
	
	public static Image getImage() {
	 return (Image)Proxy.newProxyInstance(ImageFactory.class.getClassLoader(), new Class[] {Image.class},
								new ImageInvocationHandler());
	}
	
}