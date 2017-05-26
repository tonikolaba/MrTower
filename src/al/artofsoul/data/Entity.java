package al.artofsoul.data;

public interface Entity {
	public float getX();
	public float getY();
	public int getWidth();
	public int getHeight();
	public void setX(float x);
	public void setY(float y);
	public void setWidth(int width);
	public void setHeight(int height);
	public void update();
	public void draw();
}
