package collectionHierarchy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        Scanner scan = new Scanner(System.in);

        String[] tokens = scan.nextLine().split("\\s+");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        for (String item : tokens) {
            int index1 = addCollection.add(item);
            sb1.append(index1).append(" ");
            int index2 = addRemoveCollection.add(item);
            sb2.append(index2).append(" ");
            int index3 = myList.add(item);
            sb3.append(index3).append(" ");
        }

        int amountOperation = Integer.parseInt(scan.nextLine());

        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder();

        while (amountOperation-- > 0) {
            String removeItem1 = addRemoveCollection.remove();
            sb4.append(removeItem1).append(" ");

            String removeItem2 = myList.remove();
            sb5.append(removeItem2).append(" ");
        }
        System.out.println(sb1.toString().trim());
        System.out.println(sb2.toString().trim());
        System.out.println(sb3.toString().trim());
        System.out.println(sb4.toString().trim());
        System.out.println(sb5.toString().trim());
    }
}
