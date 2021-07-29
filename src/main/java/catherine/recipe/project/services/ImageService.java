package catherine.recipe.project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
