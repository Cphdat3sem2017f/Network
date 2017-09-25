package socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;

public class TcpServer
{
    public TcpServer( int port )
    {
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket( port );

            System.out.println( "Server created..." );
            System.out.println( "Listening on port " + port + "..." );
            
            try
            {
                while (true)
                {
                    System.out.println( "Waiting for connections..." );
                    
                    new ClientHandler( serverSocket.accept() );
                }
            }
            catch (IOException e)
            {
                System.err.println( "Accepting connection failed..." );
            }
        }
        catch (Exception e)
        {
            System.err.println( "Could not listen on port: " + port + "..." );
        }
        finally
        {
            try
            {
                serverSocket.close();
                
                System.err.println( "Closing port: " + port + "..." );
            }
            catch ( IOException e )
            {
                System.err.println( "Could not close port: " + port + "..." );
            }
        }
    }    
}
