package view;

import java.util.Scanner;

import utilities.Global;

public class PrintMenu extends PrintHeroDetails
{

	public static String printWelcome()
	{
		Global.clearScreen();
		System.out.println( "****** WELCOME TO SWINGY ******\n\n" +
							"Enter your name to continue\n" );

		String player = null;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			player = scanner.nextLine();
			player = player.trim();
			
			if ( player.length() > 0 )
			{
				String[] check = player.split( "\\s" );
				if ( check != null )
					player = String.join( "_", check );
				break;
			}
			else
				System.out.println( "Invalid input. Try again." );
		}
		
		return player;
	}
	
	public static void printMode( String mode ) 
	{
		Global.clearScreen();
		System.out.println( "****** SWINGY ******\n\n" +
			"Welcome to Swingy \n\n" +
			"Your game mode is "+ mode.toUpperCase() + "\n"+
			"1. Press 1 to continue\n" +
			"2. To change to GUI mode" );
	}

	public static int printHeroMenu() 
	{
		Global.clearScreen();
		System.out.println( "****** SELECT HERO ******\n\n" +
			"1. Create a new hero\n" +
			"2. Selected existing hero\n" );

		int option = 0;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-2]\\s*" ) )
			{
				option = Integer.parseInt( line );
				break;	
			}
			else
				System.out.println( "Invalid input. Try again." );
		}
		
		return option;
	}

	public static int printHeroSelection()
	{
		Global.clearScreen();
		System.out.println( "****** HERO LIST ******\n\n" +
			"1. Warrior\n" +
			"2. Protector\n" +
			"3. Master\n" );

		int option = 0;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-3]\\s*" ) )
			{
				option = Integer.parseInt( line );
				break;	
			}
			else
				System.out.println("Invalid input. Try again.");
		}
		
		return option;
	}

	public static void printDirections()
	{
		System.out.println( "\n\n***** SELECT DIRECTION ******\n\n" +
			"1. North\n" +
			"2. South\n" +
			"3. East\n" +
			"4. West\n" );
	}

	public static void printFight()
	{
		Global.clearScreen();
		System.out.println( "***** FIGHT OPTIONS *****\n\n" +
			"1. Fight\n" +
			"2. Run\n" );

	}
	
}