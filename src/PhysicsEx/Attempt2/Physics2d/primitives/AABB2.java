package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import PhysicsEx.Attempt2.Physics2d.rigidbody.RigidBody2;

public class AABB2{
    public Vector2 halfSize;
    public RigidBody2 rigidBody2;

    public AABB2(){}
    public AABB2(Vector2 min, Vector2 max){
        halfSize = max.substract(min).multiply(0.5);
        rigidBody2 = new RigidBody2(min.add(halfSize));
    }

    public Vector2 getMin(){return rigidBody2.position.substract(halfSize);}
    public Vector2 getMax(){return rigidBody2.position.add(halfSize);}

    public boolean collide(AABB2 other){
        double tolerance = 0;
        Vector2 contact = halfSize.add(other.halfSize),//this is distance bellow which we got contact
                delta = rigidBody2.position.substract(other.rigidBody2.position).abs();//this is absolute difference in location of two bodies
        delta = contact.substract(delta).substract(new Vector2(tolerance,tolerance));//how much is missing delta to reach contact (minus tolerance)

        return delta.x <0||delta.y <0;
    }
}