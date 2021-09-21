package PhysicsEx.Attempt2.Physics2d.rigidbody;

import PhysicsEx.Attempt2.MyMath;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import PhysicsEx.Attempt2.Physics2d.primitives.AABB2;
import PhysicsEx.Attempt2.Physics2d.primitives.Box2;
import PhysicsEx.Attempt2.Physics2d.primitives.Circe2;
import PhysicsEx.Attempt2.Physics2d.primitives.Line2;

public class IntersectionDetector2 {
    public static final double tolerance=0.001;
    //==================================================================================================================Point vs Primitives

    public static boolean collide(Line2 line2, Vector2 point){
        Vector2 relativeEnd = line2.to.substract(line2.from),
                relativePoint = point.substract(line2.from);

        //this ver return if point lies on INFINITE line. not this 'part' of line
        /*return relativeEnd//'original' solution. apparently works
                .multiply(relativePoint.x)
                .equals(relativePoint.multiply(relativeEnd.x))&&
                //this part is added so ensure that we are on this 'part' of line
                MyMath.inRange(0,relativePoint.x,relativeEnd.x)&&
                MyMath.inRange(0,relativePoint.y,relativeEnd.y);
        */

        //new and updated if it lands on this part of line. another 'original' solution.
        return
                (relativeEnd.x<=tolerance)?//IF line is straight up
                        (MyMath.abs(relativePoint.x)<=tolerance&&//IS the point on line
                                0<=relativePoint.y&&relativePoint.y<=relativeEnd.y):(//ensure that we are WITHIN limits
                                           //ELSE
                0<=relativePoint.x&&relativePoint.x<=relativeEnd.x&&//ensure that we are WITHIN limits
                MyMath.abs(relativeEnd.y*(relativePoint.x/relativeEnd.x)-//get HEIGHT of vector of LENGTH (in X axis) equal to x value of point
                        relativePoint.y)<=tolerance);//subtract and get absolute difference
        //todo validate
    }

    public static boolean pointInCircle(Circe2 circe2,Vector2 point){
        Vector2 relative = point.substract(circe2.rigidBody2.position);

        return MyMath.pow2(relative.x)+MyMath.pow2(relative.y)<=MyMath.pow2(circe2.radius);
    }

    public static boolean pointInAABB2(AABB2 aabb2, Vector2 point){
        Vector2 min = aabb2.getMin(),
                max = aabb2.getMax();

        return MyMath.inRange(min.x,point.x,max.x)&&
               MyMath.inRange(min.y,point.y,max.y);
    }

    public static boolean pointInBox2(Box2 aabb2, Vector2 point){
        return false;//todo implement
    }

    //==================================================================================================================Line  vs Primitives


}
