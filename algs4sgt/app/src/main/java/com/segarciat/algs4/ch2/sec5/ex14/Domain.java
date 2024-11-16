package com.segarciat.algs4.ch2.sec5.ex14;


import java.util.Objects;

/**
 * <strong>2.5.14)</strong>
 * Represents a domain name.
 * @author Sergio E. Garcia Tapia
 */
public final class Domain implements Comparable<Domain> {
    private final String domain;
    private final String reverseDomain;
    private final String[] reverseSubdomains;

    public Domain(String domain) {
        if (domain == null)
            throw new NullPointerException("domain cannot be null");
        this.domain = domain;

        // Save domain in reverse
        reverseSubdomains = domain.split("\\.");
        for (int start = 0, end = reverseSubdomains.length - 1; start < end; start++, end--) {
            String temp = reverseSubdomains[start];
            reverseSubdomains[start] = reverseSubdomains[end];
            reverseSubdomains[end] = temp;
        }

        reverseDomain = String.join(".", reverseSubdomains);
    }

    /**
     * @return The domain string for this {@link Domain} object.
     */
    public String domain() {
        return domain;
    }

    /**
     * Returns the reverse subdomain according to the domain in this object.
     * For example if the domain is <code>cs.princeton.edu</code>, then the reverse subdomain
     * is <code>edu.princeton.cs</code>.
     * @return The reverse subdomain.
     */
    public String reverseDomain() {
        return reverseDomain;
    }

    /**
     * Imposes order on {@link Domain} objects by their reverse subdomain.
     */
    @Override
    public int compareTo(Domain that) {
        int shortest = Math.min(this.reverseSubdomains.length, that.reverseSubdomains.length);
        for (int i = 0; i < shortest; i++) {
            int cmp = this.reverseSubdomains[i].compareTo(that.reverseSubdomains[i]);
            if (cmp != 0)
                return cmp;
        }
        // Part of same domain
        return Integer.compare(this.reverseSubdomains.length, that.reverseSubdomains.length);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain1 = (Domain) o;
        return Objects.equals(domain, domain1.domain);
    }
}
