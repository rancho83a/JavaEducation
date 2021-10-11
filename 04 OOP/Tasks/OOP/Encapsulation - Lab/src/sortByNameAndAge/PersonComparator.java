package sortByNameAndAge;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        int sComp = o1.getFirstName().compareTo(o2.getFirstName());
        if (sComp != 0) {
            return sComp;
        } else {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    }
}
