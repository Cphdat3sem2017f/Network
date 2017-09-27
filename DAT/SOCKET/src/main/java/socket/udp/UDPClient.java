package socket.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient
{
    public static void main(String args[]) throws Exception
    {
        String serverHostname = new String("127.0.0.1");
        int serverPort = 9876;
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        InetAddress IPAddress = InetAddress.getByName(serverHostname);
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter Message: ");
        String sentence = clientInput.readLine();
        sendData = sentence.getBytes();
        System.out.println("Sending data to " + sendData.length + " bytes to server.");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Waiting for return packet");
        clientSocket.setSoTimeout(10000);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        InetAddress returnIPAddress = receivePacket.getAddress();
        System.out.println("Received data from: " + returnIPAddress + ":" + receivePacket.getPort());
        System.out.println("Message: " + modifiedSentence);

        clientSocket.close();
    }
}