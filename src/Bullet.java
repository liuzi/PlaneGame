
public class Bullet extends FlyingObject {
	/**
	 * @param args
	 */
	//private int speed = 3;  //子弹速度
	private boolean state;
	//默认子弹填充背景
	public Bullet(int x,int y){
	    super(x,y);
	  
		this.width=15;
		this.height=18;
		state=true;
   }
	//设置子弹填充背景
	public Bullet(int x,int y,String dirurl){
	    super(x,y);
		this.width=15;
		this.height=18;
		state=true;
   }
   
   public boolean isState() {
	   return state;
   }
   public void setState(boolean state) {
	   this.state = state;
   }
   
  

   //我方飞机子弹速度
   public void move(){
	   
	   y-=3.5;
   }
   //敌方飞机子弹速度
   public void Enemy_move(){
	   
	   y+=3;
   }
   @Override
   public boolean outOfBounds() {
	// TODO Auto-generated method stub
	   return this.y < -this.height;
   }
}
