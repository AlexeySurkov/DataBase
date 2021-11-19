package ru.rsreu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ru.rsreu.classes.SanitaryWare;
import ru.rsreu.classes.SanitaryWare.Type;
import ru.rsreu.classes.Order;
import ru.rsreu.classes.Basket;
import ru.rsreu.data.IngredientRepository;
import ru.rsreu.data.TacoRepository;

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]
@Controller
@RequestMapping("/design")
//end::injectingIngredientRepository[]
@SessionAttributes("order")
//tag::injectingIngredientRepository[]
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  //end::injectingIngredientRepository[]
  private TacoRepository tacoRepo;

  //end::injectingDesignRepository[]
  /*
  //tag::injectingIngredientRepository[]
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  //end::injectingIngredientRepository[]
   */
  //tag::injectingDesignRepository[]

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "design")
  public Basket design() {
    return new Basket();
  }

  //end::injectingDesignRepository[]

  //tag::injectingIngredientRepository[]

  @GetMapping
  public String showDesignForm(Model model) {
    List<SanitaryWare> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = SanitaryWare.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }

    return "design";
  }
  //end::injectingIngredientRepository[]

//tag::injectingDesignRepository[]
  @PostMapping
  public String processDesign(
          @Valid Basket taco, Errors errors,
          @ModelAttribute Order order) {

    if (errors.hasErrors()) {
      return "design";
    }

    Basket saved = tacoRepo.save(taco);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

//end::injectingDesignRepository[]

  private List<SanitaryWare> filterByType(
          List<SanitaryWare> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

  /*
  //tag::injectingDesignRepository[]
  //tag::injectingIngredientRepository[]

   ...
  //end::injectingIngredientRepository[]
  //end::injectingDesignRepository[]
  */

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]

}
//end::injectingIngredientRepository[]
//end::injectingDesignRepository[]
