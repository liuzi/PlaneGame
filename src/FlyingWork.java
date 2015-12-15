import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

public class FlyingWork extends Thread{
	
	public static int numvers=0;
	public static int enemy_num=0;//�л�����
	public static int time=0;
	private JPanel jPanel;
	private HeroPlane hp;//Ӣ�ۻ�
	private Bee award=null;
	private Vector<Bant> vecBants=new Vector<Bant>();//��ըЧ��
	private Vector<Bullet> vectorBullet=new Vector<Bullet>();//�ӵ�����
	private Vector<EnemyPlane> vectorEnemy=new Vector<EnemyPlane>();//�л�����
	//private SQLConnect sqlconnect=new SQLConnect();
	
	public FlyingWork(JPanel jPanel ){
		//2��ʼ���������ʼ��
		this.jPanel=jPanel;
		hp=new HeroPlane();
		//vectorBullet.add(new Bullet(100,100));
	}
	
	//���÷����ӵ�����
	public Vector<Bullet> getvectorBullet() {
		return vectorBullet;
	}
	public void setvectorBullet(Vector<Bullet> vectorBullet) {
		this.vectorBullet = vectorBullet;
	}
	//���÷���Ӣ�ۻ�
	public HeroPlane gethp() {
		return hp;
	}
	public void sethp(HeroPlane hp) {
		this.hp = hp;
	}
	//���÷���Ӣ�ۻ�
	public Bee getAward() {
		return award;
	}
	public void setAward(Bee award) {
		this.award = award;
	}
	//���÷��صл�����
	public Vector<EnemyPlane> getVector() {
		return vectorEnemy;
	}
	public void setVector(Vector<EnemyPlane> vector) {
		vectorEnemy = vector;
	}
	//��ը����
	public Vector<Bant> getVecBants() {
		return vecBants;
	}
	public void setVecBants(Vector<Bant> vecBants) {
		this.vecBants = vecBants;
	}
	//�߳�
	public void run(){
		while(true){
			//addEnemyPlane();
			if(GameState.gamestate==GState.RUNNING){
				addBullet();//Ӣ�ۻ������ӵ�
				BulletMove();//�ӵ��켣����
				addEnemyPlane();//�ӵл�
				Enemy_Move();//�л�����
				allBantMove();//��ըЧ��
				HeroPlanemove();//Ӣ�ۻ�����
				collision();//Ӣ�ۻ��ӵ����м��
				HeroCollision();//�л��ӵ����м��
				checkGameOverAction();//��Ϸ״̬����
				timer();
			}	
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//�쳣����
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
		if(isGameOver()){    //������Ϸ
			SocketClient so=new SocketClient();
			so.connectServer(HeroPlane.hpNumber);
			
			GameState.transform(GState.GAMEOVER);
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < vectorEnemy.size();i++){   //ײ���ˣ�
			if(hp.hit(vectorEnemy.get(i))){
				hp.subtractLife();          //������1
				hp.resetDoubleFire();       //����ֵ����				
				vectorEnemy.get(i).setState(false);
			}
		}
		return HeroPlane.getLife() <= 0;  //Ӣ�ۻ�����<=0,��Ϸ����
	}
	
	private void collision(){//�жϷɻ��Ƿ񱻻���
		//Ӣ�ۻ��ӵ��Ƿ���ел�
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
		//�л��Ƿ����Ӣ�ۻ�
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
	

	
	//Ӣ�ۻ������ӵ�
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
	
	//���ӵл�����
	private void addEnemyPlane(){
		Random rand = new Random();
		int type = rand.nextInt(100);  //����0��19�������
		if(type == 0&&award==null){                //�����Ϊ0������bee;���򷵻صл�
			award=new Bee();			
		}else{
			if(enemy_num<5){
				int x=(int)(Math.random()*256);
				vectorEnemy.add(new EnemyPlane(x,0));
				enemy_num++;
			}
		}
		
	}
	
	//�ӵ��켣����
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
	
	
	//��ըЧ��
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
	
	//Ӣ�ۻ����п���
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

	//�л�/�������п���
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
					HeroPlane.hpNumber+=10;//�������
				}
			}
		}
	
	public void clean() {
		// TODO Auto-generated method stub
//		sqlconnect.connect();
//		SocketClient so=new SocketClient();
//		so.connectServer(HeroPlane.hpNumber);
	
		hp = new HeroPlane();//�����ֳ�
		vectorEnemy = new Vector<EnemyPlane>();;
		vectorBullet =new Vector<Bullet>();
		enemy_num=0;
		HeroPlane.hpNumber=0;
	}

}
