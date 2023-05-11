package com.learn1.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author Feng Jie
 * @date 2023/5/9 15:20
 */
public class CustomRecursiveAction extends RecursiveAction {
    private String workload = "";
    private static final int THRESHOLD = 4;
    /**
     * Constructor for subclasses to call.
     */
    public CustomRecursiveAction(String workload) {
        this.workload = workload;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD){
            ForkJoinTask.invokeAll(createSubTask());
        }else{
            String result = workload.toUpperCase();
            System.out.println(Thread.currentThread().getName() + ":" + result);
        }

    }

    private List<CustomRecursiveAction> createSubTask(){
        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());

        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));

        return subtasks;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CustomRecursiveAction abcdefghijk = new CustomRecursiveAction("abcdefghijk");
        forkJoinPool.invoke(abcdefghijk);

    }

}
