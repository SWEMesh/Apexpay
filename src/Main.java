public class Main {
    public static void main (String[] args){

        WalletService wallet = new WalletService();
        User user1 = new User("U1", "Meshal", "Meshaalxc1@gmail.com", 3000.0);

        wallet.registerUser(user1);

        User user2 = new User("U2", "Suhaib", "Suhaib123123@gmail.com", 1000.0);
        wallet.registerUser(user2);

        User user3 = new User("U3", "Sultan", "SultanMunstashri1@gmail.com", 500.0);
        wallet.registerUser(user3);

        try {

            wallet.transfer("U1", "U2", 1500.0);

        } catch (InsufficientBalanceException e){

            System.out.println("Transfer Failed: " +e.getMessage());
        }


        try {

            wallet.transfer("U3", "U1", 3000.0);

        } catch (InsufficientBalanceException e){

            System.out.println("Transfer Failed: " +e.getMessage());
        }

        user2.setStatus(AccountStatus.FROZEN);
        try {

            wallet.transfer("U2", "U3", 400);

        } catch (AccountNotActiveException e){

            System.out.println("Security Alert: " +e.getMessage());

        }
        System.out.println("------------------------------");
        wallet.printSystemSummary();
        System.out.println("------------------------------");

        wallet.printUserLeger("U1");


    }

}
