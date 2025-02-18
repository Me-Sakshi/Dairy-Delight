package com.dairyproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.DeletedConsumerRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DeletedConsumerRepository extends JpaRepository<DeletedConsumerRecords, Integer> {

    @Query("select c from DeletedConsumerRecords c")
    public List<DeletedConsumerRecords> findAllDeletedConsumerRecords();

    @Query("select c from DeletedConsumerRecords c where c.firstName like %:firstName%")
    public List<DeletedConsumerRecords> findDeletedConsumerRecordsByFirstName(@Param("firstName") String firstName);

    @Query("select c from DeletedConsumerRecords c where c.emailId like %:emailId%")
    public DeletedConsumerRecords findDeletedConsumerByEmailId(@Param("emailId") String emailId);
}

//
//@Repository
//public interface DeletedConsumerRepository extends CrudRepository<DeletedConsumerRecords, Integer> {
//
//	@Query("select c from DeletedConsumerRecords c")
//	public List<DeletedConsumerRecords> findAllDeletedConsumerRecords();
//
//	@Query("select c from DeletedConsumerRecords c where c.firstName like %?1%")
//	public List<DeletedConsumerRecords> findDeletedConsumerRecordsByFirstName(String firstName);
//
//	@Query("select c from DeletedConsumerRecords c where c.emailId like %?1%")
//	public DeletedConsumerRecords findDeletedConsumerByEmailId(String emailId);
//
//}
