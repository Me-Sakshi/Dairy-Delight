package com.dairyproject.repositories;
import java.util.List;
import java.util.Optional;

import com.dairyproject.entities.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.ConsumerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerDetails, Integer> {

    @Query("select c from ConsumerDetails c where c.username = :username and c.password = :password")
    public ConsumerDetails findConsumerDetailsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("select c from ConsumerDetails c where c.emailId = :emailId and c.password = :password")
    public ConsumerDetails findConsumerDetailsByEmailAndPassword(@Param("emailId") String emailId, @Param("password") String password);

    @Query("select c from ConsumerDetails c where c.phoneNumber = :phoneNumber and c.password = :password")
    public ConsumerDetails findConsumerDetailsByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Query("select count(c) from ConsumerDetails c where c.emailId = :emailId")
    public int findConsumerDetailsByEmailId(@Param("emailId") String emailId);

    @Query("select count(c) from ConsumerDetails c where c.username = :username")
    public int findConsumerDetailsByUsername(@Param("username") String username);

    @Query("select count(c) from ConsumerDetails c where c.phoneNumber = :phoneNumber")
    public int findConsumerDetailsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select c from ConsumerDetails c where c.emailId = :emailId")
    public ConsumerDetails findConsumerDetailsByEmailIdOnly(@Param("emailId") String emailId);

    @Query("select c from ConsumerDetails c where c.username = :username")
    public ConsumerDetails findConsumerDetailsByUsernameOnly(@Param("username") String username);

    @Query("select c from ConsumerDetails c where c.phoneNumber = :phoneNumber")
    public ConsumerDetails findConsumerDetailsByPhoneNumberOnly(@Param("phoneNumber") String phoneNumber);

    public int deleteConsumerDetailsByConsumerId(Integer consumerId);

    public int deleteConsumerDetailsByEmailId(String emailId);

    @Query("select c from ConsumerDetails c")
    public List<ConsumerDetails> findAllConsumerDetails();

    @Query("select c from ConsumerDetails c where c.firstName like %:name%")
    public List<ConsumerDetails> findConsumerByName(@Param("name") String name);

    @Query("select c from ConsumerDetails c where c.address.pincode = :pincode")
    public List<ConsumerDetails> findConsumersByPincode(@Param("pincode") String pincode);

    @Query("select c from ConsumerDetails c where c.address.district = :district")
    public List<ConsumerDetails> findConsumersByDistrict(@Param("district") String district);

    @Query("select c from ConsumerDetails c where c.address.town = :town")
    public List<ConsumerDetails> findConsumersByTown(@Param("town") String town);

    @Query("select c from ConsumerDetails c where c.address.AID = :aid")
    public List<ConsumerDetails> findConsumerDetailsByAid(@Param("aid") Integer aid);

    @Query("select c from ConsumerDetails c where c.consumerId = :consumerId")
    public ConsumerDetails findConsumerDetailsByConsumerId(@Param("consumerId") Integer consumerId);

    Optional<ConsumerDetails> findByEmailId(String emailId);
}

//@Repository
//public interface ConsumerRepository extends CrudRepository<ConsumerDetails, Integer> {
//
//	@Query("select c from ConsumerDetails c where c.username = ?1 and c.password = ?2")
//	public ConsumerDetails findConsumerDetailsByUsernameAndPassword(String username, String password);
//
//	@Query("select c from ConsumerDetails c where c.emailId = ?1 and c.password = ?2")
//	public ConsumerDetails findConsumerDetailsByEmailAndPassword(String emailId, String password);
//
//	@Query("select c from ConsumerDetails c where c.phoneNumber = ?1 and c.password = ?2")
//	public ConsumerDetails findConsumerDetailsByPhoneNumberAndPassword(String phoneNumber, String password);
//
//	@Query("select count(c) from ConsumerDetails c where c.emailId = ?1")
//	public int findConsumerDetailsByEmailId(String emailId);
//
//	@Query("select count(c) from ConsumerDetails c where c.username = ?1")
//	public int findConsumerDetailsByUsername(String username);
//
//	@Query("select count(c) from ConsumerDetails c where c.phoneNumber = ?1")
//	public int findConsumerDetailsByPhoneNumber(String phoneNumber);
//
//	@Query("select c from ConsumerDetails c where c.emailId = ?1")
//	public ConsumerDetails findConsumerDetailsByEmailIdOnly(String emailId);
//
//	@Query("select c from ConsumerDetails c where c.username = ?1")
//	public ConsumerDetails findConsumerDetailsByUsernameOnly(String username);
//
//	@Query("select c from ConsumerDetails c where c.phoneNumber = ?1")
//	public ConsumerDetails findConsumerDetailsByPhoneNumberOnly(String phoneNumber);
//
//	public int deleteConsumerDetailsByConsumerId(Integer consumerid);
//
//	public int deleteConsumerDetailsByEmailId(String emailId);
//
//	@Query("select c from ConsumerDetails c")
//	public List<ConsumerDetails> findAllConsumerDetails();
//
//	@Query("select c from ConsumerDetails c where firstName like %?1%")
//	public List<ConsumerDetails> findConsumerByName(String name);
//
//	@Query("select c from ConsumerDetails c where c.address.pincode = ?1")
//	public List<ConsumerDetails> findConsumersByPincode(String pincode);
//
//	@Query("select c from ConsumerDetails c where c.address.district = ?1")
//	public List<ConsumerDetails> findConsumersByDistrict(String district);
//
//	@Query("select c from ConsumerDetails c where c.address.town = ?1")
//	public List<ConsumerDetails> findConsumersByTown(String town);
//
//	@Query("select c from ConsumerDetails c where c.address.AID = ?1")
//	public List<ConsumerDetails> findConsumerDetailsByAid(Integer aid);
//
//	@Query("select c from ConsumerDetails c where c.consumerId = ?1")
//	public ConsumerDetails findConsumerDetailsByConsumerId(Integer consumerId);
//
//}
