package behavioral.memento;


// encapsulation -> encapsulate the state of the 
// document within memento objects
// preventing direct access and manipulation of the document's state
// Originator
public class Document {
	
	private String content;
	
	public Document(String content) {
		this.content = content;
	}
	
	public void write(String text) {
		this.content += text;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public DocumentMemento createMemento() {
		return new DocumentMemento(this.content);
	}
	
	public void restoreFromMemento(DocumentMemento memento) {
		this.content = memento.getSavedContent();
	}
	
}
