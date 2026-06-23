import java.util.LinkedList;

public class XHashtable {
    LinkedList<String>[] table;
    int size;
    public XHashtable(int size){
        this.size=size;
        table = new LinkedList[size];
        for(int i=0; i<size; i++){
            table[i]= new LinkedList<>();
        }
    }
    int hash(String key){
        return (key.hashCode() & 0x7fffffff) % size;
    }
    public void add(String key){
        int index = hash(key);
        if (!table[index].contains(key)) {
            table[index].add(key);
        }
    }
    public boolean delete(String key){
        int index = hash(key);
        return table[index].remove(key);
    }
    public boolean find(String key){
        int index = hash(key);
        return table[index].contains(key);
    }
    public LinkedList<String>[] getTable() {
        return table;
    }
}
