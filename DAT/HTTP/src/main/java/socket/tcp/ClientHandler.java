package socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread
{
    protected Socket clientSocket;

    public ClientHandler(Socket clientSoc)
    {
        clientSocket = clientSoc;
        start();
    }
    
    public void run()
    {
        System.out.println("New communication thread started...");

        try
        {
            PrintWriter out = new PrintWriter( clientSocket.getOutputStream(), true );
            BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );

            out.println( "Joined the server!" );
            
            String inputLine;
            
            while ( ( inputLine = in.readLine() ) != null )
            {
                System.out.println( "Server: " + inputLine );
                out.println( inputLine );

                if (inputLine.equals( "Leave" ))
                {
                    out.println( "Left the server!" );
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
            
            System.out.println("Communication thread stopped...");
        }
        catch ( IOException e )
        {
            System.err.println( "Problem with Communication Server..." );
        }
    }
}
