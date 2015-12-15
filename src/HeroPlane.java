
public class HeroPlane extends FlyingObject{
	private static int life;    //命
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	public static int hpNumber;//英雄机得分
	public static int highscore;
	private boolean isBullet;
	private int doubleFire; 
	private  int step;//速度控制
	/**
	 * @param args
	 */
	public HeroPlane(){
		isBullet=false;
		doubleFire=0;
		this.x=Util.jframex/2;
		this.y=500;
		this.width=Util.HeroPlanewidth;
		this.height=Util.HeroPlaneheight;
		life = 3; 
		up=false;
		down=false;
		left=false;
		right=false;
		step=2;
		hpNumber=0;
	}
	//方向状态控制
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	
	//开火控制
	public boolean isBullet() {
		return isBullet;
	}
	public void setBullet(boolean isBullet) {
		this.isBullet = isBullet;
	}

	//改变飞行状态，保证不出界
	public void up() {
		// TODO Auto-generated method stub
		if(y>=30)
		y-=step;
		
	}
	public void down() {
		// TODO Auto-generated method stub
		if(y<=560)
		y+=step;
		
	}
	public void left() {
		// TODO Auto-generated method stub
		if(x>30)
		x-=step;
		
	}
	
	public void right() {
		// TODO Auto-generated method stub
		if(x<380)
		x+=step;
		
	}
    
    //加命
    public void addLife(){
    	life++;
    }
    //获取命
    public static int getLife(){
    	return life;
    }
    //减命
    public void subtractLife(){
    	if(life>0){
    		life--;
    	}
    } 
    public void addDoubleFire(){
    	doubleFire += 40;
    }
    public void subDoubleFire(){
    	doubleFire -= 40;
    }
    public void resetDoubleFire(){
    	this.doubleFire = 0;
    }
	public int getDoubleFire(){
		return doubleFire;
	}


	//已控制飞机不出界故返回false
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//被敌机撞
    public boolean hit(FlyingObject other){
    	int x1 = other.x - this.width/2;
    	int x2 = other.x + other.width + this.width/2;
    	int y1 = other.y - this.height/2;
    	int y2 = other.y + other.height + this.height/2;
    	int hx = this.x + this.width/2;
    	int hy = this.y + this.height/2;
    	return hx > x1 && hx < x2
    			&&
    		   hy > y1 && hy < y2;
    }
}
