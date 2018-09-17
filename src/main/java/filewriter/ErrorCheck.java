package filewriter;

public class ErrorCheck 
{
	public static boolean errorCheck( String[] items )
	{
		int index = 0;

		while ( items[index] != null )
		{
			String[] data = items[index].split( "\\s" );
			if ( data.length > 8 || data.length < 8 || data.length == 0 )
				return ( false );
				index++;
		}
		return ( true );
	}
}