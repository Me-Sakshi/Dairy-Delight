package com.dairyproject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseDetails, Integer> {

    @Query("select p from PurchaseDetails p")
    public List<PurchaseDetails> findAllPurchaseDetails();

    @Query("select p from PurchaseDetails p where p.consumerDetails.emailId = :emailId")
    public List<PurchaseDetails> findAllPurchaseDetailsByConsumerEmailId(@Param("emailId") String emailId);

    @Query("select p from PurchaseDetails p where p.sellerDetails.emailId = :emailId")
    public List<PurchaseDetails> findAllPurchaseDetailsBySellerEmailId(@Param("emailId") String emailId);

    @Query("select p from PurchaseDetails p where p.purchaseId = :purchaseId")
    public PurchaseDetails findPurchaseDetailsByPurchaseId(@Param("purchaseId") Integer purchaseId);
}
