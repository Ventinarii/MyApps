package PhysicsEx.Attempt2.Physics2d.primitives;
import PhysicsEx.Attempt2.Physics2d.Vector2;

public class Line2{
    public Vector2 from, to;
    public Line2(){
        from = new Vector2(0,0);
        to = new Vector2(1,1);
    }
    public Line2(Vector2 start, Vector2 end){
        this.from =start;
        this.to =end;
    }
}
