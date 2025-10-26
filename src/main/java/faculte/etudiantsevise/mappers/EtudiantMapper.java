package faculte.etudiantsevise.mappers;

import faculte.etudiantsevise.dto.RequestEtudiantDto;
import faculte.etudiantsevise.dto.ResponseEtudiantDto;
import faculte.etudiantsevise.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public Etudiant  DTO_to_Entity(RequestEtudiantDto request){
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(request,etudiant);
        return etudiant;
    }
    public ResponseEtudiantDto Entity_to_DTO(Etudiant etudiant){
        ResponseEtudiantDto response = new ResponseEtudiantDto();
        BeanUtils.copyProperties(etudiant,response);
        return response;
    }

}

