package solid.openClose;

//Solid class (base class) - closed for modification
public abstract class Subscriber {

 	protected Long subscriberId;

 	protected String address;

 	protected Long phoneNumber;

 	protected int baseRate;

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(int baseRate) {
		this.baseRate = baseRate;
	}
    
	//인터넷 or 핸드폰에 따라 빌이 달라져서 이것만 abstract으로..
	public abstract double calculateBill(); //open for extensions
    
}
