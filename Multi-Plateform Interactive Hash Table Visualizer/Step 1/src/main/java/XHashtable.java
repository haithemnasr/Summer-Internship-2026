public class XHashtable {
    Node[] table;
    int size;
    public XHashtable(int size){
        this.size=size;
        table = new Node[size];
    }
    int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % size;
        }
        return hash;
    }
    public void add(String key) {
        int index = hash(key);
        // check if key already exists
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(key)){
                return;
            }
            current = current.next;
        }
        Node newNode = new Node(key);
        newNode.next = table[index];
        table[index] = newNode;
    }
    public boolean delete(String key){
        int index = hash(key);
        Node current = table[index];
        Node prev = null;
        while (current != null){
            if (current.key.equals(key)){
                if (prev == null){
                    table[index] = current.next;
                }else{
                    prev.next = current.next;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }
    public boolean find(String key){
        int index = hash(key);
        Node current = table[index];
        while (current != null){
            if (current.key.equals(key)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public Node[] getTable() {
        return table;
    }
}
