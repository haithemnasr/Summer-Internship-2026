import net.thevpc.nuts.io.NIn;
import net.thevpc.nuts.io.NOut;
import net.thevpc.nuts.text.NMsg;

public class AppShell {
    XHashtable table;
    XHashtableDrawer drawer;

    public AppShell() {
        table = new XHashtable(10);
        drawer = new XHashtableDrawer();
    }
    public void start() {
        NOut.println(NMsg.ofC("##:5:%s##","Type help to see commands."));
        while (true) {
            String command = NIn.readLine(NMsg.ofC("##:4:%s##","> "));
            if (command.equalsIgnoreCase("add")) {
                String value = NIn.readLine(NMsg.ofC("##:92:%s##","Value: "));
                table.add(value);
            } else if (command.equalsIgnoreCase("delete")) {
                String value = NIn.readLine(NMsg.ofC("##:92:%s##","Value: "));
                if (table.delete(value)) {
                    NOut.println(NMsg.ofC("##:2:%s##","Deleted"));
                } else {
                    NOut.println(NMsg.ofC("##:31:%s##","Not found"));
                }
            } else if (command.equalsIgnoreCase("find")) {
                String value = NIn.readLine(NMsg.ofC("##:92:%s##","Value: "));
                if (table.find(value)) {
                    NOut.println(NMsg.ofC("##:2:%s##","Found"));
                } else {
                    NOut.println(NMsg.ofC("##:1:%s##","Not found"));
                }
            } else if (command.equalsIgnoreCase("help")) {
                NOut.println(NMsg.ofC("##:34:%s##","add"));
                NOut.println(NMsg.ofC("##:2:%s##","delete"));
                NOut.println(NMsg.ofC("##:32:%s##","find"));
                NOut.println(NMsg.ofC("##:33:%s##","help"));
                NOut.println(NMsg.ofC("##:31:%s##","exit"));
            } else if (command.equalsIgnoreCase("exit")) {
                break;
            } else {
                NOut.println(NMsg.ofC("##:31:%s##","Unknown command"));
            }
            drawer.draw(table);
        }
    }
}