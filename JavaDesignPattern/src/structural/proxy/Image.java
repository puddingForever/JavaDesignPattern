package structural.proxy;


//어느 장소에서든 사진을 인화할 수 있는 인터페이스
//클라이언트가 사용 
public interface Image {

	void setLocation(Point2D point2d);
	
	Point2D getLocation();
	
	void render();
	
}
