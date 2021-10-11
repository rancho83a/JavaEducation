package collectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddCollection addCollection=new AddCollection();
        AddRemoveCollection addRemoveCollection =new AddRemoveCollection();
        MyListImpl myList=new MyListImpl();

        Scanner scan = new Scanner(System.in);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder();

        for (String item : scan.nextLine().split("\\s+")) {
            sb1.append(addCollection.add(item)).append(" ");
            sb2.append(addRemoveCollection.add(item)).append(" ");
            sb3.append(myList.add(item)).append(" ");
        }

        int n = Integer.parseInt(scan.nextLine());
        while(n-->0){
            sb4.append(addRemoveCollection.remove()).append(" ");
            sb5.append(myList.remove()).append(" ");


        }

        System.out.println(sb1.toString().trim());
        System.out.println(sb2.toString().trim());
        System.out.println(sb3.toString().trim());
        System.out.println(sb4.toString().trim());
        System.out.println(sb5.toString().trim());



        



    }
}
