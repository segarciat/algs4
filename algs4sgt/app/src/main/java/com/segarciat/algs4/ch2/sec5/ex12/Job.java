package com.segarciat.algs4.ch2.sec5.ex12;

/**
 * <code>2.5.12)</code>
 * Represents a job that requires a certain amount of processing
 * time to be completed.
 * @author Sergio E. Garcia Tapia
 */
public record Job(String name, Long duration) {
    public Job {
        if (name == null)
            throw new NullPointerException("job must have a non-null name");
        if (duration <= 0)
            throw new IllegalArgumentException("job duration must be positive");
    }
}
