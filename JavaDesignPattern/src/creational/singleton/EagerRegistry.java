package creational.singleton;
/**
 * eager signleton : 클래스로드시 바로 생성 
 */
public class EagerRegistry {
	
	/*외부에서 인스턴스 생성못함*/
	private EagerRegistry() {
	}
	
	//인스턴스 바로 생성 
	private static final EagerRegistry INSTANCE = new EagerRegistry();
	
	
	public static EagerRegistry getInstance() {
		return INSTANCE;
	}
	
}