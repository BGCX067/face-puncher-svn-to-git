package rpstudio.demo;


import rpstudio.game.GameView;
import android.app.Activity;
import android.os.Bundle;


public class TestRPGame extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		GameView view = new GameView( this, new RPGame() );
		this.setContentView( view );
	}
}
