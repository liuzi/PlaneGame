
public class Bullet extends FlyingObject {
	/**
	 * @param args
	 */
	//private int speed = 3;  //�ӵ��ٶ�
	private boolean state;
	//Ĭ���ӵ���䱳��
	public Bullet(int x,int y){
	    super(x,y);
	  
		this.width=15;
		this.height=18;
		state=true;
   }
	//�����ӵ���䱳��
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
   
  

   //�ҷ��ɻ��ӵ��ٶ�
   public void move(){
	   
	   y-=3.5;
   }
   //�з��ɻ��ӵ��ٶ�
   public void Enemy_move(){
	   
	   y+=3;
   }
   @Override
   public boolean outOfBounds() {
	// TODO Auto-generated method stub
	   return this.y < -this.height;
   }
}
