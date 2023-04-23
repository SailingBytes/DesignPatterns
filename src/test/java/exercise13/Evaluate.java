package exercise13;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class Evaluate
{
    @Test
    public void test()
    {
        ExpressionProcessor2 ep = new ExpressionProcessor2();
        ep.variables.put('x', 5);

//        assertEquals(1, ep.calculate("1"));
//        assertEquals(3, ep.calculate("1+2"));
//        assertEquals(6, ep.calculate("1+x"));
//        assertEquals(0, ep.calculate("1+xy"));

//        assertEquals(6, ep.calculate("1+2+3"));
//        assertEquals(0, ep.calculate("1+2+xy"));
        ep.variables.put('x', 3);
//        assertEquals(5, ep.calculate("10-2-x"));

//        assertEquals(5, ep.calculate("(10-2)-x"));
//        assertEquals(5, ep.calculate("10-(2+x)"));
//        assertEquals(10, ep.calculate("7+(6-3)"));
        assertEquals(15, ep.calculate("7+(6-3)+5"));
//        assertEquals(19, ep.calculate("7+((6+9)-3)"));
        assertEquals(19, ep.calculate("((7+6) +9 )-3)"));
    }
}
