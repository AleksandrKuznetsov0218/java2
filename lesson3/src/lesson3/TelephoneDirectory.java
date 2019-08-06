package lesson3;

import java.util.*;

public class TelephoneDirectory {

    public HashMap<String, HashSet<String>> sub = new HashMap<>(100);

    public String surname;
    public String phoneNumber;

    public void telephoneDirectory() {
        add("Тургенев", "123456789");
        System.out.println(sub);
        add("Булгаков", "555456789");
        System.out.println(sub);
        add("Тургенев", "33333333");
        System.out.println(sub);
        get("Тургенев");
    }

    public void add(String surname, String phoneNumber) {
        if (sub.isEmpty() || !sub.containsKey(surname)) {
            HashSet<String> subscriber = new HashSet<>();
            subscriber.add(phoneNumber);
            sub.put(surname, subscriber);
        }
        if (sub.containsKey(surname)) {
            HashSet<String> subscriber = sub.get(surname);
            subscriber.add(phoneNumber);
        }

    }

    public void get(String surname) {
        HashSet<String> subscriber = sub.get(surname);
        System.out.println(surname + " " + subscriber);
    }

}