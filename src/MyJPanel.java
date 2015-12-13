import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class MyJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FlyingWork flyingwork;
	private MyThread mth;

	
	private static BufferedImage start;
	private static BufferedImage pause;
	private static BufferedImage gameover;
	private static BufferedImage imaheroplane;
	private static BufferedImage imabullet;
	private static BufferedImage imaenemyplane;
	private static BufferedImage imabant;
	
	/*init MyJPanel such as add listen, FlyingWork,MyThread*/
	public MyJPanel(){
		setBackground(Color.black);
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
	
	static{
		try{
			//background = ImageIO.read(MyJPanel.class.getResource("picture/background.png"));
			start = ImageIO.read(MyJPanel.class.getResource("images/start.png"));
			pause = ImageIO.read(MyJPanel.class.getResource("images//pause.png"));
			gameover = ImageIO.read(MyJPanel.class.getResource("images/gameover.png"));
			imaheroplane = ImageIO.read(MyJPanel.class.getResource("images/player.PNG"));
			//bee = ImageIO.read(MyJPanel.class.getResource("images/bee.png"));
			imabullet = ImageIO.read(MyJPanel.class.getResource("images/fire.PNG"));
			imaenemyplane = ImageIO.read(MyJPanel.class.getResource("images/enemy.PNG"));
			imabant=ImageIO.read(MyJPanel.class.getResource("images/bang.PNG"));
			//hero1 = ImageIO.read(MyJPanel.class.getResource("picture/hero1.png"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//3.��ȡԪ�����ݲ���ʾ�����棨���鿪���µķ�����
		showHeroPlane(g);
		showBullet(g);
		showEnemy(g);
		showAllBant(g);
		paintScore(g);
		paintState(g);
	}
	//��״̬
		public void paintState(Graphics g){
		
			switch(GameState.gamestate){
			case START:           //����״̬������ͼ
				g.drawImage(start,0,0,null);
				break;
			case PAUSE:           //��ͣ״̬����ͣͼ
				g.drawImage(pause,0,0,null);
				break;
			case GAMEOVER:       //����״̬������ͼ
				g.drawImage(gameover,0,0,null);
				break;
			default:
				break;
				
			}
		}
	/*show Bullet effect*/
	//���ӵ�
	private void showBullet(Graphics g) {
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
	//���л�
	private void showEnemy(Graphics g) {
		// TODO Auto-generated method stub
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
				g.drawImage(imabullet,x2,y2,f2.getWidth(),f2.getHeight(),this);
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
	public void paintScore(Graphics g){    //���֣�����
//		��ʾ����
		g.setColor(new Color(255,0,0));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		String num="SCORE:"+HeroPlane.hpNumber;
		g.drawString(num, 20, 25);
		g.drawString("LIFE: " + HeroPlane.getLife(),20, 45);				                    
	}
	/*show bant effect*/
	private void showAllBant(Graphics g) {
		Vector<Bant> vb=flyingwork.getVecBants();
		for(int i=vb.size()-1;i>=0;i--){
			Bant bant=vb.get(i);
			int x=bant.getX()-bant.getWidth()/2;
			int y=bant.getY()-bant.getHeight()/2;
//			Image img=new ImageIcon(bant.getDirurl()).getImage();
			g.drawImage(imabant, x, y, x+bant.getWidth(),y+bant.getHeight(),
					65*bant.getPNGnumber(),0,65*(bant.getPNGnumber()+1),65,this);

		}
	}
}
