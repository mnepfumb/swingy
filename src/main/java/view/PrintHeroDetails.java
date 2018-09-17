package view;

import java.util.Scanner;

import model.heroes.Hero;

public class PrintHeroDetails 
{
	public static int printDetails( long choice, String player, Hero hero ) 
	{
		System.out.println( "***** HERO STATS *****\n\n" );
		if ( choice == 1 )
		{
			System.out.println( "WARRIOR " + player + "\n" +
				"Level: " + hero.getHeroStats().getLevel() + "\n" +
				"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
				"Defense: " + hero.getHeroStats().getDefense() + "\n" +
				"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" +
				"1. Start game\n" +
				"2. Quit game\n" );
		}
		else if ( choice == 2 )
		{
			System.out.println( "PROTECTOR " + player + "\n" +
				"Level: " + hero.getHeroStats().getLevel() + "\n" +
				"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
				"Defense: " + hero.getHeroStats().getDefense() + "\n" +
				"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" +
				"1. Start game\n" +
				"2. Quit game\n" );
		}
		else if ( choice == 3 )
		{
			System.out.println( "MASTER " + player + "\n" +
				"Level: " + hero.getHeroStats().getLevel() + "\n" +
				"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
				"Defense: " + hero.getHeroStats().getDefense() + "\n" +
				"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" +
				"1. Start game\n" +
				"2. Quit game\n" );
		}
		
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
}