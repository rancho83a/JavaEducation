public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.setAge(age);
        this.name=name;
    }

    public void setAge(int age) {
        this.validAge(age);
        this.age = age;
    }

    private void validAge(int age){
        if(age<0){
            throw new IllegalStateException(" vfd");
        }
    }

    public int getAge() {
        return this.age;
    }
}
