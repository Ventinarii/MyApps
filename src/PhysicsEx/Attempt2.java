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

            double radians = (rotationDeg%360);//stay in 0-360
            if(radians<0)radians+=360;//switch negative values to positive
            radians*=Math.PI/180;//add PI end result =>  ( (360+ (rotationDeg%360) )%360 )*(2*Math.PI/360); // but cheaper (avoided another '%')

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
    //===================================================================
    private static class Matrix3{
        public double
                d00,d01,d02,
                d10,d11,d12,
                d20,d21,d22,
        rotationX,rotationY,rotationZ;
        public Matrix3(){
            d00=0;d01=0;d02=0;
            d10=0;d11=0;d12=0;
            d20=0;d21=0;d22=0;
            rotationX=0;
            rotationY=0;
            rotationZ=0;
        }
        public Matrix3(double rotationX, double rotationY, double rotationZ){
            this.rotationX=rotationX;
            this.rotationY=rotationY;
            this.rotationZ=rotationZ;

            //todo implement
        }
        public Vector3 rotate(Vector3 relative){
            //todo implement
            return null;
        }

        @Override
        public String toString() {
            return "Matrix3{" +
                    "d00=" + d00 +
                    ", d01=" + d01 +
                    ", d02=" + d02 +
                    ", d10=" + d10 +
                    ", d11=" + d11 +
                    ", d12=" + d12 +
                    ", d20=" + d20 +
                    ", d21=" + d21 +
                    ", d22=" + d22 +
                    ", rotationX=" + rotationX +
                    ", rotationY=" + rotationY +
                    ", rotationZ=" + rotationZ +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Matrix3 matrix3 = (Matrix3) o;
            return Double.compare(matrix3.d00, d00) == 0 && Double.compare(matrix3.d01, d01) == 0 && Double.compare(matrix3.d02, d02) == 0 && Double.compare(matrix3.d10, d10) == 0 && Double.compare(matrix3.d11, d11) == 0 && Double.compare(matrix3.d12, d12) == 0 && Double.compare(matrix3.d20, d20) == 0 && Double.compare(matrix3.d21, d21) == 0 && Double.compare(matrix3.d22, d22) == 0;
        }
        @Override
        public int hashCode() {
            return Objects.hash(d00, d01, d02, d10, d11, d12, d20, d21, d22);
        }
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
        Vector2 test = new Matrix2(36).rotate(new Vector2(7,12));

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
