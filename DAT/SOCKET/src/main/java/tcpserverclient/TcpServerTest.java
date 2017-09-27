package tcpserverclient;

public class TcpServerTest
{
    public static void main(String[] args) throws Exception
    {
        new TcpServer("0.0.0.0", 9876);
        //new TcpServer("127.0.0.1", 9876);
    }
}