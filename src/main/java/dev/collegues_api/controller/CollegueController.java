package dev.collegues_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues_api.model.Collegue;
import dev.collegues_api.service.CollegueService;

@RestController
@RequestMapping("/collegues-api")
public class CollegueController {

	@GetMapping("/collegues")
	public List<String> reqParamNom(@RequestParam String nom) {
		List<Collegue> listeCollegues = null;
		CollegueService collegueService = new CollegueService();
		System.out.println("nom récupéré = " + nom);
		listeCollegues = collegueService.rechercherParNom(nom);
		System.out.println("liste des collègues récupérés = " + listeCollegues);

		return listeCollegues.stream().map(c -> c.getMatricule()).collect(Collectors.toList());

	}

}
