package creational.prototype.war;

//미사일위치
public class Position {
	
	private int distance;
	
	//미사일 위치 초기화 상수
	public static final Position ZERO = new Position(0);

	public Position(int distance) {
		this.distance = distance;
	}
	
	//미사일 위치 이동
	public Position multiply(int scale) {
		return new Position(distance*scale);
	}
	
	//미사일 위치 이동
	public Position add(Position p) {
		return new Position(distance+p.distance);
	} 

}
