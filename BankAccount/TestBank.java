public class TestBank {

    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "Jeffry", 12345678);
        BankAccount account1 = new BankAccount(101, "Savings", "Jeffry Account", 1000.0);
        customer1.addAccount(account1);
        account1.deposit(500);
        account1.deposit(-50);
        account1.withdraw(300);
        account1.withdraw(-20);
        account1.withdraw(5000);
        System.out.println("Final Balance: " + account1.getBalance());
    }
}
