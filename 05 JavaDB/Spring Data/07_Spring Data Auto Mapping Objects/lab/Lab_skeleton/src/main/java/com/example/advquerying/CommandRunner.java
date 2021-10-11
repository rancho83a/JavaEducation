package com.example.advquerying;

import com.example.advquerying.dto.IngredientsDTO;
import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public CommandRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Ingredient, IngredientsDTO>() {
            @Override
            protected void configure() {
                map().setName(source.getName());
            }
        });

        TypeMap<IngredientsDTO, Ingredient> typeMap = mapper.typeMap(IngredientsDTO.class, Ingredient.class);
       // typeMap.addMappings(m -> m.map(src -> src.getName(), (destination, value) -> destination.setName((String) value)));
        typeMap.addMappings(m -> m.map(IngredientsDTO::getName,Ingredient::setName));


//        System.out.println("TASK1");
//        System.out.println("Enter size:");ME
//        Scanner scan = new Scanner(System.in);
//        Size size = Size.valueOf(scan.nextLine());
//        this.shampooService.getAllBySizeOrderById(size).forEach(System.out::println);

//        System.out.println("TASK2");
//        System.out.println("Enter size and lable");
//        Scanner scan = new Scanner(System.in);
//        Size size = Size.valueOf(scan.nextLine());
//        Long labelId= Long.parseLong(scan.nextLine());
//
//        this.shampooService.getAllBySizeOrLabelIdOrderByPrice(size,labelId) .forEach(System.out::println);


//        System.out.println("TASK3");
//        System.out.println("Enter price");
//        Scanner scan = new Scanner(System.in);
//
//        this.shampooService.getALLByPriceGT(new BigDecimal(scan.nextLine())).forEach(System.out::println);

//        System.out.println("TASK4");
//        System.out.println("Enter letter");
//        Scanner scan = new Scanner(System.in);
//
//        this.ingredientService.getAllByNamesWithStart(scan.nextLine())
//                .forEach(System.out::println);


//        System.out.println("TASK5");
//        System.out.println("Enter Ingredients by space");
//        Scanner scan = new Scanner(System.in);
//        List<String> name = Arrays.asList(scan.nextLine().split("\\s+"));
//
//        this.ingredientService.getdAllByNameIn(name)
//                .forEach(System.out::println);

//        System.out.println("TASK6");
//        System.out.println("Enter price");
//        Scanner scan = new Scanner(System.in);
//BigDecimal price = new BigDecimal(scan.nextLine());
//        System.out.println(this.shampooService.countAllByPriceLessThan(price));
//
//        System.out.println("TASK7");
//        System.out.println("Enter ingredients");
//        Scanner scan = new Scanner(System.in);
//        List<String> names = Arrays.asList(scan.nextLine().split("\\s+"));
//        this.shampooService.getAllByIngredientsNames(names).forEach(System.out::println);
//
//        System.out.println("TASK8");
//        System.out.println("Enter ingredients");
//        Scanner scan = new Scanner(System.in);
//       // Long count = Long.parseLong(scan.nextLine());
//        int count = Integer.parseInt(scan.nextLine());
//        this.shampooService.getAllByIngredientsCount(count).forEach(System.out::println);

//        System.out.println("TASK9");
//        System.out.println("Enter ingredients name");
//        Scanner scan = new Scanner(System.in);
//this.ingredientService.deleteAllByNames(scan.nextLine());

//        System.out.println("UPDATED"+this.ingredientService.updateAllByPrice());
//
//        System.out.println("Enter priceChange");
//        Scanner scan = new Scanner(System.in);
//
//        BigDecimal price = new BigDecimal(scan.nextLine());
//
//        System.out.println("Enter names");
//
//        List<String> names = Arrays.asList(scan.nextLine());
//
//        System.out.println("UPDATED " + this.ingredientService.updateAllByPrice(price, names));


//        this.shampooRepository.findAllByDeletedOnIsNull()
//                .forEach(s -> System.out.println(s.getId() + " " + s.getBrand()));
//
//
//        System.out.println("____________________________");
//        this.labelRepository.findAllByDeletedOnIsNull()
//                .forEach(l -> System.out.println(l.getId() + " " + l.getSubtitle()));
//
//        this.ingredientDao.createQueryAllIngredient().forEach(i-> System.out.println(i.getId()+" "+i.getName()));


    }
}