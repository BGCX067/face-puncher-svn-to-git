package rpstudio.game;


import java.util.HashMap;

import android.content.res.Resources;


public class ResourceManager
{
	private Graphics mGraphics;

	private Resources mResources;
	
	private HashMap<Integer, Texture> mTextures;

	public ResourceManager( Graphics graphics, Resources resources )
	{
		mGraphics = graphics;
		mResources = resources;
		mTextures = new HashMap<Integer, Texture>();
	}

	public Texture loadTexture( int resourceId )
	{
		Texture texture = mTextures.get( resourceId );
		if( texture == null )
		{
			texture = Texture.load( mGraphics, mResources, resourceId );
			mTextures.put( resourceId, texture );
		}
		
		return texture;
	}
}
