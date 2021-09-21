package PhysicsEx.Attempt2;

public class MyMath{
    /**
     * method returns value between from and to.
     * @param from lowest value function can return
     * @param val desired value function can return
     * @param to highest value function can return
     */
    public static double clamp(double from, double val, double to){return (val<from)?(from):((to<val)?(to):(val));}
    /**
     * method returns IF value IS between from and to, regardless of what is greater ('from','to')
     * @param from one of border values
     * @param val check value
     * @param to one of border values
     */
    public static boolean inRange(double from, double val, double to){return (from<to)?(from<=val&&val<=to):(to<=val&&val<=from);}
    /**
     * @return d*d
     */
    public static double pow2(double d){return d*d;}
    /**
     * @return positive double of value d
     */
    public static double abs(double d){return (d<0)?(-d):(d);}
    /**
     * method returns angle expressed in radians for needs of external trigonometry functions
     * @angle angle expressed in degress (360')
     * @return angle expressed in radians (2PI)
     */
    public static double degToRadians(double angle){
        double radians = (angle%360);//stay in 0-360
        if(radians<0)radians+=360;//switch negative values to positive
        radians*=Math.PI/180;//add PI end result =>  ( (360+ (rotationDeg%360) )%360 )*(2*Math.PI/360); // but cheaper (avoided another '%')
        return radians;
    }
}
