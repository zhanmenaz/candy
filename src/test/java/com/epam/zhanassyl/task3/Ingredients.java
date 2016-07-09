package com.epam.zhanassyl.task3;


public class Ingredients {
    public Ingredients() {

    }

    public static class Ingredient{
        Integer amount;
        String unit;
        public Ingredient (Integer amount,String unit){
            this.amount=amount;
            this.unit=unit;
        }
        public Ingredient(){
            //TODO Auto-generated constructor stub
        }
        public Integer getAmount(){
            return amount;
        }
        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
        @Override

        public String toString(){
            return "("+amount.toString()+","+unit+")";
        }
    }
    Ingredients sugar;
    Ingredients fructose;
    Ingredient vanilla;

    public Ingredient getSugar() {
        return sugar;
    }

    public void setSugar(Ingredient sugar) {
        this.sugar = sugar;
    }

    public Ingredient getFructose() {
        return fructose;
    }

    public void setFructose(Ingredient fructose) {
        this.fructose = fructose;
    }

    public Ingredient getVanilla() {
        return vanilla;
    }

    public void setVanilla(Ingredient vanilla) {
        this.vanilla = vanilla;
    }
    public Ingredients(Ingredients sugar, Ingredients fructose, Ingredients vanilla){
        this.fructose = fructose;
        this.sugar = sugar;
        this.vanilla = vanilla;
    }
    public Ingredients(Integer amount, String unit){
        //TODO Auto-generated constructor stub
    }
    @Override
    public String toString(){
        return "sugar:"+getSugar()+"vanilla"+getVanilla()+"fructose"+getFructose();
    }
}
