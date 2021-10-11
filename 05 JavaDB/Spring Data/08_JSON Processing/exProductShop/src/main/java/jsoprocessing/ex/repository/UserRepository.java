package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u FROM User u " +
            "WHERE (SELECT count(p) FROM Product p WHERE p.seller.id=u.id AND p.buyer.id is NOT NULL ) > 0" +
            " ORDER BY u.lastName, u.firstName")
    List<User> findAllBySoldProductsWithOneBuyerOrderByLastNameThenFirstName();

    @Query("Select u FROM User u " +
            "WHERE (SELECT count(p) FROM Product p WHERE p.seller.id=u.id AND p.buyer is NOT NULL)    > 0" +
            " ORDER BY u.soldProducts.size desc , u.lastName")
    List<User>findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName();
}
