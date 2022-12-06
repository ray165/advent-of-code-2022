import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("input.txt");
        String input = scanFile(myFile);
        Part1(input);
        Part2(input);
    }

    public static String scanFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        String line = "";
        while (sc.hasNextLine()) {
            line = sc.nextLine();
        }
        sc.close();
        return line;
    }

    public static void Part1(String input) {
        System.out.println("Part 1");

        int counter = 0;
        for (int i = 0; i < input.length() - 3; i++) {
            counter++;
            String checkSubString = input.substring(i, i + 4);
//            System.out.println(checkSubString);
            if (isAllUnique(checkSubString)) {
                break;
            }
        }
        // # of characters from 0 to the END first 4 character marker
        System.out.println(counter + 3);
    }

    public static boolean isAllUnique(String subString) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < subString.length(); i++) {
            Character c = subString.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    public static void Part2(String input) {
        System.out.println("Part 2");

        int counter = 0;
        for (int i = 0; i < input.length() - 3; i++) {
            counter++;
            String checkSubString = input.substring(i, i + 14);
//            System.out.println(checkSubString);
            if (isAllUnique(checkSubString)) {
                break;
            }
        }
        // # of characters from 0 to the END first 4 character marker
        System.out.println(counter + 13);

    }
}