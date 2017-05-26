package al.artofsoul.data;

public class Checkpoint {
	
	private Pllaka pllaka;
	private int xDirection, yDirection;
	
	public Checkpoint (Pllaka pllaka, int xDirection, int yDirection) {
		this.pllaka = pllaka;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Pllaka getPllaka() {
		return pllaka;
	}

	public int getxDirection() {
		return xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}
}
