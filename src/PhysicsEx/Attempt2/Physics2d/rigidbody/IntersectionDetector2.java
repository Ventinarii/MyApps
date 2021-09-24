package PhysicsEx.Attempt2.Physics2d.rigidbody;

import PhysicsEx.Attempt2.MyMath;
import PhysicsEx.Attempt2.Physics2d.Vector2;
import PhysicsEx.Attempt2.Physics2d.primitives.AABB2;
import PhysicsEx.Attempt2.Physics2d.primitives.Box2;
import PhysicsEx.Attempt2.Physics2d.primitives.Circe2;
import PhysicsEx.Attempt2.Physics2d.primitives.Line2;

import static PhysicsEx.Attempt2.MyMath.*;

public class IntersectionDetector2 {
    //==================================================================================================================Point vs Primitives

    public static boolean collide(Line2 line2, Vector2 point){
        Vector2 relativeEnd = line2.to.substract(line2.from),
                relativePoint = point.substract(line2.from);

        //this ver return if point lies on INFINITE line. not this 'part' of line
        /*return relativeEnd//'original' solution. apparently works
                .multiply(relativePoint.x)
                .equals(relativePoint.multiply(relativeEnd.x))&&
                //this part is added so ensure that we are on this 'part' of line
                inRange(0,relativePoint.x,relativeEnd.x)&&
                inRange(0,relativePoint.y,relativeEnd.y);
        */

        //new and updated if it lands on this part of line. another 'original' solution.
        return
                equal(relativeEnd.x,0)?//IF line is straight up
                        (equal(relativePoint.x,0)&&//IS the point on line
                                0<=relativePoint.y&&relativePoint.y<=relativeEnd.y):(//ensure that we are WITHIN limits
                                           //ELSE
                0<=relativePoint.x&&relativePoint.x<=relativeEnd.x&&//ensure that we are WITHIN limits
                equal(relativeEnd.y*(relativePoint.x/relativeEnd.x)-//get HEIGHT of vector of LENGTH (in X axis) equal to x value of point
                        relativePoint.y,0));//subtract and get absolute difference
        //todo validate
    }

    public static boolean pointInCircle(Circe2 circe2,Vector2 point){
        Vector2 relative = point.substract(circe2.rigidBody2.position);

        return pow2(relative.x)+ pow2(relative.y)<= pow2(circe2.radius);
    }

    public static boolean pointInAABB2(AABB2 aabb2, Vector2 point){
        Vector2 min = aabb2.getMin(),
                max = aabb2.getMax();

        return inRange(min.x,point.x,max.x)&&
               inRange(min.y,point.y,max.y);
    }

    public static boolean pointInBox2(Box2 box, Vector2 point){
        Vector2 relative = point.substract(box.rigidBody2.position);//we get relative position of point

        //<=IMPORTANT this seems logical but is different from tutorial -> error?
        Vector2 pointUnRotated = box.rigidBody2.getRotationMatrix().unRotate(relative);//we rotate box to aligned with world - point follows

        Vector2 absolute = pointUnRotated.abs();//we get absolute value of point (it is mirrored to right top corner of shape)

        Vector2 diffrence = absolute.substract(box.halfSize);//we check if point is WITHIN said part or outside it

        return diffrence.x<=0 && diffrence.y<=0;//we check if negative - is true when point relative position to origin point of box is smaller than it's half size in both axes
        //todo validate
    }


    //==================================================================================================================Line  vs Primitives


}
