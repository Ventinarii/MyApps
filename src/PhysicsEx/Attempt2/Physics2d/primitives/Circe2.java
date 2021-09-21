package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Actor;
import PhysicsEx.Attempt2.Physics2d.rigidbody.RigidBody2;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;

public class Circe2 extends Actor {
    public double radius;
    public Circe2(){
        super();
        radius = 1;
    }
    public Circe2(Vector2 position, Vector2 scale, double rotation, double radius){
        //super(position,scale,rotation);
        this.radius=radius;
    }


    @Override
    public boolean collide(Vector2 point) {
        return false;
        //return radius<=point.substract(position).length();
    }
    //===================================================================Fx Part
    public Sphere sphere;
    @Override
    public Node getShape() {
        return sphere;
    }
}
