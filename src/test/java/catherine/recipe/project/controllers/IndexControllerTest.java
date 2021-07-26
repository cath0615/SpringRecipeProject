package catherine.recipe.project.controllers;

import catherine.recipe.project.domain.Recipe;
import catherine.recipe.project.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        //we want to verification for the returned set
        Set<Recipe> recipes = new HashSet<>();
        //add two more recipes
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        //a standard syntax of a behavior driven test
        when(recipeService.getRecipes()).thenReturn(recipes);

        //attribute matcher
        //creates an argument captor for the class Set
        //ArgumentCaptor allows us to capture an argument passed to a method in order to inspect it.
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        //then
        assertEquals(viewName, "index");
        verify(recipeService, times(1)).getRecipes();
        //add attribute with any set value. ep(): equals matcher make sure that the attribute we are
        //passing in the first value is going to equal to String "recipes" and the second value
        //of the method call is going to be anyset()
        //argumentCaptor.capture(): return what is captured into the captor
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(setInController.size(), 2);
    }
}