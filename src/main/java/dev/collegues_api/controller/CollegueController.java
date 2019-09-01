package dev.collegues_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.service.CollegueService;

/**
 * Classe représentant le controleur qui concerne les collègues.
 */
@Controller
public class CollegueController {

	/** collegueService : CollegueService */
	private CollegueService collegueService;

	/**
	 * Constructor
	 * 
	 * @param collegueService
	 */
	@Autowired
	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	/**
	 * Controleur envoyant en réponse HTTP la liste des matricules des collègues qui
	 * ont le nom spécifié par la requête GET.
	 * 
	 * @param nom : String nom du/des collègue(s)
	 * @return : réponse http contenant List<String> liste des matricules des
	 *         collègues
	 */
	@RequestMapping(path = "/collegues", method = RequestMethod.GET)
	@ResponseBody
	public List<String> reqParamNom(@RequestParam String nom) {

		List<Collegue> listeCollegues = collegueService.rechercherParNom(nom);

		return listeCollegues.stream().map(c -> c.getMatricule()).collect(Collectors.toList());

	}

	/**
	 * Controleur envoyant en réponse HTTP le collègue qui a le matricule spécifié
	 * par le requête GET.
	 * 
	 * @param matricule : String matricule du collègue
	 * @return : réponse http contenant dans le corps : Collegue le collègue
	 *         correspondant
	 */
	@RequestMapping(path = "/collegues/{matricule}", method = RequestMethod.GET)
	@ResponseBody
	public Collegue reqMatricule(@PathVariable String matricule) {
		return collegueService.rechercherParMatricule(matricule);
	}

	/**
	 * Gestionnaire de l'exception CollegueNonTrouveException
	 * 
	 * @param e : CollegueNonTrouveException
	 * @return : réponse http avec statut d'erreur 404, contenant dans le corps le
	 *         message d'erreur
	 */
	@ExceptionHandler(CollegueNonTrouveException.class)
	public ResponseEntity<String> handleException(CollegueNonTrouveException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	/**
	 * Gestionnaire de l'exception CollegueInvalideException
	 * 
	 * @param e : CollegueInvalideException
	 * @return : réponse http avec statut d'erreur 404, contenant dans le corps le
	 *         message d'erreur
	 */
	@ExceptionHandler(CollegueInvalideException.class)
	public ResponseEntity<String> handleException(CollegueInvalideException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	/**
	 * Controleur permettant l'ajout d'un collègue dans la Map des collègues à
	 * partir des données de la requête POST.
	 * 
	 * @param collegue : Collegue collègue à ajouter (JSON)
	 * @return : réponse http contenant dans son corps le collègue ajouté
	 */
	@PostMapping("/collegues")
	@ResponseBody
	public Collegue reqAjoutCollegue(@RequestBody Collegue collegue) {
		return collegueService.ajouterUnCollegue(collegue);
	}

	/**
	 * Controleur permettant la modification d'un collègue dans la Map stockant tous
	 * les collègues, à partir des données de la requête PATCH.
	 * 
	 * @param collegue  : Collegue données partielles pour la modification (JSON)
	 * @param matricule : String matricule du collègue à modifier
	 * @return : réponse http contenant dans son corps le collègue après
	 *         modification
	 */
	@PatchMapping("/collegues/{matricule}")
	@ResponseBody
	public Collegue partialUpdateName(@RequestBody Collegue collegue, @PathVariable("matricule") String matricule) {

		if (collegue.getEmail() != null) {
			collegueService.modifierEmail(matricule, collegue.getEmail());
		}
		if (collegue.getPhotoUrl() != null) {
			collegueService.modifierPhotoUrl(matricule, collegue.getPhotoUrl());
		}

		return collegueService.rechercherParMatricule(matricule);
	}

}
