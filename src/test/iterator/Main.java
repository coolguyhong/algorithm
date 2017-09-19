package test.iterator;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        MagicianList magicianList = new MagicianList();

        magicianList.add("이은결");
        magicianList.add("Kevin Parker");
        magicianList.add("David Blaine");

        Iterator<String> iterator = magicianList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }
}
