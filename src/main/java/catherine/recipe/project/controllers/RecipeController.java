package catherine.recipe.project.controllers;

import catherine.recipe.project.commands.RecipeCommand;
import catherine.recipe.project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jt on 6/19/17.
 */
@Controller //make this a controller to autowire the dependency injection
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    //The @PathVariable annotation is used to extract the value from the URI.
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    //implement the method that will render the view
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    //need to handle to post back
    //@ModelAttribute: an annotation to tell spring to bind the form post parameters to the recipeCommand object
    @PostMapping//tell spring when we get a post to the recipe url, invoke this specific method
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        //a command to tell spring MVC to redirect to a specific url
        return "redirect:/recipe/show/" + savedCommand.getId();
    }
}
