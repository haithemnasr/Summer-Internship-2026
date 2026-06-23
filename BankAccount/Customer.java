
import java.util.ArrayList;
public class Customer {
    int id;
    String Name;
    int phone;
    ArrayList<BankAccount> accounts;

    public Customer(int id, String Name, int phone){
        this.id=id;
        this.Name=Name;
        this.phone=phone;
        accounts = new ArrayList<>();
    }
    public void addAccount(BankAccount account){
        accounts.add(account);
    }

}
