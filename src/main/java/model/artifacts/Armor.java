package model.artifacts;

import model.artifacts.Artifact;

public class Armor extends Artifact 
{
	private int defense = 55;

	public Armor( String type )
	{
		super( type );
	}

	public int getDefense()
	{
		return ( this.defense );
	}
}
