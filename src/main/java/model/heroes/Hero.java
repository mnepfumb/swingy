package model.heroes;

import javax.validation.constraints.NotNull;

import model.HeroStats;
import model.artifacts.*;

public class Hero {
    @NotNull
    private String player;
    @NotNull
    private HeroStats heroStats = new HeroStats();
    @NotNull
    private int posx;
    @NotNull
    private int posy;
    @NotNull
    private Artifact artifact;

    public Hero(){}

    protected Hero ( String player, HeroStats heroStats, Artifact artifact )
    {
        this.player = player;
        this.heroStats = heroStats;
        this.artifact = artifact;
    }

    public HeroStats getHeroStats() 
    {
        return ( heroStats );
    }

    public Artifact getArtifact()
    {
        return ( artifact );
    }

    public void upgradeLevel( int level )
    {
        this.heroStats.setLevel( level );
    }

    public String getPlayer()
    {
        return ( this.player );
    }

    public void setArtifact( Artifact artifact )
    {
        this.artifact = artifact;
    }
}
