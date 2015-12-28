package rpstudio.game;

import android.view.MotionEvent;


public abstract class Game
{
	private ResourceManager mResourceManager;

	private Graphics mGraphics;

	protected ResourceManager getResourceManager()
	{
		return mResourceManager;
	}

	void setResourceManager( ResourceManager resourceManager )
	{
		mResourceManager = resourceManager;
	}

	protected Graphics getGraphics()
	{
		return mGraphics;
	}

	void setGraphics( Graphics graphics )
	{
		mGraphics = graphics;
	}

	protected abstract void initialize();

	protected abstract void load();

	protected abstract void update( GameTime time );

	protected abstract void draw();
	
	protected abstract void onTouchEvent( MotionEvent event );
}
