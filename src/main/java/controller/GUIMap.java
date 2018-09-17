package controller;
import java.awt.event.WindowEvent;import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controller.PlayersGenerator;
import model.enemies.Enemy;
import model.heroes.Hero;
import view.GUIView;
import filewriter.*;


public class GUIMap extends JFrame
{
    private static final long serialVersionUID = 42L;
    private static ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
    private static ArrayList<Enemy> tempArray = new ArrayList<Enemy>();;
    private static int mapy, mapx;
    private int[][] map;
    private int size, enemies, xpos, ypos, oldx, oldy, level;
    private Hero hero = new Hero();
    private Enemy enemy = new Enemy();
    private boolean set = false;
    private JTextArea area = new JTextArea();
    private JFrame frame = new JFrame();
    private static String player;

    public GUIMap( Hero hero, JFrame frame, String player )
    {
        this.hero = hero;
        this.frame = frame;
        this.player = player;
    }

    public void setMapSize()
    {
        size = ( hero.getHeroStats().getLevel() - 1 ) * 5 + 10 - ( hero.getHeroStats().getLevel() % 2 );
        mapx = size;
        mapy = size;
        map = new int[size][size];
    }

    public void setEnemies()
    {
        this.enemies = hero.getHeroStats().getLevel() * 8;
        area.append( this.enemies + " enemy number\n" );
    }

    public void setHeroPos()
    {
		int x = 0, y = 0;

        if ( ( size % 2 ) == 1 )
        {
			x = ( int )( size / 2 );
			y = ( int )( size / 2 );
		}
        else if ( ( size % 2 ) == 0 )
        {
			x = ( size / 2 );
			y = ( size / 2 );
		}
        this.xpos = x;
        this.ypos = y;
    }

    public void updateHeroPos( int xpos, int ypos )
    {
        this.oldx = this.xpos;
        this.oldy = this.ypos;
        this.xpos += xpos;
        if ( this.xpos < 0 )
        {
            this.xpos = ( int )( size / 2 );
            upgradeXP( 1 );
            hasWon();
            set = false;
            printMap();
        }      
        else if ( this.xpos >= this.size )
        {
            this.xpos = ( int )( size / 2 );
            upgradeXP( 1 );
            hasWon();
            set = false;
            printMap();
        }
        else
        {
            area.selectAll();
            area.replaceSelection( "" );
            printMap();
        }
        this.ypos += ypos;
        if ( this.ypos < 0 )
        {
            this.ypos = ( int )( size / 2 );
            upgradeXP( 1 );
            hasWon();
            set = false;
            printMap();
        }
        else if ( this.ypos >= this.size )
        {
            this.ypos = ( int )( size / 2 );
            upgradeXP( 1 );
            hasWon();
            set = false;
            printMap();
        }
        else
        {
            area.selectAll();
            area.replaceSelection( "" );
            printMap(); 
        } 
    }



    public JTextArea printMap()
    {
        if ( set == false )
        {
            setMapSize();
            setHeroPos();
            setEnemies();
            createEnemy();        
            set = true;
        }

        /* initialize map array to zeros */
        for ( int y = 0; y < size; y++ )
        {
            for ( int x = 0; x < size; x++ )
            {
                map[y][x] = 0;
            }
        }

        /* initialization for enemy */
        for ( Enemy enemy : enemyArray )
        {
            map[enemy.getEnemyPosY()][enemy.getEnemyPosX()] = enemy.getTypeID();
        }

        /* initialization for hero */
         map[this.ypos][this.xpos] = 4;

         /* check if hero  has crossed paths with enemy */
        for ( Enemy enemy : enemyArray )
        {
            boolean t = crossedEnemy(this.ypos, this.xpos, enemy.getEnemyPosY(), enemy.getEnemyPosX());
            if ( t == true )
            {
                enemyArray.remove( enemy );
                set = false;
                printMapSim();
                break;
            }
                
        }

        area.append( "Level: " + String.valueOf(hero.getHeroStats().getLevel())  + " | " +
        "Power: " + String.valueOf(hero.getHeroStats().getPower()) + " | " +
        "Hitpoints: " + String.valueOf(hero.getHeroStats().getHitPoints()) + " | " +
        "Experience: " + String.valueOf(hero.getHeroStats().getXPoints()) + "\n\n" );

        for ( int y = 0; y < mapy; y++ )
        {
            for ( int x = 0; x < mapx; x++ )
            {
                switch ( map[y][x] )
                {
                    case 0:
                        area.append( "|    |" );
                        break;
                    case 1:
                        area.append( "| x |" );
                        break;
                    case 2:
                        area.append( "| x |" );
                        break;
                    case 3:
                        area.append( "| x |" );
                        break;
                    default:
                        area.append( "| o |" );
                        break;
                }
            }
            area.append( "\n" );
        }

        return area;
    }

    public JTextArea printMapSim()
    {
        if ( set == false )
        {
            setMapSize();
            setEnemies();
            createEnemy();        
            set = true;
        }
        /* initialize map array to zeros */

        for ( int y = 0; y < size; y++ )
        {
            for ( int x = 0; x < size; x++ )
            {
                map[y][x] = 0;
            }
        }
        /* initialization for enemy */
        
        for ( Enemy enemy : enemyArray )
        {
            map[enemy.getEnemyPosY()][enemy.getEnemyPosX()] = enemy.getTypeID();
        }

        /* initialization for hero */
        map[this.ypos][this.xpos] = 4;

        /* check if hero  has crossed paths with enemy */
        for ( Enemy enemy : enemyArray )
        {
            boolean t = crossedEnemy( this.ypos, this.xpos, enemy.getEnemyPosY(), enemy.getEnemyPosX() );
            if ( t == true )
            {
                this.ypos = this.oldy;
                this.xpos = this.oldx;
                set = false;
                printMap();
                break;
            }
                
        }

        area.append( "Level: " + String.valueOf( hero.getHeroStats().getLevel() )  + " | " +
        "Power: " + String.valueOf( hero.getHeroStats().getPower() ) + " | " +
        "Hitpoints: " + String.valueOf( hero.getHeroStats().getHitPoints() ) + " | " +
        "Experience: " + String.valueOf( hero.getHeroStats().getXPoints() ) + "\n\n" );

        for ( int y = 0; y < mapy; y++ )
        {
            for ( int x = 0; x < mapx; x++ )
            {
                switch ( map[y][x] )
                {
                    case 0:
                        area.append( "|    |" );
                        break;
                    case 1:
                        area.append( "| x |" );
                        break;
                    case 2:
                        area.append( "| x |" );
                        break;
                    case 3:
                        area.append( "| x |" );
                        break;
                    default:
                        area.append( "| o |" );
                        break;
                }
            }
            area.append( "\n" );
        }

        return area;
    }

    public static void registerEnemy( Enemy enemy )
    {
        if ( enemyArray.contains( enemy ) )
            return;
        enemyArray.add( enemy );
    }

    public static void removeEnemy( Enemy enemy )
    {
        if ( enemyArray.contains( enemy ) )
            enemyArray.remove( enemy );
    }

    public void createEnemy()
    {
        for ( int i = 0; i < this.enemies; i++ )
        {
            Random random = new Random();
            int eposx = random.nextInt( size );
            int eposy = random.nextInt( size );
            while ( eposy == this.ypos || eposx == this.xpos )
            {
                eposx = random.nextInt( size );
                eposy = random.nextInt( size );
            }
            enemy = PlayersGenerator.newEnemy( hero );
            enemy.setEnemyPos( eposx, eposy );
            registerEnemy( enemy );
        }
    }

    public Enemy getCrossedEnemy()
    {
        for ( int i = 0; i < enemyArray.size(); i++ )
        {
            if ( enemyArray.get( i ).getEnemyPosY() == this.ypos && enemyArray.get( i ).getEnemyPosX() == this.xpos )
                return ( enemyArray.get( i ) );
        }
        return ( null );
    }

    public void upgradeXP( int type )
    {
        if ( type == 1 )
        {
            int xp;
            if ( hero.getHeroStats().getXPoints() < 2450 )
            {
                xp = 2450;
                hero.getHeroStats().setXPoints( xp );
            }
            else if ( hero.getHeroStats().getXPoints() < 4800 )
            {
                xp = 4800;
                hero.getHeroStats().setXPoints( xp );
            }   
            else if ( hero.getHeroStats().getXPoints() < 8050 )
            {
                xp = 8050;
                hero.getHeroStats().setXPoints( xp );
            }
            else if ( hero.getHeroStats().getXPoints() < 12200 )
            {
                xp = 12200;
                hero.getHeroStats().setXPoints( xp );
            }   
            else if ( hero.getHeroStats().getXPoints() == 12200 )
            {
                GUIView.gameOver();
            }
            hasWon();
        }
        else if ( type == 2 )
        {
            hasWon();
        }
    }

    public boolean crossedEnemy( int hy, int hx, int ey, int ex )
    {
        if ( ( hx == ex ) && ( hy == ey ) )
        {
            enemy = getCrossedEnemy();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog( this, "You have crossed paths with an enemy! Yes to fight. No to run.", "Run or Fight?", dialogButton );
            if( dialogResult == 0 )
            {
                if ( fight() == 1 )
                {
                    return ( true );
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "You lost the battle! Bye!" );
                    frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) );
                }
            } 
            else 
            {
                Random random = new Random();
                int run = random.nextInt( 2 ) + 1;
                if ( run == 1 )
                {
                    area.selectAll();
                    area.replaceSelection( "" );
                    area.append( "You have run from the enemy! \n\n" );
                    this.ypos = this.oldy;
                    this.xpos = this.oldx;
                }
                else
                {
                    if ( fight() == 1 )
                    {
                        enemyArray.remove( enemy );
                        upgradeXP( 2 );
                        return ( true );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( null, "You lost the battle! Bye!" );
                        frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) );
                    }
                }
            } 
        }
        return ( false );
    }

    public void hasWon()
    {
        if ( hero.getHeroStats().getXPoints() < 2450 && hero.getHeroStats().getXPoints() > 1000 )
        {
            this.level = 1;
        }
        else if ( hero.getHeroStats().getXPoints() == 2450 )
        {
            this.level = 2;
        }
        else if ( hero.getHeroStats().getXPoints()  > 2450 && hero.getHeroStats().getXPoints() < 4800 )
        {
            this.level = 2;
        }
        else if ( hero.getHeroStats().getXPoints() == 4800 )
        {
            this.level = 3;
        }
        else if ( hero.getHeroStats().getXPoints()  > 4800 && hero.getHeroStats().getXPoints() < 8050 )
        {
            this.level = 3;
        }  
        else if ( hero.getHeroStats().getXPoints() == 8050 )
        {
            this.level = 4;
        }
        else if ( hero.getHeroStats().getXPoints()  > 8050 && hero.getHeroStats().getXPoints() < 12200 )
        {
            this.level = 4;
        }
        else if ( hero.getHeroStats().getXPoints() == 12200 )
        {
            this.level = 5;
        }  

        if ( this.level > hero.getHeroStats().getLevel() )
        {
            hero.getHeroStats().setLevel( this.level );
            ReadFile.updateFile( hero ); 
            JOptionPane.showMessageDialog( null, "You won! You can move to the next level." );
            enemyArray.removeAll( enemyArray );
            area.append( this.level +"\n" );
        }
        else if ( this.level == hero.getHeroStats().getLevel() )
        {
            area.selectAll();
            area.replaceSelection( "" );
            tempArray.addAll( enemyArray );
            enemyArray.removeAll( enemyArray ); 
        }
    }

    public boolean inLuck()
    {
        Random random = new Random();
        int luck = random.nextInt( 2 ) + 1;
        if ( luck == 1 )
            return ( true );
        return ( false );
    }

    public int fight()
    {
        int fight = 0, won = 0, hit = 0;
        Random random = new Random(); 

        if ( inLuck() == true || hero.getHeroStats().getPower() > enemy.getPower() )
        {
            fight = 1;
        }            
        
        if ( hero.getHeroStats().getHitPoints() > 0 )
        {
            while ( hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0 )
            {
                if ( fight == 0 )
                {
                    hit = random.nextInt( 20 ) + 1; 
                    if ( enemy.getHitPoints() > 0 )
                    {
                        hero.getHeroStats().setHitPoints( -hit );
                        ReadFile.updateFile( hero );

                        if ( hero.getHeroStats().getHitPoints() <= 0 )
                        {
                            won = 0;
                            break;
                        }
                        fight = 1;
                    }
                }
                else if ( fight == 1 )
                {
                    hit = random.nextInt( 50 ) + 1; 
                    if ( hero.getHeroStats().getHitPoints() > 0 )
                    {
                        enemy.setHitPoints( -hit );
                        if ( enemy.getHitPoints() <= 0 )
                        {
                            won = 1;
                            break;
                        }                   
                        fight = 0;
                    }
                }  
            }
        }
    else
        JOptionPane.showMessageDialog( null, "hahahaha! not enough points to fight!" );
    return ( won );
    }
}