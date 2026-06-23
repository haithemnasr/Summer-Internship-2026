import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BankAccount{

    int id;
    String accountName;
    String AccountType;
    double balance;
    ArrayList<Transaction> transactions;
    public BankAccount(int id,String x, String y, double z){
        this.id=id;
        this.AccountType=x;
        this.accountName=y;
        this.balance=z;
        transactions = new ArrayList<>();
    }
    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            Transaction T = new Transaction("Deposit", amount);
            transactions.add(T);

            try(FileWriter Fw = new FileWriter("transactions.txt",true)){
                Fw.write("Deposit "+ amount+ " \n");
            }catch(IOException e){
                System.out.println("error writing logs");
            }
        }else{
        System.out.println("Invalid deposit amount.");
        }
    }
    public double getBalance(){
        return this.balance;
    }

    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Invalid withdrawal amount.");
        }
        else if(balance >= amount){
            balance -= amount;

            Transaction t = new Transaction("Withdraw", amount);
            transactions.add(t);

            try(FileWriter fw = new FileWriter("transactions.txt", true)){
                fw.write("Withdraw " + amount + "\n");
            }
            catch(IOException e){
                System.out.println("error writing logs");
            }
        }
        else{
            System.out.println("Withdrawal denied: insufficient balance.");
        }
    }
}
