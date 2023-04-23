package exercise4;

import java.util.function.Supplier;

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        // todo
        Object obj1 = func.get();
        Object obj2 = func.get();

        return obj1 == obj2;
    }
}