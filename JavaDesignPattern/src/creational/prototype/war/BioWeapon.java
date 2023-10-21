package creational.prototype.war;

//복제불가능한 생화학미사일 객체 
public class BioWeapon extends Missiles {

	
	private String state = "생화학 미사일 발사준비";
	
	
	public void attack() {
		this.state = "생화학 미사일을 발사합니다";
	}
	
	//clone()이 불가하도록 설정 
	@Override
	public Missiles clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("생화학 미사일은 복제가 불가합니다");
	}


	//clone()메소드로인해 불러지지도 않음 
	@Override
	protected void reset() {
		throw new UnsupportedOperationException("재장전이 불가합니다.");
	}


	@Override
	public String toString() {
		return "BioWeapon [state=" + state + "]";
	}
	
	

}
