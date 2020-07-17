

import java.io.File;

public class Task2 {


    File task1(Integer someGlobalConstant) {
        return new File("bleh");
    }

    Pair<Integer, File> task2(Integer someGlobalConstant, File task1Result) {
        return Pair.of(42, new File("blah"));
    }

    File task3(Integer someGlobalConstant, File task1result, Integer task2result1, File task2result2) {
        if (someGlobalConstant > 8)
            throw new RuntimeException("Kablam");
        else
            return task2result2;
    }

    void run() {

        int someGlobalConstant = 42;

        try {
            File task1result = task1(someGlobalConstant);

            // Update progress

            Pair<Integer, File> task2result = task2(someGlobalConstant, task1result);

            // Update progress

            File task3result = task3(someGlobalConstant, task1result, task2result.left, task2result.right);

            // Signal completion
        } catch (Exception e) {

            //  Signal failure
        } finally {
            // cleanup
        }
    }

}



