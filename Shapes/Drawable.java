public interface Drawable {
    default void draw(){
        System.out.println("Drawing shape...");
    }
}