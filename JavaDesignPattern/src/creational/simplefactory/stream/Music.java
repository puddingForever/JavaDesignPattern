package creational.simplefactory.stream;

public abstract class Music {
	
	private Long id;
	
	private String trend;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTrend() {
		return this.trend;
	}
	
	public void setTrend(String trend) {
		this.trend = trend;
	}


}
