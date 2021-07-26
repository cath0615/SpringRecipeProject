package catherine.recipe.project.repositories;

import catherine.recipe.project.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

//an integration test
@RunWith(SpringRunner.class)
@DataJpaTest //this will bring up an embedded in-memory database and also configure spring data jpa for us
public class UnitOfMeasureRepositoryIT {

    @Autowired //now spring is gonna do dependency injection on our integration test here
            //spring context will start up and we will get an instance of uom repository and inject into this
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        //we ask the repository to look up by teaspoon
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    //spring context still available for this test and we don't need to reload it
    @Test
    public void findByDescriptionCup() {
        //we ask the repository to look up by teaspoon
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", uomOptional.get().getDescription());
    }
}