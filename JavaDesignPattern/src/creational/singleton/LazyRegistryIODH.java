package creational.singleton;

//lazy singleton2 
public class LazyRegistryIODH {
	
	private LazyRegistryIODH() {}
	
	//inner class 
	private static class RegistryHolder{
		//static변수를 inner 클래스 내부에 선언 
		//getInstance()때만 인스턴스가 생성된다.
		static LazyRegistryIODH INSTANCE = new LazyRegistryIODH();
	}
	
	
	public static LazyRegistryIODH getInstance() {
		return RegistryHolder.INSTANCE;
	}

}
