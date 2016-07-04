package com.mycompany.myapp.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Commentaire;
import com.mycompany.myapp.domain.Paniershop;
import com.mycompany.myapp.repository.CommentaireRepository;
import com.mycompany.myapp.repository.PaniershopRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class CommentaireResource {
    @Inject
    private CommentaireRepository commentaireRepository;

    @RequestMapping(value = "/commentaire",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Commentaire> create(@RequestBody Commentaire commentaire) throws URISyntaxException {
        if (commentaire.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new commentaire cannot already have an ID").body(null);
        }
        String currentLogin = SecurityUtils.getCurrentLogin();
        commentaire.setLogin(currentLogin);
        commentaire.setDate(new DateTime());
        Commentaire result = commentaireRepository.save(commentaire);

        return ResponseEntity.created(new URI("/api/commentaire/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("commentaire", result.getId().toString()))
            .body(result);
    }

    /**
     * GET
     */
    @RequestMapping(value = "/commentaire/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Commentaire> getOne(@PathVariable Long id) {
        return Optional.ofNullable(commentaireRepository.findOne(id))
            .map(commentaire -> new ResponseEntity<>(
                commentaire,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * GET
     */
    @RequestMapping(value = "/commentaire",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Commentaire> getAll() {
        List<Commentaire> commentaire = new ArrayList<Commentaire>();
        String login = SecurityUtils.getCurrentLogin();
        commentaire = commentaireRepository.findByLoginEquals(login);
        return commentaire;
    }
    /**
     * GET  /ordinateurs -> get all the ordinateurs.
     */
    @RequestMapping(value = "/commentaire/findAll",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Commentaire> getAllOrdinateurs() {
        return commentaireRepository.findAll();
    }
    /**
     * GET
     */
    @RequestMapping(value = "/comforimage/{image}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Commentaire> getComForImage(@PathVariable Long image) {
        List<Commentaire> commentaire = new ArrayList<Commentaire>();
        commentaire = commentaireRepository.findByImageEquals(image);
        return commentaire;
    }
    /**
     * GET
     */
    @RequestMapping(value = "/nbrescom/{image}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Long getNbreCom(@PathVariable Long image) {
        List<Commentaire> commentaire = new ArrayList<Commentaire>();
        Long nbre = null;
        commentaire = commentaireRepository.findByImageEquals(image);
        for(int i=0;i<commentaire.size();i++){
            nbre = commentaireRepository.countByImage(commentaire.get(i).getImage());
        }
        return nbre;
    }
    /**
     * DELETE
     */
    @RequestMapping(value = "/delete/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        commentaireRepository.delete(id);
    }


}
