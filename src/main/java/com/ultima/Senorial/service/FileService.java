package com.ultima.Senorial.service;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import com.ultima.Senorial.domain.Recipe;
import com.ultima.Senorial.repository.RecipeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	private String filename = "recipes.txt";

	@Autowired
	private RecipeRepository recipeRepo;
	
	public FileService() {}
	
	public List<Recipe> readFile () throws IOException {

		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',')
				.withEscape('\\')
				.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes",
						"Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan",
						"Vegetarian")
				.withSkipHeaderRecord()
				.withIgnoreSurroundingSpaces();



		try (Reader fileInput = new FileReader("src/main/resources/recipes.txt")) {
			Iterable<CSVRecord> records = csvFormat.parse(fileInput);
			for (CSVRecord record : records) {


				Integer cookingMins = Integer.parseInt(record.get(0));
				Boolean dairy = Boolean.parseBoolean(record.get(1));
				Boolean gluten = Boolean.parseBoolean(record.get(2));
				String instructions = record.get(3)  ;
				Double prepMin = Double.parseDouble(record.get(4));
				Double pricePerServ = Double.parseDouble(record.get(5));
				Integer readyMins = Integer.parseInt(record.get(6));
				Integer servings = Integer.parseInt(record.get(7));
				Double score = Double.parseDouble(record.get(8));
				String title = record.get(9);
				Boolean vegan = Boolean.parseBoolean(record.get(10));
				Boolean vegetarian = Boolean.parseBoolean(record.get(11));


				Recipe recipe = new Recipe(cookingMins, dairy, gluten, instructions, prepMin, pricePerServ, readyMins,
						servings, score, title, vegan, vegetarian);

				recipeRepo.getRecipes().add(recipe);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			return recipeRepo.getRecipes();
	}


	public List<Recipe> getAllRecipes () throws IOException {
		if (recipeRepo.getRecipes().size() == 0) {
			return readFile();
		}
		return recipeRepo.getRecipes();
	}

}
