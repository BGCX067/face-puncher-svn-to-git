package rpstudio.game;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;


public class GameView extends GLSurfaceView
{
	private GameRenderer mRenderer;

	public GameView( Context context, Game game )
	{
		super( context );

		mRenderer = new GameRenderer( this, game );
		this.setEGLConfigChooser( false );
		this.setRenderer( mRenderer );
	}
	
	@Override
	public boolean onTouchEvent( MotionEvent event )
	{
		mRenderer.onTouchEvent( event );
		return true;
	}
}
