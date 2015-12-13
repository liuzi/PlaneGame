import java.util.Arrays;
import java.util.Vector;

import javax.swing.JPanel;

public class FlyingWork extends Thread{
	
	public static int numvers=0;
	public static int enemy_num=0;//�л�����
	private JPanel jPanel;
	private HeroPlane hp;//Ӣ�ۻ�
	private Vector<Bant> vecBants=new Vector<Bant>();
	private Vector<Bullet> vectorBullet=new Vector<Bullet>();//�ӵ�����
	private Vector<EnemyPlane> VectorEnemy=new Vector<EnemyPlane>();//�л�����
	
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
	//���÷��صл�����
	public Vector<EnemyPlane> getVector() {
		return VectorEnemy;
	}
	public void setVector(Vector<EnemyPlane> vector) {
		VectorEnemy = vector;
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
				addBullet();//���ӵ�
				BulletMove();//�ӵ�����
				addEnemyPlane();//�ӵл�
				Enemy_Move();//�л�����
				allBantMove();//��ըЧ��
				HeroPlanemove();//Ӣ�ۻ�����
				collision();//ײ�����
				HeroCollision();//ײ�����
				checkGameOverAction();//��������
		//		bangAction();
			}
	
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//�쳣����
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
		if(isGameOver()){    //������Ϸ
			GameState.transform(GState.GAMEOVER);
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < VectorEnemy.size();i++){   //ײ���ˣ�
			if(hp.hit(VectorEnemy.get(i))){
				hp.subtractLife();          //������1
//				hp.setDoubleFire(0);        //����ֵ����				
				VectorEnemy.get(i).setState(false);
			}
		}
		return HeroPlane.getLife() <= 0;  //Ӣ�ۻ�����<=0,��Ϸ����
	}
	
	private void collision(){//�жϷɻ��Ƿ񱻻���
		//Ӣ�ۻ��ӵ��Ƿ���ел�
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
		//�л��Ƿ����Ӣ�ۻ�
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
	
	//�л����п���
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
//				vecBants.add(new Bant(en.getX(), en.getY()));��ը����
				VectorEnemy.remove(i);
				HeroPlane.hpNumber+=10;//�������
			}
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
	
	//�����ӵ�
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
	
	//���ӵл�
	private void addEnemyPlane(){
		if(enemy_num<5){
		int x=(int)(Math.random()*256);
		VectorEnemy.add(new EnemyPlane(x,0));
		enemy_num++;
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

	public void clean() {
		// TODO Auto-generated method stub
		hp = new HeroPlane();//�����ֳ�
		VectorEnemy = new Vector<EnemyPlane>(null);;
		vectorBullet =new Vector<Bullet>(null);
		HeroPlane.hpNumber=0;
	}

}
