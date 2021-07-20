package catherine.recipe.project.controllers;

import catherine.recipe.project.domain.Category;
import catherine.recipe.project.domain.UnitOfMeasure;
import catherine.recipe.project.repositories.CategoryRepository;
import catherine.recipe.project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    //inject an instance of categoryRepository
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is:" + categoryOptional.get().getId());
        System.out.println("UOM Id is:" + unitOfMeasureOptional.get().getId());
        return "index";
    }
}
