package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Paniershop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public interface PaniershopRepository extends JpaRepository<Paniershop,Long> {

    List<Paniershop> findByAuteurEquals(String auteur);
    List<Paniershop> findByImageEquals(Long image);
    Long countByAuteur(String auteur);


}
