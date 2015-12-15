import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

public class FlyingWork extends Thread{
	
	public static int numvers=0;
	public static int enemy_num=0;//敌机数量
	public static int time=0;
	private JPanel jPanel;
	private HeroPlane hp;//英雄机
	private Bee award=null;
	private Vector<Bant> vecBants=new Vector<Bant>();//爆炸效果
	private Vector<Bullet> vectorBullet=new Vector<Bullet>();//子弹集合
	private Vector<EnemyPlane> vectorEnemy=new Vector<EnemyPlane>();//敌机集合
	//private SQLConnect sqlconnect=new SQLConnect();
	
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
	//设置返回英雄机
	public Bee getAward() {
		return award;
	}
	public void setAward(Bee award) {
		this.award = award;
	}
	//设置返回敌机集合
	public Vector<EnemyPlane> getVector() {
		return vectorEnemy;
	}
	public void setVector(Vector<EnemyPlane> vector) {
		vectorEnemy = vector;
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
				addBullet();//英雄机发射子弹
				BulletMove();//子弹轨迹控制
				addEnemyPlane();//加敌机
				Enemy_Move();//敌机飞行
				allBantMove();//爆炸效果
				HeroPlanemove();//英雄机飞行
				collision();//英雄机子弹射中检测
				HeroCollision();//敌机子弹射中检测
				checkGameOverAction();//游戏状态检验
				timer();
			}	
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//异常捕获
//			numvers++;
			for(int i=0;i<vectorEnemy.size();i++){
				vectorEnemy.get(i).setEnemy_numvers(vectorEnemy.get(i).getEnemy_numvers()+1);
				vectorEnemy.get(i).addEnemyBullet();
				//System.out.println(vectorEnemy.get(i).getEnemy_numvers()+"***");
			}
			jPanel.repaint();
		}
		
	}
	
	public void timer(){
		time++;
	}
		
	public void checkGameOverAction(){
		if(time%1000==0){
			if(hp.getDoubleFire()>0){
				hp.subDoubleFire();
			}
			time=0;
		}
		if(isGameOver()){    //结束游戏
			SocketClient so=new SocketClient();
			so.connectServer(HeroPlane.hpNumber);
			
			GameState.transform(GState.GAMEOVER);
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < vectorEnemy.size();i++){   //撞上了，
			if(hp.hit(vectorEnemy.get(i))){
				hp.subtractLife();          //生命减1
				hp.resetDoubleFire();       //火力值清零				
				vectorEnemy.get(i).setState(false);
			}
		}
		return HeroPlane.getLife() <= 0;  //英雄机的命<=0,游戏结束
	}
	
	private void collision(){//判断飞机是否被击中
		//英雄机子弹是否击中敌机
		for(int i=vectorBullet.size()-1;i>=0;i--){
			if(award!=null){
				if(award.shootBy(vectorBullet.get(i))){
					if(award.getType()==0){
						hp.addLife();
					}else{
						hp.addDoubleFire();
					}
					award=null;
				}
			}
			for(int j=vectorEnemy.size()-1;j>=0;j--){
				if(vectorEnemy.get(j).shootBy(vectorBullet.get(i))){
//					vectorEnemy.remove(j);
//					vectorBullet.remove(i);
					vectorEnemy.get(j).setState(false);
					vectorBullet.get(i).setState(false);					
				}
			}
		}
		
	}
	public void HeroCollision(){
		//敌机是否击中英雄机
		Vector<Bullet> vectorbullet;
			for(int i=0;i<vectorEnemy.size();i++){
				vectorbullet=vectorEnemy.get(i).getvectoreEnemyBullet();
				for(int j=0;j<vectorbullet.size();j++){
					if(hp.shootBy(vectorbullet.get(j))){
						hp.subtractLife();
						hp.resetDoubleFire();
						//vectorbullet.get(j).setState(false);
						vectorbullet.remove(j);
					}
				}
			}	
	}
	

	
	//英雄机发射子弹
	private void addBullet(){
//		if(hp.isBullet()&&numvers%50==0){
//			vectorBullet.add(new Bullet(hp.getX(),hp.getY()));
//			numvers=0;
//			hp.setBullet(false);
//		}
		if(hp.isBullet()){
			if(hp.getDoubleFire()!=0){
				vectorBullet.add(new Bullet(hp.getX()-hp.width/2,hp.getY()));
				vectorBullet.add(new Bullet(hp.getX()+hp.width/2,hp.getY()));
				hp.setBullet(false);
			}else{
				vectorBullet.add(new Bullet(hp.getX(),hp.getY()));
				hp.setBullet(false);
			}
		}
	}
	
	//增加敌机或奖励
	private void addEnemyPlane(){
		Random rand = new Random();
		int type = rand.nextInt(100);  //生成0到19的随机数
		if(type == 0&&award==null){                //随机数为0，返回bee;否则返回敌机
			award=new Bee();			
		}else{
			if(enemy_num<5){
				int x=(int)(Math.random()*256);
				vectorEnemy.add(new EnemyPlane(x,0));
				enemy_num++;
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
		for(int i=0;i<vectorEnemy.size();i++){
			vectorbullet=vectorEnemy.get(i).getvectoreEnemyBullet();
			for(int j=0;j<vectorbullet.size();j++){
				vectorbullet.get(j).Enemy_move();
				if(vectorbullet.get(j).getY()>MyFrame.hh){
					vectorbullet.remove(j);
				}
			}
			
			//System.out.println(vectorEnemy.get(i).getEnemy_numvers()+"***");
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

	//敌机/奖励飞行控制
		private void Enemy_Move() {
			// TODO Auto-generated method stub
			if(award!=null){
				award.step();
				if(award.outOfBounds()){
					award=null;
				}				
			}
			for(int i=0;i<vectorEnemy.size();i++){
				EnemyPlane en=vectorEnemy.get(i);
				en.move();
				if(en.getY()>MyFrame.hh){
					vectorEnemy.remove(i);
					enemy_num--;
					//System.out.println("sss"+i);
				}
				//System.out.println(vectorEnemy.get(i).isState());
				if(!en.isState()){
					enemy_num--;
					vecBants.add(new Bant(en.getX(), en.getY()));
					vectorEnemy.remove(i);
					HeroPlane.hpNumber+=10;//计算分数
				}
			}
		}
	
	public void clean() {
		// TODO Auto-generated method stub
//		sqlconnect.connect();
//		SocketClient so=new SocketClient();
//		so.connectServer(HeroPlane.hpNumber);
	
		hp = new HeroPlane();//清理现场
		vectorEnemy = new Vector<EnemyPlane>();;
		vectorBullet =new Vector<Bullet>();
		enemy_num=0;
		HeroPlane.hpNumber=0;
	}

}
