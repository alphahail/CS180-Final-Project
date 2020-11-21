import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.net.*;
import java.nio.Buffer;
import java.io.*;
import java.util.ArrayList;


public class WelcomeScreen extends JFrame  {
    //connect to server
    static String hostname = "localhost";
    static int portNumber = 6174;
    //initial startup page
    static JButton signIn;
    static JButton signUp;
    static JLabel titleLabel;
    static JTextField userName;
    static JTextField password;
    static JFrame myFrame;
    static JPanel myTopPanel;
    static JPanel myMiddlePanel;
    static JPanel myBottomPanel;
    //signup frame
    static JFrame signUpFrame;
    static JPanel signUpMyTopPanel;
    static JPanel signUpMyMiddlePanel;
    static JPanel signUpMyBottomPanel;
    static JTextField signUpUsername;
    static JTextField signUpPassword;
    static JLabel signUpTopPanelLabel;
    static JButton backButton;
    static JButton confirmSignUp;
    //message board
    private static JButton delete;
    private static JButton back;
    private static JTextField deleteMessage;
    private static JButton send;
    private static JTextField message;
    public static Socket socket;
    static ArrayList<String> chats = new ArrayList<>();
    static BufferedReader reader;
    static UserAccount currentUser;
    static String responseForValidUser = "Invalid Account";
    static JFrame chatter;
    static Container content;
    static JTextArea textArea;
    static JPanel top;
    static JPanel bottom;


    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                myFrame = new JFrame("Welcome");
                myFrame.setLayout(new BorderLayout());
                myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                myFrame.setSize(500, 500);
                myFrame.setVisible(true);
                myFrame.getContentPane().setBackground(Color.blue);
                myFrame.setResizable(false);

                signIn = new JButton("Sign in");
                //signIn.setFont(new Font(null, 0, 30));
                signIn.setPreferredSize(new Dimension(150, 40));
                signIn.addActionListener(actionListener);

                signUp = new JButton("Sign up");
                //signUp.setFont(new Font(null, 0, 30));
                signUp.setPreferredSize(new Dimension(150, 40));
                signUp.addActionListener(actionListener);

                password = new JTextField("Password");
                password.setFont(new Font(null, 0, 15));
                password.setMaximumSize(new Dimension(300, 40));

                userName = new JTextField("UserName");
                userName.setFont(new Font(null, 0, 15));
                userName.setMaximumSize(new Dimension(300, 40));

                titleLabel = new JLabel("Please enter your information:");
                titleLabel.setFont(new Font(null, 0, 25));
                myTopPanel = new JPanel();
                myTopPanel.add(titleLabel);

                myBottomPanel = new JPanel();
                myBottomPanel.add(signUp);
                myBottomPanel.add(signIn);

                myMiddlePanel = new JPanel();
                myMiddlePanel.setLayout(new BoxLayout(myMiddlePanel, BoxLayout.Y_AXIS));
                myMiddlePanel.add(userName);
                myMiddlePanel.add(password);
                myMiddlePanel.setBackground(Color.blue);

                myFrame.add(myBottomPanel, BorderLayout.SOUTH);
                myFrame.add(myMiddlePanel, BorderLayout.CENTER);
                myFrame.add(myTopPanel, BorderLayout.NORTH);

                signUpFrame = new JFrame("Sign Up");
                signUpFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                signUpFrame.setSize(500, 500);
                signUpFrame.setResizable(false);

                signUpFrame.setTitle("Sign Up");
                signUpFrame.setLayout(new BorderLayout());
                signUpFrame.getContentPane().setBackground(Color.blue);

                signUpMyTopPanel = new JPanel();
                signUpMyMiddlePanel = new JPanel();
                signUpMyMiddlePanel.setLayout(new BoxLayout(signUpMyMiddlePanel, BoxLayout.Y_AXIS));
                signUpMyMiddlePanel.setBackground(Color.blue);
                signUpMyBottomPanel = new JPanel();

                signUpTopPanelLabel = new JLabel();
                signUpTopPanelLabel.setText("Please enter your information below in order to sign up:");
                signUpTopPanelLabel.setFont(new Font(null, 0, 20));
                signUpMyTopPanel.add(signUpTopPanelLabel);
                signUpTopPanelLabel.setFont(new Font(null, 0, 15));

                signUpUsername = new JTextField("Username:");
                signUpUsername.setFont(new Font(null,0,15));
                signUpMyMiddlePanel.add(signUpUsername);
                signUpUsername.setMaximumSize(new Dimension(300, 40));

                signUpPassword = new JTextField("Password:");
                signUpPassword.setFont(new Font(null,0,15));
                signUpMyMiddlePanel.add(signUpPassword);
                signUpPassword.setMaximumSize(new Dimension(300, 40));

                backButton = new JButton("Back");
                backButton.setSize(new Dimension(signUpMyTopPanel.getWidth(), signUpMyTopPanel.getHeight()));
                backButton.addActionListener(actionListener);
                backButton.setPreferredSize(new Dimension(150, 40));

                confirmSignUp = new JButton("Sign Up");
                confirmSignUp.setSize(new Dimension(signUpMyTopPanel.getWidth(), signUpMyTopPanel.getHeight()));
                confirmSignUp.addActionListener(actionListener);
                signUpMyBottomPanel.add(confirmSignUp);
                confirmSignUp.setPreferredSize(new Dimension(150, 40));
                signUpMyBottomPanel.add(backButton);

                signUpFrame.add(signUpMyMiddlePanel, BorderLayout.CENTER);
                signUpFrame.add(signUpMyBottomPanel, BorderLayout.SOUTH);
                signUpFrame.add(signUpMyTopPanel, BorderLayout.NORTH);
                signUpFrame.setVisible(false);

                //messenger board
                chatter = new JFrame("Conversation");
                chatter.setSize(500, 500);
                chatter.setResizable(false);
                content = chatter.getContentPane();
                content.setLayout(new BorderLayout());
                chatter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                textArea = new JTextArea(10, 20);
                top = new JPanel();
                bottom = new JPanel();
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
                chatter.setVisible(false);
                chatter.setLocationRelativeTo(null);

            }
        });

        try {
            serverClient();
        } catch (UnknownHostException un) {
            JOptionPane.showMessageDialog(null,
                    "A connection with the server couldn't be established.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    static ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signIn) {
                /**
                try {
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    responseForValidUser = reader.readLine();

                } catch (IOException io) {
                    io.printStackTrace();
                }
                 **/
                if (true) {

                    myFrame.setVisible(false);
                    chatter.setVisible(true);
                    //set the currentUser to the value that was checked by the server
                } else if (responseForValidUser.equals("Invalid Account")) {
                    JOptionPane.showMessageDialog(null,"Your username or password was incorrect.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == signUp) {

                myFrame.setVisible(false);
                signUpFrame.setVisible(true);


            }
            if (e.getSource() == backButton) {
                myFrame.setVisible(true);
                signUpFrame.setVisible(false);
            }
            if (e.getSource() == confirmSignUp) {


            }

        }
    };
    /**
    public Messange sendNewMessage (String signature, String message) {

        Messange messageToSend = new Messange(signature, message);

        //need logic to communicate with the server
        return messageToSend;
    }
    **/

    public static UserAccount createAccount(String userName, String password) {
        UserAccount createdUser = new UserAccount(userName, password);
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.write(createdUser.toString());
            writer.flush();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return createdUser;
    }


    public static void serverClient() throws UnknownHostException, IOException {
        socket = new Socket(hostname, portNumber);
    }

}

