package PhysicsEx.Attempt2.Physics3d;

import PhysicsEx.Attempt2.Core;
import PhysicsEx.Attempt2.MyMath;

import java.util.Arrays;

public class Matrix3{
    public static final int Xaxis = 1, Yaxis = 2, Zaxis =3;
    public final double[][] matrix = new double[3][3];
    private Matrix3 pow_1;

    public Matrix3(){}
    public Matrix3(double[][] matrix){
        if(
                matrix.length!=3||
                        matrix[0].length!=3||
                        matrix[1].length!=3||
                        matrix[2].length!=3)
            new Exception("invalid matrix dimension").printStackTrace();

        for(int y = 0; y<3; y++)
            for(int x = 0; x<3; x++)
                this.matrix[y][x]=matrix[y][x];
    }
    public Matrix3(double rotation, int axis){
        double radians = MyMath.degToRadians(rotation),
                sin=Math.sin(radians),
                cos=Math.cos(radians);
        switch (axis){
            case Xaxis:{
                matrix[0][0]=1;matrix[0][1]=0;  matrix[0][2]=0;
                matrix[1][0]=0;matrix[1][1]=cos;matrix[1][2]=-sin;
                matrix[2][0]=0;matrix[2][1]=sin;matrix[2][2]=cos;
            }break;
            case Yaxis:{
                matrix[0][0]=cos;  matrix[0][1]=0;matrix[0][2]=sin;
                matrix[1][0]=0;    matrix[1][1]=1;matrix[1][2]=0;
                matrix[2][0]=0-sin;matrix[2][1]=0;matrix[2][2]=cos;
            }break;
            case Zaxis:{
                matrix[0][0]=cos;matrix[0][1]=-sin;matrix[0][2]=0;
                matrix[1][0]=sin;matrix[1][1]=cos; matrix[1][2]=0;
                matrix[2][0]=0;  matrix[2][1]=0;   matrix[2][2]=1;
            }break;
            default: new Exception("invalid rotataion axis: "+axis).printStackTrace();
        }
    }
    public Matrix3 clone(){
        return new Matrix3(matrix);
    }

    public Matrix3 getPow_1(){
        //based of:https://byjus.com/maths/inverse-of-3-by-3-matrix/
        if(pow_1==null){
            //prep
            double[][] mat = new double[3][3];
            double
                    a,b,c, A,B,C,
                    d,e,f, D,E,F,
                    g,h,i, G,H,I;
            a=matrix[0][0];b=matrix[0][1];c=matrix[0][2];
            d=matrix[1][0];e=matrix[1][1];f=matrix[1][2];
            g=matrix[2][0];h=matrix[2][1];i=matrix[2][2];
            //find determinants of 2x2 sub matrices
            A=det(e,f,h,i);B=det(d,f,g,i);C=det(d,e,g,h);
            D=det(b,c,h,i);E=det(a,c,g,i);F=det(a,b,g,h);
            G=det(b,c,e,f);H=det(a,d,c,f);I=det(a,b,d,e);
            //cofactor matrix
            B=-B;
            D=-D;     F=-F;
            H=-H;
            //transpose
                /*ADG
                //BEH
                  CFI*/
            //insert into struct
            mat[0][0]=A;mat[0][1]=D;mat[0][2]=G;
            mat[1][0]=B;mat[1][1]=E;mat[1][2]=H;
            mat[2][0]=C;mat[2][1]=F;mat[2][2]=I;
            //create new object
            pow_1 = new Matrix3(mat);
        }
        return pow_1;
    }
    public Vector3 multiply(Vector3 vector){
        return new Vector3(
                vector.x*matrix[0][0]+vector.y*matrix[0][1]+vector.z*matrix[0][2],
                vector.x*matrix[1][0]+vector.y*matrix[1][1]+vector.z*matrix[1][2],
                vector.x*matrix[2][0]+vector.y*matrix[2][1]+vector.z*matrix[2][2]
        );
    }
    public Matrix3 multiply(Matrix3 matrix3){
        //    | A B C
        //    | D E F
        //    | G H I
        //a b c X X X
        //d e f X X X
        //g h i X X X
        double[][] mat = new double[3][3],
                MATRIX = matrix3.matrix;
        for(int y = 0; y<3; y++)
            for(int x = 0; x<3; x++)
                mat[y][x]=matrix[y][0]*MATRIX[0][x]+matrix[y][1]*MATRIX[1][x]+matrix[y][2]*MATRIX[2][x];
        return new Matrix3(mat);
    }
    public Vector3 divide(Vector3 vector){
        return getPow_1().multiply(vector);
    }
    public Matrix3 divide(Matrix3 matrix3){
        return getPow_1().multiply(matrix3);
    }

    @Override
    public String toString() {
        return "Matrix3{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix3 matrix3 = (Matrix3) o;
        return Arrays.equals(matrix, matrix3.matrix);
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    /**
     * input matrix
     * |a b|
     * |c d|
     */
    private double det(double a,double b,double c,double d){
        return a*d-b*c;
    }
}