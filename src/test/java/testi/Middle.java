package testi;

import java.util.*;
import java.util.stream.Collectors;

public class Middle {
    public static void main(String[] args) {
        Integer[] num = {-7, -3, 2, 3, 11};

        var numQ = Arrays.stream(num)
                .map(n -> n * n)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(numQ);
    }

}
