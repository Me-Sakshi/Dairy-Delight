package com.dairyproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.dairyproject.entities.FeedBackDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FeedBackRepository extends JpaRepository<FeedBackDetails, Integer> {

    @Query("select f from FeedBackDetails f")
    public List<FeedBackDetails> findAllFeedBackDetails();

    @Query("select f from FeedBackDetails f where f.name like %:name%")
    public List<FeedBackDetails> findFeedBackDetailsByName(@Param("name") String name);

    @Query("select f from FeedBackDetails f where f.subject like %:subject%")
    public List<FeedBackDetails> findFeedBackDetailsBySubject(@Param("subject") String subject);

    @Query("select f from FeedBackDetails f where f.FID = :fid")
    public FeedBackDetails findFeedBackDetailsById(@Param("fid") Integer fid);
}

//public interface FeedBackRepository extends CrudRepository<FeedBackDetails, Integer> {
//
//	@Query("select f from FeedBackDetails f")
//	public List<FeedBackDetails> findAllFeedBackDetails();
//
//	@Query("select f from FeedBackDetails f where f.name like %?1%")
//	public List<FeedBackDetails> findFeedBackDetailsByName(String name);
//
//	@Query("select f from FeedBackDetails f where f.subject like %?1%")
//	public List<FeedBackDetails> findFeedBackDetailsBySubject(String subject);
//
//	@Query("select f from FeedBackDetails f where f.FID = ?1")
//	public FeedBackDetails findFeedBackDetailsById(Integer fid);
//
//}
