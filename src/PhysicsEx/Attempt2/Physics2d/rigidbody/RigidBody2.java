package PhysicsEx.Attempt2.Physics2d.rigidbody;

import PhysicsEx.Attempt2.Physics2d.Vector2;
import javafx.scene.Node;

public class RigidBody2 {
    public Vector2 position,
                   scale;
    public double  rotation;
    public RigidBody2(){
        position = new Vector2(0,0);
        scale = new Vector2(1,1);
        rotation = 0;
    }
    public RigidBody2(Vector2 position){
        this.position=position;
    }
    public RigidBody2(Vector2 position, double rotation){
        this.position=position;
        this.rotation=rotation;
    }
    public RigidBody2(Vector2 position, Vector2 scale){
        this.position=position;
        this.scale=scale;
    }
    public RigidBody2(Vector2 position, Vector2 scale, double rotation){
        this.position=position;
        this.scale=scale;
        this.rotation=rotation;
    }
}
