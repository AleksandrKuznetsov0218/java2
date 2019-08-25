package lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler {

    private Server server;
    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(() -> {
//                try {
//                    socket.setSoTimeout(5000);
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                }

                try {
                    // цикл авторизации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split(" ");
//                            String[] token = str.split(" +",3);
                            String newNick = AuthService.getNickByLoginAndPass(token[1], token[2]);
                            if (newNick != null) {
                                sendMSG("/authok "+newNick);
                                nick = newNick;
                                server.subscribe(this);
                                System.out.println("Клиент " + nick + " авторизовался");
                                break;
                            } else {
                                sendMSG("Неверный логин / пароль");
                            }
                        }
                    }
                    //цикл работы
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            sendMSG("/end");
                            break;
                        }

                        if (str.startsWith("/w")) {
                            String[] token = str.split(" +",3);
                            server.broadcastMsg(token[2],nick,token[1]);
                        }else {
                            server.broadcastMsg(str, nick);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    System.out.println("Клиент " + nick + " отключился");
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMSG(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}

