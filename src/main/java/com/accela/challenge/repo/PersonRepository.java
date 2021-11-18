package com.accela.challenge.repo;

import com.accela.challenge.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends JpaRepository<Person, Long> {
  @Query("SELECT p FROM Person p WHERE p.lastName = :lastName")
  List<Person> findPersonsByLastName(@Param("lastName")String lastName);

  @Query(nativeQuery = true, value="SELECT COUNT(1) FROM Person")
  int findPersonsCount();

  @Modifying(flushAutomatically = true)
  @Query(nativeQuery = true, value = "INSERT INTO Person_Addresses (FK_ADDRESS, FK_PERSON) VALUES(:addressId, :personId)")
  @Transactional
  int addAddressToPerson(@Param("addressId") Long addressId, @Param("personId") Long personId );
}
