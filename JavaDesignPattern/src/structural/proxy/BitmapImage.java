package structural.proxy;

//Image 인터페이스를 구현한 구상클래스 
//실제 기능들을 제공한다.
public class BitmapImage implements Image {
	
	private Point2D location;
	private String name;
	
	public BitmapImage(String filename) {
		System.out.println("Loaded from disk:"+filename);
		name = filename;
	}
	
	@Override
	public void setLocation(Point2D point2d) {
		location = point2d;
	}

	@Override
	public Point2D getLocation() {
		return location;
	}

	@Override
	public void render() {
		//renders to screen
		System.out.println("Rendered "+this.name);
	}
}
