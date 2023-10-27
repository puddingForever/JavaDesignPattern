package creational.objectpool.gameworld;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class ObjectPool<T extends Poolable> {
	
	//객체를 캐시메모리에 담아주는 BlockingQueue객체, 큐라고 부르겟음 
	private BlockingQueue<T> availablePool;
	
	//supplier : 인스턴스를 전달받음 , count : 저장하려는 인스턴스의 수 
	public ObjectPool(Supplier<T> creator,int count) {
		//BlockingQueue의 구현체인 LinkedBlockingQueue로 큐인스턴스를 만들고 
		availablePool = new LinkedBlockingQueue<>();
		
		for(int i=0; i<count; i++) {
			//큐에 받아온 인스턴스를 넣음 
			availablePool.offer(creator.get());
			
		}
	}
	
	//클라이언트코드가 큐에서 인스턴스를 가져가는 메소드  
	public T get() {
		try { // 자원이 큐에 있는경우 리턴 
			return availablePool.take();
		}catch(InterruptedException ex) {
			//큐에 자원이 없으면 null
			System.err.println("자원이 없습니다.");
		}
		return null;
	}
	
	//클라이언트가 큐에게 인스턴스를 돌려주는 메소드 
	public void release(T obj) {
		obj.reset(); // 받아온 인스턴스 초기화 
		try {
			//큐에 인스턴스 담기 
			availablePool.put(obj);
		}catch (InterruptedException e) {
			//큐가 꽉 찻으면 안받아옴 
			System.err.println("자원이 들어갈 자리가 없습니다");
		}
	}
	

}
