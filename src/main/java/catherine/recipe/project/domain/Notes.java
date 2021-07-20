package catherine.recipe.project.domain;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
