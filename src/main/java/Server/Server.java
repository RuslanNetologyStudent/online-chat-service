package Server;

import Logger.Logger;
import Setting.Setting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    final static List<ServerThread> threadList = Collections.synchronizedList(new ArrayList<>());
    public static int port;
    Logger logger = Logger.getInstance();

    public void start() {
        port = Setting.readPort();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server starting...");
            logger.log("Server starting...");
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class ServerThread extends Thread {
        private PrintWriter out;
        private BufferedReader in;

        public ServerThread(Socket socket) {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            String string;
            try {
                String nameClient;
                nameClient = in.readLine();
                logger.log(nameClient);
                sendToAllMessage(nameClient);
                System.out.println(nameClient);
                string = in.readLine();
                sendToAllMessage(string);
                while (string != null) {
                    if (string.equals("/exit")) {
                        logger.log(string);
                    } else {
                        System.out.println(string);
                        logger.log(string);
                        string = in.readLine();
                        sendToAllMessage(string);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private void sendToAllMessage(String message) {
            synchronized (threadList) {
                threadList.forEach(x -> x.sendMessage(message));
            }
        }
    }

}