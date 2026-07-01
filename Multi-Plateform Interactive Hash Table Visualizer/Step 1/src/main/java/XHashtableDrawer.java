import net.thevpc.nuts.io.NOut;
import net.thevpc.nuts.text.NMsg;
public class XHashtableDrawer {
    public void draw(XHashtable hashTable) {
        Node[] table = hashTable.getTable();
        for (int i = 0; i < table.length; i++) {
            StringBuilder str = new StringBuilder();
            str.append(i).append(" --- ");
            Node current = table[i];
            while (current != null) {
                str.append(current.key).append(" ");
                current = current.next;
            }
            NOut.println(NMsg.ofC("##:5:%s##", str));
        }
    }
}
