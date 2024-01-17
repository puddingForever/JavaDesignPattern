package structural.bridge;

//refined abstraction
public class Queue<T> implements FifoCollection<T>{

	//브릿지 패턴 
	private LinkedList<T> list;
	
	public Queue(LinkedList<T> list) {
		this.list = list;
	}
	
	@Override
	public void offer(T element) {
		list.addLast(element);
	}

	@Override
	public T poll() {
		return list.removeFirst();
	}
	
	
}
