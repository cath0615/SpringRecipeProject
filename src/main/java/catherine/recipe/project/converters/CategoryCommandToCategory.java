package catherine.recipe.project.converters;

import catherine.recipe.project.commands.CategoryCommand;
import catherine.recipe.project.domain.Category;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//spring framework has an interface for converter which takes in two types and implement the converter method
//it's null safe(able to return null)
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
//spring does not guarantee thread safety, so use lombok's synchronized method to be thread safe and run
    //this in a multi-threaded environment
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        //return back an instance of the converted type
        //declare final to be immutable
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
