package lecture60;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserOrig
{
    private String fullName;

    public UserOrig(String fullName) {
        this.fullName = fullName;
    }
}

class User2Orig
{
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2Orig(String fullName)
    {
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx;
            else {
                strings.add(s);
                for(int i = 0; i < strings.size(); ++i) {
                    System.out.println("Index " + i + "is " + strings.get(i));
                }
                return strings.size() - 1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> getOrAdd.apply(s)).toArray();
    }

    public String getFullName()
    {
        return Arrays.stream(names).mapToObj(i -> strings.get(i))
                .collect(Collectors.joining(","));
    }
}

class FlyWeightDemoOrig
{
    public static void main(String[] args) {
        User2Orig user = new User2Orig("John Smith");
        User2Orig user1 = new User2Orig("Jane Smith");

        // have "Smith" in common
    }
}