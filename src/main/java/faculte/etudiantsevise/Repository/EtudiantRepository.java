package faculte.etudiantsevise.Repository;

import faculte.etudiantsevise.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {

    List<Etudiant> findByFiliereId(Integer filiereId);
}
