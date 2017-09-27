package socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer
{
    public static void main(String args[]) throws Exception
    {
        int serverPort = 9876;
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        DatagramSocket serverSocket = new DatagramSocket(serverPort);

        while (true)
        {
            receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Waiting for datagram packet");
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            System.out.println("From: " + IPAddress + ":" + port);
            System.out.println("Message: " + sentence);

            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}