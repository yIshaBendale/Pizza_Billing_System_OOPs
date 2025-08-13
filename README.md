# Pizza_Billing_System_OOPs

A comprehensive Java-based pizza billing system demonstrating Object-Oriented Programming concepts. This project simulates a real-world pizza restaurant billing system with interactive menu selection, customization options, and professional bill generation.
Features

Interactive Menu System: User-friendly console-based interface
Pizza Varieties: 4 Vegetarian and 4 Non-Vegetarian pizza options
Size Options: Small, Medium, Large, and Extra Large with dynamic pricing
Customization: Custom toppings selection and extra cheese option
Order Types: Dine-in and Take-away with appropriate charges
Professional Billing: Detailed itemized bills with tax calculations
Multiple Orders: Add multiple pizzas to a single order

Pizza Menu
Vegetarian Pizzas

Margherita - Rs.200
Veggie Supreme - Rs.280
Paneer Makhani - Rs.320
Corn & Cheese - Rs.250

Non-Vegetarian Pizzas

Chicken Tikka - Rs.380
Pepperoni Classic - Rs.400
Chicken BBQ - Rs.420
Meat Lovers - Rs.480

Size Pricing

Small: 30% off base price
Medium: Base price
Large: 40% extra
Extra Large: 80% extra

OOP Concepts Demonstrated
1. Inheritance

Pizza (Abstract base class)
VegPizza extends Pizza
NonVegPizza extends Pizza
Topping (Abstract base class)
VegTopping extends Topping
NonVegTopping extends Topping

2. Polymorphism

Method overriding in pizza subclasses
Interface implementations for different strategies
Runtime polymorphism through abstract methods

3. Abstraction

Abstract classes: Pizza, Topping
Interfaces: PricingStrategy, MenuManager, BillGenerator
Abstract methods enforcing implementation in subclasses

4. Encapsulation

Private fields with public getter/setter methods
Data hiding and controlled access to object state
Proper access modifiers usage

5. Additional Concepts

Composition: Order contains OrderItem, OrderItem contains Pizza
Strategy Pattern: Different pricing strategies
Template Method Pattern: Pizza display information
Enum Usage: OrderType with associated behavior

Technical Specifications

Language: Java
Paradigm: Object-Oriented Programming
Interface: Console-based
Input Validation: Comprehensive error handling
Tax Calculation: 18% GST implementation
Packaging: Additional charges for take-away orders

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

#Class Hierarchy
PizzaBillGenerator (Main Class)
├── Pizza (Abstract)
│   ├── VegPizza
│   └── NonVegPizza
├── Topping (Abstract)
│   ├── VegTopping
│   └── NonVegTopping
├── Order
├── OrderItem
├── OrderType (Enum)
├── PricingStrategy (Interface)
│   └── RegularPricing
├── MenuManager (Interface)
│   └── PizzaMenuManager
└── BillGenerator (Interface)
    └── ConsoleBillGenerator
