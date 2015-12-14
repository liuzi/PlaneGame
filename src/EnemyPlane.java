import java.util.Vector;

public class EnemyPlane extends FlyingObject{
	private int enemy_numvers;
	private boolean isBullet;
	private boolean state;
	private Vector<Bullet> vectoreEnemyBullet=new Vector<Bullet>();
	
	/**
	 * @param args
	 */
	public EnemyPlane(int x,int y){
		super(x,y);
		this.width=25;
		this.height=25;
		enemy_numvers=0;
		isBullet=true;
		state=true;
	}
	public EnemyPlane(){
		isBullet=false;
		this.x=100;
		this.y=500;
		this.width=50;
		this.height=50;
		state=true;
    }
	public boolean isBullet() {
		return isBullet;
	}
	public void setBullet(boolean isBullet) {
		this.isBullet = isBullet;
	}
	public Vector<Bullet> getvectoreEnemyBullet() {
		return vectoreEnemyBullet;
	}
	public void setvectoreEnemyBullet(Vector<Bullet> vectoreEnemyBullet) {
		this.vectoreEnemyBullet = vectoreEnemyBullet;
	}
	public void addEnemyBullet(){
		//int x=(int)(Math.random()*256);
		
		if(isBullet()&&enemy_numvers%150==0){
			vectoreEnemyBullet.add(new Bullet(x,y,Util.bulleturl));
			//System.out.println(E.getvectoreEnemyBullet());
			enemy_numvers=0;
	//		E.setBullet(false);
		}				
	}

	public  void move(){
		int x=(int)(Math.random()*100)/40;
		y=y+x;
		
	}
	public int getEnemy_numvers() {
		return enemy_numvers;
	}
	public void setEnemy_numvers(int enemy_numvers) {
		this.enemy_numvers = enemy_numvers;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
}
