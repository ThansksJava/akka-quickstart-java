package com.learn1.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Feng Jie
 * @date 2023/5/9 15:20
 */
public class CustomRecursiveTask extends RecursiveTask<String> {
    private String workload = "";
    private static final int THRESHOLD = 4;
    /**
     * Constructor for subclasses to call.
     */
    public CustomRecursiveTask(String workload) {
        this.workload = workload;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected String compute() {
        if (workload.length() > THRESHOLD){
            ForkJoinTask.invokeAll(createSubTask());
        }
        return workload.toUpperCase();

    }

    private List<CustomRecursiveTask> createSubTask(){
        List<CustomRecursiveTask> subtasks = new ArrayList<>();

        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());

        subtasks.add(new CustomRecursiveTask(partOne));
        subtasks.add(new CustomRecursiveTask(partTwo));

        return subtasks;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CustomRecursiveTask abcdefghijk = new CustomRecursiveTask("abcdefghijk");
        String invoke = forkJoinPool.invoke(abcdefghijk);
        System.out.println(invoke);

    }

}
