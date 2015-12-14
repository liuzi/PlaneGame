
public abstract class FlyingObject {
	protected int x;//横坐标
	protected int y;//纵坐标
	protected int width;//横向长度
	protected int height;//纵向长度
	
	
	public FlyingObject(){
		 
	 }
	public FlyingObject(int x,int y){
		    this.x=x;
			this.y=y;
	   }
	
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x=x;
		
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y=y;
		
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width=width;
		
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height=height;
		
	}

	//飞行物速度
	public  void step(){
		
	}
	//判断越界
	public abstract boolean outOfBounds();
	
	//判断是否被子弹撞 
	public boolean shootBy(Bullet bullet){
		//this:敌人       other：子弹
		int x1 = this.x;
		int x2 = this.x + this.width;
		int y1 = this.y;
		int y2 = this.y + this.height;
		int x = bullet.x;
		int y = bullet.y;
		return x > x1 && x < x2
				&&
				y > y1 && y < y2;
	}
}
