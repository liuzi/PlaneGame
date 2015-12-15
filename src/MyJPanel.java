import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class MyJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FlyingWork flyingwork;
	private MyThread mth;

	private static BufferedImage imabg;
	private static BufferedImage start;
	private static BufferedImage pause;
	private static BufferedImage gameover;
	private static BufferedImage imaheroplane;
	private static BufferedImage imabullet;
	private static BufferedImage imaenemybullet;
	private static BufferedImage imaenemyplane;
	private static BufferedImage imabant;
	private static BufferedImage imaaward;

	static{
		try {
			imabg=ImageIO.read(MyJPanel.class.getResource(Util.backgroundurl));
			start=ImageIO.read(MyJPanel.class.getResource(Util.starturl));
			pause=ImageIO.read(MyJPanel.class.getResource(Util.pauseurl));
			gameover=ImageIO.read(MyJPanel.class.getResource(Util.gameoverurl));
			imabullet = ImageIO.read(MyJPanel.class.getResource(Util.bulleturl));
			imaenemybullet = ImageIO.read(MyJPanel.class.getResource(Util.enemybulleturl));
			imabant=ImageIO.read(MyJPanel.class.getResource(Util.banturl));
			imaheroplane = ImageIO.read(MyJPanel.class.getResource(Util.HeroPlaneurl));
			imaenemyplane = ImageIO.read(MyJPanel.class.getResource(Util.EnemyPlaneurl));
			imaaward = ImageIO.read(MyJPanel.class.getResource(Util.awardurl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*init MyJPanel such as add listen, FlyingWork,MyThread*/
	public MyJPanel(){
//		setForeground(imabg);
		
		flyingwork=new FlyingWork(this);
		flyingwork.start();
		mth=new MyThread(flyingwork);
		addMouseListener(mth);
		addMouseMotionListener(mth);
		addKeyListener(mth);
	}
	
	public MyThread getMth() {
		return mth;
	}
	public void setMth(MyThread mth) {
		this.mth = mth;
	}
	
	/*rewrite paint*/	
	
	public void paint(Graphics g){
		super.paint(g);
		//3.获取元素数据并显示到界面（建议开启新的方法）
		g.drawImage(imabg,0,0,null); 
		showHeroPlane(g);
		showBullet(g);
		showEnemy(g);
		showAllBant(g);
		paintScore(g);
		paintState(g);
		
	}
	
	//画状态
		public void paintState(Graphics g){
			switch(GameState.gamestate){
			case START:           //启动状态画启动图
				g.drawImage(start,0,0,null);
				break;
			case PAUSE:           //暂停状态画暂停图
				g.drawImage(pause,0,0,null);
				break;
			case GAMEOVER:       //结束状态画结束图
				g.drawImage(gameover,0,0,null);
				g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,45));
				g.drawString(String.valueOf(HeroPlane.hpNumber), 220, 280);
				g.drawString(String.valueOf(HeroPlane.highscore), 220, 320);
				break;
			default:
				break;
				
			}
		}
	/*show Bullet effect*/
	//画子弹
	private void showBullet(Graphics g)  {
		// TODO Auto-generated method stub
		Vector<Bullet> vBullets=flyingwork.getvectorBullet();
		for(Bullet f:vBullets){
			//Bullet f=vBullets.get(i);
			//Image ima=new ImageIcon(imaheroplane;
			int x=f.getX()-f.getWidth()/2;
			int y=f.getY()-f.getHeight()/2;
			g.drawImage(imabullet,x,y,f.getWidth(),f.getHeight(),this);
		}
		
	}
	/*show Enemy effect*/
	//画敌机
	private void showEnemy(Graphics g){
		// TODO Auto-generated method stub
		Bee aw=flyingwork.getAward();
		if(aw!=null){
			g.drawImage(imaaward, aw.x, aw.y, aw.width,aw.height,this);
		}
		Vector<EnemyPlane> Enemys=flyingwork.getVector();
		for( int i=Enemys.size()-1;i>=0;i--){
			EnemyPlane f=Enemys.get(i);
//			Image ima=new ImageIcon(f.getDirurl()).getImage();
			int x=f.getX()-f.getWidth()/2;
			int y=f.getY()-f.getHeight()/2;
			g.drawImage(imaenemyplane,x,y,f.getWidth(),f.getHeight(),this);
			for( int j=f.getvectoreEnemyBullet().size()-1;j>=0;j--){
				Bullet f2=f.getvectoreEnemyBullet().get(j);
//				Image ima2=new ImageIcon(f2.getDirurl()).getImage();
				int x2=f2.getX()-f2.getWidth()/2;
				int y2=f2.getY()-f2.getHeight()/2;
				g.drawImage(imaenemybullet,x2,y2,f2.getWidth(),f2.getHeight(),this);
			}
		}
		
	}
	/*show  score*/
	public void showHeroPlane(Graphics g){
		HeroPlane hp=flyingwork.gethp();
//		Image ima=new ImageIcon(hp.getDirurl()).getImage();		
		int x=hp.getX()-hp.getWidth()/2;
		int y=hp.getY()-hp.getHeight()/2;
		g.drawImage(imaheroplane,x,y,hp.getWidth(),hp.getHeight(),this);
		//System.out.println(wa.getX());
	}	
	public void paintScore(Graphics g){    //画分，画命
//		显示分数
		g.setColor(new Color(255,0,0));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		String num="SCORE:"+HeroPlane.hpNumber;
		g.drawString(num, 20, 25);
		g.drawString("LIFE: " + HeroPlane.getLife(),20, 45);				                    
	}
	//爆炸效果
	/*show bant effect*/
	private void showAllBant(Graphics g) {
		Vector<Bant> vb=flyingwork.getVecBants();
		for(int i=vb.size()-1;i>=0;i--){
			Bant bant=vb.get(i);
			int x=bant.getX()-bant.getWidth()/2;
			int y=bant.getY()-bant.getHeight()/2;	
			g.drawImage(imabant, x, y, x+bant.getWidth(),y+bant.getHeight(),
					65*bant.getPNGnumber(),0,65*(bant.getPNGnumber()+1),65,this);

		}
	}
}
