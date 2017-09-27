package socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        serverSocket = new ServerSocket(9876);

        System.out.println("Waiting for connection.....");

        clientSocket = serverSocket.accept();

        System.out.println("Connection successful..");
        System.out.println("Waiting for input.....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            out.println(inputLine.toUpperCase());

            if (inputLine.equals("leave"))
            {
                break;
            }
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
