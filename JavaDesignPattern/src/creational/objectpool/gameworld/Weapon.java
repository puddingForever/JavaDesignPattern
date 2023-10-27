package creational.objectpool.gameworld;

public class Weapon implements Item {

	private int power;
	private String name;
	
	
	public Weapon(String name) {
		this.name = name;
	}

	//poolable 인터페이스의 메소드 오버라이딩 
	@Override
	public void reset() {
		power = 0;
		System.out.println("Weapon이 리셋됬습니다.");
	}

	@Override
	public void attack() {
		System.out.println("현재파워는 " + power + "입니다");
	}

	@Override
	public int checkPower() {
		// TODO Auto-generated method stub
		return this.power;
	}

	@Override
	public void setPower(int power) {
		this.power = power;
	}

}
