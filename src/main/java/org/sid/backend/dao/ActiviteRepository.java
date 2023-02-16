package org.sid.backend.dao;
import org.sid.backend.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {




    //find by name activite
    Activite findByName(String nom);

    //find by id evenement

 //   List<Activite> findByEvenement(String id);
//find by evenement_id
  List<Activite> findByEvenementId(Long id);
    //query
//    List<Activite> findByNomActivite(String nom);
//    List<Activite> findByCategorieActivite(String categorie);
}

