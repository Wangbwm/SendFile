import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket server=null;
        Socket OnServer=null;
        server=new ServerSocket(8080);
        System.out.println("Waiting……");
        while(true){
            OnServer=server.accept();
            System.out.println("客户机:"+OnServer.getInetAddress()+"上线");
            Server serverFunction=new Server(OnServer);
            Thread serverThread=new Thread(serverFunction,"服务器线程");
            serverThread.start();
        }
    }
}
