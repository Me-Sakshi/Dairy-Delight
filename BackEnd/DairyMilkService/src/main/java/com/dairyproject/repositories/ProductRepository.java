package com.dairyproject.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Integer> {

    @Query("select p from ProductDetails p")
    public Set<ProductDetails> findAllProductDetails();

    @Query("select p from ProductDetails p where p.name = :name")
    public ProductDetails findProductDetailsByName(@Param("name") String name);

    @Query("select p from ProductDetails p where p.PID = :pid")
    public ProductDetails findProductDetailByPid(@Param("pid") Integer PID);

    @Modifying
    @Query("delete from ProductDetails p where p.PID = :pid")
    public int deleteProductDetailsByPID(@Param("pid") Integer pid);
}

//@Repository
//public interface ProductRepository extends CrudRepository<ProductDetails, Integer> {
//
//	@Query("select p from ProductDetails p")
//	public Set<ProductDetails> findAllProductDetails();
//
//	@Query("select p from ProductDetails p where p.name = ?1")
//	public ProductDetails findProductDetailsByName(String name);
//
//	@Query("select p from ProductDetails p where p.PID = ?1")
//	public ProductDetails findProductDetailByPid(Integer PID);
//
//	@Modifying
//	@Query("delete from ProductDetails where PID = ?1")
//	public int deleteProductDetailsByPID(Integer pid);
//
//}
