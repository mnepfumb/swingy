package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.GUIMap;
import filewriter.ReadFile;
import filewriter.WriteFile;
import model.heroes.Hero;

public class GUIView extends JFrame 
{
    private String player, heroType, artifact, heroData;
    private String[] check = null;
    private int level, attack, defense, hitpoints, xpoints, type; 
    private static final long serialVersionUID = 42L;
    private final JFrame modeFrame = new JFrame("Swingy Mode");  
    private final JFrame welcomeFrame = new JFrame("Swingy");  
    private final JFrame PlayerFrame = new JFrame("Create Player");;
    private final JFrame createFrame = new JFrame("Create Hero");;
    private final JFrame selectFrame = new JFrame("Select Hero");
    private final JFrame statsFrame = new JFrame("Hero Stats");
    private final JFrame gameFrame = new JFrame("GAME PLAYING");
    private static JFrame gameOverFrame = new JFrame("Game Complete");
    private final JRadioButton warrior = new JRadioButton("WARRIOR");
    private final JRadioButton protector = new JRadioButton("PROTECTOR");
    private final JRadioButton master = new JRadioButton("MASTER");
    private final String[] items = ReadFile.ReadLine();
    private final JList heroList = new JList<>(items);
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private JTextField playerName;
    private JTextArea area;
    private JButton welcomeBtn, createPlayer, selectPlayer, modeButton1, modeButton2;
    private Hero hero = new Hero();
    private GUIMap map;

    public GUIView (){}

    public void modeFrame()
    {
        label1 = new JLabel( "SELECT GAME MODE" ); 
        label1.setBounds( 120,100, 100,30 );
        modeButton1 = new JButton( "CONSOLE" );
        modeButton1.setBounds( 100, 220, 100, 30 );
        modeButton2 = new JButton( "CONTINUE GUI" );
        modeButton2.setBounds( 100, 260, 100, 30 ); 

        modeFrame.add( label1 );
        modeFrame.add( modeButton1 ); 
        modeFrame.add( modeButton2 );
        modeFrame.setSize( 350,350 );
        modeFrame.setLocationRelativeTo( null );
        modeFrame.setLayout( null );  
        modeFrame.setVisible( true );
        modeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        modeButton1.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                modeFrame.setVisible( false );
                modeFrame.dispose();
                CLView.begin();
            }
        });

        modeButton2.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                welcomeFrame();
                modeFrame.setVisible( false );
                modeFrame.dispose();
            }
        });
    }

    public void welcomeFrame()
    {
        label1 = new JLabel( "WELCOME TO SWINGY" );  
        label1.setBounds( 70, 50, 200,30 );
        label2 = new JLabel( "PLEASE ENTER YOUR NAME" );  
        label2.setBounds( 60, 90, 200,30 );
        playerName = new JTextField();
        playerName.setBounds( 50, 130, 200, 30 );
        welcomeBtn = new JButton("ENTER");
        welcomeBtn.setBounds( 50, 180, 200, 30 );  
        welcomeFrame.add( label1 ); 
        welcomeFrame.add( label2 ); 
        welcomeFrame.add( playerName );
        welcomeFrame.add( welcomeBtn ); 
        welcomeFrame.setSize( 300,300 );
        welcomeFrame.setLocationRelativeTo( null );
        welcomeFrame.setLayout( null );  
        welcomeFrame.setVisible( true );
        welcomeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        welcomeBtn.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                player = playerName.getText().toString();
                player = player.trim();

                if ( player.length() > 0 )
                {
                    check = player.split( "\\s" );

                    if ( check != null )
                        player = String.join( "_", check );
                    if ( player.isEmpty() )
                        JOptionPane.showMessageDialog( null, "Name cannot be empty!" );
                    else
                    {
                        createFrame();
                        welcomeFrame.setVisible( false );
                        welcomeFrame.dispose();
                    } 
                }
                else
                    JOptionPane.showMessageDialog( null, "Blank spaces not allowed!" );
            }
        });
    }

    public void createPlayer() 
    {
        ButtonGroup group = new ButtonGroup();
        JButton enter;
    
        enter = new JButton( "CONTINUE" );

        warrior.setBounds( 50, 50, 200,30 );
        protector.setBounds( 50, 80, 200,30 );
        master.setBounds( 50, 110, 200,30 );
        enter.setBounds( 50, 140, 200,30 );

        group.add( warrior );
        group.add( protector );
        group.add( master );

        createFrame.add( warrior);
        createFrame.add( protector );
        createFrame.add( master );
        createFrame.add( enter );
        createFrame.setSize( 300,300 );
        createFrame.setLocationRelativeTo( null );
        createFrame.setLayout( null );
        createFrame.setVisible( true );
        createFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        enter.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                if ( master.isSelected() )
                    type = 3;
                else if ( protector.isSelected() )
                    type = 2;
                else if ( warrior.isSelected() )
                    type = 1;
                heroStats();
                createFrame.setVisible( false );
                createFrame.dispose();
            }
        });
    }   

    public void createFrame()
    {
        createPlayer = new JButton( "CREATE PLAYER" );
        createPlayer.setBounds( 60, 40, 200,30 );
        selectPlayer = new JButton( "SELECT PLAYER" );
        selectPlayer.setBounds( 60, 80, 200,30 );
        PlayerFrame.add( createPlayer );
        PlayerFrame.add( selectPlayer );
        PlayerFrame.setSize( 300,300 ); 
        PlayerFrame.setLocationRelativeTo( null );
        PlayerFrame.setLayout( null );  
        PlayerFrame.setVisible( true );
        PlayerFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        createPlayer.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                createPlayer();
                PlayerFrame.dispose();
            }
        });

        selectPlayer.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                selectPlayer();
                PlayerFrame.setVisible( false );
                PlayerFrame.dispose();
            }
        });
    }

    public void selectPlayer()
    {
        JButton enter, exit;

        label1 = new JLabel( "SELECT EXISTING HERO" );
        label1.setBounds( 20, 20, 200, 30 );
        
        heroList.setBounds( 20, 50, 350, 490 );
        enter = new JButton( "Continue" );
        enter.setBounds( 390, 50, 100, 30 );
        exit = new JButton( "Quit Game" );
        exit.setBounds( 390, 105, 100, 30 );

        heroList.addListSelectionListener( new ListSelectionListener()
        {
            @Override
            public void valueChanged( ListSelectionEvent arg0 ) {
                heroData = heroList.getSelectedValue().toString();
                hero = GameView.DBHero( heroData );
            }
        });

        enter.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( heroData == null )
                    JOptionPane.showMessageDialog( null, "Select hero first!" );
                else{
                    playGame();
                    selectFrame.setVisible( false );
                    selectFrame.dispose();
                }
            }
        });

        exit.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e ) {
                selectFrame.dispose();
            }
        });

        selectFrame.add( label1 );
        selectFrame.add( enter );
        selectFrame.add( exit );
        selectFrame.add( heroList );
        selectFrame.setSize( 510, 600 );
        selectFrame.setLocationRelativeTo( null );
        selectFrame.setLayout( null );
        selectFrame.setVisible( true );
        selectFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


    }

    public void heroStats()
    {
        hero = GameView.createHero( player, type );
        JButton enter;

        label8 = new JLabel( "YOUR HERO STATS" );
        label8.setBounds( 50, 50, 200, 30 );
        label1 = new JLabel( "Player: " + player );
        label1.setBounds( 50, 70, 200, 30 );
        label2 = new JLabel( "Hero: " + ( heroType = hero.getHeroStats().getHeroType() ) );
        label2.setBounds( 50, 90, 200, 30 );
        label3 = new JLabel( "Level: " +  ( level = hero.getHeroStats().getLevel() ) );
        label3.setBounds(50, 110, 200, 30 );
        label4 = new JLabel( "Attack: " + ( attack = hero.getHeroStats().getAttack() ) );
        label4.setBounds( 50, 130, 200, 30 );
        label5 = new JLabel( "Defense: " + ( defense = hero.getHeroStats().getDefense() ) );
        label5.setBounds( 50, 150, 200, 30 );
        label6 = new JLabel( "Hitpoints: " + ( hitpoints = hero.getHeroStats().getHitPoints() ) );
        label6.setBounds( 50, 170, 200, 30 );
        label7 = new JLabel( "Artifact: " + ( artifact = hero.getArtifact().getType() ) );
        label7.setBounds( 50, 190, 200, 30 );
        enter = new JButton( "Continue" );
        enter.setBounds( 50, 220, 200, 30 );

        statsFrame.add( label1 );
        statsFrame.add( label2 );
        statsFrame.add( label3 );
        statsFrame.add( label4 );
        statsFrame.add( label5 );
        statsFrame.add( label6 );
        statsFrame.add( label7 );
        statsFrame.add( label8 );
        statsFrame.add( enter );
        statsFrame.setSize( 400, 400 );
        statsFrame.setLocationRelativeTo( null );
        statsFrame.setLayout( null );
        statsFrame.setVisible( true );
        statsFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        enter.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent arg0 ) 
            {
                heroData = hero.getHeroStats().getHeroType() + " " + 
                    player.toString() + " " + hero.getHeroStats().getLevel() + " " +
                    hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                    " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getXPoints() +
                    " " + artifact.toUpperCase();
                
                WriteFile.createFile();
                WriteFile.writeToFile( heroData );
                WriteFile.closeFile();
                playGame();
                statsFrame.setVisible( false );
                statsFrame.dispose();
            }
        });
    }

    public void playGame()
    {
        JButton north, south, east, west;

        label1 = new JLabel( "GAME PLAYING" );
        label1.setBounds( 400,20, 200,30 );
        map = new GUIMap( hero, gameFrame, player );

        area = map.printMap();
        

        north = new JButton( "NORTH" );
        north.setBounds( 20,580, 100, 30 );
        south = new JButton( "SOUTH" );
        south.setBounds( 140,580, 100, 30 );
        west = new JButton( "WEST" );
        west.setBounds( 280,580, 100, 30 );
        east = new JButton( "EAST" );
        east.setBounds( 400,580, 100, 30 );

        north.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e ) {
                map.updateHeroPos( 0, -1 );
            }
        });

        south.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e ) {
                map.updateHeroPos( 0, 1 );
            }
        });

        east.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPos(1, 0);
            }
        });

        west.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
                map.updateHeroPos( -1, 0 );
            }
        });

        area.setBounds( 20,90, 800, 480 );
        gameFrame.add( label1 );
        gameFrame.add( area );
        gameFrame.add( north );
        gameFrame.add( south );
        gameFrame.add( east );
        gameFrame.add( west );
        gameFrame.setSize( 850, 650 );
        gameFrame.setLocationRelativeTo( null );
        gameFrame.setLayout( null );
        gameFrame.setVisible( true );
        gameFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    public static void gameOver()
    {
        JLabel label1;
        JButton close;

        label1 = new JLabel( "WHoop! Game complete." );
        label1.setBounds( 50,40, 200,30 );
        close = new JButton( "Close game" );
        close.setBounds( 60,80, 200,30 );

        close.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent arg0 ) 
            {
                gameOverFrame.dispose();
                System.exit( 0 );
            }
        });

        gameOverFrame.add( label1 );
        gameOverFrame.add( close );
        gameOverFrame.setSize( 300, 300 );
        gameOverFrame.setLocationRelativeTo( null );
        gameOverFrame.setLayout( null );
        gameOverFrame.setVisible( true );
        gameOverFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
