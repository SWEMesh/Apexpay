public class Transaction {
    private String transactionId;
    private String senderId;
    private String receiverId;
    private double amount;
    private double transferFee;
    private TransactionStatus status;
    private String failureReason;


    public Transaction(String transactionId, String senderId, String receiverId, double amount, double transferFee, TransactionStatus status, String failureReason){
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.transferFee = transferFee;
        this.status = TransactionStatus.FAILED;
        this.failureReason = failureReason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public double getAmount() {
        return amount;
    }
}
