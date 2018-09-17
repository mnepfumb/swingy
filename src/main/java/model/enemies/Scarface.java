package model.enemies;

import model.artifacts.Artifact;

public class Scarface  extends Enemy 
{
	private int typeID = 3;

	public Scarface( int level, int attack, int defense, int hitpoints, int xpoints,Artifact artifact )
	{
		super( level, attack, defense, hitpoints, xpoints, artifact );
		super.setTypeID( typeID );
	}

	public int getTypeID()
	{
		return ( this.typeID );
	}
}
