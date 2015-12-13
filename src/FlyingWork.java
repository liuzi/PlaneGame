import java.util.Arrays;
import java.util.Vector;

import javax.swing.JPanel;

public class FlyingWork extends Thread{
	
	public static int numvers=0;
	public static int enemy_num=0;//敌机数量
	private JPanel jPanel;
	private HeroPlane hp;//英雄机
	private Vector<Bant> vecBants=new Vector<Bant>();
	private Vector<Bullet> vectorBullet=new Vector<Bullet>();//子弹集合
	private Vector<EnemyPlane> VectorEnemy=new Vector<EnemyPlane>();//敌机集合
	
	public FlyingWork(JPanel jPanel ){
		//2初始化，对象初始化
		this.jPanel=jPanel;
		hp=new HeroPlane();
		//vectorBullet.add(new Bullet(100,100));
	}
	
	//设置返回子弹集合
	public Vector<Bullet> getvectorBullet() {
		return vectorBullet;
	}
	public void setvectorBullet(Vector<Bullet> vectorBullet) {
		this.vectorBullet = vectorBullet;
	}
	//设置返回英雄机
	public HeroPlane gethp() {
		return hp;
	}
	public void sethp(HeroPlane hp) {
		this.hp = hp;
	}
	//设置返回敌机集合
	public Vector<EnemyPlane> getVector() {
		return VectorEnemy;
	}
	public void setVector(Vector<EnemyPlane> vector) {
		VectorEnemy = vector;
	}
	//爆炸控制
	public Vector<Bant> getVecBants() {
		return vecBants;
	}
	public void setVecBants(Vector<Bant> vecBants) {
		this.vecBants = vecBants;
	}
	//线程
	public void run(){
		while(true){
			//addEnemyPlane();
			if(GameState.gamestate==GState.RUNNING){
				addBullet();//加子弹
				BulletMove();//子弹发射
				addEnemyPlane();//加敌机
				Enemy_Move();//敌机飞行
				allBantMove();//爆炸效果
				HeroPlanemove();//英雄机飞行
				collision();//撞机检测
				HeroCollision();//撞机检测
				checkGameOverAction();//死机检验
		//		bangAction();
			}
	
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//异常捕获
			numvers++;
			for(int i=0;i<VectorEnemy.size();i++){
				VectorEnemy.get(i).setEnemy_numvers(VectorEnemy.get(i).getEnemy_numvers()+1);
				VectorEnemy.get(i).addEnemyBullet();
				//System.out.println(VectorEnemy.get(i).getEnemy_numvers()+"***");
			}
			jPanel.repaint();
		}
		
	}
	
		
	public void checkGameOverAction(){
		if(isGameOver()){    //结束游戏
			GameState.transform(GState.GAMEOVER);
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < VectorEnemy.size();i++){   //撞上了，
			if(hp.hit(VectorEnemy.get(i))){
				hp.subtractLife();          //生命减1
//				hp.setDoubleFire(0);        //火力值清零				
				VectorEnemy.get(i).setState(false);
			}
		}
		return HeroPlane.getLife() <= 0;  //英雄机的命<=0,游戏结束
	}
	
	private void collision(){//判断飞机是否被击中
		//英雄机子弹是否击中敌机
		for(int i=vectorBullet.size()-1;i>=0;i--){
//			if((vectoreFire.get(i).getX()))
			for(int j=VectorEnemy.size()-1;j>=0;j--){
				if(((vectorBullet.get(i).getX()-VectorEnemy.get(j).getX()>-VectorEnemy.get(j).getWidth()/2))&&((vectorBullet.get(i).getX()-VectorEnemy.get(j).getX())<VectorEnemy.get(j).getWidth()/2)){
					if((vectorBullet.get(i).getY()-VectorEnemy.get(j).getY())<VectorEnemy.get(j).getHeight()/2){
						//vectoreFire.remove(i);
						//VectorEnemy.remove(j);
						VectorEnemy.get(j).setState(false);
						vectorBullet.get(i).setState(false);
						//System.out.println("unity");
					}
				}
			}
		}
		
	}
	public void HeroCollision(){
		//敌机是否击中英雄机
		Vector<Bullet> vectorbullet;
			for(int i=0;i<VectorEnemy.size();i++){
				vectorbullet=VectorEnemy.get(i).getvectoreEnemyBullet();
				for(int j=0;j<vectorbullet.size();j++){
					if(((vectorbullet.get(j).getX()-hp.getX()>-hp.getWidth()/2))&&((vectorbullet.get(j).getX()-hp.getX())<hp.getWidth()/2)){
						if((vectorbullet.get(j).getY()-hp.getY())<hp.getHeight()/2){
							hp.subtractLife();
							vectorbullet.remove(j);	
						}
					}
				}
			}	
	}
	
	//子弹轨迹控制
	private void BulletMove() {
		// TODO Auto-generated method stub
		for(int k=vectorBullet.size()-1;k>=0;k--){
			vectorBullet.get(k).move();
			
			if(!vectorBullet.get(k).isState()){
			//	System.out.println(vectorBullet.get(k).isState()+"Bullet");
				vectorBullet.remove(k);
			}
			else if(vectorBullet.get(k).getY()<0){
				vectorBullet.remove(k);
			}
		}
		Vector<Bullet> vectorbullet;
		for(int i=0;i<VectorEnemy.size();i++){
			vectorbullet=VectorEnemy.get(i).getvectoreEnemyBullet();
			for(int j=0;j<vectorbullet.size();j++){
				vectorbullet.get(j).Enemy_move();
				if(vectorbullet.get(j).getY()>MyFrame.hh){
					vectorbullet.remove(j);
				}
			}
			
			//System.out.println(VectorEnemy.get(i).getEnemy_numvers()+"***");
		}
	}
	
	//敌机飞行控制
	private void Enemy_Move() {
		// TODO Auto-generated method stub
		for(int i=0;i<VectorEnemy.size();i++){
			EnemyPlane en=VectorEnemy.get(i);
			en.move();
			if(en.getY()>MyFrame.hh){
				VectorEnemy.remove(i);
				enemy_num--;
				//System.out.println("sss"+i);
			}
			//System.out.println(VectorEnemy.get(i).isState());
			if(!en.isState()){
				enemy_num--;
//				vecBants.add(new Bant(en.getX(), en.getY()));爆炸控制
				VectorEnemy.remove(i);
				HeroPlane.hpNumber+=10;//计算分数
			}
		}
	}
	
	//爆炸效果
	public void allBantMove(){
		if(numvers%10!=0){
			return;
		}
		for(int i=vecBants.size()-1;i>=0;i--){
			Bant b1=vecBants.get(i);
			if (b1.getPNGnumber()>7){
				vecBants.remove(i);
			}
			b1.setPNGnumber(b1.getPNGnumber()+1);
		}
	}
	
	//增加子弹
	private void addBullet(){
//		if(hp.isBullet()&&numvers%50==0){
//			vectorBullet.add(new Bullet(hp.getX(),hp.getY()));
//			numvers=0;
//			hp.setBullet(false);
//		}
		if(hp.isBullet()){
			vectorBullet.add(new Bullet(hp.getX(),hp.getY()));
			hp.setBullet(false);
		}
	}
	
	//增加敌机
	private void addEnemyPlane(){
		if(enemy_num<5){
		int x=(int)(Math.random()*256);
		VectorEnemy.add(new EnemyPlane(x,0));
		enemy_num++;
		}
	}
	
	//英雄机飞行控制
	private void HeroPlanemove(){
		if(hp.isUp()){
			hp.up();
		}
		if(hp.isDown()){
			hp.down();
		}
		if(hp.isLeft()){
			hp.left();
		}
		if(hp.isRight()){
			hp.right();
		}
	}

	public void clean() {
		// TODO Auto-generated method stub
		hp = new HeroPlane();//清理现场
		VectorEnemy = new Vector<EnemyPlane>(null);;
		vectorBullet =new Vector<Bullet>(null);
		HeroPlane.hpNumber=0;
	}

}
