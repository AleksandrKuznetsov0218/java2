package lesson6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    static DataInputStream in;
    static DataOutputStream out;
    static ServerSocket server = null;
    static Socket socket = null;
    static final int PORT = 8189;

    public static void main(String[] args) {


        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен.");
            socket = server.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключился.");
            Server serverS = new Server();

            serverS.SendMsg();
            serverS.recMsg();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendMsg() {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Thread t = new Thread(()->{
            try {
                while (true) {
                    String msg = bfr.readLine();
                    out.writeUTF(msg);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        t.setDaemon(true);
        t.start();

    }

    public void recMsg() {

        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    System.out.println("client: " + msg);
                    if (msg.equals("/end")){
                        System.out.println("Клиент отключился.");
                        socket.close();
                        server.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}




