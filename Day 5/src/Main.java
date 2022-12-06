import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("input.txt");
        List<List<Integer>> commands = scanFile(myFile);
        Part1(commands);
        Part2(commands);
    }

    public static List<Stack<Character>> buildOriginalSetup() {
        List<Stack<Character>> allCrates = new ArrayList<>();
        Stack<Character> first = new Stack<Character>();
        Stack<Character> two = new Stack<Character>();
        Stack<Character> three = new Stack<Character>();
        Stack<Character> four = new Stack<Character>();
        Stack<Character> five = new Stack<Character>();
        Stack<Character> six = new Stack<Character>();
        Stack<Character> seven = new Stack<Character>();
        Stack<Character> eight = new Stack<Character>();
        Stack<Character> nine = new Stack<Character>();

        first.push('G');
        first.push('T');
        first.push('R');
        first.push('W');

        two.push('G');
        two.push('C');
        two.push('H');
        two.push('P');
        two.push('M');
        two.push('S');
        two.push('V');
        two.push('W');

        three.push('C');
        three.push('L');
        three.push('T');
        three.push('S');
        three.push('G');
        three.push('M');

        four.push('J');
        four.push('H');
        four.push('D');
        four.push('M');
        four.push('W');
        four.push('R');
        four.push('F');

        five.push('P');
        five.push('Q');
        five.push('L');
        five.push('H');
        five.push('S');
        five.push('W');
        five.push('F');
        five.push('J');

        six.push('P');
        six.push('J');
        six.push('D');
        six.push('N');
        six.push('F');
        six.push('M');
        six.push('S');

        seven.push('Z');
        seven.push('B');
        seven.push('D');
        seven.push('F');
        seven.push('G');
        seven.push('C');
        seven.push('S');
        seven.push('J');

        eight.push('R');
        eight.push('T');
        eight.push('B');

        nine.push('H');
        nine.push('N');
        nine.push('W');
        nine.push('L');
        nine.push('C');

        allCrates.add(first);
        allCrates.add(two);
        allCrates.add(three);
        allCrates.add(four);
        allCrates.add(five);
        allCrates.add(six);
        allCrates.add(seven);
        allCrates.add(eight);
        allCrates.add(nine);

        return allCrates;
    }

    public static List<List<Integer>> scanFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        List<List<Integer>> output = new ArrayList<>();
        boolean startAdding = false;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            List<Integer> inner = new ArrayList<>();
            if (startAdding) {
                line = line.replaceAll("move |from |to ", "");
//                System.out.println(line);
                String[] numbs = line.split(" ");
                for (String numb : numbs) {
                    inner.add(Integer.parseInt(numb));
                }

                output.add(inner);
            }

            if (line.isBlank() || line.isEmpty()) startAdding = true;
        }
        sc.close();
        return output;
    }

    public static void followCommands(List<Stack<Character>> allCrates, List<Integer> command) {
        int countToMove = command.get(0);
        int source = command.get(1) - 1;
        int target = command.get(2) - 1;

        for (int i = 0; i < countToMove; i++) {
            Character popped = allCrates.get(source).pop();
//            System.out.println(popped);
            allCrates.get(target).push(popped);
        }
    }

    public static void followCommandsPart2(List<Stack<Character>> allCrates, List<Integer> command) {
        int countToMove = command.get(0);
        int source = command.get(1) - 1;
        int target = command.get(2) - 1;

        Stack<Character> helper = new Stack<>();

        for (int i = 0; i < countToMove; i++) {
            Character popped = allCrates.get(source).pop();
            helper.push(popped);
        }

        while (!helper.isEmpty()) {
            Character popped = helper.pop();
            allCrates.get(target).push(popped);
        }

    }


    public static void Part1(List<List<Integer>> commands) {
        System.out.println("Part 1");
        List<Stack<Character>> allCrates = buildOriginalSetup();
        for (List<Integer> command : commands) {
            followCommands(allCrates, command);
        }
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> cratesStack : allCrates) {
            sb.append(cratesStack.peek());
        }
        System.out.println(sb);
    }

    public static void Part2(List<List<Integer>> commands) {
        System.out.println("Part 2");
        List<Stack<Character>> allCrates = buildOriginalSetup();
        for (List<Integer> command : commands) {
            followCommandsPart2(allCrates, command);
        }
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> cratesStack : allCrates) {
            sb.append(cratesStack.peek());
        }
        System.out.println(sb);
    }

}
