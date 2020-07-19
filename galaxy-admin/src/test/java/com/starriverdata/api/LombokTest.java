package com.starriverdata.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LombokTest {

    @Data
    @EqualsAndHashCode(exclude={"id", "shape"})
    public class EqualsAndHashCodeExample {
        private transient int transientVar = 10;
        private int id;
        private String name;
        private double score;
        private Square shape = new Square(5, 10);
        private String[] tags;


        @EqualsAndHashCode
        public  class Square {
            private final int width, height;

            public Square(int width, int height) {
                this.width = width;
                this.height = height;
            }
        }
    }

    @Test
    public void deepEqualsTest() {
        EqualsAndHashCodeExample e1 = new EqualsAndHashCodeExample();
        EqualsAndHashCodeExample e2 = new EqualsAndHashCodeExample();
        e1.setId(1);
        e2.setId(2);
        System.out.println(e1.equals(e2));

        List<EqualsAndHashCodeExample> list1 = new ArrayList<>();
        List<EqualsAndHashCodeExample> list2 = new ArrayList<>();
        list1.add(e1);
        list1.add(e2);

        list2.add(e2);
        list2.add(e1);
        System.out.println(Arrays.deepEquals(list1.toArray(), list2.toArray()));

    }

    @Test
    public void ip() throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(host);

        int p1 = 3;
        int p2 = 3;
        System.out.println( p1 == p2 ? 0 : (p1 > p2 ? 1 : -1));
    }
}
