package org.sid.backend.dao;
import org.sid.backend.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {




    //find by name activite
    Activite findByName(String nom);




//    List<Activite> findByNomActivite(String nom);
//    List<Activite> findByCategorieActivite(String categorie);
}

