package client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client implements Runnable{
    Socket MySocket;
    DataOutputStream out = null;

    Client(Socket MySocket) throws IOException {
        this.MySocket=MySocket;
        out=new DataOutputStream(this.MySocket.getOutputStream());
    }
    @Override
    public void run() {
        Scanner reader=new Scanner(System.in);
        String name=null;
        String path=null;
        System.out.print("输入文件路径：");
        path=new String(reader.next());
        File myFile=new File(path);
        if(!myFile.exists()){
            System.out.println("文件不存在");
        }else {
            try {
                name = new String(myFile.getName());
                out.writeUTF(name);
                InputStream input=new FileInputStream(myFile);
                byte[] buff=new byte[1024];
                int len=0;
                while((len = input.read(buff)) !=-1){
                    out.write(buff,0,len);
                }
                input.close();
                out.close();
                System.out.println("文件发送完成");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
