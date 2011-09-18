var utils = {};

utils.body = utils.b = function(world, x, y, options) {
    return (function() {
        var opt, self = this,
        body = new box2d.BodyDef();

        body.position.Set(x, y);
        for (opt in options) {
            body[opt] = options[opt];
        }

        self.circle = function(radius, options) {
            var cricle = new box2d.CircleDef();

            circle.radius = radius;
            for (opt in options) {
                circle[opt] = options[opt];
            }

            body.AddShape(circle);

            return self;
        };

        self.box = function(width, height, options) {
            var box = new box2d.BoxDef();

            box.extents.Set(width, height);
            for (opt in options) {
                box[opt] = options[opt];
            }

            body.AddShape(box);

            return self;
        };

        self.create = self.c = function() {
            return world.CreateBody(body);
        };

        return self;
    } ());
};

utils.link = function(world, b1, b2) {
    var jd = new box2d.RevoluteJointDef();
    jd.anchorPoint.Set(b1.m_position.x, b1.m_position.y + 1);
    jd.body1 = b1;
    jd.body2 = b2;
    world.CreateJoint(jd);
};

utils.createBridge = function(world, x, y, width) {
    var b1 = utils.body(world, x, y).box(5, 2).create(),
    i = 1,
    b2;
    for (; i < 10; i++) {
        b2 = utils.body(world, x + (i * 15), y).box(5, 2, {
            density: 20,
            friction: 0.5
        }).create();
        utils.link(world, b1, b2);
        b1 = b2;
    }
    b2 = utils.body(world, x + (i * 15), y).box(5, 2).create();
    utils.link(world, b1, b2);
};

