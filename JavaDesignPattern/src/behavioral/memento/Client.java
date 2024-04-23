package behavioral.memento;

public class Client {
	public static void main(String[] args) {
		Document document =  new Document("initial");
		
		History history = new History();
		
		//content 작성
		document.write("Additional context");
		
		//memento에 상태 저장
		history.addMemento(document.createMemento());
		
		document.write("More content");
		history.addMemento(document.createMemento());
		
		
		//이전 저장상태 복구 
		document.restoreFromMemento(history.getMemento(1));
		
		// content 출력
		System.out.println(document.getContent());
	
	}
}
