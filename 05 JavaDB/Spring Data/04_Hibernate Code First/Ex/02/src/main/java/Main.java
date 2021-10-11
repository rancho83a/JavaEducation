import entities.Product;
import entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

        em.getTransaction().begin();

//        Sale sale = new Sale();
//        sale.setLocalDateTime(LocalDateTime.now());
//
//        Product product=new Product();
//        product.setName("testProduct");
//        product.setPrice(BigDecimal.TEN);
//        product.setQuantity(10.0);
//        product.getSales().add(sale);
//        sale.setProduct(product);
//
//        em.persist(product);
//        Product found = em.find(Product.class, 1L);
//
//        em.remove(found);


        em.getTransaction().commit();
    }
}
