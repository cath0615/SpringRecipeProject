package catherine.recipe.project.repositories;

import catherine.recipe.project.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

//even if we don't require them right now, they have been created as Spring Beans in the context
//and if we had anything that needed them, it would have got injected there
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
