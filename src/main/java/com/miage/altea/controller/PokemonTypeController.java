package com.miage.altea.controller;

import annotations.Controller;
import annotations.RequestMapping;

import java.util.Map;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepository;

@Controller
public class PokemonTypeController {

	private PokemonTypeRepository repository = new PokemonTypeRepository();

	@RequestMapping(uri="/pokemons")
	public PokemonType getPokemon(Map<String,String[]> parameters){
		if (parameters == null) {
			throw new IllegalArgumentException("parameters should not be empty");
		}
		if (parameters.containsKey("id")) {
			return repository.findPokemonById(Integer.parseInt(parameters.get("id")[0]));
		}
		else if (parameters.containsKey("name")) {
			return repository.findPokemonByName(parameters.get("name")[0]);
		}
		else if (!parameters.keySet().isEmpty()) {
			throw new IllegalArgumentException("unknown parameter");
		}
		return null;
	}
}
