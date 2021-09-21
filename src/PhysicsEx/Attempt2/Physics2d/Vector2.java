package PhysicsEx.Attempt2.Physics2d;
import PhysicsEx.Attempt2.MyMath;
import java.util.Objects;

 public class Vector2{
    public double x =0, y =0;

    public Vector2(){}
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 vector){
        return new Vector2(x +vector.x, y +vector.y);
    }
    public Vector2 substract(Vector2 vector){
        return new Vector2(x -vector.x, y -vector.y);
    }
    public Vector2 abs(){
        return new Vector2(MyMath.abs(x),MyMath.abs(y));
    }
    public Vector2 multiply(double a){
        return new Vector2(x*a,y*a);
    }

    public double length() {
        return Math.pow(MyMath.pow2(x) + MyMath.pow2(y), 0.5d);
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
