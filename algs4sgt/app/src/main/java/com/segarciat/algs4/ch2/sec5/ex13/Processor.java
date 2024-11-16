package com.segarciat.algs4.ch2.sec5.ex13;

import com.segarciat.algs4.ch2.sec5.ex12.Job;
import edu.princeton.cs.algs4.Queue;

/**
 * <strong>2.5.13)</strong>
 * Represents a processor to which jobs can be assigned.
 * @author Sergio E. Garcia Tapia
 */
public final class Processor {
    private final String id;
    private final Queue<Job> jobs = new Queue<>();
    private long totalProcessingTime = 0;

    public Processor(String id) {
        if (id == null)
            throw new NullPointerException("processor must have a non-null id");
        this.id = id;
    }

    public void assign(Job job) {
        if (job == null)
            throw new NullPointerException("cannot assign a null job");
        jobs.enqueue(job);
        totalProcessingTime += job.duration();
    }

    public long totalProcessingTime() {
        return totalProcessingTime;
    }

    public String id() {
        return id;
    }
}
