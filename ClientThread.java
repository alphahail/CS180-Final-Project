import java.io.*;
import java.net.Socket;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClientThread extends Thread{
    private Socket socket;
    private JButton delete;
    private JButton back;
    private JTextField deleteMessage;
    private JButton send;
    private JTextField message;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        /*Here will go the implementation for
        * Each Client  */

    }

    public String isValidLogin(String userName, String password) throws IOException {
        File f = new File("users.txt");
        FileReader fos = new FileReader(f);
        BufferedReader br = new BufferedReader(fos);

        String user = br.readLine();
        String tempPass = br.readLine();
        while(user != null && tempPass != null) {
            if (user.equals(userName) && tempPass.equals(password)) {
                return "Valid User";
            }

            user = br.readLine();
            password = br.readLine();
        }
        return "Invalid User";
    }

    public void addUser(String userName, String password) throws FileNotFoundException {
        File f = new File("users.txt");
        FileOutputStream fos = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(fos);

        pw.println(userName);
        pw.println(password);
    }
}
