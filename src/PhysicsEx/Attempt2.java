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
import java.util.stream.Collectors;

public class Attempt2  extends Application {
    //==================================================================================================================util classes
    //*always there when you need it and can't use C# anonymous classes*
    private class LaazyContainer<A,B,C,D,E,F,G,H>{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public F f;//6
        public G g;//7
        public H h;//8
        public LaazyContainer(A a,B b,C c,D d,E e,F f,G g,H h){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
            this.g=g;
            this.h=h;
        }
        public LaazyContainer(A a,B b,C c,D d,E e,F f,G g){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
            this.g=g;
        }
        public LaazyContainer(A a,B b,C c,D d,E e,F f){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
        }
        public LaazyContainer(A a,B b,C c,D d,E e){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
        }
        public LaazyContainer(A a,B b,C c,D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public LaazyContainer(A a,B b,C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public LaazyContainer(A a,B b){
            this.a=a;
            this.b=b;
        }
        public LaazyContainer(A a){
            this.a=a;
        }
    }
    //custom math
    private static class MyMath{
        public static double clamp(double a, double b, double c){
            return (b<a)?(a):((c<b)?(c):(b));
        }
        public static double pow2(double d){return d*d;}
        public static double abs(double d){
            return (d<0)?(-d):(d);
        }
    }
    //vectors
    private class Vector2{
        public double a=0,b=0;

        public Vector2(){}
        public Vector2(double a, double b){
            this.a=a;
            this.b=b;
        }

        public Vector2 add(Vector2 vector){
            return new Vector2(a+vector.a,b+vector.b);
        }
        public Vector2 substract(Vector2 vector){
            return new Vector2(a-vector.a,b-vector.b);
        }
        public Vector2 abs(){
            return new Vector2(MyMath.abs(a),MyMath.abs(b));
        }

        public double vectorDistance() {
            return Math.pow(MyMath.pow2(a) + MyMath.pow2(b), 0.5d);
        }
    }
    private class Vector3{
        public double a=0,b=0,c=0;

        public Vector3(){}
        public Vector3(double a, double b,double c){
            this.a=a;
            this.b=b;
            this.c=c;
        }

        public Vector3 add(Vector3 vector){
            return new Vector3(a+vector.a,b+vector.b,c+vector.c);
        }
        public Vector3 substract(Vector3 vector){
            return new Vector3(a-vector.a,b-vector.b,c-vector.c);
        }
        public Vector3 abs(){
            return new Vector3(MyMath.abs(a),MyMath.abs(b),MyMath.abs(c));
        }

        public double vectorDistance(){
            return Math.pow(MyMath.pow2(a)+MyMath.pow2(b)+MyMath.pow2(c),0.5d);
        }
    }
    //==================================================================================================================primitive classes
    private class AABB2{
        public Vector2 center, halfSize;
        public boolean collide(AABB2 other){
            double tolerance = 0.1;
            Vector2 contact = halfSize.add(other.halfSize),//this is distance bellow which we got contact
                    delta = center.substract(other.center).abs();//this is difference in location of two bodies
            delta = contact.substract(delta).substract(new Vector2(tolerance,tolerance));//how much is missing delta to reach contact (minus tolerance)

            return delta.a<0||delta.b<0;
        }
    }
    private abstract class RigidBody2 {
        public Vector2 position,
                       scale;
        public double rotation;
        public RigidBody2(){
            position = new Vector2(0,0);
            scale = new Vector2(1,1);
            rotation = 0;
        }
        abstract public Node getShape();
    }
    private class Box2 extends RigidBody2 {
        public Box box;
        @Override
        public Node getShape() {
            return box;
        }
    }
    private class Circe2 extends RigidBody2 {
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
        launch(args);
    }
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

    private class Time extends AnimationTimer {
        @Override
        public void handle(long now) {
            if(then==0)then=now;
            double dTime = (now-then)/1000000000d;

            then=now;
        }
    }

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
