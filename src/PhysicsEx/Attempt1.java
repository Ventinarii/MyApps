package PhysicsEx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Attempt1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public Group root;
    public PerspectiveCamera camera;
    public Scene scene;
    public Time clock;
    public ArrayList<Drop> droplets = new ArrayList<>();
    public double
            mX=-100,
            xX=100,
            mY=-100,
            xY=100,
            mZ=-100,
            xZ=100,
            rad = 10,
            g=100;
    public int max = 3;
    public long then=0;

    //*always there when you need it and can't use C# anonymous classes*
    private class LaazyContainer<A,B,C,D,E,F,G>{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public F f;//6
        public G g;//7
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

    private class Drop extends Sphere{
        public double
            posX=0,posY=0,posZ=0,
            velX=0,velY=0,velZ=0,
            forX=0,forY=0,forZ=0;
        public Drop(double radius,double posX,double posY,double posZ){
            super(radius);
            this.posX=posX;
            this.posY=posY;
            this.posZ=posZ;
            setTranslateX(posX);
            setTranslateY(posY);
            setTranslateZ(posZ);
            setMaterial(new PhongMaterial(Color.BLUE));
        }
        public void update(){
            setTranslateX(posX);
            setTranslateY(posY);
            setTranslateZ(posZ);

        }
    }

    private class Time extends AnimationTimer{
        public Attempt1 host;
        public Time(Attempt1 host){
            this.host=host;
        }
        @Override
        public void handle(long now) {
            if(then==0)then=now;
            double dTime = (now-then)/1000000000d;

            moveAll(dTime,droplets);
            colideAll(droplets);
            forceAll(dTime,droplets);

            then=now;
        }

        public void moveAll(double dTime,ArrayList<Drop> droplets){
            //parallel processing
            droplets.parallelStream().forEach(d->{
                //what happens here is we move obj by it's AVARAGE speed withing given frame simplification of
                // p=v*dt+a*dt/2 ->
                // p=(2v+a)(dt/2)
                // p=v*(dt/2) + (v+a)*(dt/2)

                if(false){
                    //p+=v*(dt/2)
                    d.posX += d.velX * (dTime / 2d);
                    d.posY += d.velY * (dTime / 2d);
                    d.posZ += d.velZ * (dTime / 2d);
                    //v'=(v+a)
                    d.velX += d.forX * dTime;
                    d.velY += d.forY * dTime;
                    d.velZ += d.forZ * dTime;
                    //p+=v'*(dt/2)
                    d.posX += d.velX * (dTime / 2d);
                    d.posY += d.velY * (dTime / 2d);
                    d.posZ += d.velZ * (dTime / 2d);
                    //stay in borders
                    d.posX = clamp(mX, d.posX, xX);
                    d.posY = clamp(mY, d.posY, xY);
                    d.posZ = clamp(mZ, d.posZ, xZ);
                }else {
                    d.posX = clamp(mX,d.posX+(d.velX+(d.forX/2))*dTime,xX);
                    d.posY = clamp(mY,d.posY+(d.velY+(d.forY/2))*dTime,xY);
                    d.posZ = clamp(mZ,d.posZ+(d.velZ+(d.forZ/2))*dTime,xZ);
                }
            });
            //line update cuz of FX
            droplets.forEach(d->d.update());
        }
        public void colideAll(ArrayList<Drop> droplets){
            droplets.parallelStream().forEach(drop->{
                //get all in collision
                List<Drop> coll = droplets
                        .parallelStream()
                        .filter(e->vbh(drop.posX,drop.posY,drop.posZ,e.posX,e.posY,e.posZ,(rad*2)))
                        .filter(e->
                                pow2(drop.posX-e.posX)+
                                pow2(drop.posY-e.posY)+
                                pow2(drop.posZ-e.posZ)<=
                                pow2(rad*2))
                        .collect(Collectors.toList());
                //process collisions
                coll.forEach(e->{

                });
                //check for border collision

            });
        }
        public void forceAll(double dTime, ArrayList<Drop> droplets){

            droplets.parallelStream().forEach(drop->{
                drop.forZ=g;
                //ESTIMATED position in NEXT frame based on CURRENT <dTime> of THIS drop
                double  eX=clamp(mX,drop.posX+(drop.velX+(drop.forX/2))*dTime,xX),
                        eY=clamp(mY,drop.posY+(drop.velY+(drop.forY/2))*dTime,xY),
                        eZ=clamp(mZ,drop.posZ+(drop.velZ+(drop.forZ/2))*dTime,xZ);

                //get all in contact
                List<Drop> coll = droplets
                        .parallelStream()
                        .filter(e->vbh(drop.posX,drop.posY,drop.posZ,e.posX,e.posY,e.posZ,(rad*2)))
                        .filter(e->
                                pow2(drop.posX-e.posX)+
                                pow2(drop.posY-e.posY)+
                                pow2(drop.posZ-e.posZ)<=
                                pow2(rad*2))
                        //at this point we got list of all CURRENTLY colliding objects
                        .map(e->new LaazyContainer<Drop,Double,Double,Double,Object,Object,Object>(//pack in container and add ESTIMATED position in NEXT frame based on CURRENT <dTime> this is SPECULATION unless <dTime> is constant
                                e,
                                clamp(mX,e.posX+(e.velX+(e.forX/2))*dTime,xX),
                                clamp(mY,e.posY+(e.velY+(e.forY/2))*dTime,xY),
                                clamp(mZ,e.posZ+(e.velZ+(e.forZ/2))*dTime,xZ)
                                ))
                        .filter(e->//if in NEXT frame STILL in collision then we call this CONTACT. it is POSSIBLE because we DISPERSE Ek as 'heat' and thus each collision takes away some energy AND we demand that minVel<=Vel <- should that be not met we assume vel is -=ZERO=-.
                                pow2(eX-e.b)+
                                pow2(eY-e.c)+
                                pow2(eZ-e.d)<=
                                pow2(rad*2))
                        //at this point we got list of all FUTURE colliding objects
                        .map(e->e.a)
                        //extracted useful object from container
                        .collect(Collectors.toList());
                //now add forces
                coll.stream().filter(e->drop.posZ<e.posZ).forEach(e->{

                });
                //now give forces
                coll.stream().filter(e->drop.posZ<e.posZ).forEach(e->{

                });
                //check for border collision

            });
        }

        //useful math functions
        public double clamp(double a, double b, double c){
            return (b<a)?(a):((c<b)?(c):(b));
        }
        public double pow2(double d){return d*d;}
        public double vectorDistance(double aX, double aY, double aZ){
            return Math.pow(aX*aX+aY+aY+aZ*aZ,0.5d);
        }
        public boolean vbh(double aX, double aY, double aZ, double bX, double bY, double bZ, double tolerance){
            return abs(aX-bX)<tolerance && abs(aY-bY)<tolerance && abs(aZ-bZ)<tolerance;
        }
        public double abs(double d){
            return (d<0)?(-d):(d);
        }
    }

    public void start(Stage primaryStage) {
        //here is init
        {
            root = new Group();
        }
        //here we simulate
        if(false){//test image
            Sphere box = new Sphere(10);
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
                        droplets.add(new Drop(rad,x*rad*2,y*rad*2,z*rad*2));
            root.getChildren().addAll(droplets);
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
            primaryStage.setTitle("FluidSim");
            primaryStage.show();

            clock = new Time(this);
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