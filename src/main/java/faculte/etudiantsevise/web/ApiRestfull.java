package faculte.etudiantsevise.web;

import faculte.etudiantsevise.dto.RequestEtudiantDto;
import faculte.etudiantsevise.dto.ResponseEtudiantDto;
import faculte.etudiantsevise.service.EtudiantServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Etudiants",
                description = "cette offre tous les méthodes pour gérer les etudiant",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8083"
        )
)
@RestController
@RequestMapping("/v1/etudiants")

public class ApiRestfull {
    private final EtudiantServiceImpl etudiantService;

    public ApiRestfull(EtudiantServiceImpl etudiantService) {
        this.etudiantService = etudiantService;
    }
    @Operation(
            summary = " Ajouter un etudiant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )

    @PostMapping
    public ResponseEntity<ResponseEtudiantDto> add(@RequestBody RequestEtudiantDto requestEtudiantDto) {
        ResponseEtudiantDto response = etudiantService.Add_Etudiant(requestEtudiantDto);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = " récuperer liste des etudiant",

            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEtudiantDto.class ))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseEtudiantDto>> getAll() {
        List<ResponseEtudiantDto> etudiants = etudiantService.GetAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }
    @Operation(
            summary = " récupérer etudiant par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien récuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> getById(@PathVariable Integer id) {
        ResponseEtudiantDto response = etudiantService.GetEtudiantByID(id);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = " Modifier un etudiant",
            parameters = @Parameter(name = "id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> update(
            @PathVariable Integer id,
            @RequestBody RequestEtudiantDto requestEtudiantDto) {
        ResponseEtudiantDto response = etudiantService.Update_Etudiant(id, requestEtudiantDto);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = " supprimer etudiant par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien supprimer"),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        etudiantService.DeleteEtudiantByID(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filieres/{filiereId}")
    public ResponseEntity<List<ResponseEtudiantDto>> getByFiliere(@PathVariable Integer filiereId) {
        List<ResponseEtudiantDto> etudiants = etudiantService.GetEtudiantsByFiliereId(filiereId);
        return ResponseEntity.ok(etudiants);
    }



}
