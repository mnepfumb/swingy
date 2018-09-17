package filewriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import model.heroes.Hero;

public class ReadFile 
{
	public static int getLinesCount()
	{
		try
		{
			File file = new File( "swingy.txt" );
			FileReader fReader = new FileReader( file );
			LineNumberReader lReader = new LineNumberReader( fReader );
			lReader.skip( Long.MAX_VALUE );
			int count = lReader.getLineNumber();
			lReader.close();
			return ( count );
		}
		catch ( IOException ioe )
		{
			ioe.getMessage();
		}
		return ( -1 );
	}
	public static String[] ReadLine()
	{
		try
		{
			File file = new File( "swingy.txt" );
			FileReader fReader = new FileReader( file );
			BufferedReader bReader = new BufferedReader( fReader );
			String line = null;
			String items[] = new String[getLinesCount()];
			int index = 0;

			while ( ( line = bReader.readLine() ) != null )
			{
				items[index] = line;
				index++;
			}
			bReader.close();
			return ( items );
		}
		catch ( IOException ioe )
		{
			ioe.getMessage();
		}
		return ( null );
	}

	public static void getDBHeroes()
	{
		String items[] = new String[getLinesCount()];
		int index = 0;
		int counter = 1;
		items = ReadLine();

		System.out.print( "\033[H\033[2J" );
		System.out.flush();
		System.out.println( "****** CHOOSE FROM EXISTING HEROES ******\n\n" );

		while ( index < getLinesCount() )
		{
			System.out.println( counter++ + ". " + items[index++] );
		}
	}

	public static String getHero( long hero )
	{
		String items[] = new String[getLinesCount()];
		items = ReadLine();
		return items[( int )hero - 1];
	}

	public static void updateFile( Hero hero )
	{
		try
		{	
			String[] items = ReadLine();
			String deleteLine = null;
			String newLine = null;
			File file = new File( "swingy.txt" );
			FileWriter fWriter = new FileWriter( file );

			for ( String line : items )
			{
				if ( line.contains( hero.getPlayer() ) && line.contains( hero.getHeroStats().getHeroType() ) )
					deleteLine = line;
			}

			newLine = 	( hero.getHeroStats().getHeroType() + " " + hero.getPlayer() + " " + 
						Integer.toString( hero.getHeroStats().getLevel() ) + " " + 
						Integer.toString( hero.getHeroStats().getAttack() ) + " " + 
						Integer.toString( hero.getHeroStats().getDefense() ) + " " + 
						Integer.toString( hero.getHeroStats().getHitPoints() ) + " " +
						Integer.toString( hero.getHeroStats().getXPoints() ) + " " + 
						hero.getArtifact().getType().toUpperCase() );

			if ( newLine != null )
			{
				for ( String line : items )
				{
					if ( line.equals( deleteLine ) )
						fWriter.write( newLine + "\n" );
					else
						fWriter.write( line + "\n" );
				}				
			}
			fWriter.close();
		}
		catch ( IOException ioe )
		{
			System.out.println( "Error updating hero stats in file: " + ioe );
		}
	}
}