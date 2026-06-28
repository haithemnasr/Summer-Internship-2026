package com.mycompany.javafxapplicationphase2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class XHashtableFXAnimator {
    private XHashtableFXDrawer drawer;
    private Timeline timeline;
    
    public XHashtableFXAnimator(XHashtableFXDrawer drawer) {
        this.drawer = drawer;
    }
    
    public void animate(int index, Color color) {
        if (timeline != null) {
            timeline.stop();
        }
        drawer.highlight(index, color);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            drawer.clearHighlight();
        }));
        timeline.play();
    }
}