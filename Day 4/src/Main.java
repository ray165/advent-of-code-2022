import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("input.txt");
        List<List<Integer>> startEndTimes = scanFile(myFile);
        Part1(startEndTimes);
        Part2(startEndTimes);
    }

    public static List<List<Integer>> scanFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        List<List<Integer>> output = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            List<Integer> inner = new ArrayList<>();
            line = line.replaceAll("[,-]", " ");
            String[] times = line.split(" ");
            for (String time : times) {
                inner.add(Integer.parseInt(time));
            }

            output.add(inner);
        }
        sc.close();
        return output;
    }

    public static void Part1(List<List<Integer>> input) {
        int total = 0;
        System.out.println("Part 1");

        for (List<Integer> pair : input) {

            boolean firstContainsSecond = pair.get(0) <= pair.get(2) && pair.get(1) >= pair.get(3);
            boolean secondContainsFirst = pair.get(2) <= pair.get(0) && pair.get(3) >= pair.get(1);
            if (firstContainsSecond || secondContainsFirst ) {
                total++;
            }
        }
        System.out.println(total);
    }

    public static void Part2(List<List<Integer>> input) {
        int total = 0;
        System.out.println("Part 2");
        for (List<Integer> pair : input) {

            boolean firstContainsSecond = pair.get(0) <= pair.get(2) && pair.get(1) >= pair.get(3);
            boolean secondContainsFirst = pair.get(2) <= pair.get(0) && pair.get(3) >= pair.get(1);
            boolean firstEndsBetweenSecond = pair.get(1) >= pair.get(2) && pair.get(1) <= pair.get(3); // second starts within first
            boolean secondEndsBetweenFirst = pair.get(2) >= pair.get(1) && pair.get(3) <= pair.get(1); // first starts within second
            boolean firstStartsBetweenSecond = pair.get(0) >= pair.get(2) && pair.get(0) <= pair.get(3);
            boolean secondStartsBetweenFirst = pair.get(2) >= pair.get(0) && pair.get(2) <= pair.get(1);


            if (firstContainsSecond || secondContainsFirst ||
                    firstEndsBetweenSecond || secondEndsBetweenFirst ||
                    firstStartsBetweenSecond || secondStartsBetweenFirst
            ) {
                total++;
            }
        }
        System.out.println(total);
    }

}
