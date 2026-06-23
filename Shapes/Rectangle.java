public class Rectangle extends Shape implements Drawable, Transformable {
    double width;
    double height;
    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }
    @Override
    public double getArea(){
        return width * height;
    }
}