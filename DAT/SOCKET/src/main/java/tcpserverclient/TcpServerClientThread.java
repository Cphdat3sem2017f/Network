package tcpserverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpServerClientThread extends Thread
{
    private Socket clientSocket;

    public TcpServerClientThread(Socket s)
    {
        clientSocket = s;
        start();
    }
    
    public void run()
    {
        System.out.println("Server log: Client connected...");

        try
        {
            PrintWriter toClient = new PrintWriter( clientSocket.getOutputStream(), true );
            BufferedReader fromClient = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );

            toClient.println("WELCOME...");
            
            String clientInput;
            
            while ( ( clientInput = fromClient.readLine() ) != null )
            {
                System.out.println("Server log: Received input - " + clientInput );

                if (clientInput.startsWith("UPPERCASE#" ))
                {  
                    System.out.println("Server log: Asked for uppercased...");
                    
                    toClient.println("UPPERCASED..." + clientInput.substring(10, clientInput.length()).toUpperCase() );
                }                
                else if (clientInput.equals( "TIME#" ))
                {  
                    System.out.println("Server log: Asked for the time...");
                    
                    toClient.println("THE TIME IS NOW..." );
                }
                else if (clientInput.equals( "LEAVE#" ))
                {
                    System.out.println("Server log: Asked to leave...");
                    System.out.println("Server log: Client disconnected...");
                    
                    toClient.println("GOODBYE..." );
                    
                    break;
                }
                else
                {
                    System.out.println("Server log: Asked for something unknown...");
                    
                    toClient.println( "DO NOT UNDERSTAND..." );    
                }
            }

            toClient.close();
            fromClient.close();
            clientSocket.close();
        }
        catch ( Exception e )
        {
            System.out.println( "Server log: Problem with Communication Server..." );
        }
    }
}