package jsoprocessing.ex.repository;

import jsoprocessing.ex.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    //@Query(value = "Select * FROM suppliers Where is_importer=false",nativeQuery = true)
    @Query("Select s FROM Supplier s Where s.importer = false ")
    List<Supplier> findAllByIsImporterEqualsNo();
}
