package creational.objectpool.gameworld;

public interface Item extends Poolable {
	
	void attack();

	int checkPower();
	
	void setPower(int power);
}
