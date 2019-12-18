package structures;

public class Silver implements Level{

	int fee;
	@Override
	public void setFee() {
		fee = 20;
		
	}

	@Override
	public int getFee() {
		return fee;
	}
}
