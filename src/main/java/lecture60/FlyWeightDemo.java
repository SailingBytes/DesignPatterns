package lecture60;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//class User
//{
//    private String fullName;
//
//    public User(String fullName) {
//        this.fullName = fullName;
//    }
//}

class User2
{
    static List<String> strings = new ArrayList<>();
    int[] names;

    public User2(String fullName)
    {
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);

            if (idx == -1){
                strings.add(s);
                idx = strings.size() - 1;
            }

            System.out.println("Index is of " + s +" is " + idx);
            return idx;
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> {
                    System.out.println("s is now " + s);
                    return getOrAdd.apply(s);
                }).toArray();
    }

    public String getFullName()
    {
        return Arrays.stream(names).mapToObj(i -> strings.get(i))
                .collect(Collectors.joining(" "));
    }
}

public class FlyWeightDemo
{
    public static void main(String[] args) {
        User2 user = new User2("John Smith");
        User2 user1 = new User2("Jane Smith");

        User2.strings.forEach(s -> System.out.println("String is " + s));
        System.out.println("Indizes: " + user.names[0] + " " + user.names[1] +" name = " + user.getFullName());
        System.out.println("Indizes: " + user1.names[0] + " " + user1.names[1] +" name = " + user.getFullName());
        // have "Smith" in common
    }
}
