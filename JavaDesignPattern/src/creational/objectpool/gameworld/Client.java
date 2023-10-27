package creational.objectpool.gameworld;

//object pool에서 캐시메모리로 저장된 인스턴스를 가져옴 
public class Client {
	
	public static final ObjectPool<Weapon> weaponPool = new ObjectPool(()->new Weapon("아이언돔"),5);
	
    public static void main(String[] args) {
	
		Weapon w1 = weaponPool.get();
		w1.setPower(100);
		Weapon w2 = weaponPool.get();
		w2.setPower(70);
		
		w1.attack();//100
		w2.attack();//70
		
		weaponPool.release(w1);
		weaponPool.release(w2);
		
		
		
	}
}
