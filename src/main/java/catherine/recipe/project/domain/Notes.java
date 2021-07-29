package catherine.recipe.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //we don't need to specify cascade because this instance we are
    //going to allow the Recipe to own it
    @OneToOne
    private Recipe recipe;

    //Lob: JPA will expect a distort in a CLOB(character
    //large Object: a collection of character data in a database management system)
    //field in the database
    //@Lob annotation is used to map fields/properties of large value to a
    //corresponding database-supported large object type.
    @Lob
    private String recipeNotes;
}
