package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.instagraphlite.models.entity.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);
    User findFirstByUsername(String username);

    //@Query("SELECT u From User u ORDER BY size(u.posts) DESC, u.id  ")
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.posts p ORDER BY size(p) DESC, u.id")
    List<User> findAllWithPostsOrderedByCountPosts();
}
