package exercise5;

class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    private Rectangle internal;
    public SquareToRectangleAdapter(Square square)
    {
        this.internal = new Rectangle() {
            @Override
            public int getWidth() {
                return square.side;
            }

            @Override
            public int getHeight() {
                return square.side;
            }
        };
    }

    @Override
    public int getWidth() {
        return internal.getWidth();
    }

    @Override
    public int getHeight() {
        return internal.getHeight();
    }
}