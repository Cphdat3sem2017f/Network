package tcpserverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient
{
    private Socket clientSocket;
    private PrintWriter toServer;
    private BufferedReader fromServer;

    private String input, output;
    private BufferedReader clientInput;
    
    public TcpClient( String ip, int port ) throws IOException
    {  
        clientInput = new BufferedReader(new InputStreamReader(System.in));
        
        try
        {
            System.out.println("Client message: Attemping to connect to host " + ip + " on port " + port);
            
            clientSocket = new Socket(ip, port);
            toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Client message: Connected to the server!");

            System.out.println("Server message: " + fromServer.readLine());

            System.out.print("Client input: ");

            while ((input = clientInput.readLine()) != null)
            {
                toServer.println(input);

                output = fromServer.readLine();

                System.out.println("Server message: " + output);

                if (output.equals("GOODBYE..."))
                {
                    System.out.println("Client message: Disconnected from the server!" );

                    break;
                }

                System.out.print("Client input: ");
            }
            
            clientInput.close();
            toServer.close();
            fromServer.close();
            clientSocket.close();
        }
        catch (Exception e)
        {
            System.out.println("Client message: Unabe to connect to host: " + ip + " on port " + port);
        }
    }
}