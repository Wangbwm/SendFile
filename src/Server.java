import java.io.*;
import java.net.Socket;

public class Server implements Runnable{
    Socket MySocket;
    DataInputStream in = null;
    Server(Socket MySocket) throws IOException {
        this.MySocket=MySocket;
        in=new DataInputStream(this.MySocket.getInputStream());
    }
    @Override
    public void run() {

        File myFile=null;
        String name=null;
        try {
            name=in.readUTF();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            myFile=new File(name);
            if(myFile.exists()){
                myFile.delete();
            }
        try {
            myFile.createNewFile();
            OutputStream output=new FileOutputStream(myFile);
            byte[] buff=new byte[1024];
            int len=0;
            while((len = in.read(buff)) !=-1){
                output.write(buff,0,len);
            }
            System.out.println("接收到来自："+MySocket.getInetAddress()+"的文件:"+myFile.getName());
            in.close();
            output.close();
            System.out.println(MySocket.getInetAddress()+"：下线");
            MySocket.close();
        } catch (IOException e) {
            System.out.println(MySocket.getInetAddress()+"：下线");
        }
    }
}
