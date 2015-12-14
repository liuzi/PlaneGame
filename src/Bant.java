
public class Bant extends FlyingObject{

	private boolean isFire;
	private boolean isType;
	private int PNGnumber;
	public Bant(int x,int y){
		this.x=x;
		this.y=y;
		this.width=Util.bantheight;
		this.height=Util.bantheight;
		isType=true;
		PNGnumber=6;
	}
	
	
	public int getPNGnumber() {
		return PNGnumber;
	}


	public void setPNGnumber(int pNGnumber) {
		PNGnumber = pNGnumber;
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getwidth() {
		return width;
	}
	public void setwidth(int width) {
		this.width = width;
	}
	public int getheight() {
		return height;
	}
	public void setheight(int height) {
		this.height = height; 
	}
	public boolean isFire() {
		return isFire;
	}
	public void setFire(boolean isFire) {
		this.isFire = isFire;
	}
	public boolean isType() {
		return isType;
	}
	public void setType(boolean isType) {
		this.isType = isType;
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
