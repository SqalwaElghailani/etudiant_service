package faculte.etudiantsevise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantWithFiliereDto {
    private ResponseEtudiantDto etudiant;
    private ResponseFiliereDto filiere;
}
