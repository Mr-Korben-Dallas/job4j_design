package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 1;
        short b = 1;
        int c = 1;
        long d = 1;
        char e = 'A';
        float f = 1;
        double g = 1;
        boolean h = false;

        LOG.debug("a : {}, b : {}, c : {}, d : {}, e : {}, f : {}, g : {}, h : {}", a, b, c, d, f, g, e, h);
    }
}