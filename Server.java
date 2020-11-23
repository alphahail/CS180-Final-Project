import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    static final int PORT = 6174; //Kaprekar's routine final number
    // When relaunching server we should open up the files to get the usernames that are active rn, with an arraylist that encompases it.    	
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();    	
    ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();
    public final String userFile = "users.txt";
    
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket socket = serverSocket.accept();

            new ClientThread(socket).start();
        }
    }

    // Reads the file for account info
    public ArrayList<UserAccount> readNameFile(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				//TODO implement the JOptionPane for error messages, "
			}
		}
        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
        	String line = bfr.readLine();
        	while (line != null) {
        		String[] currentAccount = line.split(" - ");
        		usernames.add(currentAccount[0]);
        		passwords.add(currentAccount[1]);
        		accounts.add(new UserAccount(currentAccount[0], currentAccount[1]));		
        		line = bfr.readLine();
        	}
        } catch (IOException e) {
        	System.out.println("Main File doesn't currently Exist");
        }
        return accounts;
    }

    public boolean signIn(String username, String password) {
    	File f = new File(username);
    	if (!f.exists()) {
    		return false;
    	}
    	try (BufferedReader bfr2 = new BufferedReader(new FileReader(f))) {
			String line2 = bfr2.readLine();
			while {
				String[] accountInfo = line2.split(" - ");
				if (username.equals(accountInfo[0]) && password.equals(accountInfo[1])) {
					return true;
				}
				line2 = bfr2.readLine();
			}
			return false;
		} catch (IOException e) {
        	return false;
		}
    }

    public void addAccount(String username, String password) {
    	File f = new File(userFileFile);
    	String string = username + " - " + password;
    	File f2 = new File(username);
    	try {
    		f2.createNewFile();
    	} catch (IOException e) {
    		return;
    	}
    	writeToFile(userFile, string);
    }

    //assuming we already asked the user if they are sure on the client side
    public void deleteAccount(String username, String password) {
    	File f = new File(userFile);
    	usernames.remove(username);
    	passwords.remove(password);
    	accounts.remove(new UserAccount(username, password));
    	try (PrintWriter pw = new PrintWriter(new FileOutputStream(f))){
			for (int x = 0; x < usernames.size(); x++) {
				pw.println(usernames.get(x) + " - " + passwords.get(x));
			}
		} catch (IOException e) {
			
		}
    }

    //Please format the ArrayList to make it so each line is the proper input for the file. If this isn't easy to do let me know @steve
    //This is to handle most writing into files, since the updating of convos is best done outside of the file and written in. This should add only one line in
    // and will be used to handle adding new lines into the convo
    public void writeToFile(String fileName, String input) {
    	File f = new File(fileName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			//TODO implement the JOptionPane for error messages, "
		}
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
			pw.println(input);
		} catch (IOException e) {
			// not sure what to put here
		}
    }

    // this one should be used to rewrite entire files, such as editing out a single message from the list
    public void writeToFile(String fileName, ArrayList<String> input) {
    	File f = new File(fileName);
		try {
			f.delete();
			f.createNewFile();
		} catch (IOException e) {
			//TODO implement the JOptionPane for error messages, "
		}
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(f))){
			for (int x = 0; x < input.size(); x++) {
				pw.println(input.get(0));
			}
		} catch (IOException e) {
			
		}
    }

    //reads the personal convo files, should be input the convo that they requested to access, maybe do a check with the server side to see if it can verify if file exists?
    public ArrayList<String> getMembers(String convo) {
		File f = new File(convo);
		ArrayList<String> messages = new ArrayList<String>();
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				//TODO implement the JOptionPane for error messages, "
			}
		}
        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
        	String line = bfr.readLine();
        	String[] string = line.split(" - ");
        	for (int x = 0; x < string.length; x++) {
        		messages.add(string[x]);
        	}
        } catch (IOException e) {
        	System.out.println("Convo File doesn't currently Exist");
        	// Change above to joption later
        }
        return messages;
    }

    //depends on how we get this to work
    public void deleteConvo(String convo, String filename) {
    	
    }
    //The starting arrayList should be of all the members in this convo, with the person creating the convo first and anyone else in order of addit
    public void createConvo(ArrayList<String> members) {
    	
    }
    
    public void sendMessage(String message, String convo) {
    	
    }
}
