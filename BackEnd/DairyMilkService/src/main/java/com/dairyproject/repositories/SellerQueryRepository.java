package com.dairyproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.SellerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface SellerQueryRepository extends JpaRepository<SellerQuery, Integer> {

    @Query("select q from SellerQuery q")
    public List<SellerQuery> findAllSellerQueries();

    @Query("select q from SellerQuery q where q.dateTime like %:dateTime%")
    public List<SellerQuery> findSellerQueriesByDateTime(@Param("dateTime") String dateTime);

    @Query("select q from SellerQuery q where q.sellerDetails.emailId like %:emailId%")
    public List<SellerQuery> findSellerQueriesBySellerEmailId(@Param("emailId") String emailId);
}

//@Repository
//public interface SellerQueryRepository extends CrudRepository<SellerQuery, Integer> {
//
//	@Query("select q from SellerQuery q")
//	public List<SellerQuery> findAllSellerQueries();
//
//	@Query("select q from SellerQuery q where q.dateTime like %?1%")
//	public List<SellerQuery> findSellerQueriesByDateTime(String dateTime);
//
//	@Query("select q from SellerQuery q where q.sellerDetails.emailId like %?1%")
//	public List<SellerQuery> findSellerQueriesBySellerEmailId(String emailId);
//
//}
