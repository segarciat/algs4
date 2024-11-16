package com.segarciat.algs4.ch2.sec5.ex12;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

import java.util.Comparator;

/**
 * <code>2.5.12)</code>
 * Given a list of jobs and their processing times, prints
 * a schedule that minimizes average completion time.
 * @author Sergio E. Garcia Tapia
 */
public class SPT {
    private SPT() {}
    public static void main(String[] args) {
        // Reads jobs from standard input
        MinPQ<Job> jobs = new MinPQ<>(Comparator.comparing(Job::duration));
        while (!StdIn.isEmpty()) {
            String[] tokens = StdIn.readLine().split("\\s+");
            String jobName = tokens[0];
            Long processingTime = Long.parseLong(tokens[1]);
            jobs.insert(new Job(jobName, processingTime));
        }

        // Display on standard output in ascending order of processing time
        System.out.println("Job Duration Start");
        long startTime = 0;
        while (!jobs.isEmpty()) {
            Job job = jobs.delMin();
            System.out.printf("%s %d %d%n", job.name(), job.duration(), startTime);
            startTime += job.duration();
        }
    }
}
