

import java.io.File;

public class Task {


    Result<File> task1(Integer someGlobalConstant) {
        return Result.success(new File("bleh"));
    }

    Result<Pair<Integer, File>> task2(Integer someGlobalConstant, File task1Result) {
        return Result.success(Pair.of(42, new File("blah")));
    }

    Result<File> task3(Integer someGlobalConstant, File task1result, Integer task2result1, File task2result2) {
        if (someGlobalConstant > 8)
            throw new RuntimeException("Kablam");
        else
            return Result.success(task2result2);
    }

    void run() {

        int someGlobalConstant = 42;

        task1(someGlobalConstant)
                .andThen(task1result ->

                        // monitor.setProgress

                        task2(someGlobalConstant, task1result)
                                .andThen(task2result ->

                                        // monitor.setProgress

                                        task3(someGlobalConstant, task1result, task2result.left, task2result.right)
                                                .andThen(task3result -> {

                                                    // monitor.setSuccess
                                                    // use intermediate results

                                                    return Result.success(Pair.of(task2result, task3result)); // because Java has no Unit type
                                                }))).accept(new Result.Visitor<Pair<Pair<Integer, File>, File>, Void>() {

            @Override
            public Void visitSuccess(Pair<Pair<Integer, File>, File> value) {
                // use final result
                // monitor.setSuccess

                return null; // because Java has no Unit type
            }

            @Override
            public Void visitFailure(Throwable cause) {
                // handle failure

                return null; // because Java has no Unit type
            }
        });
    }

}



