/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte draw işlemine ilişkin polimorfik yaklaşıma dikkat ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        DemoPowerPointApp.run(args);
    }
}

class DemoPowerPointApp {
    private static void fillShapes(Shape [] shapes, ShapeFactory factory)
    {
        for (var i = 0; i < shapes.length; ++i)
            shapes[i] = factory.createShape();
    }

    private static void drawShapes(Shape [] shapes)
    {
        var random = new Random();

        for (var shape : shapes)
            shape.draw(random.nextInt(0, 200), random.nextInt(0, 200));
    }

    public static void run(String[] args)
    {
        Shape [] shapes;
        ShapeFactory factory = new ShapeFactory();

        while (true) {
            var count = Console.readInt("Input shape count:");

            if (count <= 0)
                break;

            shapes = new Shape[count];
            fillShapes(shapes, factory);
            drawShapes(shapes);
        }
    }
}

class ShapeFactory {
    private final Random m_random = new Random();
    private static final ShapeColor [] SHAPE_COLORS = ShapeColor.values();

    private ShapeColor createShapeColor()
    {
        return SHAPE_COLORS[m_random.nextInt(SHAPE_COLORS.length)];
    }

    public Shape createShape()
    {
        return switch (m_random.nextInt(5)) {
            case 0 -> new Rectangle(createShapeColor(), createShapeColor());
            case 1 -> new Triangle(createShapeColor(), createShapeColor());
            case 2 -> new Ellipse(createShapeColor(), createShapeColor());
            case 3 -> new ConnectorCurve(createShapeColor());
            default -> new Line(createShapeColor());
        };
    }
}

class Rectangle extends Shape {
    //...

    public Rectangle(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Rectangle:%s, %s", foreGroundColor, backGroundColor);
    }
}


class Triangle extends Shape {
    //...

    public Triangle(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Triangle:%s, %s", foreGroundColor, backGroundColor);
    }
}

class Ellipse extends Shape {
    //...

    public Ellipse(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Ellipse:%s, %s", foreGroundColor, backGroundColor);
    }
}

class Line extends Shape {
    //...

    public Line(ShapeColor foreGroundColor/*...*/)
    {
        super(foreGroundColor, null);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Line:%s", foreGroundColor);
    }
}

class ConnectorCurve extends Shape {
    //...

    public ConnectorCurve(ShapeColor foreGroundColor/*...*/)
    {
        super(foreGroundColor, null);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw ConnectorCurve:%s", foreGroundColor);
    }
}

enum ShapeColor {
    AUTO, RED, GREEN, BLUE, WHITE, BLACK
}

abstract class Shape {
    protected ShapeColor foreGroundColor;
    protected ShapeColor backGroundColor;

    //...

    protected  Shape(ShapeColor foreGroundColor, ShapeColor backGroundColor)
    {
        this.foreGroundColor = foreGroundColor;
        this.backGroundColor = backGroundColor;
    }

    public abstract void draw(int x, int y);
}

