package utilities;

import java.util.Random;

public class Enemies 
{
	private static final String enemies[] = { "SCARFACE", "PSYCHO", "GHOST" };

	public static String randomEnemy()
	{
		Random random = new Random();
		return ( enemies[random.nextInt( 3 )] );
	}
}
