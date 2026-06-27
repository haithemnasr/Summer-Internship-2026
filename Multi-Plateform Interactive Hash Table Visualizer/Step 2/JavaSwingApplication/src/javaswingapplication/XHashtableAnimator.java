package javaswingapplication;
import javax.swing.Timer;
import java.awt.Color;
public class XHashtableAnimator {
    private XHashtableSwingDrawer drawer;
    private Timer timer;
    
    public XHashtableAnimator(XHashtableSwingDrawer drawer) {
        this.drawer = drawer;
    }
    public void animate(int index, Color color) {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        drawer.highlight(index, color);
        timer = new Timer(1000, e -> {
            drawer.clearHighlight();
            timer.stop();
        });
        timer.start();
    }
}