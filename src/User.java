public class User {
    private String userId;
    private String customerName;
    private String userEmail;
    private double balance;
    private AccountStatus status;


    public User(String userId, String customerName, String userEmail, double balance){

        this.userId = userId;
        this.customerName = customerName;
        this.userEmail = userEmail;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    public String getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void credit(double amount){
        this.balance+= amount;
    }

    public void deduct(double amount){
        this.balance -= amount;
    }
}
