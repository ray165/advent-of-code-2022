import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        // Day 2
        System.out.println("Day 2");
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        Map<String, String> beats = new HashMap<>();
        beats.put("Y", "A");
        beats.put("Z", "B");
        beats.put("X", "C");

        Map<String, Integer> shapeScores = new HashMap<>();
        shapeScores.put("X", 1);
        shapeScores.put("Y", 2);
        shapeScores.put("Z", 3);

        Map<String, String> equivalents = new HashMap<>();
        equivalents.put("X", "A");
        equivalents.put("Y", "B");
        equivalents.put("Z", "C");

        int total = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            String[] tokens = line.split(" ");
            String opp = tokens[0];
            String stratPlay = tokens[1];
//            System.out.println(opp + " " + stratPlay);

            total += shapeScores.get(stratPlay);

//            System.out.println(equivalents.get(stratPlay) + beats.get(stratPlay));

            if ( equivalents.get(stratPlay).equals(opp)) {
                total += 3;
            } else if ( beats.get(stratPlay).equals(opp)) {
                total += 6;
            }
        }
        System.out.println();
        sc.close();

        System.out.println(total);

        part2();

    }

    public static void part2() throws FileNotFoundException {
        System.out.println("Day 2 part 2");
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        Map<String, Integer> shapeScores = new HashMap<>();
        shapeScores.put("A", 1);
        shapeScores.put("B", 2);
        shapeScores.put("C", 3);

        int total = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            String[] tokens = line.split(" ");
            String opp = tokens[0];
            String command = tokens[1];

            String shapeNeededToPlay = getShape(opp, command);
            total += shapeScores.get(shapeNeededToPlay);

            switch(command) {
                case "Y":
                    total +=3;
                    break;
                case "Z":
                    total +=6;
                    break;
                default:
                    break;
            }
        }

        sc.close();
        System.out.println(total);
    }

    public static String getShape(String opp, String command) {
        Map<String, String> beats = new HashMap<>();
        beats.put("A", "B");
        beats.put("B", "C");
        beats.put("C", "A");

        Map<String, String> loses = new HashMap<>();
        loses.put("A", "C");
        loses.put("B", "A");
        loses.put("C", "B");

        if (command.equals("X")) {
            // lose
            return loses.get(opp);
        } else if (command.equals("Y")) {
            // draw
            return opp;
        } else {
            // win
            return beats.get(opp);
        }
    }
}
