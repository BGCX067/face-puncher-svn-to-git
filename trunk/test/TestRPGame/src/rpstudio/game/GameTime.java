package rpstudio.game;


public class GameTime
{
	public float totalTime;

	public float elapsedTime;

	GameTime( long totalTime, long elapsedTime )
	{
		this.totalTime = totalTime / 1000.0f;
		this.elapsedTime = elapsedTime / 1000.0f;
	}
}
