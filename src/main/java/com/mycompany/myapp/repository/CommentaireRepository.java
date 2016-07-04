package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Commentaire;
import com.mycompany.myapp.domain.Paniershop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    List<Commentaire> findByLoginEquals(String login);
    List<Commentaire> findByImageEquals(Long image);
    Long countByImage(Long image);

}
