package PhysicsEx.Attempt2.Physics2d;
import PhysicsEx.Attempt2.MyMath;
import PhysicsEx.Attempt2.Physics3d.Matrix3;

import java.util.Objects;

import static PhysicsEx.Attempt2.MyMath.*;

public class Matrix2{
    public final double
            d00,d01,// cos(t)/-sin(t)
            d10,d11,// sin(t)/ cos(t)
            rotation;
    private Matrix2 pow_1;

    public Matrix2(){
        d00=0;d01=0;
        d10=0;d11=0;
        rotation=0;
    }
    public Matrix2(double rotationDeg){
        this.rotation=rotationDeg;
        double radians = degToRadians(rotationDeg);

        d00=Math.cos(radians);d01=-Math.sin(radians);
        d10=-d01;             d11=d00;
    }
    public Matrix2(double d00,double d01,double d10,double d11,double rotation){
        this.d00=d00;
        this.d01=d01;
        this.d10=d10;
        this.d11=d11;
        this.rotation=rotation;
    }
    public Matrix2(Matrix2 copyOF){
        this.d00=copyOF.d00;
        this.d01=copyOF.d01;
        this.d10=copyOF.d10;
        this.d11=copyOF.d11;
        this.rotation=copyOF.rotation;
    }

    public Vector2 rotate(Vector2 relative){
        return new Vector2(
                (relative.x*d00)+(relative.y*d01),
                (relative.x*d10)+(relative.y*d11)
        );
    }
    public Vector2 unRotate(Vector2 relative){
        return getPow_1().rotate(relative);
    }

    public Matrix2 clone(){
        return new Matrix2(this);
    }
    public Matrix2 getPow_1(){
        if(pow_1==null){
            double det = d00*d11+d10*d01;
            double n00=d11 ,n01=-d01,
                   n10=-d10,n11=d00;
            n00/=det;n01/=det;
            n10/=det;n11/=det;
            pow_1 = new Matrix2(n00,n01,n10,n11,-rotation);
        }
        return pow_1;
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