package catherine.recipe.project.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//recipe object is now represented as POJO in the database and have been annotated
//for JPA
public class Recipe {
    //the @entity set up the object world and we need to deal with the relational
    //world. We need to give recipe its ID value
    @Id
    //tells it how to generate the id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    //defines relationship from Recipe class and this Recipe will be stored
    //on a property on the child/sets of ingredients on each object of
    //Ingredient's recipe property
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    //create image as a binary large objects field(blob) inside the database
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    //create the relationship for one to one mapping between notes and recipe
    //cascade: make the recipe the owner
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    //create a jointable called recipe_category, from this way of relationship
    //we will use recipe ID and coming back(from the other side) we will use category id
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient) {
        //build the bidirectional method
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }
}
