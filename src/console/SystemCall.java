package console;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SystemCall extends Thread {
    private ServerSocket sock;
    private String[] dataBase ;

    public void close() throws IOException {
        this.sock.close();
    }

    public SystemCall(int port, int fileSize) throws IOException {
        this.sock = new ServerSocket(port);
        this.dataBase = new String[fileSize];
    }

    public void run() {
        try {
            while (!sock.isClosed()){
                System.out.println("Instantiating SystemCall, waiting for acception");
                Socket connection = sock.accept();
                System.out.println("ACCEPTED. START WORK!");
                Scanner inputScanner = new Scanner(connection.getInputStream());
                PrintWriter outputWriter = new PrintWriter(connection.getOutputStream(), true);
                System.out.println("SCANNERS INITIATED");
                String value = inputScanner.nextLine();
                System.out.println("Request is "+value);
                System.out.println("Trying to split");
                String[] command = value.split(" ");
                outputWriter.println(respond(command));
                System.out.println("My answer is "+respond(command));
                connection.close();
                System.out.println("Closing");
            }
        } catch (IOException e) {
            return;
        }
    }

    private String respond(String[] value){
        System.out.println("Entering respond method");
        switch (value[0]){
            case "size": {
                System.out.println("returning size");
                return String.valueOf(dataBase.length);
            }
            case "write": {
                try {
                    System.out.println("returning write");
                    dataBase[Integer.parseInt(value[1])] = value[2];
                    return "Data's written successfully";
                } catch (NullPointerException | NumberFormatException e){
                    return "ERROR. No such cell available";
                }
            }
            case "read": {
                try {
                    System.out.println("returning read");
                    if (dataBase[Integer.parseInt(value[1])] == null) return "null";
                    return dataBase[Integer.parseInt(value[1])];
                } catch (NullPointerException | NumberFormatException e){
                    return "ERROR. No such cell available";
                }
            }
            default: return "ERROR. Wrong command";
        }
    }
}
