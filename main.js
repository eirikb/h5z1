
var world = utils.createWorld();

var circleSd = new box2d.CircleDef();
circleSd.density = 1.0;
circleSd.radius = 20;
circleSd.restitution = 1.0;
circleSd.friction = 0;

var circleBd = new box2d.BodyDef();
circleBd.AddShape(circleSd);
circleBd.position.Set(200, 10);

var circleBody = world.CreateBody(circleBd);

var jointDef = new box2d.RevoluteJointDef();
jointDef.anchorPoint.Set(250, 200);
jointDef.body1 = world.GetGroundBody();
jointDef.body2 = circleBody;
world.CreateJoint(jointDef);

var $canvas = $('<canvas>'),
ctx = $canvas[0].getContext('2d');

$(function() {
    $('body').append($canvas);

    $canvas.playground({
        width: 1000,
        height: 1000
    }).startGame().registerCallback(function() {
        world.Step(1.0 / 60, 1);
        draw.drawWorld(world, ctx);
    },
    30);

});

