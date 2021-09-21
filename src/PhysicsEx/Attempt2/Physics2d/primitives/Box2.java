package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Physics2d.Colider2;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import javafx.scene.Node;
import javafx.scene.shape.Box;

public class Box2 extends Colider2 {
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
    public float getInertiaTensor(float mass) {
        return 0;
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
