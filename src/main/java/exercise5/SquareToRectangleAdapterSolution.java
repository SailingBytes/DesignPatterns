package exercise5;

//class Square
//{
//    public int side;
//
//    public Square(int side)
//    {
//        this.side = side;
//    }
//}
//
//interface Rectangle
//{
//    int getWidth();
//    int getHeight();
//
//    default int getArea()
//    {
//        return getWidth() * getHeight();
//    }
//}

class SquareToRectangleAdapterSolution implements Rectangle
{
    private Square square;

    public SquareToRectangleAdapterSolution(Square square)
    {
        this.square = square;
    }

    @Override
    public int getWidth()
    {
        return square.side;
    }

    @Override
    public int getHeight()
    {
        return square.side;
    }
}
