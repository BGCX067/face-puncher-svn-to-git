package rpstudio.game;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;
import android.opengl.GLU;
import android.opengl.Matrix;


public class Graphics
{
	private GL10 mGL;

	private FloatBuffer mVertexBuffer;

	private ByteBuffer mIndexBuffer;

	private int mWindowWidth;

	private int mWindowHeight;

	public Graphics( GL10 gl )
	{
		mGL = gl;
		this.initialize();
	}

	public int getWindowWidth()
	{
		return mWindowWidth;
	}

	public int getWindowHeight()
	{
		return mWindowHeight;
	}

	public void clear( float red, float green, float blue )
	{
		mGL.glClearColor( red, green, blue, 1 );
		mGL.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
	}

	public void drawTexture( Texture texture, Point position )
	{
		Point size = new Point( texture.getWidth(), texture.getHeight() );
		this.drawTexture( texture, position, size );
	}

	public void drawTexture( Texture texture, Point position, Point size )
	{
		float[] transform = new float[16];
		Matrix.setIdentityM( transform, 0 );
		Matrix.translateM( transform, 0, position.x, position.y, 0 );
		Matrix.scaleM( transform, 0, size.x, size.y, 1 );
		texture.draw( this, transform );
	}

	GL10 getGL()
	{
		return mGL;
	}

	void setGL( GL10 gl )
	{
		mGL = gl;
	}

	FloatBuffer getVertexBuffer()
	{
		return mVertexBuffer;
	}

	ByteBuffer getIndexBuffer()
	{
		return mIndexBuffer;
	}

	void resize( int width, int height )
	{
		if( height == 0 )
		{
			height = 1;
		}

		mWindowWidth = width;
		mWindowHeight = height;

		mGL.glViewport( 0, 0, width, height );

		mGL.glMatrixMode( GL10.GL_PROJECTION );
		mGL.glLoadIdentity();
		GLU.gluOrtho2D( mGL, 0, width, height, 0 );

		mGL.glMatrixMode( GL10.GL_MODELVIEW );
		mGL.glLoadIdentity();
	}

	private void initialize()
	{
		mGL.glDisable( GL10.GL_DITHER );
		mGL.glShadeModel( GL10.GL_FLAT );
		mGL.glClearColor( 0.5f, 0.5f, 0.5f, 1 );
		mGL.glClearDepthf( 1 );
		mGL.glEnable( GL10.GL_DEPTH_TEST );
		mGL.glEnable( GL10.GL_TEXTURE_2D );
		mGL.glDepthFunc( GL10.GL_LEQUAL );
		mGL.glEnable( GL10.GL_BLEND );
		mGL.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
		mGL.glHint( GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST );

		float[] vertices = { 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0 };
		ByteBuffer buffer = ByteBuffer.allocateDirect( vertices.length * Float.SIZE / 8 );
		buffer.order( ByteOrder.nativeOrder() );
		mVertexBuffer = buffer.asFloatBuffer();
		mVertexBuffer.put( vertices );
		mVertexBuffer.position( 0 );

		byte[] indices = { 0, 1, 2, 1, 3, 2 };
		mIndexBuffer = ByteBuffer.allocateDirect( indices.length );
		mIndexBuffer.put( indices );
		mIndexBuffer.position( 0 );
	}
}
