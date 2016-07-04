package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Ordinateur;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ordinateur entity.
 */
public interface OrdinateurRepository extends JpaRepository<Ordinateur,Long> {

    public Ordinateur findById(Long id);
    Ordinateur findByNameEquals(String name);

}
