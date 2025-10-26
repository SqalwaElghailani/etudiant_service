package faculte.etudiantsevise.Repository;

import faculte.etudiantsevise.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {

}
