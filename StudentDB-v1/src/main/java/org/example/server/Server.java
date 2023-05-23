package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private final ServerSocket serverSocket;

  public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8000);
    Server server = new Server(serverSocket);
    server.startServer();
  }
  public void startServer(){
    try {
      while(!serverSocket.isClosed()) {
        Socket socket = serverSocket.accept();
        System.out.println("A new client is connected");
        Thread thread = new ClientHandler(socket);
        thread.start();
        System.out.println("Thread is assigned");
      }
    } catch (Exception e) {
      closeServerSocket();
    }
  }

  public void closeServerSocket(){
    try {
      if (serverSocket != null)
        serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
