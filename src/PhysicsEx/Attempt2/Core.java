package PhysicsEx.Attempt2;
import PhysicsEx.Attempt2.Physics2d.rigidbody.RigidBody2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.stream.Collectors;
//partially based of tutorial series: https://www.youtube.com/watch?v=vcgtwY39FT0&list=PLtrSb4XxIVbpZpV65kk73OoUcIrBzoSiO&index=1
//testing JavaDoc in practice
public class Core extends Application {
    public static final boolean enable3d = false;
    //==================================================================================================================Fx classes
    public static void main(String[] args) {

        int x = 2;
        //launch(args);
    }
    //===================================================================
    private Group root;
    private PerspectiveCamera camera;
    private Scene scene;
    private Time clock;
    private ArrayList<Actor> simlulate = new ArrayList<>();
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
