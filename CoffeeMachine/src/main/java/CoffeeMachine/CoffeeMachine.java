package CoffeeMachine;

enum State {
    MENU, BUYCOFFEE, FILL
}

enum Ingredient {
    WATER, MILK, COFFEE, CUPS
}

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;
    private State states;
    private String state;
    private String ingredient;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.cups = 9;
        this.money = 550;
        this.state = State.MENU.name();
        this.ingredient = Ingredient.WATER.name();
    }

    public void processInput(String input) {
        switch (this.state) {
            case "MENU":
                switch (input) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                        this.state = State.BUYCOFFEE.name();
                        break;
                    case "fill":
                        System.out.println("Write how many ml of water do you want to add: ");
                        this.state = State.FILL.name();
                        break;
                    case "take":
                        System.out.println("I gave you $" + this.money);
                        this.money = 0;
                        break;
                    case "remaining":
                        getTotals();
                }
                break;
            case "BUYCOFFEE":
                switch (input) {
                    case "1":
                        if (this.water >= 250 && this.coffee >= 16 && this.cups >= 1) {
                            System.out.println("I have enough resources, making you a coffee!");
                            this.water -= 250;
                            this.coffee -= 16;
                            this.money += 4;
                            this.cups--;
                        } else if (this.water < 250) {
                            System.out.println("Sorry, not enough water!");
                        } else {
                            System.out.println("Sorry, not enough coffee!");
                        }
                        break;
                    case "2":
                        if (this.water >= 350 && this.milk >= 75 && this.coffee >=20) {
                            System.out.println("I have enough resources, making you a coffee!");
                            this.water -= 350;
                            this.milk -= 75;
                            this.coffee -= 20;
                            this.money += 7;
                            this.cups--;
                        } else if (this.water < 350) {
                            System.out.println("Sorry, not enough water!");
                        } else if (this.milk < 75) {
                            System.out.println("Sorry, not enough milk!");
                        } else {
                            System.out.println("Sorry, not enough coffee!");
                        }
                        break;
                    case "3":
                        if (this.water >= 200 && this.milk >= 100 && this.coffee >= 12) {
                            System.out.println("I have enough resources, making you a coffee!");
                            this.water -= 200;
                            this.milk -= 100;
                            this.coffee -= 12;
                            this.money += 6;
                            this.cups--;
                        } else if (this.water < 200) {
                            System.out.println("Sorry, not enough water!");
                        } else if (this.milk < 100) {
                            System.out.println("Sorry, not enough milk!");
                        } else {
                            System.out.println("Sorry, not enough coffee!");
                        }
                        break;
                    case "back":
                        this.state = State.MENU.name();
                }
                this.state = State.MENU.name();
                break;
            case "FILL":
                switch (ingredient) {
                    case "WATER":
                        this.water += Integer.valueOf(input);
                        System.out.println("Write how many ml of milk do you want to add: ");
                        this.ingredient = Ingredient.MILK.name();
                        break;
                    case "MILK":
                        this.milk += Integer.valueOf(input);
                        System.out.println("Write how many grams of coffee beans do you want to add: ");
                        this.ingredient = Ingredient.COFFEE.name();
                        break;
                    case "COFFEE":
                        this.coffee += Integer.valueOf(input);
                        System.out.println("Write how many disposable cups of coffee do you want to add: ");
                        this.ingredient = Ingredient.CUPS.name();
                        break;
                    case "CUPS":
                        cups += Integer.valueOf(input);
                        this.ingredient = Ingredient.WATER.name();
                        this.state = State.MENU.name();
                }
        }
    }

    public void getTotals() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffee + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println("$" + this.money + " of money");
    }

    public String getState() {
        return this.state;
    }
}
