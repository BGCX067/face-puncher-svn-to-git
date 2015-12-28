package rpstudio.game;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.view.MotionEvent;


class GameRenderer implements Renderer
{
	private GameView mView;

	private Game mGame;

	private long mStartTime;

	private long mCurrentTime;

	public GameRenderer( GameView view, Game game )
	{
		mStartTime = SystemClock.elapsedRealtime();
		mCurrentTime = SystemClock.elapsedRealtime();
		mView = view;
		mGame = game;
	}

	@Override
	public void onDrawFrame( GL10 gl )
	{
		long curTime = SystemClock.elapsedRealtime();
		GameTime time = new GameTime( curTime - mStartTime, curTime - mCurrentTime );
		mCurrentTime = curTime;

		mGame.getGraphics().setGL( gl );
		mGame.update( time );
		mGame.draw();
	}

	@Override
	public void onSurfaceChanged( GL10 gl, int width, int height )
	{
		mGame.getGraphics().setGL( gl );
		mGame.getGraphics().resize( width, height );
	}

	@Override
	public void onSurfaceCreated( GL10 gl, EGLConfig config )
	{
		Graphics graphics = new Graphics( gl );
		graphics.setGL( gl );
		ResourceManager resourceManager = new ResourceManager( graphics, mView.getContext().getResources() );
		mGame.setResourceManager( resourceManager );
		mGame.setGraphics( graphics );

		mGame.getGraphics().setGL( gl );
		mGame.initialize();
		mGame.load();
	}
	
	void onTouchEvent( MotionEvent event )
	{
		mGame.onTouchEvent( event );
	}
}
