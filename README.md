# Pizza_Billing_System_OOPs

A comprehensive Java-based pizza billing system demonstrating Object-Oriented Programming concepts. This project simulates a real-world pizza restaurant billing system with interactive menu selection, customization options, and professional bill generation.


Features:
1. Interactive Menu System: User-friendly console-based interface
2. Pizza Varieties: 4 Vegetarian and 4 Non-Vegetarian pizza options
3. Size Options: Small, Medium, Large, and Extra Large with dynamic pricing
4. Customization: Custom toppings selection and extra cheese option
5. Order Types: Dine-in and Take-away with appropriate charges
6. Professional Billing: Detailed itemized bills with tax calculations
7. Multiple Orders: Add multiple pizzas to a single order


Pizza Menu:

Vegetarian Pizzas:
1. Margherita - Rs.200
2. Veggie Supreme - Rs.280
3. Paneer Makhani - Rs.320
4. Corn & Cheese - Rs.250

Non-Vegetarian Pizzas:
1. Chicken Tikka - Rs.380
2. Pepperoni Classic - Rs.400
3. Chicken BBQ - Rs.420
4. Meat Lovers - Rs.480

Size Pricing:
1. Small: 30% off base price
2. Medium: Base price
3. Large: 40% extra
4. Extra Large: 80% extra

OOP Concepts Demonstrated:

1. Inheritance
   -Pizza (Abstract base class)
   -VegPizza extends Pizza
   -NonVegPizza extends Pizza
   -Topping (Abstract base class)
   -VegTopping extends Topping
   -NonVegTopping extends Topping

2. Polymorphism:
   -Method overriding in pizza subclasses
   -Interface implementations for different strategies
   -Runtime polymorphism through abstract methods

3. Abstraction:
   -Abstract classes: Pizza, Topping
   -Interfaces: PricingStrategy, MenuManager, BillGenerator
   -Abstract methods enforcing implementation in subclasses

4. Encapsulation:
   -Private fields with public getter/setter methods
   -Data hiding and controlled access to object state
   -Proper access modifiers usage


#How to Run:-
Prerequisites:

Java Development Kit (JDK) 8 or higher
Command line interface

Steps:

1. Download/Clone the repository
2. Save the code as "PizzaBillGenerator.java"
3. Compile the program:
    bash:
    javac PizzaBillGenerator.java
4: Run the program:
    bash
    java PizzaBillGenerator

