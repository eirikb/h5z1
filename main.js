var world;

$(function() {
    var worldAABB = new box2d.AABB(),
    gravity = new box2d.Vec2(0, 300),
    doSleep = true;

    worldAABB.minVertex.Set( - 1000, - 1000);
    worldAABB.maxVertex.Set(1000, 1000);

    world = new box2d.World(worldAABB, gravity, doSleep);
    utils.createBox(100, 300, 50, 5, true, true);
    utils.createBridge(150, 300, 100);
    utils.createBox(350, 300, 50, 5, true, true);

    $canvas = $('<canvas>'),

    ctx = $canvas[0].getContext('2d');
    $('body').append($canvas);

    $canvas.playground({
        width: 1000,
        height: 1000
    }).startGame().registerCallback(function() {
        world.Step(1.0 / 60, 1);
        draw.drawWorld(world, ctx);
    },
    30).click(function(e) {
        utils.createBox(e.offsetX, e.offsetY, 10, 10);
    });
});

