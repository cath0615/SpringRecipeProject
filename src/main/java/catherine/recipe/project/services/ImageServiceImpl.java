package catherine.recipe.project.services;

import catherine.recipe.project.domain.Recipe;
import catherine.recipe.project.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }

    @Override
    @Transactional
    //Spring dynamically creates a proxy that implements the same interface(s) as the class you're annotating.
    //And when clients make calls into your object, the calls are intercepted and the behaviors injected
    //via the proxy mechanism
    public void saveImageFile(Long recipeId, MultipartFile file) { //going to be passed from the controller
        //The try statement allows you to define a block of code to be tested for errors while it is being executed.
        //The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.
        try {
            //get the recipe object since we'll be saving against an existing recipe
            Recipe recipe = recipeRepository.findById(recipeId).get();

            //need to convert it to a byte obj since multipart file use a java primitive byte array
            //do a new byteArray to the length of the primitive byte array
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}
