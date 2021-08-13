package PhysicsEx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
//partially based of tutorial series: https://www.youtube.com/watch?v=vcgtwY39FT0&list=PLtrSb4XxIVbpZpV65kk73OoUcIrBzoSiO&index=1
//testing JavaDoc in practice
public class Attempt2  extends Application {
    //==================================================================================================================util classes
    //=======================================================================================================custom math
    public static class MyMath{
        /**
         * method returns value between from and to.
         * @param from lowest value function can return
         * @param val desired value function can return
         * @param to highest value function can return
         */
        public static double clamp(double from, double val, double to){return (val<from)?(from):((to<val)?(to):(val));}
        /**
         * method returns IF value IS between from and to, regardless of what is greater ('from','to')
         * @param from one of border values
         * @param val check value
         * @param to one of border values
         */
        public static boolean inRange(double from, double val, double to){return (from<to)?(from<=val&&val<=to):(to<=val&&val<=from);}
        /**
         * @return d*d
         */
        public static double pow2(double d){return d*d;}
        /**
         * @return positive double of value d
         */
        public static double abs(double d){return (d<0)?(-d):(d);}
        /**
         * method returns angle expressed in radians for needs of external trigonometry functions
         * @angle angle expressed in degress (360')
         * @return angle expressed in radians (2PI)
         */
        public static double degToRadians(double angle){
            double radians = (angle%360);//stay in 0-360
            if(radians<0)radians+=360;//switch negative values to positive
            radians*=Math.PI/180;//add PI end result =>  ( (360+ (rotationDeg%360) )%360 )*(2*Math.PI/360); // but cheaper (avoided another '%')
            return radians;
        }
    }
    //===========================================================================================================vectors
    private static class Vector2{
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
    //===================================================================
    private static class Vector3{
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
    //============================================================================================================matrix
    private static class Matrix2{
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
    private static class Matrix3{
        public static final int Xaxis = 1, Yaxis = 2, Zaxis =3;
        public double[][] matrix = new double[3][3];
        private Matrix3 pow_1;

        public Matrix3(){}
        public Matrix3(double[][] matrix){
            if(
                    matrix.length!=3||
                    matrix[0].length!=3||
                    matrix[1].length!=3||
                    matrix[2].length!=3)
                new Exception("invalid matrix dimension").printStackTrace();
            this.matrix=matrix;
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

        public Matrix3 getPow_1(){
            if(pow_1==null){
                //prep
                double[][] mat = new double[3][3];
                double
                        a,b,c,A,B,C,
                        d,e,f,D,E,F,
                        g,h,i,G,H,I;
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
                mat[0][0]=A;mat[0][1]=D;mat[0][1]=G;
                mat[1][0]=B;mat[1][1]=E;mat[1][1]=H;
                mat[2][0]=C;mat[2][1]=F;mat[2][1]=I;
                //create new object
                pow_1 = new Matrix3(mat);
            }
            return pow_1;
        }//todo validate
        public Vector3 multiply(Vector3 vector){
            return new Vector3(
                    vector.x*matrix[0][0]+vector.y*matrix[0][1]+vector.z*matrix[0][2],
                    vector.x*matrix[1][0]+vector.y*matrix[1][1]+vector.z*matrix[1][2],
                    vector.x*matrix[2][0]+vector.y*matrix[2][1]+vector.z*matrix[2][2]
            );
        }//todo validate
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
        }///todo validate
        public Vector3 divide(Vector3 vector){
            return getPow_1().multiply(vector);
        }
        public Matrix3 divide(Matrix3 matrix3){
            return getPow_1().multiply(matrix3);
        }//todo validate

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
    //===================================================================
    @Deprecated
    private static class QuaternionPrototype {//THIS IS...DIFFERENT. it is used for ROTATION.
        public double
                x00,x01,x02,
                x10,x11,x12,
                x20,x21,x22,
                rotationX,
                y00,y01,y02,
                y10,y11,y12,
                y20,y21,y22,
                rotationY,
                z00,z01,z02,
                z10,z11,z12,
                z20,z21,z22,
                rotationZ;
        public QuaternionPrototype(){
            x00=0;x01=0;x02=0;
            x10=0;x11=0;x12=0;
            x20=0;x21=0;x22=0;
            rotationX=0;
            y00=0;y01=0;y02=0;
            y10=0;y11=0;y12=0;
            y20=0;y21=0;y22=0;
            rotationY=0;
            z00=0;z01=0;z02=0;
            z10=0;z11=0;z12=0;
            z20=0;z21=0;z22=0;
            rotationZ=0;
        }
        public QuaternionPrototype(double rotationX, double rotationY, double rotationZ){
            double radians=0;
            double sin=0,cos=0;

            this.rotationX=rotationX;
            radians = MyMath.degToRadians(rotationX);
            sin = Math.sin(radians);
            cos = Math.cos(radians);

            x00=1;x01=0;x02=0;
            x10=0;x11=cos;x12=-sin;
            x20=0;x21=sin;x22=cos;

            this.rotationY=rotationY;
            radians = MyMath.degToRadians(rotationY);
            sin = Math.sin(radians);
            cos = Math.cos(radians);

            y00=cos; y01=0;y02=sin;
            y10=0;   y11=1;y12=0;
            y20=-sin;y21=0;y22=cos;

            this.rotationZ=rotationZ;
            radians = MyMath.degToRadians(rotationZ);
            sin = Math.sin(radians);
            cos = Math.cos(radians);

            z00=cos;z01=-sin;z02=0;
            z10=sin;z11=cos; z12=0;
            z20=0;  z21=0;   z22=1;
        }
        public Vector3 rotate(Vector3 relative){
            /*     |A|
            *      |B|
            *      |C|
            * - - - -
            * a b c|X|    |X=A*a+B*b+C*c|
            * d e f|Y| => |Y=A*d+B*e+C*f| x3
            * g h i|Z|    |Z=A*g+B*h+C*i|
            * - - - -
            * */
            Vector3 ret = relative;
            ret = new Vector3(
                    ret.x*x00+ret.y+x01+ret.z*x02,
                    ret.x*x10+ret.y+x11+ret.z*x12,
                    ret.x*x20+ret.y+x21+ret.z*x22
            );
            ret = new Vector3(
                    ret.x*y00+ret.y+y01+ret.z*y02,
                    ret.x*y10+ret.y+y11+ret.z*y12,
                    ret.x*y20+ret.y+y21+ret.z*y22
            );
            ret = new Vector3(
                    ret.x*z00+ret.y+z01+ret.z*z02,
                    ret.x*z10+ret.y+z11+ret.z*z12,
                    ret.x*z20+ret.y+z21+ret.z*z22
            );

            //todo validate if correct
            return ret;
        }
    }
    //===================================================================
    private static class Quaternion{
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
        }//todo validate

        public Vector3 rotate(Vector3 relative){
            return
                    rotationMatrixZ.multiply(
                    rotationMatrixY.multiply(
                    rotationMatrixX.multiply(relative)));
        }//todo validate
        public Vector3 unRotate(Vector3 relative){
            return
                    rotationMatrixX.divide(
                    rotationMatrixY.divide(
                    rotationMatrixZ.divide(relative)));
        }//todo validate
    }
    //==================================================================================================================primitive classes
    private static class AABB2{
        public Vector2 center, halfSize;
        public boolean collide(AABB2 other){
            double tolerance = 0.1;
            Vector2 contact = halfSize.add(other.halfSize),//this is distance bellow which we got contact
                    delta = center.substract(other.center).abs();//this is absolute difference in location of two bodies
            delta = contact.substract(delta).substract(new Vector2(tolerance,tolerance));//how much is missing delta to reach contact (minus tolerance)

            return delta.x <0||delta.y <0;
        }
    }
    //===================================================================
    private static class Line2{
        public Vector2 start, end;
        public Line2(){
            start = new Vector2(0,0);
            end = new Vector2(1,1);
        }
        public Line2(Vector2 start, Vector2 end){
            this.start=start;
            this.end=end;
        }
        public boolean collide(Vector2 point){
            Vector2 relativeEnd = end.substract(start),
                    relativePoint = point.substract(start);
            //this ver return if point lies on INFINITE line. not this 'part' of line
            return relativeEnd//'original' solution. apparently works
                    .multiply(relativePoint.x)
                    .equals(relativePoint.multiply(relativeEnd.x))&&
                    //this part is added so ensure that we are on this 'part' of line
                    MyMath.inRange(0,relativePoint.x,relativeEnd.x)&&
                    MyMath.inRange(0,relativePoint.y,relativeEnd.y);
        }
    }
    //===================================================================
    private static abstract class RigidBody2 {
        public Vector2 position,
                       scale;
        public double rotation;
        public RigidBody2(){
            position = new Vector2(0,0);
            scale = new Vector2(1,1);
            rotation = 0;
        }
        public RigidBody2(Vector2 position){
            this.position=position;
        }
        public RigidBody2(Vector2 position,double rotation){
            this.position=position;
            this.rotation=rotation;
        }
        public RigidBody2(Vector2 position,Vector2 scale){
            this.position=position;
            this.scale=scale;
        }
        public RigidBody2(Vector2 position,Vector2 scale,double rotation){
            this.position=position;
            this.scale=scale;
            this.rotation=rotation;
        }

        //===================================================================Fx Part
        abstract public Node getShape();
        public abstract boolean collide(Vector2 point);
    }
    //===================================================================
    private static class Box2 extends RigidBody2 {
        public Vector2 halfSize;
        public Box2(){
            super();
            halfSize = new Vector2(1,1);
        }
        public Box2(Vector2 position, Vector2 scale, double rotation, Vector2 halfSize){
            super(position,scale,rotation);
            this.halfSize=halfSize;
        }

        @Override
        public boolean collide(Vector2 point) {//todo write this func
            return false;
        }

        //===================================================================Fx Part
        public Box box;
        @Override
        public Node getShape() {
            return box;
        }
    }
    //===================================================================
    private static class Circe2 extends RigidBody2 {
        public double radius;
        public Circe2(){
            super();
            radius = 1;
        }
        public Circe2(Vector2 position, Vector2 scale, double rotation, double radius){
            super(position,scale,rotation);
            this.radius=radius;
        }

        @Override
        public boolean collide(Vector2 point) {
            return radius<=point.substract(position).length();
        }
        //===================================================================Fx Part
        public Sphere sphere;
        @Override
        public Node getShape() {
            return sphere;
        }
    }
    //==================================================================================================================physics classes
    private class MyPhysics2{

    }
    //==================================================================================================================Fx classes
    public static void main(String[] args) {
        Matrix3 mat = new Matrix3(new double[][]{{3,3,3},{3,3,3},{3,3,3}});

        int x = 2;
        launch(args);
    }
    //===================================================================
    private Group root;
    private PerspectiveCamera camera;
    private Scene scene;
    private Time clock;
    private ArrayList<RigidBody2> simlulate = new ArrayList<>();
    private double
            mX=-100,
            xX=100,
            mY=-100,
            xY=100,
            mZ=-100,
            xZ=100,
            g=100;
    private int max = 3;
    private long then=0;
    //===================================================================
    private class Time extends AnimationTimer {
        @Override
        public void handle(long now) {
            if(then==0)then=now;
            double dTime = (now-then)/1000000000d;

            then=now;
        }
    }
    //===================================================================
    public void start(Stage primaryStage) {
        //here is init
        {
            root = new Group();
        }
        //here we generate objects for tests
        if(true){//test image
            Box box = new Box(10,10,10);
            box.setMaterial(new PhongMaterial(Color.FORESTGREEN));
            box.setCullFace(CullFace.NONE);
            box.setTranslateX(0);
            box.setTranslateY(0);
            box.setTranslateZ(0);

            root.getChildren().add(box);
        }else {//values
            for(int x=0; x<max; x++)
                for(int y=0;y<max; y++)
                    for(int z=0; z<max; z++)
                        //droplets.add(new Attempt1.Drop(rad,x*rad*2,y*rad*2,z*rad*2));
            root.getChildren().addAll(simlulate.stream().map(e->e.getShape()).collect(Collectors.toList()));
        }
        //here is camera
        {
            camera = new PerspectiveCamera(false);
            camera.setTranslateX(-200);
            camera.setTranslateY(-450);
            camera.setTranslateZ(200);
            camera.setRotationAxis(Rotate.X_AXIS);
            camera.setRotate(45);
        }
        //here we run it all
        {
            scene = new Scene(root, 500, 500, true);
            scene.setCamera(camera);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Physics");
            primaryStage.show();

            clock = new Time();
            clock.start();
        }
        //here we add keys
        {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case D: camera.setTranslateX(camera.getTranslateX()+10); break;
                        case A: camera.setTranslateX(camera.getTranslateX()-10); break;
                        case S: camera.setTranslateY(camera.getTranslateY()+10); break;
                        case W: camera.setTranslateY(camera.getTranslateY()-10); break;
                        case Q: camera.setTranslateZ(camera.getTranslateZ()+10); break;
                        case E: camera.setTranslateZ(camera.getTranslateZ()-10); break;
                        case SPACE: System.out.println(camera.getTranslateX()+";"+camera.getTranslateY()+";"+camera.getTranslateZ()); break;
                        case ESCAPE: Platform.exit(); break;
                    }
                }
            });
        }
    }
}
