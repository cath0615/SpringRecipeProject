package catherine.recipe.project.controllers;

import catherine.recipe.project.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        String viewName = indexController.getIndexPage(model);
        assertEquals(viewName, "index");
        verify(recipeService, times(1)).getRecipes();
        //add attribute with any set value. ep(): equals matcher make sure that the attribute we are
        //passing in the first value is going to equal to String "recipes" and the second value
        //of the method call is going to be anyset()
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}