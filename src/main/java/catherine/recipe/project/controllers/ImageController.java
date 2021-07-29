package catherine.recipe.project.controllers;

import catherine.recipe.project.commands.RecipeCommand;
import catherine.recipe.project.services.ImageService;
import catherine.recipe.project.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageuploadform";
        //return back a form for the recipe id
    }


    @PostMapping("recipe/{id}/image")
    //The @RequestParam is used to read the HTML form data provided by a user and bind it to the request parameter
    //the file will be passed by the form on the post
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    //httpServletResponse represents the entire response, including headers and a bunch of other stuff.
    //The response object is where the servlet can write information about the data it will send back.
    //we are just to return back a raw image
    public void renderImageFormDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        if (recipeCommand.getImage() != null) {
            //doing from boxed type to the primitive type
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            for (Byte wrappedByte : recipeCommand.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
                //Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and
                //their corresponding object wrapper classes. unboxing is vice versa
            }

            response.setContentType("image/jpeg");
            //inputStream represents an ordered stream of bytes. In other words, can read data from a Java
            //InputStream as an ordered sequence of bytes
            InputStream is = new ByteArrayInputStream(byteArray);
            //IOUtils copy from the byte array input stream to the response output stream
            //IoUtils Copies some or all chars from a large (over 2GB) InputStream to an OutputStream
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
