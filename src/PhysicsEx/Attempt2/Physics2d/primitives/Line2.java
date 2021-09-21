package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.MyMath;
import PhysicsEx.Attempt2.Physics2d.Vector2;

public class Line2{
    public Vector2 start, end;
    public Line2(){
        start = new Vector2(0,0);
        end = new Vector2(1,1);
    }
    public Line2(Vector2 start, Vector2 end){
        this.start=start;
        this.end=end;
    }
    public boolean collide(Vector2 point){
        Vector2 relativeEnd = end.substract(start),
                relativePoint = point.substract(start);
        //this ver return if point lies on INFINITE line. not this 'part' of line
        return relativeEnd//'original' solution. apparently works
                .multiply(relativePoint.x)
                .equals(relativePoint.multiply(relativeEnd.x))&&
                //this part is added so ensure that we are on this 'part' of line
                MyMath.inRange(0,relativePoint.x,relativeEnd.x)&&
                MyMath.inRange(0,relativePoint.y,relativeEnd.y);
    }
}
