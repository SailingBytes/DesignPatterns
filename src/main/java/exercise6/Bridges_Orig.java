package exercise6;

abstract class ShapeOrig
{
    public abstract String getName();
}

class TriangleOrig extends ShapeOrig
{
    @Override
    public String getName()
    {
        return "Triangle";
    }
}

class SquareOrig extends ShapeOrig
{
    @Override
    public String getName()
    {
        return "Square";
    }
}

class VectorSquareOrig extends SquareOrig
{
    @Override
    public String toString()
    {
        return String.format("Drawing %s as lines", getName());
    }
}

class RasterSquareOrig extends SquareOrig
{
    @Override
    public String toString()
    {
        return String.format("Drawing %s as pixels", getName());
    }
}

// imagine VectorTriangle and RasterTriangle are here too