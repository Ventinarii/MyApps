package PhysicsEx.Attempt2.Physics2d;

import javafx.scene.Node;

public abstract class Colider2 {
    public Vector2 position,
            scale;
    public double rotation;
    public Colider2(){
        position = new Vector2(0,0);
        scale = new Vector2(1,1);
        rotation = 0;
    }
    public Colider2(Vector2 position){
        this.position=position;
    }
    public Colider2(Vector2 position, double rotation){
        this.position=position;
        this.rotation=rotation;
    }
    public Colider2(Vector2 position, Vector2 scale){
        this.position=position;
        this.scale=scale;
    }
    public Colider2(Vector2 position, Vector2 scale, double rotation){
        this.position=position;
        this.scale=scale;
        this.rotation=rotation;
    }

    public abstract float getInertiaTensor(float mass);

    //===================================================================Fx Part
    abstract public Node getShape();
    public abstract boolean collide(Vector2 point);
}
