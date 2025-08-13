import java.util.*;

// Abstract base class demonstrating Abstraction and Inheritance
abstract class Pizza {
    protected String name;
    protected double basePrice;
    protected String category;
    protected List<String> defaultToppings;
    
    // Constructor
    public Pizza(String name, double basePrice, String category) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        this.defaultToppings = new ArrayList<>();
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract String getDescription();
    
    // Concrete methods
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public List<String> getDefaultToppings() { return new ArrayList<>(defaultToppings); }
    
    // Template method pattern
    public void displayInfo() {
        System.out.println("Pizza: " + name + " - Rs." + basePrice);
        System.out.println("Category: " + category);
        System.out.println("Description: " + getDescription());
    }
}

// Concrete class extending Pizza - Inheritance
class VegPizza extends Pizza {
    private boolean isJainFriendly;
    
    public VegPizza(String name, double basePrice, boolean isJainFriendly) {
        super(name, basePrice, "Vegetarian");
        this.isJainFriendly = isJainFriendly;
        initializeDefaultToppings();
    }
    
    private void initializeDefaultToppings() {
        defaultToppings.add("Cheese");
        defaultToppings.add("Tomato Sauce");
    }
    
    @Override
    public String getDescription() {
        return "Delicious vegetarian pizza" + (isJainFriendly ? " (Jain Friendly)" : "");
    }
    
    public boolean isJainFriendly() { return isJainFriendly; }
}

// Another concrete class extending Pizza - Inheritance
class NonVegPizza extends Pizza {
    private String meatType;
    
    public NonVegPizza(String name, double basePrice, String meatType) {
        super(name, basePrice, "Non-Vegetarian");
        this.meatType = meatType;
        initializeDefaultToppings();
    }
    
    private void initializeDefaultToppings() {
        defaultToppings.add("Cheese");
        defaultToppings.add("Tomato Sauce");
        defaultToppings.add(meatType);
    }
    
    @Override
    public String getDescription() {
        return "Mouth-watering non-vegetarian pizza with " + meatType;
    }
    
    public String getMeatType() { return meatType; }
}

// Interface for pricing strategies - Abstraction
interface PricingStrategy {
    double calculatePrice(double basePrice, String size);
    String getSizeDescription();
}

// Concrete implementation of pricing strategy - Polymorphism
class RegularPricing implements PricingStrategy {
    private static final Map<String, Double> SIZE_MULTIPLIERS = new HashMap<>();
    
    static {
        SIZE_MULTIPLIERS.put("Small", 0.7);
        SIZE_MULTIPLIERS.put("Medium", 1.0);
        SIZE_MULTIPLIERS.put("Large", 1.4);
        SIZE_MULTIPLIERS.put("Extra Large", 1.8);
    }
    
    @Override
    public double calculatePrice(double basePrice, String size) {
        return basePrice * SIZE_MULTIPLIERS.getOrDefault(size, 1.0);
    }
    
    @Override
    public String getSizeDescription() {
        return "Regular Pricing: Small(30% off), Medium(base), Large(40% extra), XL(80% extra)";
    }
}

// Abstract class for toppings - Abstraction
abstract class Topping {
    protected String name;
    protected double price;
    
    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    
    public abstract boolean isVegetarian();
}

// Concrete topping classes - Inheritance and Polymorphism
class VegTopping extends Topping {
    public VegTopping(String name, double price) {
        super(name, price);
    }
    
    @Override
    public boolean isVegetarian() { return true; }
}

class NonVegTopping extends Topping {
    public NonVegTopping(String name, double price) {
        super(name, price);
    }
    
    @Override
    public boolean isVegetarian() { return false; }
}

// Enum for order types - Encapsulation
enum OrderType {
    DINE_IN("Dine In", 0.0),
    TAKE_AWAY("Take Away", 25.0);
    
    private final String displayName;
    private final double packagingCharge;
    
    OrderType(String displayName, double packagingCharge) {
        this.displayName = displayName;
        this.packagingCharge = packagingCharge;
    }
    
    public String getDisplayName() { return displayName; }
    public double getPackagingCharge() { return packagingCharge; }
}

// Class representing an order item - Encapsulation and Composition
class OrderItem {
    private Pizza pizza;
    private String size;
    private int quantity;
    private List<Topping> customToppings;
    private boolean extraCheese;
    private PricingStrategy pricingStrategy;
    
    public OrderItem(Pizza pizza, String size, int quantity, PricingStrategy pricingStrategy) {
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
        this.pricingStrategy = pricingStrategy;
        this.customToppings = new ArrayList<>();
        this.extraCheese = false;
    }
    
    // Getters and Setters - Encapsulation
    public Pizza getPizza() { return pizza; }
    public String getSize() { return size; }
    public int getQuantity() { return quantity; }
    public List<Topping> getCustomToppings() { return new ArrayList<>(customToppings); }
    public boolean hasExtraCheese() { return extraCheese; }
    
    public void addTopping(Topping topping) { customToppings.add(topping); }
    public void setExtraCheese(boolean extraCheese) { this.extraCheese = extraCheese; }
    
    public double calculateItemTotal() {
        double basePrice = pricingStrategy.calculatePrice(pizza.getBasePrice(), size);
        double toppingsPrice = customToppings.stream().mapToDouble(Topping::getPrice).sum();
        double extraCheesePrice = extraCheese ? 50.0 : 0.0;
        
        return (basePrice + toppingsPrice + extraCheesePrice) * quantity;
    }
}

// Main order class - Composition and Encapsulation
class Order {
    private List<OrderItem> items;
    private OrderType orderType;
    private Date orderDate;
    private static final double TAX_RATE = 0.18;
    
    public Order(OrderType orderType) {
        this.items = new ArrayList<>();
        this.orderType = orderType;
        this.orderDate = new Date();
    }
    
    public void addItem(OrderItem item) { items.add(item); }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public OrderType getOrderType() { return orderType; }
    public Date getOrderDate() { return orderDate; }
    
    public double calculateSubtotal() {
        return items.stream().mapToDouble(OrderItem::calculateItemTotal).sum();
    }
    
    public double calculatePackagingCharges() {
        return orderType.getPackagingCharge() * items.size();
    }
    
    public double calculateTax() {
        return (calculateSubtotal() + calculatePackagingCharges()) * TAX_RATE;
    }
    
    public double calculateTotal() {
        return calculateSubtotal() + calculatePackagingCharges() + calculateTax();
    }
}

// Interface for menu management - Abstraction
interface MenuManager {
    List<Pizza> getVegPizzas();
    List<Pizza> getNonVegPizzas();
    List<Topping> getVegToppings();
    List<Topping> getNonVegToppings();
}

// Concrete implementation of menu manager - Polymorphism
class PizzaMenuManager implements MenuManager {
    private List<Pizza> vegPizzas;
    private List<Pizza> nonVegPizzas;
    private List<Topping> vegToppings;
    private List<Topping> nonVegToppings;
    
    public PizzaMenuManager() {
        initializeMenu();
    }
    
    private void initializeMenu() {
        // Initialize Veg Pizzas - Only 4 options
        vegPizzas = Arrays.asList(
            new VegPizza("Margherita", 200.0, true),
            new VegPizza("Veggie Supreme", 280.0, false),
            new VegPizza("Paneer Makhani", 320.0, true),
            new VegPizza("Corn & Cheese", 250.0, true)
        );
        
        // Initialize Non-Veg Pizzas - Only 4 options
        nonVegPizzas = Arrays.asList(
            new NonVegPizza("Chicken Tikka", 380.0, "Chicken"),
            new NonVegPizza("Pepperoni Classic", 400.0, "Pepperoni"),
            new NonVegPizza("Chicken BBQ", 420.0, "Chicken"),
            new NonVegPizza("Meat Lovers", 480.0, "Mixed Meat")
        );
        
        // Initialize Veg Toppings
        vegToppings = Arrays.asList(
            new VegTopping("Extra Mushrooms", 30.0),
            new VegTopping("Bell Peppers", 25.0),
            new VegTopping("Onions", 20.0),
            new VegTopping("Tomatoes", 20.0),
            new VegTopping("Olives", 35.0),
            new VegTopping("Corn", 25.0)
        );
        
        // Initialize Non-Veg Toppings
        nonVegToppings = Arrays.asList(
            new NonVegTopping("Extra Chicken", 60.0),
            new NonVegTopping("Pepperoni", 50.0),
            new NonVegTopping("Sausage", 55.0),
            new NonVegTopping("Bacon", 65.0)
        );
    }
    
    @Override
    public List<Pizza> getVegPizzas() { return new ArrayList<>(vegPizzas); }
    
    @Override
    public List<Pizza> getNonVegPizzas() { return new ArrayList<>(nonVegPizzas); }
    
    @Override
    public List<Topping> getVegToppings() { return new ArrayList<>(vegToppings); }
    
    @Override
    public List<Topping> getNonVegToppings() { return new ArrayList<>(nonVegToppings); }
}

// Interface for bill generation - Abstraction
interface BillGenerator {
    void generateBill(Order order);
}

// Concrete implementation of bill generator - Polymorphism
class ConsoleBillGenerator implements BillGenerator {
    @Override
    public void generateBill(Order order) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("             PIZZA PALACE BILL");
        System.out.println("=".repeat(50));
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Order Type: " + order.getOrderType().getDisplayName());
        System.out.println("=".repeat(50));
        
        List<OrderItem> items = order.getItems();
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            displayItemDetails(item, i + 1);
        }
        
        displayBillSummary(order);
    }
    
    private void displayItemDetails(OrderItem item, int itemNumber) {
        System.out.printf("\nItem %d:\n", itemNumber);
        System.out.printf("  %s (%s) x %d\n", 
                         item.getPizza().getName(), item.getSize(), item.getQuantity());
        
        double basePrice = new RegularPricing().calculatePrice(
            item.getPizza().getBasePrice(), item.getSize()) * item.getQuantity();
        System.out.printf("  Base Price: Rs.%.2f\n", basePrice);
        
        if (!item.getCustomToppings().isEmpty()) {
            System.out.println("  Toppings:");
            for (Topping topping : item.getCustomToppings()) {
                double toppingTotal = topping.getPrice() * item.getQuantity();
                System.out.printf("    - %s: Rs.%.2f x %d = Rs.%.2f\n", 
                                topping.getName(), topping.getPrice(), 
                                item.getQuantity(), toppingTotal);
            }
        }
        
        if (item.hasExtraCheese()) {
            double extraCheeseTotal = 50.0 * item.getQuantity();
            System.out.printf("  Extra Cheese: Rs.%.2f x %d = Rs.%.2f\n", 
                            50.0, item.getQuantity(), extraCheeseTotal);
        }
        
        System.out.printf("  Item Total: Rs.%.2f\n", item.calculateItemTotal());
    }
    
    private void displayBillSummary(Order order) {
        System.out.println("\n" + "-".repeat(50));
        System.out.printf("Subtotal: Rs.%.2f\n", order.calculateSubtotal());
        
        if (order.getOrderType() == OrderType.TAKE_AWAY) {
            System.out.printf("Packaging Charges: Rs.%.2f\n", order.calculatePackagingCharges());
        }
        
        System.out.printf("GST (18%%): Rs.%.2f\n", order.calculateTax());
        System.out.println("=".repeat(50));
        System.out.printf("TOTAL AMOUNT: Rs.%.2f\n", order.calculateTotal());
        System.out.println("=".repeat(50));
        System.out.println("\nThank you for choosing Pizza Palace!");
        System.out.println("Enjoy your delicious pizza!");
        
        if (order.getOrderType() == OrderType.TAKE_AWAY) {
            System.out.println("\nYour order will be ready for pickup in 20-25 minutes.\n");
        } else {
            System.out.println("\nYour order will be served to your table shortly.\n");
        }
    }
}

// Main application class - Composition and Orchestration
public class PizzaBillGenerator {
    private MenuManager menuManager;
    private PricingStrategy pricingStrategy;
    private BillGenerator billGenerator;
    private Scanner scanner;
    
    public PizzaBillGenerator() {
        this.menuManager = new PizzaMenuManager();
        this.pricingStrategy = new RegularPricing();
        this.billGenerator = new ConsoleBillGenerator();
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        PizzaBillGenerator app = new PizzaBillGenerator();
        app.run();
    }
    
    public void run() {
        System.out.println(" ");
        System.out.println("=== WELCOME TO PIZZA PALACE ===");
        System.out.println("Your favorite pizza destination!");
        //System.out.println("================================\n");
        System.out.println("\n");
        
        OrderType orderType = getOrderType();
        Order order = new Order(orderType);
        
        boolean ordering = true;
        while (ordering) {
            processOrderItem(order);
            
            System.out.print("\nAdd more pizzas to order? (y/n): ");
            String moreItems = scanner.next().toLowerCase();
            ordering = moreItems.equals("y") || moreItems.equals("yes");
        }
        
        billGenerator.generateBill(order);
        scanner.close();
    }
    
    private OrderType getOrderType() {
        System.out.print("Dine in or Take away? \n1. Dine in\n2. Take away\n");
        int choice = getValidIntInput(1, 2);
        return (choice == 1) ? OrderType.DINE_IN : OrderType.TAKE_AWAY;
    }
    
    private void processOrderItem(Order order) {
        // First ask for category
        System.out.print("\nChoose pizza category \n1. Veg\n2. Non-Veg\n ");
        int category = getValidIntInput(1, 2);
        
        List<Pizza> availablePizzas = (category == 1) ? 
            menuManager.getVegPizzas() : menuManager.getNonVegPizzas();
        
        // Then display pizzas for selected category
        displaySelectedCategoryPizzas(availablePizzas, category);
        
        System.out.print("Choose pizza (1-4): ");
        int pizzaChoice = getValidIntInput(1, 4);
        Pizza selectedPizza = availablePizzas.get(pizzaChoice - 1);
        
        String size = getSize();
        
        System.out.print("\nEnter quantity: ");
        int quantity = getValidIntInput(1, 10);
        
        OrderItem item = new OrderItem(selectedPizza, size, quantity, pricingStrategy);
        
        addCustomizations(item, category == 1);
        order.addItem(item);
    }
    
    private void displaySelectedCategoryPizzas(List<Pizza> pizzas, int category) {
        String categoryName = (category == 1) ? "VEGETARIAN" : "NON-VEGETARIAN";
        System.out.println("\n------- " + categoryName + " PIZZAS -------\n");
        
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            System.out.printf("%d. %-20s - Rs.%.0f (Medium)\n", 
                             i + 1, pizza.getName(), pizza.getBasePrice());
        }
        System.out.println();
    }
    
    private String getSize() {
        System.out.println("\n------- SIZE OPTIONS -------\n");
        System.out.println("1. Small (30% off)");
        System.out.println("2. Medium (Base price)");
        System.out.println("3. Large (40% extra)");
        System.out.println("4. Extra Large (80% extra)");
        
        System.out.print("\nChoose size (1-4): ");
        int sizeChoice = getValidIntInput(1, 4);
        
        String[] sizes = {"Small", "Medium", "Large", "Extra Large"};
        return sizes[sizeChoice - 1];
    }
    
    private void addCustomizations(OrderItem item, boolean isVeg) {
        // Extra cheese
        System.out.print("\nAdd extra cheese? (y/n): ");
        String extraCheeseChoice = scanner.next().toLowerCase();
        item.setExtraCheese(extraCheeseChoice.equals("y") || extraCheeseChoice.equals("yes"));
        
        // Toppings
        System.out.print("\nAdd custom toppings? (y/n): ");
        String toppingsChoice = scanner.next().toLowerCase();
        
        if (toppingsChoice.equals("y") || toppingsChoice.equals("yes")) {
            addToppings(item, isVeg);
        }
    }
    
    private void addToppings(OrderItem item, boolean isVeg) {
        List<Topping> availableToppings = new ArrayList<>(menuManager.getVegToppings());
        if (!isVeg) {
            availableToppings.addAll(menuManager.getNonVegToppings());
        }
        
        System.out.println("\n------- AVAILABLE TOPPINGS -------");
        for (int i = 0; i < availableToppings.size(); i++) {
            Topping topping = availableToppings.get(i);
            System.out.printf("%d. %-20s - Rs.%.0f\n", 
                             i + 1, topping.getName(), topping.getPrice());
        }
        
        System.out.println("\nEnter topping numbers (comma-separated, e.g., 1,3,5) or 0 for no toppings:");
        String toppingsInput = scanner.next();
        
        if (!toppingsInput.equals("0")) {
            String[] toppingChoices = toppingsInput.split(",");
            for (String choice : toppingChoices) {
                try {
                    int toppingIndex = Integer.parseInt(choice.trim()) - 1;
                    if (toppingIndex >= 0 && toppingIndex < availableToppings.size()) {
                        item.addTopping(availableToppings.get(toppingIndex));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid topping choice: " + choice);
                }
            }
        }
    }
    
    private int getValidIntInput(int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d: ", min, max);
                scanner.next();
            }
        }
    }
}