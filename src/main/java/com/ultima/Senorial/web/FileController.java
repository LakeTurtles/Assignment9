package com.ultima.Senorial.web;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import com.ultima.Senorial.domain.Recipe;
import com.ultima.Senorial.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FileController {

	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private FileService fileService;

	@GetMapping("")
	public Set<Recipe> home() throws IOException {

		Set<Recipe> home = fileService.getAllRecipes().stream()
				.collect(()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		home.forEach(System.out::println);

		return home;
	}


	@GetMapping("/gluten-free") 
	public Set<Recipe> glutenFree() throws IOException {

		Set<Recipe> glutten = fileService.getAllRecipes().stream().filter(Recipe::getGlutenFree)
				.collect(()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		glutten.forEach(System.out::println);

		return glutten;
	}
	

	@GetMapping("/vegan")
	public Set<Recipe> vegan() throws IOException {

		Set<Recipe> vegan = fileService.getAllRecipes().stream().filter(Recipe::getVegan)
				.collect(()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		vegan.forEach(System.out::println);

		return vegan;
	}


	@GetMapping("/vegan-and-gluten-free")
	public Set<Recipe> veganAndGlutenFree() throws IOException {

		Set<Recipe> veganAndGlutten = fileService.getAllRecipes().stream().filter(Recipe::getGlutenFree)
				.filter(Recipe::getVegan).collect(()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		veganAndGlutten.forEach(System.out::println);

		return veganAndGlutten;
	}


	@GetMapping("/vegetarian")
	public Set<Recipe> vegetarian() throws IOException {

		Set<Recipe> vegetarian = fileService.getAllRecipes().stream().filter(Recipe::getVegetarian)
				.collect(()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		vegetarian.forEach(System.out::println);

		return vegetarian;
	}


	@GetMapping("/all-recipes")
	public Set<Recipe> allRecipes() throws IOException  {

		Set<Recipe> allRrecipes = fileService.getAllRecipes().stream().collect(
				()-> new TreeSet<>(Comparator.comparing(Recipe::getCookingMinutes)),
				TreeSet::add, TreeSet::addAll);

		allRrecipes.forEach(System.out::println);

		return allRrecipes;
	}
}
