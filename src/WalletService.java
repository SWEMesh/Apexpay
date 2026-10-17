import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletService {

    private Map<String, User> users;
    private List<Transaction> ledger;

    public WalletService(){
        this.users = new HashMap<>();
        this.ledger = new ArrayList<>();
    }

    public void registerUser(User user){

        if(user == null)
        throw new IllegalArgumentException("User not found");

        if(users.containsKey(user.getUserId()))
            throw new IllegalArgumentException("User already exists");

        this.users.put(user.getUserId(), user);
    }

    public void deposit(String userId, double amount){

        User user = users.get(userId);
        if( amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.credit(amount);
    }

    public Transaction transfer(String senderId, String receiverId, double amount){

        User sender = users.get(senderId);
        User receiver = users.get(receiverId);

        String transactionID = "WQ-" + (ledger.size() +1);

        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Cannot send money to yourself");
        }
        if(sender == null || receiver == null) {
            throw new IllegalArgumentException("Account was not found !");
        }
        if(sender.getStatus() != AccountStatus.ACTIVE) {

            Transaction failedTx = new Transaction(transactionID, senderId, receiverId, amount, 0.0, TransactionStatus.FAILED, "Sender account is: " + sender.getStatus());
            ledger.add(failedTx);

            throw new AccountNotActiveException("Cannot transfer ! Sender account is: "+sender.getStatus());
        }

        if (sender.getBalance() < amount) {
            Transaction failedTx = new Transaction(transactionID, senderId, receiverId, amount, 0.0, TransactionStatus.FAILED, "Insufficient balance");
            ledger.add(failedTx);
            throw new InsufficientBalanceException("Cannot transfer ! Insufficient funds.");
        }

        sender.deduct(amount);
        receiver.credit(amount);

        Transaction successEx = new Transaction(transactionID, senderId, receiverId, amount, 0.0, TransactionStatus.SUCCESS, null );

        ledger.add(successEx);
        return successEx;
    }

    public void printUserLedger(String userId){

        System.out.println("==== Bank statement for User: "+ userId + " ====");
        for(Transaction ldg : ledger){

            if( ldg.getSenderId().equals(userId)  || ldg.getReceiverId().equals(userId)){

                System.out.println("ID: "+ldg.getTransactionId() +
                        " -> "+ldg.getReceiverId()+
                        "| Amount: "+ldg.getAmount()+ "$" +

                        "| Status: " + ldg.getStatus());
            }
        }
    }

    public void printSystemSummary(){

        for(User user : users.values()){

            System.out.println("ID: "+user.getUserId() +
                    "| Name: " + user.getCustomerName() +
                    "| Balance: "+ user.getBalance()+
                    "| Status: " + user.getStatus());

        }
    }

}

