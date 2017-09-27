package tcpserverclient;

public class TcpClientTest
{
    public static void main(String[] args) throws Exception
    {
        new TcpClient("0.0.0.0", 9876);
        //new TcpClient("127.0.0.1", 9876);
        //new TcpClient("example.com", 9876); //Domain name
        //new TcpClient("111.111.111.111", 9876); //Public IP
    }
}