package view;

import model.HeroStats;
import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import controller.PlayersGenerator;
import filewriter.*;
import model.heroes.Hero;

import utilities.*;

public class GameView 
{
	private static int level;
	private static int attack;
	private static int defense;
	private static int hitpoints; 
	private static int xpoints;
	private static String artifact;
	private static String playerStats;
	private static Hero newHero = new Hero();
	private static Hero dbHero = new Hero();

	public static Hero addHero( String type, String player )
	{
		artifact = Artifacts.randomArtifact();

		if ( artifact.equals( "WEAPON" ) )
		{
			Weapon weapon = new Weapon( "Weapon" );
			level = 1;
			attack = 100 + weapon.getAttack();
			defense = 100;
			hitpoints = 100;
			xpoints = 1000;
			HeroStats heroStats = new HeroStats( type, level, attack, defense, hitpoints, xpoints );
			newHero = PlayersGenerator.newPlayer( type, player, heroStats, weapon );
			playerStats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + xpoints + " " + artifact;
		}
		else if ( artifact.equals( "ARMOR" ) )
		{
			Armor armor = new Armor( "Armor" );
			level = 1;
			attack = 100;
			defense = 100 + armor.getDefense();
			hitpoints = 100;
			xpoints = 1000;
			HeroStats heroStats = new HeroStats( type, level, attack, defense, hitpoints, xpoints );
			newHero = PlayersGenerator.newPlayer( type, player, heroStats, armor );
			playerStats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + xpoints + " " + artifact;
		}
		else if ( artifact.equals( "HELM" ) )
		{
			Helm helm = new Helm( "Helm" );
			level = 1;
			attack = 100 + helm.getHitPoints();
			defense = 100;
			hitpoints = 100;
			xpoints = 1000;
			HeroStats heroStats = new HeroStats( type, level, attack, defense, hitpoints, xpoints );
			newHero = PlayersGenerator.newPlayer( type, player, heroStats, helm );
			playerStats = type + " " + player + " " + level + " " + attack + " " + defense + " " + hitpoints + " " + xpoints + " " + artifact;
		}

		WriteFile.writeToFile(playerStats);
		return ( newHero );
	}

	public static Hero createHero( String player, long type )
	{
			if ( type == 1 )
			{
				return ( addHero( "Warrior", player ) );
			}
			else if ( type == 2 )
			{
				return ( addHero( "Protector", player ) );
			}
			else if ( type == 3 )
			{
				return ( addHero( "Master", player ) );
			}
			else 
				return ( null );
	}

	public static Hero DBHero( String hero )
	{
		String[] items = hero.split( " " );

		String type = items[0];
		String player = items[1];
		int level = Integer.parseInt( items[2] );
		int attack = Integer.parseInt( items[3] );
		int defense = Integer.parseInt( items[4] );
		int hitpoints = Integer.parseInt( items[5] );
		int xpoints = Integer.parseInt( items[6] );
		String artifact = items[7];
		HeroStats heroStats = new HeroStats(type, level, attack, defense, hitpoints, xpoints);

		if ( artifact.equals( "WEAPON" ) )
		{
			Weapon weapon = new Weapon( "Weapon" );
			dbHero = PlayersGenerator.newPlayer( type, player, heroStats, weapon );
		}
		else if ( artifact.equals( "ARMOR" ) )
		{
			Armor armor = new Armor( "Armor" );
			dbHero = PlayersGenerator.newPlayer( type, player, heroStats, armor );
		}
		else if ( artifact.equals( "HELM" ) )
		{
			Helm helm = new Helm( "Helm" );
			dbHero = PlayersGenerator.newPlayer( type, player, heroStats, helm );
		}
		return ( dbHero );
	}
}