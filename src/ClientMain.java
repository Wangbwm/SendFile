import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket MySocket=new Socket("127.0.0.1",8080);
        Client clientFunction=new Client(MySocket);
        Thread clientThread=new Thread(clientFunction,"客户端线程");
        clientThread.start();
    }
}
