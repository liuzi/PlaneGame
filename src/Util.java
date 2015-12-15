import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
	public static int jframex;
	public static int jframey;
	public static int HeroPlanewidth;
	public static int HeroPlaneheight;
	public static int bantheight;
	public static int bantwidth;
	public static String backgroundurl;
	public static String starturl;
	public static String pauseurl;
	public static String gameoverurl;
	public static String HeroPlaneurl;
	public static String bossurl;
	public static String EnemyPlaneurl;
	public static String bulleturl;
	public static String enemybulleturl;
	public static String banturl;
	public static String awardurl;
	static {
		InputStream in=Util.class.getResourceAsStream("a.txt");
		Properties ps=new Properties();
		System.out.println(in);
		try {
			ps.load(in);
			jframex=Integer.parseInt(ps.getProperty("jframex"));
			jframey=Integer.parseInt(ps.getProperty("jframey"));
			HeroPlanewidth=Integer.parseInt(ps.getProperty("HeroPlanewidth"));
			HeroPlaneheight=Integer.parseInt(ps.getProperty("HeroPlaneheight"));
			bantheight=Integer.parseInt(ps.getProperty("bantheight"));
			bantwidth=Integer.parseInt(ps.getProperty("bantwidth"));
			backgroundurl=ps.getProperty("backgroundurl");
			starturl=ps.getProperty("starturl");
			pauseurl=ps.getProperty("pauseurl");
			gameoverurl=ps.getProperty("gameoverurl");
			HeroPlaneurl=ps.getProperty("HeroPlaneurl");
			bossurl=ps.getProperty("bossurl");
			EnemyPlaneurl=ps.getProperty("EnemyPlaneurl");
			bulleturl=ps.getProperty("bulleturl");
			enemybulleturl=ps.getProperty("enemybulleturl");
			banturl=ps.getProperty("banturl");
			awardurl=ps.getProperty("awardurl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(in);
	}
	public static void fireup(){
		System.out.println("”Œœ∑∆Ù∂Øº”‘ÿ");
	}
}
