package model.enemies;

import model.artifacts.Artifact;

public class Ghost extends Enemy 
{
	private int typeID = 1;
	
	public Ghost( int level, int attack, int defense, int hitpoints, int xpoints, Artifact artifact )
	{
		super( level, attack, defense, hitpoints, xpoints, artifact );
		super.setTypeID( typeID );
	}

	public int getTypeID()
	{
		return ( this.typeID );
	}
}
