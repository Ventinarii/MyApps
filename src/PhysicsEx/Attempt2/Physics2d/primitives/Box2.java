package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Actor;
import PhysicsEx.Attempt2.MyMath;
import PhysicsEx.Attempt2.Physics2d.Matrix2;
import PhysicsEx.Attempt2.Physics2d.rigidbody.RigidBody2;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import javafx.scene.Node;
import javafx.scene.shape.Box;

import static PhysicsEx.Attempt2.MyMath.abs;

public class Box2 extends Actor {
    public Vector2 halfSize;
    public RigidBody2 rigidBody2;

    public Box2(){
        super();
        halfSize = new Vector2(1,1);
    }
    public Box2(Vector2 position, Vector2 scale, double rotation, Vector2 halfSize){
        rigidBody2 = new RigidBody2(position,scale,rotation);
        this.halfSize=halfSize;
    }

    public Vector2[] getVertices(){
        Vector2[] arr = new Vector2[4];

        //get inital coords
        arr[0]=halfSize;
        arr[1]=new Vector2(halfSize.x,halfSize.y);
        arr[2]=arr[0].multiply(-1);
        arr[3]=arr[1].multiply(-1);

        //rotate if need
        if(0.01<=abs(rigidBody2.rotation)){
            Matrix2 rotate = new Matrix2(rigidBody2.rotation);
            for(int x=0;x<arr.length;x++)
                arr[x]=rotate.rotate(arr[x]);
        }//todo validate rotation mechanics

        //add relative corrds
        for(int x=0;x<arr.length;x++)
            arr[x]=arr[x].add(rigidBody2.position);

        return arr;
    }

    public Vector2 getMin(){
        return null;
    }
    public Vector2 getMax(){
        return null;
    }

    //===================================================================Fx Part
    public Box box;
    @Override
    public Node getShape() {
        return box;
    }
}
