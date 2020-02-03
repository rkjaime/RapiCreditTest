package com.rapiCredit.test.repositories;

import java.util.List;
import java.util.UUID;

import com.rapiCredit.test.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
    @Query("select p from Client p where p.age > 18")
    List<Client> findClientByAge();

}

