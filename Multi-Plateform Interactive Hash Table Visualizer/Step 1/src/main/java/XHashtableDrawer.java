import java.util.LinkedList;
import net.thevpc.nuts.io.NOut;
import net.thevpc.nuts.text.NMsg;
public class XHashtableDrawer {

    public void draw(XHashtable hashTable) {
        LinkedList<String>[] table = hashTable.getTable();
        for (int i = 0; i < table.length; i++) {
            StringBuilder str = new StringBuilder();
            str.append(i).append(" -> ");
            for (String value : table[i]) {
                str.append(value).append(" ");
            }
            NOut.println(NMsg.ofC("##:5:%s##", str));
        }
    }
}
