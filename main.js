window.onload = function() {
    var canvas, ctx, player, world, bd, sd, worldAABB = new box2d.AABB(),
    gravity = new box2d.Vec2(0, 300),
    doSleep = true;

    worldAABB.minVertex.Set( - 1000, - 1000);
    worldAABB.maxVertex.Set(1000, 1000);

    world = new box2d.World(worldAABB, gravity, doSleep);
    utils.b(world, 100, 300).box(50, 5, {
        userData: 'filled'
    }).c();
    utils.createBridge(world, 150, 300, 100);
    utils.b(world, 350, 300).box(50, 5, {
        userData: 'filled'
    }).c();

    player = utils.b(world, 150, 250, {
        allowSleep: false,
        preventRotation: true
    }).box(10, 20, {
        density: 1,
        friction: 0
    }).circle(10, {
        density: 1,
        friction: 1
    },
    0, 16).c();
    player.speed = 100;

    world.SetFilter({
        ShouldCollide: function(a, b) {
            if (a === player.m_shapeList || b === player.m_shapeList) {
                player.canJump = true;
            }
        }
    });

    canvas = document.createElement('canvas');
    canvas.width = 1000;
    canvas.height = 1000;
    ctx = canvas.getContext('2d');
    document.body.appendChild(canvas);

    document.onkeydown = function(e) {
        switch (e.keyCode) {
        case 65:
            player.way = - 1;
            break;
        case 68:
            player.way = 1;
            break;
        case 87:
            if (player.canJump) {
                player.canJump = false;
                player.way = player.way ? player.way: 0;
                var v = player.GetLinearVelocity();
                v.Set(player.way * player.speed, - player.speed);
            }
            break;
        }
    };
    document.onkeyup = function(e) {
        if ((e.keyCode === 65 && player.way === - 1) || (e.keyCode === 68 && player.way === 1)) {
            var v = player.GetLinearVelocity();
            v.Set(player.way = 0, v.y);
        }
    };

    canvas.onclick = function(e) {
        utils.b(world, e.offsetX, e.offsetY).box(10, 10, {
            density: 1
        }).c();
    };

    var t1, t2, t3;
    setInterval(function() {
        t1 = t2 = Date.now();
        world.Step(1.0 / 60, 1);
        t2 = Date.now() - t2;
        if (player.way) {
            var v = player.GetLinearVelocity();
            v.Set(player.way * player.speed, v.y);
        }
        t3 = Date.now();
        draw.drawWorld(world, ctx);
        t1 = Date.now() - t1;
        t3 = Date.now() - t3;
        ctx.fillText('FPS: ' + Math.floor(1000 / t1) + '. Game: ' + t2 + '. Draw: ' + t3, 0, 10);
    },
    1000 / 60);
};

