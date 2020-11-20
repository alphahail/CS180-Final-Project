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
    
    public void chat(ArrayList<String> chats) throws FileNotFoundException {

        JFrame chatter = new JFrame("Conversation");
        Container content = chatter.getContentPane();
        content.setLayout(new BorderLayout());
        chatter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea(10, 20);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        back = new JButton("Return");
        delete = new JButton("Delete");
        deleteMessage = new JTextField("What message would you like to delete?...");
        send = new JButton("Send");
        message = new JTextField("...", 40);

        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < chats.size(); i++) {
            textArea.append("  " + chats.get(i));
            textArea.append("\n");
            textArea.append("\n");
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        }
        top.add(back);
        top.add(delete);
        top.add(deleteMessage);
        bottom.add(send);
        bottom.add(message);
        content.add(bottom, BorderLayout.SOUTH);
        content.add(top, BorderLayout.NORTH);
        content.add(scroll, BorderLayout.CENTER);
        chatter.setSize(500, 500);
        chatter.setVisible(true);
        chatter.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        /*Here will go the implementation for
        * Each Client  */
    }
}
