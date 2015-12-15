import java.util.Random;

public class Bee extends FlyingObject{
	private int abs_x=1;
	private int xSpeed = abs_x;     //x坐标走步步数
	private int ySpeed = 2;     //y坐标走步步数
	private int awardType;      //奖励类型
	
	public Bee(){
		width = 25;
		height = 25;
		Random rand = new Random();
		x = rand.nextInt(380 - this.width);
	    y = -this.height; 
		awardType = rand.nextInt(2);//随机生成奖励类型(0:life++,1:fire++)	
	}
	
	public int getType(){
		return awardType;
	}
    public void step(){   	
    		if(x >= Util.jframex - this.width){
        		xSpeed = -abs_x;
    		
    		}else if(x <= 0){
				xSpeed = abs_x;
			}
    			
    	x += xSpeed;
    	y += ySpeed;	
	}
	
	@Override
	public boolean outOfBounds() {
		return this.y > Util.jframey;  
	}
	

}
