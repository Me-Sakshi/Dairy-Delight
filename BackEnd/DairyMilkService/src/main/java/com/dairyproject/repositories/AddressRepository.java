package com.dairyproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.dairyproject.entities.AddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDetails, Integer> {

    public AddressDetails findAddressDetailsByPincode(String pincode);

    public List<AddressDetails> findAddressDetailsByDistrict(String district);

    public AddressDetails findAddressDetailsByTown(String town);


    /**
     *  Paging -> Pagable Interface
     *  asc and desc
     *
     * @Transctional
     *      -> Isolation
     *      -> Propagation
     *   DB - ACID
     */



    // Using named parameters in the custom query
    //@Query("select a.AID from AddressDetails a where a.pincode = :pincode")
    //public Integer getAddressAidByPincode(@Param("pincode") String pincode);
}

//public interface AddressRepository extends CrudRepository<AddressDetails, Integer> {
//
//	public AddressDetails findAddressDetailsByPincode(String pincode);
//
//	public List<AddressDetails> findAddressDetailsByDistrict(String district);
//
//	public AddressDetails findAddressDetailsByTown(String town);
//
//	@Query("select a.AID from AddressDetails a where a.pincode = ?1")
//	public Integer getAddressAidByPincode(String pincode);
//
//}
