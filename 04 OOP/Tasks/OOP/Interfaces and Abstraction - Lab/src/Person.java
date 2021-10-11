public class Person implements Printable,Comparable<String>{

    private String name;
    public Person(String name){
        this.name=name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }

    @Override
    public String getAsString() {
        return this.name;
    }

    @Override
    public int compareTo(String o) {
        return this.name.compareTo(o);
    }
}
