package catherine.recipe.project.controllers;

import catherine.recipe.project.domain.Category;
import catherine.recipe.project.domain.UnitOfMeasure;
import catherine.recipe.project.repositories.CategoryRepository;
import catherine.recipe.project.repositories.UnitOfMeasureRepository;
import catherine.recipe.project.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        //assign it to a property on the view model of "recipes"
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
