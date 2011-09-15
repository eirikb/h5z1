var world;

window.onload = function() {
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

    var sd = new box2d.BoxDef();
    sd.density = 1;
    sd.friction = 0;
    sd.extents.Set(10, 20);
    var bd = new box2d.BodyDef();
    bd.preventRotation = true;
    bd.allowSleep = false;
    bd.AddShape(sd);
    sd = new box2d.CircleDef();
    sd.density = 1;
    sd.friction = 1;
    sd.radius = 10;
    sd.localPosition.Set(0, 16);
    bd.AddShape(sd);
    bd.position.Set(150, 250);
    player = world.CreateBody(bd);
    player.speed = 100;

    world.SetFilter({
        ShouldCollide: function(a, b) {
            if (a === player.m_shapeList || b === player.m_shapeList) {
                player.canJump = true;
            }
        }
    });

    var canvas = document.createElement('canvas');
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
        utils.createBox(e.offsetX, e.offsetY, 10, 10);
    };

    var time = Date.now();
    setInterval(function() {
        world.Step(1.0 / 60, 1);
        if (player.way) {
            var v = player.GetLinearVelocity();
            v.Set(player.way * player.speed, v.y);
        }
        draw.drawWorld(world, ctx);

    },
    1000 / 60);
};

