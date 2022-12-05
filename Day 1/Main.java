import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Day 1
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        List<Integer> elves = new ArrayList<>();
        int elf = 0;
        elves.add(elf, 0);

        int max = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty() || line.isBlank()) {
                elf++;
                elves.add(elf, 0);
            } else {
                int calories = Integer.parseInt(line);
                elves.add(elf, elves.get(elf) + calories);
            }
            max = Math.max(elves.get(elf), max);
        }

        sc.close();

        System.out.println(max);

        // Day 2 top three
        Collections.sort(elves);
        int topThree = elves.get(elves.size() - 1) + elves.get(elves.size() -2) + elves.get(elves.size() -3);
        System.out.println(topThree);



    }

}
