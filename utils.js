var utils = {};

utils.createWorld = function() {
    var worldAABB = new box2d.AABB(),
    gravity = new box2d.Vec2(0, 300),
    doSleep = true;

    worldAABB.minVertex.Set( - 1000, - 1000);
    worldAABB.maxVertex.Set(1000, 1000);

    utils.world = new box2d.World(worldAABB, gravity, doSleep);
    utils.createBox(250, 305, 250, 5, true, true);
    utils.createBox(5, 185, 5, 125, true, true);
    utils.createBox(495, 185, 5, 125, true, true);
    return utils.world;
};

utils.createBall = function(x, y, radius) {
    radius = radius || 20;
    var ballSd = new box2d.CircleDef();
    ballSd.density = 1.0;
    ballSd.radius = radius;
    ballSd.restitution = 0.8;
    ballSd.friction = 0.9;
    var ballBd = new box2d.BodyDef();
    ballBd.AddShape(ballSd);
    ballBd.position.Set(x, y);
    return utils.world.CreateBody(ballBd);
};

utils.createBox = function(x, y, width, height, fixed, filled) {
    var boxSd = new box2d.BoxDef();
    if (!fixed) {
        boxSd.density = 1.0;
    }
    if (filled) {
        boxSd.userData = 'filled';
    }
    boxSd.extents.Set(width, height);
    var boxBd = new box2d.BodyDef();
    boxBd.AddShape(boxSd);
    boxBd.position.Set(x, y);
    return utils.world.CreateBody(boxBd);
};

