package rpstudio.game;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;


public class Texture
{
	private int mTextureId;

	private int mWidth;

	private int mHeight;

	private FloatBuffer mTexCoordBuffer;

	private Texture()
	{
	}

	public int getWidth()
	{
		return mWidth;
	}

	public int getHeight()
	{
		return mHeight;
	}

	static Texture load( Graphics graphics, Resources resources, int resourceId )
	{
		GL10 gl = graphics.getGL();
		Texture texture = new Texture();

		int[] textureIds = new int[1];
		gl.glGenTextures( 1, textureIds, 0 );
		gl.glBindTexture( GL10.GL_TEXTURE_2D, textureIds[0] );
		texture.mTextureId = textureIds[0];

		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE );
		gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE );

		Bitmap bitmap = BitmapFactory.decodeResource( resources, resourceId );
		texture.mWidth = bitmap.getWidth();
		texture.mHeight = bitmap.getHeight();
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
		bitmap.recycle();

		float[] texCoords = { 0, 1, 0, 0, 1, 1, 1, 0 };
		ByteBuffer buffer = ByteBuffer.allocateDirect( texCoords.length * Float.SIZE / 8 );
		buffer.order( ByteOrder.nativeOrder() );
		texture.mTexCoordBuffer = buffer.asFloatBuffer();
		texture.mTexCoordBuffer.put( texCoords );
		texture.mTexCoordBuffer.position( 0 );

		return texture;
	}

	void draw( Graphics graphics, float[] transform )
	{
		GL10 gl = graphics.getGL();
		FloatBuffer vertexBuffer = graphics.getVertexBuffer();
		ByteBuffer indexBuffer = graphics.getIndexBuffer();

		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadMatrixf( transform, 0 );

		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		gl.glEnable( GL10.GL_TEXTURE_2D );
		gl.glBindTexture( GL10.GL_TEXTURE_2D, mTextureId );
		gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, vertexBuffer );
		gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, mTexCoordBuffer );
		gl.glDrawElements( GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, indexBuffer );
		gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
	}
}
