package ex.xmlprocessing.Repository;

import ex.xmlprocessing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
    select * from users as u
where (select count(*) from products as p where u.id = p.seller_id AND  p.buyer_id is not null )>0
ORDER BY u.last_name, u.first_name
     */

    @Query("Select u FROM User u " +
            "WHERE  (SELECT count (p) FROM  Product p WHERE p.seller.id = u.id AND p.buyer.id is not null)>0 " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findAllWith1SoldProductAtLeast();

    @Query("Select u FROM User u " +
            "WHERE (SELECT count(p) FROM Product p WHERE p.seller.id=u.id AND p.buyer is NOT NULL)    > 0" +
            " ORDER BY u.soldProducts.size desc , u.lastName")
    List<User>findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName();


}
