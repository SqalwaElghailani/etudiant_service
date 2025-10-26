package faculte.etudiantsevise.service;

import faculte.etudiantsevise.Repository.EtudiantRepository;
import faculte.etudiantsevise.dto.EtudiantWithFiliereDto;
import faculte.etudiantsevise.dto.RequestEtudiantDto;
import faculte.etudiantsevise.dto.ResponseEtudiantDto;
import faculte.etudiantsevise.dto.ResponseFiliereDto;
import faculte.etudiantsevise.entities.Etudiant;
import faculte.etudiantsevise.mappers.EtudiantMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;
    private final RestTemplate restTemplate;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper,RestTemplate restTemplate) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
        this.restTemplate = restTemplate;
    }

    private ResponseFiliereDto getFiliereById(Integer filiereId) {
        try {
            String url = "http://localhost:8082/v1/filieres/" + filiereId;
            return restTemplate.getForObject(url, ResponseFiliereDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Filiere not found with id: " + filiereId);
        }
    }
    @Override
    public EtudiantWithFiliereDto GetEtudiantWithFiliere(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));

        ResponseEtudiantDto etudiantDto = etudiantMapper.Entity_to_DTO(etudiant);
        ResponseFiliereDto filiereDto = getFiliereById(etudiant.getFiliereId());

        return new EtudiantWithFiliereDto(etudiantDto, filiereDto);
    }

    @Override
    public ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto request) {
        ResponseFiliereDto filiere = getFiliereById(request.getFiliereId());
        Etudiant etudiant = etudiantMapper.DTO_to_Entity(request);
        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.Entity_to_DTO(saved_etudiant);

    }

    @Override
    public List<ResponseEtudiantDto> GetAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<ResponseEtudiantDto> etudiantDTos= new ArrayList<>();

        for (Etudiant c: etudiants){
            etudiantDTos.add(etudiantMapper.Entity_to_DTO(c));
        }
        return etudiantDTos;    }

    @Override
    public ResponseEtudiantDto GetEtudiantByID(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();
        return etudiantMapper.Entity_to_DTO(etudiant);
    }

    @Override
    public ResponseEtudiantDto Update_Etudiant(Integer id, RequestEtudiantDto request) {
        Etudiant new_etudiant = etudiantMapper.DTO_to_Entity(request);
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();
        if(new_etudiant.getNom() != null){
            etudiant.setNom(new_etudiant.getNom());
        }
        if(new_etudiant.getPrenom() != null){
            etudiant.setPrenom(new_etudiant.getPrenom());
        }
        if(new_etudiant.getCne() != null){
            etudiant.setCne(new_etudiant.getCne());
        }
        if(new_etudiant.getFiliereId() != null){
            etudiant.setFiliereId(new_etudiant.getFiliereId());
        }
        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.Entity_to_DTO(saved_etudiant);
    }

    @Override
    public void DeleteEtudiantByID(Integer id) {
        etudiantRepository.deleteById(id);
    }
    @Override
    public List<ResponseEtudiantDto> GetEtudiantsByFiliereId(Integer filiereId) {
        ResponseFiliereDto filiere = getFiliereById(filiereId);
        List<Etudiant> etudiants = etudiantRepository.findByFiliereId(filiereId);

        return etudiants.stream()
                .map(etudiantMapper::Entity_to_DTO)
                .collect(Collectors.toList());

    }



}
