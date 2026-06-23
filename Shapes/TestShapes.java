import java.util.ArrayList;
public class TestShapes {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Triangle(3, 8));
        for(Shape s : shapes){
            System.out.println("Area = " + s.getArea());
        }
    }
}