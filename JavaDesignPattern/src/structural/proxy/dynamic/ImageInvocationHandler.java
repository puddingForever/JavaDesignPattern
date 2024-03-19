package structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import structural.proxy.Image;
import structural.proxy.Point2D;

public class ImageInvocationHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Method setLocationMethod = Image.class.getMethod("setLocation", new Class[] {Point2D.class});
		//image 프록시에서 누군가가 setLocation메소드를 호출했다는 뜻 
		if(setLocationMethod.equals(method)) {
			Point2D point2d = (Point2D)args[0];
			System.out.println("From Invocation Handler: "+point2d);
		}
		
		return null;
	
	}
	
}
