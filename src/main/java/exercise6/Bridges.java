package exercise6;

interface Renderer
{
    public String whatToRenderAs();
}

class RasterRenderer implements Renderer
{

    @Override
    public String whatToRenderAs()
    {
        return "pixels";
    }
}

class VectorRenderer implements Renderer
{
    @Override
    public String whatToRenderAs()
    {
        return "lines";
    }
}

abstract class Shape {
    protected Renderer renderer;
    protected String name;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", name, renderer.whatToRenderAs());
    }
}

class Triangle extends Shape {

    public Triangle(Renderer renderer) {
        super(renderer);
        name = "Triangle";
    }
}

class Square extends Shape {
    public Square(Renderer renderer) {
        super(renderer);
        name = "Square";
    }
}