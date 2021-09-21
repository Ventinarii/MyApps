package PhysicsEx.Attempt2.Physics3d;

public class Quaternion{
    public Matrix3 rotationMatrixX, rotationMatrixY, rotationMatrixZ;
    private double rotationX, rotationY, rotationZ;

    public double getRotationX() {
        return rotationX;
    }
    public Quaternion setRotationX(double rotation){
        rotationX=rotation;
        rotationMatrixX=new Matrix3(rotation,Matrix3.Xaxis);
        return this;
    }
    public double getRotationY() {
        return rotationY;
    }
    public Quaternion setRotationY(double rotation){
        rotationY=rotation;
        rotationMatrixY=new Matrix3(rotation,Matrix3.Yaxis);
        return this;
    }
    public double getRotationZ() {
        return rotationZ;
    }
    public Quaternion setRotationZ(double rotation){
        rotationZ=rotation;
        rotationMatrixZ=new Matrix3(rotation,Matrix3.Zaxis);
        return this;
    }

    public Quaternion(){
        rotationX=0;
        rotationY=0;
        rotationZ=0;
        rotationMatrixX=new Matrix3(0,Matrix3.Xaxis);
        rotationMatrixY=new Matrix3(0,Matrix3.Yaxis);
        rotationMatrixZ=new Matrix3(0,Matrix3.Zaxis);
    }
    public Quaternion(double rotationX,double rotationY,double rotationZ){
        this.rotationX=rotationX;
        this.rotationY=rotationY;
        this.rotationZ=rotationZ;
        rotationMatrixX=new Matrix3(rotationX,Matrix3.Xaxis);
        rotationMatrixY=new Matrix3(rotationY,Matrix3.Yaxis);
        rotationMatrixZ=new Matrix3(rotationZ,Matrix3.Zaxis);
    }

    public Vector3 rotate(Vector3 relative){
        return
                rotationMatrixZ.multiply(
                        rotationMatrixY.multiply(
                                rotationMatrixX.multiply(relative)));
    }
    public Vector3 unRotate(Vector3 relative){
        return
                rotationMatrixX.divide(
                        rotationMatrixY.divide(
                                rotationMatrixZ.divide(relative)));
    }
}