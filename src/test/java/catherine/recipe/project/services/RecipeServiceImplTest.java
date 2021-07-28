package catherine.recipe.project.services;

import catherine.recipe.project.converters.RecipeCommandToRecipe;
import catherine.recipe.project.converters.RecipeToRecipeCommand;
import catherine.recipe.project.domain.Recipe;
import catherine.recipe.project.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//if we want to test getRecipes method, we need to provide a recipe repository since it's a required
//dependency of the implementation class. At runtime, Spring data JPA provides the instance of the repository
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    //need dependency since we need to inject the repository
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        //initialize our mock
        MockitoAnnotations.initMocks(this); //tells mokito to give me a mock repository
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand); //init our recipeService
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe); //now we have a set and we wand Mokito to return this

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1); //mokito is giving us an empty set
        //use verify with mockito. Verify that the recipeRepository times once
        //--> make sure findAll is called and only called once
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
}