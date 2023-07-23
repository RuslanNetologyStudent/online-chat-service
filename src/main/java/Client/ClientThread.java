package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;

    public ClientThread(Socket soc) {
        this.socket = soc;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(in.readLine());
            }

        } catch (Exception e) {
            System.out.println("Всем пока!");
            ;
        }
    }
}