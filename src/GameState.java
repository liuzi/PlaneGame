
public  class GameState {

	public static GState gamestate=GState.START;
	
	public GState gamestate(){
		return gamestate;
	}
	public static void transform(GState state){

		GameState.gamestate=state;
	}
}
