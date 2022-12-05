import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        File myFile = new File("input.txt");
        List<String> strings = scanFile(myFile);
        Part1(strings);
        Part2(strings);
//        testCommonBetweenAll();
    }

    public static void Part1(List<String> input) {
        System.out.println("Part 1");
        int totalPriorities = 0;
        for (String x : input) {
            String[] compartments = splitLine(x);
            Character duplicate = findDuplicate(compartments);
            totalPriorities += getPriorityValue(duplicate);
        }
        System.out.println(totalPriorities);
    }

    public static void Part2(List<String> input) {
        System.out.println("Part 2");
        int totalPriorities = 0;
        for (int i = 0; i < input.size() - 2; i += 3) {
            int j = i + 1;
            int k = i + 2;

//            System.out.println(i + " " + j + " " + k);

            System.out.println(input.get(i) + "\n" +
                    input.get(j) + "\n" +
                    input.get(k)+ "\n#######################");

            String[] window = new String[] {
                    input.get(i),
                    input.get(j),
                    input.get(k)
            };

            Character common = findCommonBetweenAll(window);
            totalPriorities += getPriorityValue(common);
        }

        System.out.println(totalPriorities);
    }

    public static List<String> scanFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        List<String> output = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            output.add(line);
        }
        sc.close();
        return output;
    }

    public static String[] splitLine(String input) {
        int mid = input.length() / 2;
        String[] output = new String[2];
        output[0] = input.substring(0, mid);
        output[1] = input.substring(mid);
//        System.out.println(output[0]);
//        System.out.println(output[1]);
//        System.out.println("#########################");

        return output;
    }

    public static int getPriorityValue(Character c) {
        if (!Character.isAlphabetic(c)) return -1;
        int priority = 0;

        if (c >= 'a' && c <= 'z') {
            priority = (int) c - 'a' + 1;
        } else {
            priority = (int) c - 'A' + 27;
        }
        return priority;
    }

    public static Character findDuplicate(String[] items) {
        Set<Character> comp1 = new HashSet<>();
        Set<Character> comp2 = new HashSet<>();
        for (int i = 0; i < items[0].length(); i++) {
            Character first = items[0].charAt(i);
            Character second = items[1].charAt(i);

            comp1.add(first);
            comp2.add(second);

            if (comp1.contains(second)) {
                return second;
            } else if (comp2.contains(first)) {
                return first;
            }
        }
        return 'a';
    }

    public static Character findCommonBetweenAll(String[] window) {
        Set<Character> first = window[0].chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        for (int i = 1; i < window.length; i++) {
            Set<Character> chars = window[i].chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
            first.retainAll(chars);
        }
        System.out.println(Arrays.toString(first.toArray()));
        return first.iterator().next();
    }

    public static void testCommonBetweenAll() {
        String[] testOne = new String[] {
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg"
        };

        String[] testTwo = new String[] {
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
        };

        Character testOneCharacter = findCommonBetweenAll(testOne);
        Character testTwoCharacter = findCommonBetweenAll(testTwo);


        System.out.println("Expected 'r' \t" + testOneCharacter + "\tvalue: " + getPriorityValue(testOneCharacter));
        System.out.println("Expected 'Z' \t" + testTwoCharacter + "\tvalue: " + getPriorityValue(testTwoCharacter));
    }

    public static Character findCommonBetweenAllHashMap(String[] window) {
        Map<Character, Integer> count = new HashMap<>();
        for (String x : window) {
            char[] chars = x.toCharArray();
            for (char y : chars) {
                count.put(y, count.getOrDefault(y, 0) + 1);
                System.out.println(y + " " + count.get(y));
                if (count.get(y) == 3) return y;
            }
        }
        System.out.println("SHOULD NOT HAVE XECUTED!");
        return 'a';
    }

}
