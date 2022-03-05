package com.app.index;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//DATA ACCESS LAYER
//Data Access Object class
@Repository
public interface IndexRepository extends JpaRepository<Index, Long> {
    
    //JPQL language: SELECT * FROM student WHERE email = ?
    @Query("SELECT i FROM Index i WHERE i.ticker = ?1") 
    Optional<Index> findIndexByTicker(String ticker);

}
