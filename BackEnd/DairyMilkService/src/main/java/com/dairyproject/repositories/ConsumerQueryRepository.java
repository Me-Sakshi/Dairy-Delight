package com.dairyproject.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.ConsumerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ConsumerQueryRepository extends JpaRepository<ConsumerQuery, Integer> {

    @Query("select q from ConsumerQuery q")
    public List<ConsumerQuery> findAllConsumerQueries();

    @Query("select q from ConsumerQuery q where q.dateTime like %:dateTime%")
    public List<ConsumerQuery> findConsumerQueriesByDateTime(@Param("dateTime") String dateTime);

    @Query("select q from ConsumerQuery q where q.consumerDetails.emailId like %:emailId%")
    public List<ConsumerQuery> findConsumerQueriesByConsumerEmailId(@Param("emailId") String emailId);
}

//@Repository
//public interface ConsumerQueryRepository extends CrudRepository<ConsumerQuery, Integer> {
//
//	@Query("select q from ConsumerQuery q")
//	public List<ConsumerQuery> findAllConsumerQueries();
//
//	@Query("select q from ConsumerQuery q where q.dateTime like %?1%")
//	public List<ConsumerQuery> findConsumerQueriesByDateTime(String dateTime);
//
//	@Query("select q from ConsumerQuery q where q.consumerDetails.emailId like %?1%")
//	public List<ConsumerQuery> findConsumerQueriesByConsumerEmailId(String emailId);
//
//}
