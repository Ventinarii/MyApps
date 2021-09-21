package PhysicsEx.Attempt2.Physics3d;
import PhysicsEx.Attempt2.MyMath;
import java.util.Objects;

public class Vector3{
    public double x =0, y =0, z =0;

    public Vector3(){}
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 vector){
        return new Vector3(x +vector.x, y +vector.y, z +vector.z);
    }
    public Vector3 substract(Vector3 vector){
        return new Vector3(x -vector.x, y -vector.y, z -vector.z);
    }
    public Vector3 abs(){
        return new Vector3(MyMath.abs(x),MyMath.abs(y),MyMath.abs(z));
    }
    public Vector3 multiply(double a){
        return new Vector3(x*a,y*a,z*a);
    }

    public double length(){
        return Math.pow(MyMath.pow2(x)+MyMath.pow2(y)+MyMath.pow2(z),0.5d);
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Double.compare(vector3.x, x) == 0 && Double.compare(vector3.y, y) == 0 && Double.compare(vector3.z, z) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}