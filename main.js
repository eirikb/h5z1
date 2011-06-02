var world;

$(function() {
    var worldAABB = new box2d.AABB(),
    gravity = new box2d.Vec2(0, 300),
    doSleep = true;

    worldAABB.minVertex.Set( - 1000, - 1000);
    worldAABB.maxVertex.Set(1000, 1000);

    world = new box2d.World(worldAABB, gravity, doSleep);
    utils.createBox(100, 300, 50, 5, {
        userData: 'filled'
    });
    utils.createBridge(150, 300, 100);
    utils.createBox(350, 300, 50, 5, {
        userData: 'filled'
    });

    var boxSd = new box2d.BoxDef();
    boxSd.density = 1;
    boxSd.friction = 0;
    boxSd.extents.Set(10, 20);
    var boxBd = new box2d.BodyDef();
    boxBd.preventRotation = true;
    boxBd.allowSleep = false;
    boxBd.AddShape(boxSd);
    boxSd = new box2d.BoxDef();
    boxSd.density = 1;
    boxSd.friction = 0;
    boxSd.extents.Set(6, 2);
    boxSd.localPosition.Set(1, 22);
    boxBd.AddShape(boxSd);
    boxBd.position.Set(150, 250);
    player = world.CreateBody(boxBd);

    world.SetFilter({
        ShouldCollide: function(a, b) {
            if (a === player.m_shapeList || b === player.m_shapeList) {
                player.canJump = true;
            }
        }
    })

    var $canvas = $('<canvas>'),
    ctx = $canvas[0].getContext('2d');
    $('body').append($canvas).keydown(function(e) {
        switch (e.keyCode) {
        case 65:
            player.way = - 100;
            break;
        case 68:
            player.way = 100;
            break;
        case 87:
            if (player.canJump) {
                player.canJump = false;
                player.way = player.way ? player.way: 0;
                var v = player.GetLinearVelocity();
                v.Set(player.way, - 100);
            }
            break;
        }
    }).keyup(function(e) {
        if ([65, 68].indexOf(e.keyCode) >= 0) {
            var v = player.GetLinearVelocity();
            v.Set(player.way = 0, v.y);
        }
    });

    $canvas.playground({
        width: 1000,
        height: 1000
    }).startGame().registerCallback(function() {
        world.Step(1.0 / 60, 1);
        if (player.way) {
            var v = player.GetLinearVelocity();
            v.Set(player.way, v.y);
        }
        draw.drawWorld(world, ctx);
    },
    30).click(function(e) {
        utils.createBox(e.offsetX, e.offsetY, 10, 10);
    });
});

