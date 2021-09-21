package PhysicsEx.Attempt2.Physics2d.primitives;

import PhysicsEx.Attempt2.Physics2d.Vector2;

public abstract class Colider2 {
    public Vector2 offset = new Vector2();

    public abstract float getInertiaTensor(float mass);
}
