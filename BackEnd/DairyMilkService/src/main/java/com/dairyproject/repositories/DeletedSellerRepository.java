package com.dairyproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.DeletedSellerRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DeletedSellerRepository extends JpaRepository<DeletedSellerRecords, Integer> {

    @Query("select s from DeletedSellerRecords s")
    public List<DeletedSellerRecords> findAllDeletedSellerRecords();

    @Query("select s from DeletedSellerRecords s where s.firstName like %:firstName%")
    public List<DeletedSellerRecords> findDeletedSellerRecordsByFirstName(@Param("firstName") String firstName);

    @Query("select s from DeletedSellerRecords s where s.emailId like %:emailId%")
    public DeletedSellerRecords findDeletedSellerRecordsByEmailId(@Param("emailId") String emailId);
}

//@Repository
//public interface DeletedSellerRepository extends CrudRepository<DeletedSellerRecords, Integer> {
//
//	@Query("select s from DeletedSellerRecords s")
//	public List<DeletedSellerRecords> findAllDeletedSellerRecords();
//
//	@Query("select s from DeletedSellerRecords s where s.firstName like %?1%")
//	public List<DeletedSellerRecords> findDeletedSellerRecordsByFirstName(String firstName);
//
//	@Query("select s from DeletedSellerRecords s where s.emailId like %?1%")
//	public DeletedSellerRecords findDeletedSellerRecordsByEmailId(String emailId);
//
//}
