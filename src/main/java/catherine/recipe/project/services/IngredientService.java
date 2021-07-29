package catherine.recipe.project.services;

import catherine.recipe.project.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientid);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
