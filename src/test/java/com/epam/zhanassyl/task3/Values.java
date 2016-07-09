package com.epam.zhanassyl.task3;

public class Values {
    public static class Value {
        		Integer amount;
         		String unit;
         		public Value(Integer amount,String unit){
             			this.amount=amount;
             			this.unit=unit;
             		}

                 		public Integer getAmount() {
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


                 		public Value() {
             			// TODO Auto-generated constructor stub
            		}
         		@Override
         		public String toString() {

             			return "( "+amount.toString()+ " , "+unit+" )";
             		}
         	}
     	Value protein;
     	Value fat;
     	Value carbohydrate;

             	public Value getProtein() {
         		return protein;
         	}


             	public void setProtein(Value protein) {
         		this.protein = protein;
         	}


             	public Value getFat() {
         		return fat;
         	}


             	public void setFat(Value fat) {
         		this.fat = fat;
         	}


             	public Value getCarbohydrate() {
         		return carbohydrate;
         	}


             	public void setCarbohydrate(Value carbohydrate) {
         		this.carbohydrate = carbohydrate;
         	}


             	public Values(Value protein, Value fat, Value carbohydrate){
         		this.protein=protein;
        		this.fat=fat;
         		this.carbohydrate=carbohydrate;
         	}


             	public Values() {
         		// TODO Auto-generated constructor stub
        	}

             	@Override
     	public String toString() {
         		return " protein: "+getProtein()+" fat: "+getFat()+" carbohydrate: "+getCarbohydrate();
         	}


}
