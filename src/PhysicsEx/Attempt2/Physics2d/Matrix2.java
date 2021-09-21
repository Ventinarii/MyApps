package PhysicsEx.Attempt2.Physics2d;
import PhysicsEx.Attempt2.MyMath;
import java.util.Objects;

public class Matrix2{
    public double
            d00,d01,// cos(t)/-sin(t)
            d10,d11,// sin(t)/ cos(t)
            rotation;
    public Matrix2(){
        d00=0;d01=0;
        d10=0;d11=0;
        rotation=0;
    }
    public Matrix2(double rotationDeg){
        this.rotation=rotationDeg;
        double radians = MyMath.degToRadians(rotationDeg);

        d00=Math.cos(radians);d01=-Math.sin(radians);
        d10=-d01;             d11=d00;
    }
    public Vector2 rotate(Vector2 relative){
        return new Vector2(
                (relative.x*d00)+(relative.y*d01),
                (relative.x*d10)+(relative.y*d11)
        );
    }

    @Override
    public String toString() {
        return "Matrix2{" +
                "d00=" + d00 +
                ", d01=" + d01 +
                ", d10=" + d10 +
                ", d11=" + d11 +
                ", rotation=" + rotation +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix2 matrix2 = (Matrix2) o;
        return Double.compare(matrix2.d00, d00) == 0 && Double.compare(matrix2.d01, d01) == 0 && Double.compare(matrix2.d10, d10) == 0 && Double.compare(matrix2.d11, d11) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(d00, d01, d10, d11);
    }
}