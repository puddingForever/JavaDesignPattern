package creational.prototype.war;

//복제가 가능한 초음속미사일 객체 
public class HyperSonicMissiles extends Missiles {

	private String state = "초음속미사일 발사준비";

	public void attack() {
		this.state = "공격";
	}
	
	//프로토타입 객체에서 가져온 추상메소드 
	@Override
	protected void reset() {
		this.state = "초음속미사일 재발사";
	}

	@Override
	public String toString() {
		return "HyperSonicMissiles [state=" + state + "]";
	}
	
	

}
