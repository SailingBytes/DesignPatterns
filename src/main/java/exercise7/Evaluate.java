package exercise7;

import java.util.List;

public class Evaluate
{
    public static void main(String[] args)
    {
        SingleValue singleValue = new SingleValue(11);
        ManyValues otherValues = new ManyValues();
        otherValues.add(22);
        otherValues.add(33);

        int sum = new MyList(List.of(singleValue, otherValues)).sum(); // 66

        System.out.println("Expected value = 66. Got " + sum);
    }
}
