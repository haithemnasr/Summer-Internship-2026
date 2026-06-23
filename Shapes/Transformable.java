public interface Transformable {
    default void resize(double factor){
        System.out.println("Resizing by factor " + factor);
    }
}