import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
	public static int jframex;
	public static int jframey;
	public static String HeroPlaneurl;
	public static int HeroPlanewidth;
	public static int HeroPlaneheight;
	public static int bantheight;
	public static int bantwidth;
	static {
		InputStream in=Util.class.getResourceAsStream("a.txt");
		Properties ps=new Properties();
		System.out.println(in);
		try {
			ps.load(in);
			jframex=Integer.parseInt(ps.getProperty("jframex"));
			jframey=Integer.parseInt(ps.getProperty("jframey"));
			HeroPlaneurl=ps.getProperty("HeroPlaneurl");
			HeroPlanewidth=Integer.parseInt(ps.getProperty("HeroPlanewidth"));
			HeroPlaneheight=Integer.parseInt(ps.getProperty("HeroPlaneheight"));
			bantheight=Integer.parseInt(ps.getProperty("bantheight"));
			bantwidth=Integer.parseInt(ps.getProperty("bantwidth"));
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
