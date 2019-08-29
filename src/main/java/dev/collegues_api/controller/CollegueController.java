package dev.collegues_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.service.CollegueService;

@Controller
// @RequestMapping("/jbmerand-collegues-api")
public class CollegueController {

	CollegueService collegueService = new CollegueService();

	@RequestMapping(path = "/collegues", method = RequestMethod.GET)
	@ResponseBody
	public List<String> reqParamNom(@RequestParam String nom) {

		List<Collegue> listeCollegues = null;
		listeCollegues = collegueService.rechercherParNom(nom);

		return listeCollegues.stream().map(c -> c.getMatricule()).collect(Collectors.toList());

	}

	@RequestMapping(path = "/collegues/{matricule}", method = RequestMethod.GET)
	@ResponseBody
	public Collegue reqMatricule(@PathVariable String matricule) {
		// ou @PathVariable(name = "ageBilel") String aB
		Collegue collegue = null;

		collegue = collegueService.rechercherParMatricule(matricule);
		return collegue;
	}

	@ExceptionHandler(CollegueNonTrouveException.class)
	public ResponseEntity<String> handleException(CollegueNonTrouveException e) {
		return ResponseEntity.status(404).body("Erreur : Ce matricule ne correspond à aucun collègue :/");
	}

	@PostMapping("/collegues")
	@ResponseBody
	public Collegue reqAjoutCollegue(@RequestBody Collegue collegue) {
		Collegue collegueAjoute = collegueService.ajouterUnCollegue(collegue);
		return collegueAjoute;
	}

}
