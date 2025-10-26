package faculte.etudiantsevise.service;

import faculte.etudiantsevise.dto.RequestEtudiantDto;
import faculte.etudiantsevise.dto.ResponseEtudiantDto;

import java.util.List;

public interface EtudiantService {

        public ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto request);
        public List<ResponseEtudiantDto> GetAllEtudiants();
        public ResponseEtudiantDto GetEtudiantByID(Integer id);
        public ResponseEtudiantDto Update_Etudiant(Integer id,RequestEtudiantDto request);
        public void DeleteEtudiantByID(Integer id);

    List<ResponseEtudiantDto> GetEtudiantsByFiliereId(Integer filiereId);
}
