package com.erely.zk;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {

    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        Collections.shuffle(al);
        System.out.println(al);
    }
}
