package catherine.recipe.project.services;

import catherine.recipe.project.commands.UnitOfMeasureCommand;
import catherine.recipe.project.converters.UnitOfMeasureToUnitOfMeasureCommand;
import catherine.recipe.project.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        //streamSupport: a convenient way to swap an iterator to something that we can stream against
        //A stream is a sequence of objects that supports various methods which can be
        //pipelined to produce the desired result.
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                //spliterator gives us a java stream
                //which is used to iterate elements one-by-one from a List implemented object
                .spliterator(), false)
                //use map function to convert our domain objects to a command object
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                //collect them into a set and return them
                .collect(Collectors.toSet());
    }
}
