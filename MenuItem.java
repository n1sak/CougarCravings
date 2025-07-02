

/**
https://d16k74nzx9emoe.cloudfront.net/e91371ba-2d98-4041-9b00-a1ca030fe261/LUNCH.pdf 
https://d16k74nzx9emoe.cloudfront.net/1949a8fa-9819-4a6a-b4d3-a87e99bb1de0/APR-FULL-NUT-LUNCH.pdf 
https://www.esuhsd.org/documents/Community/CNS/menus/Recipe-Allergen-List-2-14-2024.pdf 
*/
public class MenuItem {
 /** Name of menu item */
 private String name;
 /** Calories of menu item */
 private double calories;
 /** Sodium of menu item */
 private double sodium;
 /** Total Sugars of menu item */
 private double totalSugars;
 /** Carbs of menu item */
 private double carbs;

 /** True if menu item has egg as an ingredient */
 private boolean hasEgg;
 /** True if menu item has milk as an ingredient */
 private boolean hasMilk;
 /** True if menu item has soy as an ingredient */
 private boolean hasSoy;
 /** True if menu item has wheat as an ingredient */
 private boolean hasWheat;
 /** True if menu item has peanut as an ingredient */
 private boolean hasPeanuts;
 /** True if menu item is not a vegetarian as an ingredient */
 private boolean isNotVegetarian;
 
 /** MenuItem constructor to initialize all variables 
  * @param name Name of menu item
  * @param calories Calories of menu item
  * @param sodium Sodium of menu item
  * @param totalSugars Sugars of menu item
  * @param carbs Carbs of menu item
  * @param hasEgg Egg of menu item
  * @param hasMilk Milk of menu item
  * @param hasSoy Soy of menu item
  * @param hasWheat Wheat of menu item
  * @param hasPeanuts Peanuts of menu item
  * @param isNotVegetarian Vegetarian status of menu item
  */
 public MenuItem(String name, double calories, double sodium, double totalSugars, double carbs, boolean hasEgg, boolean hasMilk, boolean hasSoy, boolean hasWheat, boolean hasPeanuts, boolean isNotVegetarian) {
     
     this.name = name;
     this.calories = calories;
     this.sodium = sodium;
     this.totalSugars = totalSugars;
     this.carbs = carbs;

     this.hasEgg = hasEgg;
     this.hasMilk = hasMilk;
     this.hasSoy = hasSoy;
     this.hasWheat = hasWheat;
     this.hasPeanuts = hasPeanuts;
     this.isNotVegetarian = isNotVegetarian;
 }

 /** Returns menu item name 
  * @return menu item name
  */
 public String getName() {
     return name;
 }

 /** Returns menu item boolean properties 
  * @param inp String to be looked for
  * @return boolean value based off of string looked for
  */
 public boolean returnBoolProperty(String inp) 
 {
     if (inp.equals("veg"))
     {
         return isNotVegetarian;
     }
     else if (inp.equals("milk"))
     {
         return hasMilk;
     }
     else if (inp.equals("wheat"))
     {
         return hasWheat;
     }
     else if (inp.equals("soy"))
     {
         return hasSoy;
     }
     else if (inp.equals("egg"))
     {
         return hasEgg;
     }
     else if (inp.equals("pea"))
     {
         return hasPeanuts;
     }

     return true;
 }

 /** Returns menu item number properties 
  * @param inp String to be looked for
  * @return Number from a specific category
  */
 public double returnNumProperty(String inp)
 {
     if (inp.equals("cal"))
     {
         return calories;
     }
     else if (inp.equals("sod"))
     {
         return sodium;
     }
     else if (inp.equals("sug"))
     {
         return totalSugars;
     }
     else if (inp.equals("carb"))
     {
         return carbs;
     }

     return 0.0;
 }

 /** Formatter string */
 public String toString() {
     return name + "\nCalories: " + calories + " Sodium: " + sodium + " Total Sugars: " + totalSugars + " Carbs: " + carbs + "\nEgg: " + hasEgg + " Milk: " + hasMilk + " Soy: " + hasSoy + " Wheat: " + hasWheat + " Peanuts: " + hasPeanuts + "\nNon-Vegetarian: " + isNotVegetarian + "\n";
 }
}
