package test.adapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Main {
    public static void goodMethod(Enumeration<String> enu) {
        while (enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("이은결");
        list.add("Kevin parker");
        list.add("David Blaine");

        Enumeration<String> ite = new IteratorToEnumeration(list.iterator());
        goodMethod(ite);
    }
}
