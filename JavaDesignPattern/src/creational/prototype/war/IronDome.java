package creational.prototype.war;

//복제가 가능한 아이언돔 객체 
public class IronDome extends Missiles {

	private String state = "아이언돔 발사준비";
	
	public void attack() {
		this.state = "공격";
	}
	
	//프로토타입 객체(Missiles)에서 가져온 추상메소드
	@Override
	protected void reset() {
	      this.state = "아이언돔 재발사";
	}

	@Override
	public String toString() {
		return "IronDome [state=" + state + "]";
	}

}
