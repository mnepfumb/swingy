package model;

public class HeroStats 
{
	private String heroType;
	private int level;
	private int attack;
	private int defense;
	private int hitpoints;
	private int xpoints;
	private int power;

	/** CONSTRUCTOR **/

	public HeroStats(){}

	public HeroStats( String type, int level, int attack, int defense, int hitpoints, int xpoints )
	{
		this.heroType = type;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
		this.hitpoints = hitpoints;
		this.xpoints = xpoints;
		this.power = attack + defense;
	}

	/** GETTERS **/

	public String getHeroType()
	{
		return ( this.heroType );
	}

	public int getLevel()
	{
		return ( this.level );
	}

	public int getAttack()
	{
		return ( this.attack );
	}

	public int getDefense()
	{
		return ( this.defense );
	}

	public int getHitPoints()
	{
		return ( this.hitpoints );
	}

	public int getXPoints()
	{
		return ( this.xpoints );
	}

	public int getPower()
	{
		return ( this.power );
	}

	/** SETTERS **/

	public void setHeroType( String type )
	{
		this.heroType += type;
	}

	public void setLevel( int level )
	{
		this.level = level;
	}

	public void setAttack( int attack )
	{
		this.attack = attack;
	}

	public void setDefense( int defense )
	{
		this.defense += defense;
		if ( this.defense <= 0 )
			this.defense = 0;
	}
	
	public void setHitPoints( int hitpoints )
	{
		this.hitpoints += hitpoints;
		if ( this.hitpoints <= 0 )
			this.hitpoints = 0;
	}

	public void setXPoints( int xpoints )
	{
		this.xpoints = xpoints;
	}

	public void setPower( int power )
	{
		this.power += power;
		if ( this.power <= 0 )
			this.power = 0;
	}
}