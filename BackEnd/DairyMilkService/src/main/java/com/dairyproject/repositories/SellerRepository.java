package com.dairyproject.repositories;

import java.util.List;
import java.util.Optional;

import com.dairyproject.entities.Administrator;
import org.springframework.data.jpa.repository.Query;
import com.dairyproject.entities.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerDetails, Integer> {

    @Query("select s from SellerDetails s where s.username = :username and s.password = :password")
    public SellerDetails findSellerDetailsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("select s from SellerDetails s where s.emailId = :emailId and s.password = :password")
    public SellerDetails findSellerDetailsByEmailAndPassword(@Param("emailId") String emailId, @Param("password") String password);

    @Query("select s from SellerDetails s where s.phoneNumber = :phoneNumber and s.password = :password")
    public SellerDetails findSellerDetailsByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Query("select count(s) from SellerDetails s where s.emailId = :emailId")
    public int findSellerDetailsByEmailId(@Param("emailId") String emailId);

    @Query("select count(s) from SellerDetails s where s.username = :username")
    public int findSellerDetailsByUsername(@Param("username") String username);

    @Query("select count(s) from SellerDetails s where s.phoneNumber = :phoneNumber")
    public int findSellerDetailsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select s from SellerDetails s where s.emailId like %:emailId%")
    public SellerDetails findSellerDetailsByEmailIdOnly(@Param("emailId") String emailId);

    @Query("select s from SellerDetails s where s.username = :username")
    public SellerDetails findSellerDetailsByUsernameOnly(@Param("username") String username);

    @Query("select s from SellerDetails s where s.phoneNumber = :phoneNumber")
    public SellerDetails findSellerDetailsByPhoneNumberOnly(@Param("phoneNumber") String phoneNumber);

    public int deleteSellerDetailsBySellerId(Integer sellerId);

    public int deleteSellerDetailsByEmailId(String emailId);

    @Query("select s from SellerDetails s")
    public List<SellerDetails> findAllSellerDetails();

    @Query("select s from SellerDetails s where s.firstName like %:name%")
    public List<SellerDetails> findSellerByName(@Param("name") String name);

    @Query("select s from SellerDetails s where s.address.pincode = :pincode")
    public List<SellerDetails> findSellersByPincode(@Param("pincode") String pincode);

    @Query("select s from SellerDetails s where s.address.district = :district")
    public List<SellerDetails> findSellersByDistrict(@Param("district") String district);

    @Query("select s from SellerDetails s where s.address.town = :town")
    public List<SellerDetails> findSellersByTown(@Param("town") String town);

    @Query("select s from SellerDetails s where s.sellerId = :sellerId")
    public SellerDetails findSellerDetailsBySellerId(@Param("sellerId") Integer sellerId);

    Optional<SellerDetails> findByEmailId(String emailId);
}

//public interface SellerRepository extends CrudRepository<SellerDetails, Integer> {
//
//	@Query("select s from SellerDetails s where s.username = ?1 and s.password = ?2")
//	public SellerDetails findSellerDetailsByUsernameAndPassword(String username, String password);
//
//	@Query("select s from SellerDetails s where s.emailId = ?1 and s.password = ?2")
//	public SellerDetails findSellerDetailsByEmailAndPassword(String emailId, String password);
//
//	@Query("select s from SellerDetails s where s.phoneNumber = ?1 and s.password = ?2")
//	public SellerDetails findSellerDetailsByPhoneNumberAndPassword(String phoneNumber, String password);
//
//	@Query("select count(s) from SellerDetails s where s.emailId = ?1")
//	public int findSellerDetailsByEmailId(String emailId);
//
//	@Query("select count(s) from SellerDetails s where s.username = ?1")
//	public int findSellerDetailsByUsername(String username);
//
//	@Query("select count(s) from SellerDetails s where s.phoneNumber = ?1")
//	public int findSellerDetailsByPhoneNumber(String phoneNumber);
//
//	@Query("select s from SellerDetails s where s.emailId like %?1%")
//	public SellerDetails findSellerDetailsByEmailIdOnly(String emailId);
//
//	@Query("select s from SellerDetails s where s.username = ?1")
//	public SellerDetails findSellerDetailsByUsernameOnly(String username);
//
//	@Query("select s from SellerDetails s where s.phoneNumber = ?1")
//	public SellerDetails findSellerDetailsByPhoneNumberOnly(String phoneNumber);
//
//	public int deleteSellerDetailsBySellerId(Integer sellerId);
//
//	public int deleteSellerDetailsByEmailId(String emailId);
//
//	@Query("select s from SellerDetails s")
//	public List<SellerDetails> findAllSellerDetails();
//
//	@Query("select s from SellerDetails s where firstName like %?1%")
//	public List<SellerDetails> findSellerByName(String name);
//
//	@Query("select s from SellerDetails s where s.address.pincode = ?1")
//	public List<SellerDetails> findSellersByPincode(String pincode);
//
//	@Query("select s from SellerDetails s where s.address.district = ?1")
//	public List<SellerDetails> findSellersByDistrict(String district);
//
//	@Query("select s from SellerDetails s where s.address.town = ?1")
//	public List<SellerDetails> findSellersByTown(String town);
//
//	@Query("select s from SellerDetails s where s.sellerId = ?1")
//	public SellerDetails findSellerDetailsBySellerId(Integer sellerId);
//
//}
