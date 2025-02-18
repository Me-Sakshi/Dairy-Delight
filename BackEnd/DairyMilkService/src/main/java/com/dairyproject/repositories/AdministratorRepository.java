package com.dairyproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dairyproject.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select a from Administrator a where a.adminId = 1")
    public Administrator findAdminDetails();

    @Query("select a from Administrator a where a.emailId = :emailId and a.password = :password")
    public Administrator findAdminDetailsByEmailIdAndPassword(
        @Param("emailId") String emailId, 
        @Param("password") String password
    );

    Optional<Administrator> findByEmailId(String emailId);
}

//public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {
//
//	@Query("select a from Administrator a where a.adminId = 1")
//	public Administrator findAdminDetails();
//
//	@Query("select a from Administrator a where a.emailId = ?1 and a.password = ?2")
//	public Administrator findAdminDetailsByEmailIdAndPassword(String emailId, String password);
//}
