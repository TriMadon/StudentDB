package org.example.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {
  private BufferedReader inputStream;
  private PrintWriter outputStream;
  private Socket socket;
  private Student student;
  private StudentDAO studentDAO;
  private volatile String fromClient;
  public ClientHandler(Socket clientSocket) {
    try {
      this.socket = clientSocket;
      this.outputStream = new PrintWriter(socket.getOutputStream(),true);
      this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.studentDAO = new StudentDAO();
      this.fromClient = null;
    } catch (IOException e){
      closeResources(socket, outputStream, inputStream);
    }
  }

  @Override
  public void run() {
    listenForMessages();

    logInStudent();

    List<Course> courses = studentDAO.findCoursesForStudent(student.id());
    sendAndFlush("Hello, " + student.name() + ", Here are your courses and their grades:\n");

    try {
      showCoursesInALoop(courses);
    } catch (InterruptedException e) {
      closeResources(socket, outputStream, inputStream);
      Thread.currentThread().interrupt();
    }
    try {
      // closing resources
      this.inputStream.close();
      this.outputStream.close();

    } catch(IOException e) {
      closeResources(socket, outputStream, inputStream);
    }
  }

  private void logInStudent() {
    while (true) {
      sendAndFlush("Please enter your student id: ");
      int id = Integer.parseInt(receiveData());
      sendAndFlush("Enter password: ");
      String password = receiveData();
      student = studentDAO.findStudentByIdAndPassword(id, password);
      if (student == null)
        sendAndFlush("invalid credentials, try again");
      else break;
    }
  }

  private void showCoursesInALoop(List<Course> courses) throws InterruptedException {
    List<String> courseIds = new ArrayList<>();
    for (Course course : courses)
      courseIds.add(course.id());

    while (socket.isConnected()) {
      sendAndFlush("\n");
      sendAndFlush("course ID  :  grade");
      for (Course course : courses)
        sendAndFlush(course.id() + "       :  " + course.studentGrade());

      sendAndFlush("Please write the course ID you wish to see grades for, or write exit to quit:\nCourse ID: ");
      String courseID = receiveData();

      if (courseID.equals("exit")) {
        closeResources(socket, outputStream, inputStream);
        System.out.println("Student of ID: " + student.id() + " has exited");
        this.join();
      }

      if (!courseIds.contains(courseID))
        sendAndFlush("invalid course ID, try again");
      else {
        sendAndFlush("\n");
        printCourseStatistics(studentDAO, courseID);
      }
    }
  }

  private void sendAndFlush(String message) {
    outputStream.println(message);
    outputStream.flush();
  }

  private void printCourseStatistics(StudentDAO studentDAO, String courseID) {
    sendAndFlush(courseID + " grades:\n"+
        "Average : " + studentDAO.findAverageOfCourse(courseID) + "\n"+
        "Max     : " + studentDAO.findMaxOfCourse(courseID) + "\n"+
        "Min     : " + studentDAO.findMinOfCourse(courseID) + "\n"+
        "Median  : " + studentDAO.findMedianOfCourse(courseID)
        );
  }

  private String receiveData() {
    while (fromClient == null)
      Thread.onSpinWait();
    String message = fromClient;
    fromClient = null;
    return message;
  }

  public void listenForMessages() {
    new Thread(() -> {
      while (socket.isConnected()){
        try {
          fromClient = inputStream.readLine();
          System.out.println(fromClient);
        } catch (IOException e) {
          closeResources(socket, outputStream, inputStream);
        }
      }
    }).start();
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
