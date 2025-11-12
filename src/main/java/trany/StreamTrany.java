package trany;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTrany {
    static Map<Integer, String> map = new HashMap<>();
    static Map<String, String> immutableMap = Map.of("key1", "value1", "key2", "value2");
//    static List<String> strings = List.of("one", "two", "three"); падает от такого билд

    static List<String> strings = new ArrayList<>();

    static { // Статический инициализатор, чтобы доступно сразу после запуска программы
        map.put(1, "one");
        map.put(2, "two");

        strings.add("one");
        strings.add("two");
        strings.add("three");
    }

    public static void main(String[] args) {
        String result = immutableMap.values().stream()
                .collect(Collectors.joining(" "));
        System.out.println("++++++" + result);


        Map<Integer, String> collectMap = strings.stream()
                .collect(Collectors.toMap(
                        el -> strings.indexOf(el),
                        el -> el + " plus"
                ));

        collectMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });


        map.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

        //тут нужен именно линкед, чтобы сортировка сохранилась
        var reverseMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                //   .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))

                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (first, second) -> second, LinkedHashMap::new));

        reverseMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

        ClazzWithParam<String> stringParam = new ClazzWithParam<>();


    }
}
