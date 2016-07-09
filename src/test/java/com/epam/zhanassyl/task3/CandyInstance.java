package com.epam.zhanassyl.task3;

public class CandyInstance {
    Ingredients ingredients;
    Values values;
    String production;
    Integer id;
    String name;
    Integer energy;
    CandyType type;
    ChocolateType chocolate;
    boolean water;

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public CandyType getType() {
        return type;
    }

    public void setType(CandyType type) {
        this.type = type;
    }

    public ChocolateType getChocolate() {
        return chocolate;
    }

    public void setChocolate(ChocolateType chocolate) {
        this.chocolate = chocolate;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }
    public CandyInstance(){

    }
    public void printEverything(){
        System.out.println("Candy:");
        System.out.println("Ingredients"+ ingredients+"\n"+
        "Values"+values+"\n"+
        "Products"+production+"\n"+
        "id"+id+"\n"+
        "name"+name+"\n"+
        "energy"+energy+"\n"+
        "candyType"+type+"\n"+
        "chocolateType"+chocolate+"\n"+ "water"+water+"\n");
    }
}
