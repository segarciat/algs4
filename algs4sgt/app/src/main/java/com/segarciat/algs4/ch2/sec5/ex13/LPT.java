package com.segarciat.algs4.ch2.sec5.ex13;

import com.segarciat.algs4.ch2.sec5.ex12.Job;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * <strong>2.5.13)</strong>
 * Reads a list of jobs and their processing times from standard
 * input, and distributes them among <code>m</code> processors
 * (the single command-line argument). Display a schedule that
 * approximately minimizes the time when the last job completes.
 * @author Sergio E. Garcia Tapia
 */
public class LPT {
    private LPT() {}
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);

        // Read and save jobs in descending order of processing time
        MaxPQ<Job> jobs = new MaxPQ<>(Comparator.comparing(Job::duration));
        while (!StdIn.isEmpty()) {
            String[] tokens = StdIn.readLine().split("\\s+");
            String jobName = tokens[0];
            Long processingTime = Long.parseLong(tokens[1]);
            jobs.insert(new Job(jobName, processingTime));
        }

        // Create at most m processors for the jobs
        m = Math.min(jobs.size(), m);
        MinPQ<Processor> processors = new MinPQ<>(m, Comparator.comparingLong(Processor::totalProcessingTime));
        while (processors.size() != m)
            processors.insert(new Processor("" + processors.size()));

        // Assign jobs to processors and print schedule
        System.out.println("Job Duration Start Processor");
        while (!jobs.isEmpty()) {
            Job job = jobs.delMax();
            Processor processor = processors.delMin();
            StdOut.printf("%s %d %d %s%n",
                    job.name(), job.duration(), processor.totalProcessingTime(), processor.id());
            processor.assign(job);
            processors.insert(processor);
        }
    }
}
