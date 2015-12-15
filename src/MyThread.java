import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//Êó±ê¡¢¼üÅÌ¼àÌýÆ÷Àà

public class MyThread implements MouseMotionListener,MouseListener,KeyListener{
	/*through (FlyingWork) flyingwork's status to control the game*/
	private FlyingWork flyingwork;
    Thread th=null;
   
    public MyThread(FlyingWork flyingwork){
   	 this.flyingwork=flyingwork;
    }
    @Override
   public void mouseDragged(MouseEvent e) {
   	// TODO Auto-generated method stub
   	
   }
    

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getButton());
		switch(GameState.gamestate){		
		case START:
			GameState.transform(GState.RUNNING);
//			flyingwork.interrupt();
			break;
		case GAMEOVER:
			flyingwork.clean();
			GameState.transform(GState.START);
			break;
		default:
			break;
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			flyingwork.gethp().setBullet(false);
		}
//		System.out.println(e.getButton());
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println(e.getButton());
		if(e.getButton()==MouseEvent.BUTTON1){
			flyingwork.gethp().setBullet(true);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(GameState.gamestate == GState.PAUSE){
			GameState.transform(GState.RUNNING);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(GameState.gamestate == GState.RUNNING){
			GameState.transform(GState.PAUSE);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(GameState.gamestate==GState.RUNNING){
			flyingwork.gethp().setX(e.getX());
			flyingwork.gethp().setY(e.getY());
		}
		//System.out.println(flyingwork.gethp().getX());
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(KeyEvent.VK_LEFT);
		if(GameState.gamestate==GState.START||GameState.gamestate==GState.PAUSE){
			GameState.transform(GState.RUNNING);
		}else if(GameState.gamestate==GState.RUNNING){
			int key=e.getKeyCode();
			switch(key){
			case KeyEvent.VK_UP:
				if(!flyingwork.gethp().isDown())
				//System.out.println("shang");
				flyingwork.gethp().setUp(true);
				break;
			case KeyEvent.VK_LEFT:
				if(!flyingwork.gethp().isRight())
				flyingwork.gethp().setLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				if(!flyingwork.gethp().isLeft())
				flyingwork.gethp().setRight(true);
				break;
			case KeyEvent.VK_DOWN:
				if(!flyingwork.gethp().isUp())
				flyingwork.gethp().setDown(true);
				//System.out.println("xia");
				break;
			case KeyEvent.VK_SPACE:
				if(!flyingwork.gethp().isBullet())
					flyingwork.gethp().setBullet(true);
				break;
			default:
				GameState.transform(GState.PAUSE);
		}
		}else if(GameState.gamestate==GState.GAMEOVER){
			flyingwork.clean();
			GameState.transform(GState.START);
		}
		
		}
		
//		if(e.getKeyChar()=='x'&&flyingwork.gethp().isBullet()==true){
//			flyingwork.gethp().setBullet(true);
//		}
//		System.out.println(e.getKeyChar());
//		
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			//if(!flyingwork.gethp().isDown())
			flyingwork.gethp().setUp(false);
			break;
		case KeyEvent.VK_LEFT:
			flyingwork.gethp().setLeft(false);
			break;
		case KeyEvent.VK_RIGHT:
			flyingwork.gethp().setRight(false);
			break;
		case KeyEvent.VK_DOWN:
			flyingwork.gethp().setDown(false);
			break;
		case KeyEvent.VK_SPACE:
			if(flyingwork.gethp().isBullet())
				flyingwork.gethp().setBullet(false);
			break;
		}
		
	}
}
