import net.thevpc.nuts.Nuts;
import net.thevpc.nuts.core.NWorkspace;

public class Main {
    public static void main(String[] args) {
        NWorkspace ws = Nuts.openWorkspace(args);
        ws.runWith(() -> new AppShell().start());
    }
}