package basic;


import basic.entity.City;
import basic.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("softuni");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        City city = em.find(City.class, 2);
        em.remove(city);

//        var sofia = new City();
//        sofia.setName("Burgas");
//
//        var user = new User();
//        user.setUsername("bu");
//        user.setPassword("pbbbs");
//        user.setCity(sofia);
//
//        em.persist(user);

        em.getTransaction().commit();


//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        EntityManager session = sessionFactory.openSession();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//
//        CriteriaQuery<User> cr = cb.createQuery(User.class);
//        Root<User> root = cr.from(User.class);
//
//        cr.select(root).where(cb.gt(root.get("id"), 2));
//
//        TypedQuery<User> query = session.createQuery(cr);
//        query.getResultList().forEach(u-> System.out.println(u));


        //Session session = sessionFactory.openSession();

        //TypedQuery<User> query = session.createQuery("select u FROM  User u  WHERE u.username= :un", User.class);
        //query.setParameter("un", "u11");

//        TypedQuery<User> query = session.createQuery("select u FROM  User u  WHERE u.id> :id", User.class);
//        query.setParameter("id",1);
//        query.getResultList().forEach(System.out::println);

//        TypedQuery<User> query = session.createQuery("FROM  User u  WHERE u.city.name =  :city_name", User.class);
//        query.setParameter("city_name","Varna");
//       query.getResultList().forEach(System.out::println);


//        Transaction transaction = session.beginTransaction();
//
//        var query = session.createQuery("FROM City c WHERE c.name = :city_name", City.class);
//        query.setParameter("city_name", "Sofia");
//        City sofiaCity = query.getSingleResult();
//
//        User manol=new User();
//        manol.setUsername("manol");
//        manol.setPassword("ppp");
//        manol.setCity(sofiaCity);
//
//        session.save(manol);
//        transaction.commit();


    }
}
