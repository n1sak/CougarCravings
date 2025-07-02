import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.*;
import javax.swing.ImageIcon;
import java.awt.Image;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MenuSearch extends JFrame implements ActionListener
{
    private boolean[] filterValues;
    private double[] maxValues;
    private String[] filterItems;
    private String[] maxItems;
    private ArrayList<String> filterItemsList;
    private ArrayList<String> maxItemsList;
    private ArrayList<String> filterItemsListReal;
    private ArrayList<String> maxItemsListReal;

    private JList<String> filters;
    private JScrollPane filterScroll;
    private JLabel slogan;
    
    private JLabel calLabel  = new JLabel(" Max Calories:  ");
    private JLabel sugLabel  = new JLabel(" Max Sugar (g): ");
    private JLabel salLabel  = new JLabel(" Max Salt (mg):  ");
    private JLabel carLabel  = new JLabel(" Max Carbs (g): ");

    private JTextField maxCal = new JTextField(7);
    private JTextField maxSug = new JTextField(7);
    private JTextField maxSalt = new JTextField(7);
    private JTextField maxCarb = new JTextField(7);

    private JLabel searchLabel = new JLabel("Search: ");
    private JTextField searchBar = new JTextField(20);
    private Box searchBox = new Box(BoxLayout.X_AXIS);
    
    private Box calBox = new Box(BoxLayout.X_AXIS);
    private Box sugBox = new Box(BoxLayout.X_AXIS);
    private Box salBox = new Box(BoxLayout.X_AXIS);
    private Box carBox = new Box(BoxLayout.X_AXIS);
    
    private Box inpBox = new Box(BoxLayout.Y_AXIS);
    private Box allBox = new Box(BoxLayout.X_AXIS);

    private Box sloganBox = new Box(BoxLayout.Y_AXIS);

    private Filters menuFilters;
    private ArrayList<MenuItem> currentMenuItems;
    private ArrayList<String> currentFilters;
    private MenuItem[] fullMenuList;

    private JTextField foodName = new JTextField(20);
    private Box foodDisplayBox = new Box(BoxLayout.Y_AXIS);

    private Box caloriesBox = new Box(BoxLayout.X_AXIS);
    private JLabel caloriesLabel = new JLabel("Calories:        ");
    private JTextField caloriesTF = new JTextField(5);

    private Box sugarBox = new Box(BoxLayout.X_AXIS);
    private JLabel sugarLabel = new JLabel("Sugar (g):       ");
    private JTextField sugarTF = new JTextField(5);

    private Box saltBox = new Box(BoxLayout.X_AXIS);
    private JLabel saltLabel = new JLabel("Salt (mg):         ");
    private JTextField saltTF = new JTextField(5);

    private Box carbsBox = new Box(BoxLayout.X_AXIS);
    private JLabel carbsLabel = new JLabel("Carbs (g):       ");
    private JTextField carbsTF = new JTextField(5);

    private Box eggsBox = new Box(BoxLayout.X_AXIS);
    private JLabel eggsLabel = new JLabel("Eggless:          ");
    private JTextField eggsTF = new JTextField(5);

    private Box dairyBox = new Box(BoxLayout.X_AXIS);
    private JLabel dairyLabel = new JLabel("Dairy Free:     ");
    private JTextField dairyTF = new JTextField(5);

    private Box soyBox = new Box(BoxLayout.X_AXIS);
    private JLabel soyLabel = new JLabel("Soy Free:        ");
    private JTextField soyTF = new JTextField(5);

    private Box glutenBox = new Box(BoxLayout.X_AXIS);
    private JLabel glutenLabel = new JLabel("Gluten Free:   ");
    private JTextField glutenTF = new JTextField(5);

    private Box peanutBox = new Box(BoxLayout.X_AXIS);
    private JLabel peanutLabel = new JLabel("Peanut Free:  ");
    private JTextField peanutTF = new JTextField(5);

    private Box vegBox = new Box(BoxLayout.X_AXIS);
    private JLabel vegetarianLabel = new JLabel("Vegetarian:    ");
    private JTextField vegetarianTF = new JTextField(5);

    private int index = 0;
    private JButton increment = new JButton("next");
    private JButton decrement = new JButton("prev");
    private Box totalBox = new Box(BoxLayout.X_AXIS);
    
    // Initializing all of the GUI elements and storing the list of filters
    public MenuSearch()
    {
        setTitle("Cougar Cravings");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        // Menu filters instantiation
        this.menuFilters = new Filters();
        this.currentMenuItems = menuFilters.getFilteredList();
        this.fullMenuList = menuFilters.getFullList();

        // Positioning
        this.setSize(350, 750);
        this.setLocation(225, 0);

        // Setting background color
        String hexColor = "#006d75";
        this.getContentPane().setBackground(Color.decode(hexColor));

        // Adding List Components
        setLayout(new FlowLayout());

        filterItems = new String[] {
            "No Eggs",
            "Dairy Free",
            "Soy Free",
            "Gluten Free",
            "Peanut Free",
            "Vegetarian"
        };

        filterItemsList = new ArrayList<String>();
        for (String item: filterItems)
        {
            filterItemsList.add(item);
        }

        filterItemsListReal = new ArrayList<String>();
        filterItemsListReal.add("egg");
        filterItemsListReal.add("milk");
        filterItemsListReal.add("soy");
        filterItemsListReal.add("wheat");
        filterItemsListReal.add("pea");
        filterItemsListReal.add("veg");

        filterValues = new boolean[] {true, true, true, true, true, true};

        filters = new JList<String>(filterItems);
        filters.setVisibleRowCount(filterItems.length);

        filterScroll = new JScrollPane(filters);
        filterScroll.setMaximumSize(new Dimension(200, 130));

        filters.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );

        // updating current list of filters when list is edited
        filters.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false)
                {
                    filterValues = new boolean[] {true, true, true, true, true, true};
				
                    List<String> selectedFilters = filters.getSelectedValuesList();

                    for (String filter: selectedFilters)
                    {
                        int index = filterItemsList.indexOf(filter);
                        filterValues[index] = false;
                    }
                    
                    updateFilters();
                }
			}
		});
        
        // adding text inputs
        maxValues = new double[] {
            Integer.MAX_VALUE,
            Integer.MAX_VALUE,
            Integer.MAX_VALUE,
            Integer.MAX_VALUE
        };

        maxItems = new String[] {
            "cal",
            "sug",
            "sod",
            "carb"
        };

        calLabel.setForeground(Color.WHITE);
        salLabel.setForeground(Color.WHITE);
        sugLabel.setForeground(Color.WHITE);
        carLabel.setForeground(Color.WHITE);
        searchLabel.setForeground(Color.WHITE);

        maxItemsList = new ArrayList<String>();
        maxItemsList.add("cal");
        maxItemsList.add("sug");
        maxItemsList.add("sod");
        maxItemsList.add("carb");
        
        maxItemsListReal = new ArrayList<String>();
        maxItemsListReal.add("cal");
        maxItemsListReal.add("sug");
        maxItemsListReal.add("sod");
        maxItemsListReal.add("carb");

        maxCal.addActionListener(this);
        maxSug.addActionListener(this);
        maxSalt.addActionListener(this);
        maxCarb.addActionListener(this);
        
        maxCal.setActionCommand("cal");
        maxSug.setActionCommand("sug");
        maxSalt.setActionCommand("sod");
        maxCarb.setActionCommand("carb");

        maxCal.setToolTipText("Enter a #");
        maxSug.setToolTipText("Enter a #");
        maxSalt.setToolTipText("Enter a #");
        maxCarb.setToolTipText("Enter a #");

        // formatting text inputs on screen
        maxCal.setMaximumSize(new Dimension(200, 20));
        maxSalt.setMaximumSize(new Dimension(200, 20));
        maxCarb.setMaximumSize(new Dimension(200, 20));
        maxSug.setMaximumSize(new Dimension(200, 20));
        
        calBox.add(calLabel, BorderLayout.EAST);
        maxCal.setHorizontalAlignment(JLabel.CENTER);
        maxCal.setEditable(true);
        maxCal.setEnabled(true);
        calBox.add(maxCal, BorderLayout.EAST);
        
        sugBox.add(sugLabel, BorderLayout.EAST);
        maxSug.setHorizontalAlignment(JLabel.CENTER);
        maxSug.setEditable(true); 
        sugBox.add(maxSug, BorderLayout.EAST);
        
        salBox.add(salLabel, BorderLayout.EAST);
        maxSalt.setHorizontalAlignment(JLabel.CENTER);
        maxSalt.setEditable(true); 
        salBox.add(maxSalt, BorderLayout.EAST);
        
        carBox.add(carLabel, BorderLayout.EAST);
        maxCarb.setHorizontalAlignment(JLabel.CENTER);
        maxCarb.setEditable(true); 
        carBox.add(maxCarb, BorderLayout.EAST);
        
        inpBox.add(calBox, BorderLayout.EAST);
        inpBox.add(Box.createRigidArea(new Dimension(1, 15)));
        inpBox.add(sugBox, BorderLayout.EAST);
        inpBox.add(Box.createRigidArea(new Dimension(1, 15)));
        inpBox.add(salBox, BorderLayout.EAST);
        inpBox.add(Box.createRigidArea(new Dimension(1, 15)));
        inpBox.add(carBox, BorderLayout.EAST);
        inpBox.add(Box.createRigidArea(new Dimension(1, 20)));
        
        allBox.add(filterScroll);
        allBox.add(Box.createRigidArea(new Dimension(20, 20)));
        allBox.add(inpBox, BorderLayout.EAST);

        // adding components
        ImageIcon sloganIcon = new ImageIcon("Slogan.png");
        Image sloganImage = sloganIcon.getImage();
        Image resizedSlogan = sloganImage.getScaledInstance(240, 180, Image.SCALE_SMOOTH);
        slogan = new JLabel(new ImageIcon(resizedSlogan));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(slogan, BorderLayout.NORTH);

        // search bar
        searchBar.addActionListener(this);
        searchBar.setActionCommand("Search");

        searchBox.add(searchLabel);
        searchBox.add(searchBar);

        add(searchBox);
        add(allBox, BorderLayout.SOUTH);

        // dislpaying foods
        foodName.setEditable(false); 

        caloriesLabel.setForeground(Color.WHITE);
        caloriesBox.add(caloriesLabel);
        caloriesTF.setEditable(false); 
        caloriesTF.setHorizontalAlignment(JLabel.CENTER);
        caloriesBox.add(caloriesTF);

        sugarLabel.setForeground(Color.WHITE);
        sugarBox.add(sugarLabel);
        sugarTF.setEditable(false); 
        sugarTF.setHorizontalAlignment(JLabel.CENTER);
        sugarBox.add(sugarTF);

        saltLabel.setForeground(Color.WHITE);
        saltBox.add(saltLabel);
        saltTF.setEditable(false); 
        saltTF.setHorizontalAlignment(JLabel.CENTER);
        saltBox.add(saltTF);

        carbsLabel.setForeground(Color.WHITE);
        carbsBox.add(carbsLabel);
        carbsTF.setEditable(false); 
        carbsTF.setHorizontalAlignment(JLabel.CENTER);
        carbsBox.add(carbsTF);

        eggsLabel.setForeground(Color.WHITE);
        eggsBox.add(eggsLabel);
        eggsTF.setEditable(false); 
        eggsTF.setHorizontalAlignment(JLabel.CENTER);
        eggsBox.add(eggsTF);

        dairyLabel.setForeground(Color.WHITE);
        dairyBox.add(dairyLabel);
        dairyTF.setEditable(false);
        dairyTF.setHorizontalAlignment(JLabel.CENTER);
        dairyBox.add(dairyTF);

        soyLabel.setForeground(Color.WHITE);
        soyBox.add(soyLabel);
        soyTF.setEditable(false); 
        soyTF.setHorizontalAlignment(JLabel.CENTER);
        soyBox.add(soyTF);

        glutenLabel.setForeground(Color.WHITE);
        glutenBox.add(glutenLabel);
        glutenTF.setEditable(false); 
        glutenTF.setHorizontalAlignment(JLabel.CENTER);
        glutenBox.add(glutenTF);

        peanutLabel.setForeground(Color.WHITE);
        peanutBox.add(peanutLabel);
        peanutTF.setEditable(false); 
        peanutTF.setHorizontalAlignment(JLabel.CENTER);
        peanutBox.add(peanutTF);

        vegetarianLabel.setForeground(Color.WHITE);
        vegBox.add(vegetarianLabel);
        vegetarianTF.setEditable(false); 
        vegetarianTF.setHorizontalAlignment(JLabel.CENTER);
        vegBox.add(vegetarianTF);

        foodName.setHorizontalAlignment(JLabel.CENTER);

        add(Box.createRigidArea(new Dimension(1, 5)));
        add(foodName);
        foodDisplayBox.add(Box.createRigidArea(new Dimension(1, 7)));
        foodDisplayBox.add(caloriesBox);
        foodDisplayBox.add(sugarBox);
        foodDisplayBox.add(saltBox);
        foodDisplayBox.add(carbsBox);
        foodDisplayBox.add(eggsBox);
        foodDisplayBox.add(dairyBox);
        foodDisplayBox.add(soyBox);
        foodDisplayBox.add(glutenBox);
        foodDisplayBox.add(peanutBox);
        foodDisplayBox.add(vegBox);

        increment.addActionListener(this);
        decrement.addActionListener(this);

        increment.setActionCommand("Right");
        decrement.setActionCommand("Left");

        totalBox.add(decrement);
        totalBox.add(Box.createRigidArea(new Dimension(15, 1)));
        totalBox.add(foodDisplayBox);
        totalBox.add(Box.createRigidArea(new Dimension(15, 1)));
        totalBox.add(increment);

        add(totalBox);

        updateDisplay();
    }

    // returns the currently selected filters
    public boolean[] getFilterValues()
    {
        return this.filterValues;
    }

    // returns the current maximum values for numerical variables
    public double[] getMaxValues()
    {
        return this.maxValues;
    }

    public void updateDisplay()
    {
        if (this.currentMenuItems.size() > 0)
        {
            this.index = this.index % this.currentMenuItems.size();
            
            if (this.index == -1)
            {
            	this.index = this.currentMenuItems.size() - 1;
            }

            MenuItem currFood = this.currentMenuItems.get(this.index);

            String name = currFood.getName();

            if (name.length() > 32)
            {
                name = name.substring(0, 30);
                name += "...";
            }

            foodName.setText(name);
            caloriesTF.setText("" + currFood.returnNumProperty("cal"));
            sugarTF.setText("" + currFood.returnNumProperty("sug"));
            saltTF.setText("" + currFood.returnNumProperty("sod"));
            carbsTF.setText("" + currFood.returnNumProperty("carb"));
            eggsTF.setText("" + !currFood.returnBoolProperty("egg"));
            dairyTF.setText("" + !currFood.returnBoolProperty("milk"));
            soyTF.setText("" + !currFood.returnBoolProperty("soy"));
            glutenTF.setText("" + !currFood.returnBoolProperty("wheat"));
            peanutTF.setText("" + !currFood.returnBoolProperty("pea"));
            vegetarianTF.setText("" + !currFood.returnBoolProperty("veg"));
        }
        else
        {
            foodName.setText("None!");
            caloriesTF.setText("N/A");
            sugarTF.setText("N/A");
            saltTF.setText("N/A");
            carbsTF.setText("N/A");
            eggsTF.setText("N/A");
            dairyTF.setText("N/A");
            soyTF.setText("N/A");
            glutenTF.setText("N/A");
            peanutTF.setText("N/A");
            vegetarianTF.setText("N/A");
        }
    }

    // displaying the currently selected filters
    public void outputFilterValues()
    {
        for (int i = 0; i < filterItems.length; i++)
        {
            System.out.println(filterItems[i] + ": " + filterValues[i]);
        }
        System.out.println("\n");
        for (int i = 0; i < maxValues.length; i++)
        {
            System.out.println(maxItemsList.get(i) + ": " + maxValues[i]);
        }
        System.out.println("\n");
    }

    public void outputCurrentValues()
    {
        for (MenuItem item: currentMenuItems)
        {
            System.out.println(item);
        }
        System.out.println("\n");
    }

    public void actionPerformed(ActionEvent textEntry)  
    {
        if (textEntry.getActionCommand().equals("Search"))
        {
            this.currentMenuItems = searchQuery(searchBar.getText());
            this.index = 0;
            this.updateDisplay();
        }
        else if (textEntry.getActionCommand().equals("Right"))
        {
            this.index++;
            updateDisplay();
        }
        else if (textEntry.getActionCommand().equals("Left"))
        {
            this.index--;
            updateDisplay();
        }
        else
        {
            updateMaxValues(textEntry.getActionCommand());
        }
        repaint();
    }

    public ArrayList<MenuItem> searchQuery(String query)
    {
        ArrayList<MenuItem> tempList = new ArrayList<MenuItem>();
        
        for (MenuItem item: this.menuFilters.getFilteredList())
        {
            tempList.add(item);
        }

        for (int i = tempList.size() - 1; i >= 0; i--)
        {
            if (tempList.get(i).getName().toLowerCase().indexOf(query.toLowerCase()) == -1)
            {
                tempList.remove(i);
            }
        }

        return tempList;
    }

    public void updateMaxValues(String command)
    {
        String str;

        if (command.equals("cal")) {
            str = maxCal.getText();
            if (str.equals(""))
            {
            	maxCal.setText("Enter a #");
            }
        } else if (command.equals("sug")) {
            str = maxSug.getText();
            if (str.equals(""))
            {
            	maxSug.setText("Enter a #");
            }
        } else if (command.equals("sod")) {
            str = maxSalt.getText();
            if (str.equals(""))
            {
            	maxSalt.setText("Enter a #");
            }
        } else {
            str = maxCarb.getText();
            if (str.equals(""))
            {
            	maxCarb.setText("Enter a #");
            }
        }

        try {

            double num = Double.parseDouble(str);
            int index = maxItemsList.indexOf(command);
            maxValues[index] = num;

        } catch (NumberFormatException e) {

            if (command.equals("cal")) {
                maxCal.setText("Enter a #");
            } else if (command.equals("sug")) {
                maxSug.setText("Enter a #");
            } else if (command.equals("sod")) {
                maxSalt.setText("Enter a #");
            } else {
                maxCarb.setText("Enter a #");
            }

            int index = maxItemsList.indexOf(command);
            maxValues[index] = Integer.MAX_VALUE;
        }

        updateFilters();
    }

    public void updateFilters()
    {
        this.currentFilters = new ArrayList<String>();

        for (int i = 0; i < this.filterValues.length; i++)
        {
            if (!filterValues[i])
            {
                this.currentFilters.add(this.filterItemsListReal.get(i));
            }
        }

        for (String maxItem: this.maxItems)
        {
            this.currentFilters.add(maxItem);
        }

        this.menuFilters.filter(this.filterItemsListReal, this.filterValues, this.maxItemsListReal, this.maxValues, this.currentFilters);

        this.currentMenuItems = this.menuFilters.getFilteredList();
        this.currentMenuItems = searchQuery(searchBar.getText());

        this.index = 0;
        this.updateDisplay();
    }
}