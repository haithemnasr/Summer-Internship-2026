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
        NOut.println(NMsg.ofPlain("Type help to see commands."));
        while (true) {
            String command = NIn.readLine(NMsg.ofPlain("> "));
            if (command.equalsIgnoreCase("add")) {
                String value = NIn.readLine(NMsg.ofPlain("Value: "));
                table.add(value);
            } else if (command.equalsIgnoreCase("delete")) {
                String value = NIn.readLine(NMsg.ofPlain("Value: "));
                if (table.delete(value)) {
                    NOut.println(NMsg.ofPlain("Deleted"));
                } else {
                    NOut.println(NMsg.ofPlain("Not found"));
                }
            } else if (command.equalsIgnoreCase("find")) {
                String value = NIn.readLine(NMsg.ofPlain("Value: "));
                if (table.find(value)) {
                    NOut.println(NMsg.ofPlain("Found"));
                } else {
                    NOut.println(NMsg.ofPlain("Not found"));
                }
            } else if (command.equalsIgnoreCase("help")) {
                NOut.println(NMsg.ofPlain("add"));
                NOut.println(NMsg.ofPlain("delete"));
                NOut.println(NMsg.ofPlain("find"));
                NOut.println(NMsg.ofPlain("help"));
                NOut.println(NMsg.ofPlain("exit"));
            } else if (command.equalsIgnoreCase("exit")) {
                break;
            } else {
                NOut.println(NMsg.ofPlain("Unknown command"));
            }
            drawer.draw(table);
        }
    }
}