public class UserAccount {
    private String userName;
    private String password;
    private ArrayList<String> chatNames;

    public UserAccount(String userName, String password, ArrayList<String> chatNames) {
        this.userName = userName;
        this.password = password;
	this.chatNames = chatNames;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<String> getChatNames(){
        return this.chatNames;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChatNames(ArrayList<String> chatNames){
        this.chatNames = chatNames;
    }
    
    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
