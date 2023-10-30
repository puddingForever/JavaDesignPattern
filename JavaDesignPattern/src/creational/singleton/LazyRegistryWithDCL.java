package creational.singleton;

//lazy singleton 
public class LazyRegistryWithDCL {

	private LazyRegistryWithDCL() {}
	
	//volatile : 캐시메모리가 아닌, 항상 메인 메모리의 값을 사용 
	// 스레드가 항상 메인메모리의 최신값을 읽도록 함 
	private static volatile LazyRegistryWithDCL INSTANCE;
 
	public static LazyRegistryWithDCL getInstance() {	
		
		//동기화처리 
		if(INSTANCE == null){
			synchronized (LazyRegistryWithDCL.class) {
				if(INSTANCE == null) {
					INSTANCE = new LazyRegistryWithDCL();
				}
			}
		}
		return INSTANCE;
	}
}
