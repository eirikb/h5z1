var worldAABB = new box2d.AABB();
worldAABB.minVertex.Set(-1000, -1000);
worldAABB.maxVertex.Set(1000, 1000);
var gravity = new box2d.Vec2(0, 300);
var doSleep = true;
var world = new box2d.World(worldAABB, gravity, doSleep); 
