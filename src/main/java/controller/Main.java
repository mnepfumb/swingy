package controller;

import java.util.*;

import view.*;
import filewriter.WriteFile;

public class Main 
{
	public static void main ( String[] args )
	{
		String line = null;
		int mode = 0;

		try
		{
			WriteFile.createFile();

			switch ( args[0] ) 
			{
               case "console":
					PrintMenu.printMode( args[0] ); //print menu with options 1. create a new hero or 2. select existing hero
					Scanner scanner = new Scanner( System.in );
					while ( scanner.hasNextLine() )
					{
						line = scanner.nextLine();

						if ( line.matches( "\\s*[1-2]\\s*" ) )
						{
							mode = Integer.parseInt( line );

							if ( mode == 1 )
							{
								CLView.begin();
								break;
							}
							else if ( mode == 2 )
							{
								GUIView guiView = new GUIView();
								guiView.welcomeFrame();
								break;
							}
						}
						else
							System.out.println( "Invalid mode selection. Try again." );
					}
                   break;
               case "gui":
					GUIView guiView = new GUIView();
					guiView.modeFrame();
					break;
               default:
					System.out.println( "Invalid mode argument. Try again." );
                   break;
           }
		}
		catch ( Exception e )
		{
			System.out.println( "Game mode selection error: " + e );
		}
		finally
		{
			WriteFile.closeFile();
		}
	}
}