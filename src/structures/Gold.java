package structures;

public class Gold implements Level{
	
	int fee;
	@Override
	public void setFee() {
		fee = 10;
		
	}

	@Override
	public int getFee() {
		return fee;
	}

}
