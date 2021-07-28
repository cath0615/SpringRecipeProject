package catherine.recipe.project.services;

import catherine.recipe.project.commands.RecipeCommand;
import catherine.recipe.project.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    //we want to persist this into the database
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
