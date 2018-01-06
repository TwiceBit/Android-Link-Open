import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
int port = 8888;
        try {

    if(args.length == 1){
        Scanner src = new Scanner(args[0]);
        if(src.hasNextInt()){
            port = src.nextInt();
        }

    }

    ServerSocket server = new ServerSocket(port);
    Socket client = null;
    while(server.isBound()){
        client = server.accept();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(client.isConnected()){
                if (in.ready()){
                    String txt = in.readLine();
                    if(txt != null || !txt.isEmpty()){
                        ArrayList<String> list = new ArrayList<>();
                        list.add("firefox");
                        list.add(txt);
                        new ProcessBuilder(list).start();
                        client.close();
                        break;
                    }
                }
            }


        }catch (Exception e){}



    }



}catch (Exception e){

}





    }
}
