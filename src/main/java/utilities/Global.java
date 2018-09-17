package utilities;

public class Global 
{
    public static void clearScreen()
    {
		System.out.print( "\033[H\033[2J" );
        System.out.flush();
	}

	public static boolean isNumber( String line )
    {
        for ( char c : line.toCharArray() )
        {
            if ( Character.isDigit( c ) != true)
                return ( false );
        }
        return ( true );
    }
}