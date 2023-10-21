package creational.prototype.war;

//프로토타입 객체
public abstract class Missiles implements Cloneable{

	private Position position;
	
	//디폴트 생성자 사용시, 미사일 위치 0 
	public Missiles() {
		this.position = Position.ZERO;
	}
	
	//파라메터로 인스턴스 생성시 , 미사일 위치 이동하여 인스턴스 생성
	public Missiles(int distance) {
		position = new Position(distance);
	}
	
	
	//Object 객체의 clone() 메소드 
	@Override
	public Missiles clone() throws CloneNotSupportedException {
		Missiles missile = (Missiles)super.clone(); //자기 자신을 객체로 만들고 
		missile.initialize(); //초기화 
		return missile;
	}
	
	//복사할 객체 초기화 
	protected void initialize() {
		this.position = Position.ZERO;
	}
	
	//반드시 구현해야하는 추상 메소드 (인스턴스간, 고유 특징을 부여)
	protected abstract void reset();
	
	//미사일 이동 
	public void move(Position position,int scale) {
		Position finalMove = position.multiply(scale);
		this.position = this.position.add(finalMove);
	}
	
	//미사일 위치 보여주기 
	public Position getPosition() {
		return this.position;
	}

}
