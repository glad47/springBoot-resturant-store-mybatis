package com.example.demo;

import com.example.demo.mapper.IngredientMapper;
import com.example.demo.mapper.IngredientTacoMapper;
import com.example.demo.mapper.TacoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Ingredient.Type;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private IngredientMapper ingredientMapper;
    private TacoMapper tacoMapper;
    private IngredientTacoMapper ingredientTacoMapper;
    @Autowired
    public DesignTacoController(IngredientMapper ingredientMapper,
                                TacoMapper tacoMapper,
                                IngredientTacoMapper ingredientTacoMapper){
        this.ingredientMapper=  ingredientMapper;
        this.tacoMapper=tacoMapper;
        this.ingredientTacoMapper=ingredientTacoMapper;
    }
    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }
    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients=new ArrayList<>();
        ingredientMapper.findAll().forEach(i->ingredients.add(i));
        Type[] types=Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
//        model.addAttribute("design", new Taco());
        return "design";
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute Taco taco, Errors errors,
                                @ModelAttribute Order order) {

    if(errors.hasErrors()){
        return "design";
    }
    // Save the taco design...
    // We'll do this in chapter 3
        Taco saved=save(taco);
        order.addDesign(saved);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    public Taco save(Taco design){
        design.setCreatedAt(new Date());
        tacoMapper.saveTacoInfo(design);
        long tacoId=design.getId();
        design.setId(tacoId);
        for( Ingredient ingredient: design.getIngredients()){
            ingredientTacoMapper.saveIngredientToTaco(ingredient.getId(),tacoId);

        }
        return design;
    }
}
