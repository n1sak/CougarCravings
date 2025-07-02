import java.util.ArrayList;

/** The Filter Class filters out the possible food items based on the user's requests */
public class Filters {

    /** Full List of Menu and Properties */
    private MenuItem[] fullList;
    
    /** List of Filtered Menu and Properties */
    private ArrayList<MenuItem> filteredList;
    
    /** Names of all menu items */
    private String[] nameList;
    /** Amount of calories in all menu items */
    private double[] calList;
    /** Amount of sodium in all menu items */
    private double[] sodiumList;
    /** Amount of carbs in all menu items */
    private double[] carbList;
    /** Amount of sugar in all menu items */
    private double[] sugarList;
    
    /** List of Simplified Item Names */
    private String[] innerNameList = {"Chicken", "Chili", "Waffle", "Sandwich", "Wrap", "Rice", "Salad", "Roll", "Fries"};
    /** Calories of the listed item names */
    private double[] innerCalList = {250.0, 287.0, 216.0, 193.0, 393.0, 126.0, 79.0, 50.0, 205.0};

    /** List of whether or not all menu items contains egg */
    private boolean[] eggList;
    /** List of whether or not all menu items contains milk */
    private boolean[] milkList;
    /** List of whether or not all menu items contains soy */
    private boolean[] soyList;
    /** List of whether or not all menu items contains wheat */
    private boolean[] wheatList;
    /** List of whether or not all menu items contains peanuts */
    private boolean[] peanutList;
    /** List of whether or not all menu items contains vegetables */
    private boolean[] vegList;
    
    /** Filters Constructor */
    public Filters() {

        // --- list of all meals and relevant filters ---

        this.nameList = new String[] {"Chicken Chili Verde", "Chicken Nuggets w/ Fries & Dinner Roll", "Pizza, Cheese - Homemade", "Yogurt Fruit Cup-strawberries", "Penne Pasta w/ MeatSauce", "Cheeseburger on a bun (Steak Burger Patty) Delux", "Sesame Chicken Salad w/ Roll", "Penne Pasta, Meatless", "Beef Dunkers, Teriyaki w/brown rice & Broccoli", "Chicken, Spicy Sandwich Deluxe", "Salad, Taco beef", "Pizza, Veggie", "Chicken and Waffle (Maple)", "Fish Filet on Bun w/Tarter Sauce", "Wrap, Chicken Caesar", "Garlic Noodles w/ Tofu", "Chicken,Mand Orange w/Rice", "Beef Rib Grill Honey BBQ Sandwich-only"};

        this.calList = new double[] {0.0, 0.0, 316.0, 382.0, 372.0, 385.0, 0.0, 392.0, 426.0, 0.0, 679.0, 323.0, 0.0, 420.0, 0.0, 610.0, 0.0, 360.0};

        for (int i = 0; i < 18; i++) {
            if (nameList[i].indexOf("Chicken") != -1) {
                calList[i] = calculateChickenCalories(nameList[i]);
            }
        }

        this.sodiumList = new double[] {2228.0, 515.0, 622.0, 252.0, 513.0, 986.0, 471.0, 496.0, 1281.0, 897.0, 1336.0, 624.0, 547.0, 1009.0, 1411.0, 2194.0, 224.0, 870.0};

        this.carbList = new double[] {76.63, 65.85, 32.22, 75.62, 45.53, 33.69, 39.22, 46.03, 54.67, 50.69, 50.25, 33.83, 46.89, 46.28, 62.00, 79.44, 65.43, 44.00};

        this.sugarList = new double[] {5.0, 4.0, 5.0, 30.0, 4.0, 5.0, 7.0, 4.0, 5.0, 6.0, 3.0, 6.0, 13.0, 5.0, 3.0, 6.0, 9.0, 14.0}; 
        
        this.eggList = new boolean[] {false, false, false, false, false, false, false, false, false, false, true, false, true, true, true, false, false, false};

        this.milkList = new boolean[] {false, false, true, true, true, true, false, true, false, false, true, true, true, false, true, true, false, true};

        this.soyList = new boolean[] {true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        this.wheatList = new boolean[] {true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true};

        this.peanutList = new boolean[]{false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false};

        this.vegList = new boolean[] {true, true, false, false, true, true, true, false, true, true, true, false, true, true, true, false, true, true};

        // -------- end of setup --------

        // initializing the full menu list, unfiltered
        this.fullList = new MenuItem[nameList.length];
        for (int i = 0; i < nameList.length; i++)
        {
            fullList[i] = new MenuItem(nameList[i], calList[i], sodiumList[i], carbList[i], sugarList[i], eggList[i], milkList[i], soyList[i], wheatList[i], peanutList[i], vegList[i]);
        }

        this.filteredList = new ArrayList<MenuItem>();
        for (MenuItem item: this.fullList)
        {
            this.filteredList.add(item);
        }
    }
    
    /** Calculates the amount of calories in a chicken menu item. 
     * @param s String to be inputted
     * @return chicken calories
     */
    public double calculateChickenCalories(String s) {
        for (int i = 0; i < 9; i++) {
            int ind = s.indexOf(innerNameList[i]);
            if (ind != -1) {
                return innerCalList[i] + calculateChickenCalories(s.substring(0, ind) + s.substring(ind + innerNameList[i].length()));
            }
        }
        return 0;
    }

    /** Outputs the full menu to standard output. */
    public void printFullMenu() {
        for (MenuItem item: fullList) {
            System.out.println(item);
        }
    }

    /** Returns full list of menu items 
     * @return Full List of Menu Items
     */
    public MenuItem[] getFullList() 
    {
        return fullList;
    }

    /** Returns filtered list of menu items based on current filter 
     * @return Full List of Menu Items
      */
    public ArrayList<MenuItem> getFilteredList()
    {
        return filteredList;
    }

    /** Filters menu list based on inputted list of filters 
     * @param boolFilters Filtered Menu Items
     * @param specification Specified category to filter by
     * @param maxFilters The max amount of filters used
     * @param max Maximum amount of items
     * @param currFilters Current Filters
     * */
    public void filter(ArrayList<String> boolFilters, boolean[] specification, ArrayList<String> maxFilters, double[] max, ArrayList<String> currFilters) {

        ArrayList<MenuItem> filtered = new ArrayList<MenuItem>();

        for (MenuItem item: fullList) {
            filtered.add(item);
        }

        for (String filter: currFilters)
        {
            if (boolFilters.indexOf(filter) != -1)
            {
                int index = boolFilters.indexOf(filter);
                filtered = filterBool(filtered, specification[index], boolFilters.get(index));
            }
            else if (maxFilters.indexOf(filter) != -1)
            {
                int index = maxFilters.indexOf(filter);
                filtered = filterNum(filtered, max[index], maxFilters.get(index));
            }
        }
        
        this.filteredList = filtered;
    }
    
    /** Filters menu items by using booleans 
     * @param filteredList The filtered list
     * @param specification Specified category to filter by
     * @param item The item to be looked for
     * @return Filtered out filteredList
     */
    private ArrayList<MenuItem> filterBool(ArrayList<MenuItem> filteredList, boolean specification, String item) {

        for (int i = filteredList.size() - 1; i >= 0; i--)
        {
            if (filteredList.get(i).returnBoolProperty(item) != specification) {
                filteredList.remove(i);
            }
        }

        return filteredList;
    }
    
    /** Filters menu items by using numbers 
     * @param filteredList The filtered list
     * @param max The max amount of nutrition somebody is looking for
     * @param item The item to be looked for
     * @return Filtered out filteredList
     */
    private ArrayList<MenuItem> filterNum(ArrayList<MenuItem> filteredList, double max, String item) {

        for (int i = filteredList.size() - 1; i >= 0; i--)
        {
        	
            if (filteredList.get(i).returnNumProperty(item) > max) {
                filteredList.remove(i);
            }
        }

        return filteredList;
    }
}