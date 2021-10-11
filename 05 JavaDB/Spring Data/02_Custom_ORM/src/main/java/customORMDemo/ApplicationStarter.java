package customORMDemo;

import OrmFramework.core.EntityManager;
import OrmFramework.core.EntityManagerFactory;
import customORMDemo.entity.Address;
import customORMDemo.entity.Departments;
import customORMDemo.entity.Employee;
import customORMDemo.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * 1)  Class for registration settings for DB connection - DB type,DB name,  user, password
         Return Object which can control DB on abstract level
 * 2)  Before the Return Object -> loop over all classes and find those classes which have annotation Entity (it will
 *      be our Annotation)
 * 3) If DB is not exist, create DB with previously describe @Id and @Column(name, type)
 * 4) Class from point 1 (EntityManger) must have next operation:
 *    -- find(id, Class)
 *    -- insert new object or update exist Object => persist(Object)
 *    -- delete object by Id => delete(id, Class)

 */
public class ApplicationStarter {
    public static void main(String[] args) throws SQLException, URISyntaxException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        EntityManager entityManager = EntityManagerFactory.create(
                "mysql",
                "localhost",
                3306,
                "root",
                "mypass",
                "orm_test",
                ApplicationStarter.class
                );
        //User user = new User("Pesho",17);
//        User pesho = entityManager.findEntityById(1,User.class);
//        pesho.setAge(22);
//
//        entityManager.persist(pesho);

//        User up = entityManager.findEntityById(1,User.class);
//        entityManager.delete(up);

//        Employee entityById = entityManager.findEntityById(25, Employee.class);
//        Address a1 = entityManager.findEntityById(1, Address.class);
//        Address a2 = entityManager.findEntityById(2, Address.class);

        entityManager.alterTable(User.class);

        System.out.println();
    }
}
