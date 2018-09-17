package model.enemies;

import model.artifacts.Artifact;
import controller.Map;

public class Enemy 
{
    private int posx;
    private int posy;
    private int level, attack, defense, hitpoints, xpoints, power;
    private Map map;
    private long id;
    private long idCounter = 1;
    private int typeID;
    private Artifact artifact;

    public Enemy() {}

    public Enemy( int level, int attack, int defense, int hitpoints, int xpoints, Artifact artifact )
    {
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.hitpoints = hitpoints;
        this.xpoints = xpoints;
        this.id = nextId();
        this.power = attack + defense;
        this.artifact = artifact;
    }

    private long nextId() 
    {
        idCounter += 1;
        return idCounter;
    }

    public int getEmemyId()
    {
        return ( this.getEmemyId() );
    }
    
    public void setEnemyPos( int posx, int posy )
    {
        this.posx = posx;
        this.posy = posy;
    }

    public int getEnemyPosX()
    {
        return ( this.posx );
    }

    public int getEnemyPosY()
    {
        return ( this.posy );
    }

    public void setTypeID( int id )
    {
        this.typeID = id;
    }

    public int getTypeID()
    {
		return ( this.typeID );
    }
    
    /************ ENEMY STATS ***************/

    	/** GETTERS **/

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
    
    public Artifact getArtifact()
    {
        return ( artifact );
    }

	/** SETTERS **/

    public void setLevel( int level )
    {
		this.level += level;
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
		this.xpoints += xpoints;
	}

    public void setPower( int power )
    {
		this.power = power;
	}

}