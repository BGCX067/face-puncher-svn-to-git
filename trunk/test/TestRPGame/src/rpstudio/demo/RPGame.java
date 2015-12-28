package rpstudio.demo;


import android.graphics.Point;
import android.view.MotionEvent;
import rpstudio.game.Game;
import rpstudio.game.GameTime;
import rpstudio.game.R;
import rpstudio.game.Texture;


public class RPGame extends Game
{
	private Texture mIconTexture;
	
	private Texture mWomenTexture;

	private Point mIconPosition;
	
	private Point mTargetPosition;
	
	private Point mWomenPosition;

	public RPGame()
	{
		mIconPosition = new Point();
		mTargetPosition = new Point();
		mWomenPosition = new Point();
	}

	@Override
	protected void initialize()
	{

	}

	@Override
	protected void load()
	{
		mIconTexture = this.getResourceManager().loadTexture( R.drawable.icon );
		mWomenTexture = this.getResourceManager().loadTexture( R.drawable.women );
	}
	
	@Override
	protected void update( GameTime time )
	{
		float curX = mIconPosition.x + ( mTargetPosition.x - mIconPosition.x ) * time.elapsedTime * 3;
		float curY = mIconPosition.y + ( mTargetPosition.y - mIconPosition.y ) * time.elapsedTime * 3;
		mIconPosition.x = (int)curX;
		mIconPosition.y = (int)curY;
		
		int halfWindowX = this.getGraphics().getWindowWidth() / 2 - 50;
		int halfWindowY = this.getGraphics().getWindowHeight() / 2 - 50;
		mWomenPosition.x = halfWindowX + (int)( Math.cos( time.totalTime * 5 ) * halfWindowX );
		mWomenPosition.y = halfWindowY + (int)( Math.sin( time.totalTime * 5 ) * halfWindowY );
	}

	@Override
	protected void draw()
	{
		this.getGraphics().clear( 0, 0, 0.5f );
		this.getGraphics().drawTexture( mIconTexture, mIconPosition );
		this.getGraphics().drawTexture( mWomenTexture, mWomenPosition, new Point( 100, 100 ) );
	}

	@Override
	protected void onTouchEvent( MotionEvent event )
	{
		if( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			mTargetPosition.x = (int)event.getX();
			mTargetPosition.y = (int)event.getY();
		}
	}

}
