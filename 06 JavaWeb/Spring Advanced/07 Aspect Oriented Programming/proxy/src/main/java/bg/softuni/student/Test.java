package bg.softuni.student;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        StudentServiceIfc studentServiceIfc = pretendThatIamSpringAndInjectStudentService();

        System.out.println(studentServiceIfc.getAllStudents());
        System.out.println(studentServiceIfc.getAllStudents());
    }

    private static StudentServiceIfc pretendThatIamSpringAndInjectStudentService() {
         StudentServiceIfc toProxy = new StudentServiceImpl();
         return  (StudentServiceIfc) Proxy.newProxyInstance(
                 Test.class.getClassLoader(),
                 new Class[]{StudentServiceIfc.class},
                 new CacheableInvocationHandler(toProxy)
         );

    }
}
