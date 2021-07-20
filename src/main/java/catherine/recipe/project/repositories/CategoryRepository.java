package catherine.recipe.project.repositories;

import catherine.recipe.project.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
