package creational.prototype.war;

public class Client {

	public static void main(String[] args) throws CloneNotSupportedException{
		
		IronDome d1 = new IronDome();
		d1.move(new Position(5), 5);
		d1.attack();
		System.out.println(d1); 
		
		IronDome d2 = (IronDome)d1.clone(); //이미 생성된 아이언돔 객체를 복제 
		System.out.println("복제하여 만든 아이언돔" + d2); 
		
		IronDome d3 = (IronDome)d2.clone();
		d3.reset();
		System.out.println(d3); // 아이언돔 재발사 
		
		HyperSonicMissiles h1 = new HyperSonicMissiles();
		h1.move(new Position(100), 2);
		System.out.println(h1); 
		
		
		HyperSonicMissiles h2 = (HyperSonicMissiles)h1.clone();
		h2.reset();
		System.out.println(h2); //초음속 미사일 재발사 
		
		BioWeapon b1 = new BioWeapon();
		b1.attack();
		System.out.println(b1); //생화학 미사일을 발사
		
		BioWeapon b2 = (BioWeapon)b1.clone(); // 생화학 미사일은 복제가 불가합니다
		b2.reset();
		System.out.println(b2);
		
		
		
		
	}
}
