package solution;

import eval.HeuristicFacotry;
import model.Plain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 11:57 PM
 */
public class Main {
    private static List<Integer> getInput(String filename) throws FileNotFoundException {
        String path = new File(filename).getAbsolutePath();
        FileReader reader = new FileReader(path);
        Scanner scanner = new Scanner(reader);

        List<Integer> ret = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            ret.add(scanner.nextInt());
        }

        return ret;
    }

    public static void main(String[] args) {
        String strInput = "";
        String strHeuristic = "";
        int size = 0;
        if (args.length > 1) {
            size = Integer.parseInt(args[0]);
            strHeuristic = args[1];
            strInput = args[2];
        }


        List<Integer> inputList = null;
        if (!strInput.isEmpty()) {
            try {
                inputList = getInput(strInput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        if (inputList != null && inputList.size() > 0) {
            if (Math.pow(size, 2) != inputList.size()) {
                System.err.println("Input size = " + size + " which is conflicted with amount of the data in the " + strInput);
                return;
            }
            else if (!Plain.checkLegal(inputList)) {
                System.err.println("The input array cannot be solved");
                return;
            }
        }

        HeuristicFacotry.typeHeuristic hType = HeuristicFacotry.typeHeuristic.Distance;
        if (strHeuristic.toLowerCase().contains("misplaced")) {
            hType = HeuristicFacotry.typeHeuristic.Misplaced;
        }

        Puzzle puzzle = new Puzzle(size, hType, inputList);
        puzzle.Run();
    }
}
