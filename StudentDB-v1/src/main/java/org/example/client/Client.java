package org.example.client;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  private Socket socket;
  private PrintWriter outputStream;
  private BufferedReader inputStream;
  private BufferedReader reader;

  public Client(Socket socket){
    try {
      this.socket = socket;
      this.outputStream = new PrintWriter(socket.getOutputStream(), true);
      this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.reader = new BufferedReader(new InputStreamReader(System.in));
    } catch (IOException e) {
      closeResources(socket, outputStream, inputStream);
    }
  }

  public static void main(String[] args) throws IOException {
    InetAddress ip = InetAddress.getByName("localhost");
    Socket socket = new Socket(ip, 8000);
    Client client = new Client(socket);
    client.listenForMessages();
    client.sendMessages();
  }

  public void listenForMessages() {
    new Thread(() -> {
      String fromServer;
      while (socket.isConnected()){
        try {
          fromServer = inputStream.readLine();
          System.out.println(fromServer);
        } catch (IOException e) {
          closeResources(socket, outputStream, inputStream);
        }
      }
    }).start();
  }

  public void sendMessages(){
    try {
      while (socket.isConnected()){
        String toServer = reader.readLine();
        outputStream.println(toServer);
        outputStream.flush();
        if (toServer.equals("exit")) {
          closeResources(socket, outputStream, inputStream);
          break;
        }
      }
    } catch (IOException e) {
      closeResources(socket, outputStream, inputStream);
    }
  }

  public void closeResources(Socket socket, PrintWriter writer, BufferedReader reader){
    try {
      if (reader != null)
        reader.close();
      if (writer != null)
        writer.close();
      if (socket != null)
        socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

