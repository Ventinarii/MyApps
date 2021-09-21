package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Physics2d.Vector2;

public class AABB2{
    public Vector2 center, halfSize;
    public boolean collide(AABB2 other){
        double tolerance = 0.1;
        Vector2 contact = halfSize.add(other.halfSize),//this is distance bellow which we got contact
                delta = center.substract(other.center).abs();//this is absolute difference in location of two bodies
        delta = contact.substract(delta).substract(new Vector2(tolerance,tolerance));//how much is missing delta to reach contact (minus tolerance)

        return delta.x <0||delta.y <0;
    }
}
