package lesson6.client;

import java.io.*;
import java.net.Socket;


public class Client {

    static DataInputStream in;
    static DataOutputStream out;
    static final int PORT = 8189;
    static final String IP_ADRESS = "localhost";
    static Socket socket = null;

    public static void main(String[] args) {

        try {
            Client client = new Client();
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            client.SendMsg();
            client.recMsg();

        } catch (IOException e) {
            System.out.println("");

        }
    }

    public void SendMsg() {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                while (true) {
                    String msg = bfr.readLine();
                    out.writeUTF(msg);
                    out.flush();
                    if (msg.equals("/end")) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
//                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void recMsg() {

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Server: " + in.readUTF());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
