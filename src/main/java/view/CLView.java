package view;

import java.util.*;
import controller.GameController;
import filewriter.ReadFile;
import model.heroes.Hero;
import utilities.*;

public class CLView 
{

	public static void begin() 
	{
		String player;
		int type, createHero, option = 0, play;
		Hero hero = new Hero();
		
		try
		{
			createHero = PrintMenu.printHeroMenu();
			if ( createHero == 1 )
			{
				player = PrintMenu.printWelcome();
				type = PrintMenu.printHeroSelection();
				hero = GameView.createHero( player, type );
				play = PrintHeroDetails.printDetails( type, player, hero );
				if ( play == 1 )
				{
					GameController.run( hero );
				}
				else
				{
					System.out.println( "****** GOODBYE ******\n\n" );
					System.exit( 0 );
				}
			}
			else if ( createHero == 2 )
			{
				ReadFile.getDBHeroes();
				Scanner scanner = new Scanner( System.in );
				while ( scanner.hasNextLine() )
				{
					String line = scanner.nextLine();
					int linesCount = ReadFile.getLinesCount();
					if ( Global.isNumber( line ) == true )
					{
						try
						{
							int index = Integer.parseInt( line );
							if ( index > 0 && index <= linesCount )
							{
								option = index;
								break;
							}
						}
						catch( Exception e )
						{
							System.out.println( "Invalid input. Try again." );
						}
					}
					else
						System.out.println( "Invalid input. Try again." );
				}
				hero = GameView.DBHero( ReadFile.getHero( option ) );
				GameController.run( hero );
			}
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
	}
}