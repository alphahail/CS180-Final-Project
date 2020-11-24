import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is to create a button for every conversation and it also sets a method for what to do when that button is
 * pressed.
 */
public class Conversation {
    private ArrayList<UserAccount> users;
    private JButton convo;
    private String title;


    public Conversation(ArrayList<UserAccount> users, String title) {
        if (users.size() == 1) {
            this.title = users.get(0).getUserName();
        } else {
            this.title = title;
        }
        convo = new JButton(title);
        convo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressed();
            }
        });

    }

    public void buttonPressed() {
        //this happens whenever the button is pressed
    }

    public ArrayList<UserAccount> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserAccount> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JButton getButton() {
        return convo;
    }
}
