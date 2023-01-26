import java.time.LocalDate;
import java.util.*;
import java.lang.*;
interface adminFunctionalities{     //INTERFACE
    void loginAdmin();
    void addProduct();
    void deleteProduct();
    void addCategory();
    void deleteCategory();
}
class Admin implements adminFunctionalities{
    Scanner sc = new Scanner(System.in);
    private String username; // ENCAPSULATION
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void loginAdmin(){
        setUsername("uwuditi");
        setPassword("abc123");
        System.out.println("Enter username: ");
        String user = sc.next();
        System.out.println("Enter password: ");
        String pass = sc.next();
        while (!Objects.equals(user, getUsername()) && !Objects.equals(pass, getPassword())){
            System.out.println("Login failed! Try again.");
            System.out.println("Enter username: ");
            user = sc.next();
            System.out.println("Enter password: ");
            pass = sc.next();
        }
        System.out.println("Login successful!\nWELCOME "+getUsername()+"!");
    }
    public void addCategory(){
        System.out.println("Enter Category ID: ");
        int id = sc.nextInt();
        sc.nextLine();      //avoid scanner bug
        System.out.println("Enter Category Name: ");
        String name = sc.nextLine();
        while(FlipZone.category.containsKey(id) || FlipZone.category.containsValue(name)){
            System.out.println("Category ID or Name already exists! Please try again.");
            System.out.println("Enter Category ID: ");
            id = sc.nextInt();
            sc.nextLine();  //avoid scanner bug
            System.out.println("Enter Category Name: ");
            name = sc.nextLine();
        }
        FlipZone.category.put(id, name);
        System.out.println("Category added successfully!");
    }
    public void deleteCategory(){
        System.out.println("Enter Category ID: ");
        int id = sc.nextInt();
        while(!FlipZone.category.containsKey(id)){
            System.out.println("Category ID does not exist! Please try again.");
            System.out.println("Enter Category ID: ");
            id = sc.nextInt();
        }
        FlipZone.category.remove(id);
        System.out.println("Category deleted successfully!");
    }
    public void addProduct(){
        System.out.println("Enter category ID");
        int catid = sc.nextInt();
        while(!FlipZone.category.containsKey(catid)){
            System.out.println("Category ID does not exist! Please try again.");
            System.out.println("Enter Category ID: ");
            catid = sc.nextInt();
        }
        System.out.println("Enter product ID");
        float prodid = sc.nextFloat();
        while(FlipZone.product.containsKey(prodid)){
            System.out.println("Product ID already exists! Please try again.");
            System.out.println("Enter product ID");
            prodid = sc.nextFloat();
        }
        ArrayList<String> prod = new ArrayList<>();
        System.out.println("Enter product name");
        String prodname = sc.next();
        System.out.println("Enter product price");
        String prodprice = sc.next();
        System.out.println("Enter product quantity");
        String prodqty = sc.next();
        prod.add(Integer.toString(catid));
        prod.add(prodname);
        prod.add(prodprice);
        prod.add(prodqty);
        FlipZone.product.put(prodid, prod);
        System.out.println("Product added successfully!");
    }
    public void deleteProduct(){
        System.out.println("Enter product ID");
        float prodid = sc.nextFloat();
        while(!FlipZone.product.containsKey(prodid)){
            System.out.println("Product ID does not exist! Please try again.");
            System.out.println("Enter product ID");
            prodid = sc.nextFloat();
        }
        FlipZone.product.remove(prodid);
        System.out.println("Product deleted successfully!");
    }
}
class Offers extends Admin{ //INHERITANCE
    public void discount(){
        System.out.println("Enter product ID");
        float prodid = sc.nextFloat();
        while(!FlipZone.product.containsKey(prodid)){
            System.out.println("Product ID does not exist! Please try again.");
            System.out.println("Enter product ID");
            prodid = sc.nextFloat();
        }
        System.out.println("Enter discount percentage for:");
        System.out.println("1. Elite:");
        int elite = sc.nextInt();
        System.out.println("2. Prime:");
        int prime = sc.nextInt();
        System.out.println("3. Regular:");
        int normal = sc.nextInt();
        ArrayList<Integer> disc = new ArrayList<>();
        disc.add(elite);
        disc.add(prime);
        disc.add(normal);
        FlipZone.discount.put(prodid, disc);
        System.out.println("Discount added successfully!");
    }
    public void giveaway(){
        System.out.println("Enter product ID 1");
        float prodid1 = sc.nextFloat();
        System.out.println("Enter product ID 2");
        float prodid2 = sc.nextFloat();
        while(!FlipZone.product.containsKey(prodid1) || !FlipZone.product.containsKey(prodid2)){
            System.out.println("Product ID does not exist! Please try again.");
            System.out.println("Enter product ID 1");
            prodid1 = sc.nextFloat();
            System.out.println("Enter product ID 2");
            prodid2 = sc.nextFloat();
        }
        System.out.println("Enter the discounted price:");
        int price = sc.nextInt();
        if(price > Integer.parseInt(FlipZone.product.get(prodid1).get(2)) + Integer.parseInt(FlipZone.product.get(prodid2).get(2))){
            System.out.println("Discounted price cannot be greater than combined price! Please try again.");
            System.out.println("Enter the discounted price:");
            price = sc.nextInt();
        }
        ArrayList<Float> prod = new ArrayList<>();
        prod.add(prodid1);
        prod.add(prodid2);
        FlipZone.giveaway.put(prod, price);
        System.out.println("Giveaway added successfully!");
    }
}
class Display extends Admin{
    public void catalog(){
        System.out.println("Category ID\tCategory Name");
        for(Map.Entry<Integer, String> entry : FlipZone.category.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
            System.out.println("____________________________");
            for(Map.Entry<Float, ArrayList<String>> entry1 : FlipZone.product.entrySet()){
                if(Integer.parseInt(entry1.getValue().get(0)) == entry.getKey()){
                    System.out.println("Product ID: "+entry1.getKey());
                    System.out.println("Product Name: "+entry1.getValue().get(1));
                    System.out.println("Product Price: "+entry1.getValue().get(2));
                    System.out.println("Product Quantity: "+entry1.getValue().get(3)+"\n");
                }
            }
            System.out.println("____________________________");
        }
    }
    public void deals(){
        System.out.println("Discount available on:\n");
        for(Map.Entry<Float, ArrayList<Integer>> entry : FlipZone.discount.entrySet()){
            System.out.println("Product: "+entry.getKey()+"\t"+FlipZone.product.get(entry.getKey()).get(1));
            System.out.println("Discount for Elite: "+entry.getValue().get(0));
            System.out.println("Discount for Prime: "+entry.getValue().get(1));
            System.out.println("Discount for Regular: "+entry.getValue().get(2)+"\n");
        }
        System.out.println("____________________________");
        System.out.println("Giveaways available on:\n");
        for(Map.Entry<ArrayList<Float>, Integer> entry : FlipZone.giveaway.entrySet()){
            System.out.println("Product 1: "+entry.getKey().get(0)+"\t"+FlipZone.product.get(entry.getKey().get(0)).get(1));
            System.out.println("Product 2: "+entry.getKey().get(1)+"\t"+FlipZone.product.get(entry.getKey().get(1)).get(1));
            System.out.println("Discounted price: "+entry.getValue()+"\n");
        }
    }
}
class Customer{
    Scanner sc = new Scanner(System.in);
    private String username;
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public void signUp(){
        System.out.println("Enter your username");
        String username = sc.next();
        while(FlipZone.customer.containsKey(username)){
            System.out.println("Username already exists! Please try again.");
            System.out.println("Enter your username");
            username = sc.next();
        }
        System.out.println("Enter your password");
        String password = sc.next();
        FlipZone.customer.put(username, password);
        ArrayList<String> cust = new ArrayList<>();
        cust.add("Normal");
        cust.add("1000");
        FlipZone.details.put(username, cust);
        System.out.println("Account created successfully!");
    }
    public void logIn(){
        System.out.println("Enter your username");
        String username = sc.next();
        while(!FlipZone.customer.containsKey(username)){
            System.out.println("Username does not exist! Please try again.");
            System.out.println("Enter your username");
            username = sc.next();
        }
        System.out.println("Enter your password");
        String password = sc.next();
        while(!FlipZone.customer.get(username).equals(password)){
            System.out.println("Password is incorrect! Please try again.");
            System.out.println("Enter your password");
            password = sc.next();
        }
        setUsername(username);
        System.out.println("Login successful!");
        System.out.println("Welcome "+username+"!");
    }
    public void viewCoupon(){
        if(FlipZone.coupons.containsKey(getUsername())){
            System.out.println("Following are the coupons available for you:");
            for(Integer i : FlipZone.coupons.get(getUsername())){
                System.out.println(i+"%");
            }
        }
        else{
            System.out.println("No coupons available!");
        }
    }
    public void upgradeStatus(){
        String status = FlipZone.details.get(getUsername()).get(0);
        if(status.equals("Elite")){
            System.out.println("You are already an Elite member!");
        }
        else if(status.equals("Prime")){
            System.out.println("You're currently a Prime member. Would you like to upgrade to Elite? (Y/N)");
            String choice = sc.next();
            if(choice.equals("Y")){
                int balance = Integer.parseInt(FlipZone.details.get(getUsername()).get(1));
                if(balance-300 > 0){
                    FlipZone.details.get(getUsername()).set(0, "Elite");
                    FlipZone.details.get(getUsername()).set(1, Integer.toString(balance-300));
                    System.out.println("You have been upgraded to Elite!");
                }
                else{
                    System.out.println("You don't have enough balance to upgrade to Elite!");
                }
            }
            else{
                System.out.println("Thank you for your interest!");
            }
        }
        else{
            System.out.println("You're currently a Normal member. Would you like to upgrade to Prime? (Y/N)");
            String choice = sc.next();
            if(choice.equals("Y")){
                int balance = Integer.parseInt(FlipZone.details.get(getUsername()).get(1));
                if(balance-200 > 0){
                    FlipZone.details.get(getUsername()).set(0, "Prime");
                    FlipZone.details.get(getUsername()).set(1, Integer.toString(balance-200));
                    System.out.println("You have been upgraded to Prime!");
                }
                else{
                    System.out.println("You don't have enough balance to upgrade to Prime!");
                }
            }
            else{
                System.out.println("Thank you for your interest!");
            }
        }
    }
    public void checkBalance(){
        System.out.println("Your current balance is: "+FlipZone.details.get(getUsername()).get(1));
    }
    public void addBalance(){
        System.out.println("Enter the amount you want to add");
        int amount = sc.nextInt();
        int balance = Integer.parseInt(FlipZone.details.get(getUsername()).get(1));
        FlipZone.details.get(getUsername()).set(1, Integer.toString(balance+amount));
        System.out.println("Balance added successfully!");
    }
}
class Browse extends Display{ // POLYMORPHISM
    @Override
    public void catalog(){
        System.out.println("Category ID\tCategory Name");
        for(Map.Entry<Integer, String> entry : FlipZone.category.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
            System.out.println("____________________________");
            for(Map.Entry<Float, ArrayList<String>> entry1 : FlipZone.product.entrySet()){
                if(Integer.parseInt(entry1.getValue().get(0)) == entry.getKey()){
                    if(Integer.parseInt(entry1.getValue().get(3)) > 0){
                        System.out.println("Product ID: "+entry1.getKey());
                        System.out.println("Product Name: "+entry1.getValue().get(1));
                        System.out.println("Product Price: "+entry1.getValue().get(2));
                        System.out.println("Product Quantity: "+entry1.getValue().get(3)+"\n");
                    }
                }
            }
            System.out.println("____________________________");
        }
    }
    @Override
    public void deals(){
        System.out.println("Discount available on:\n");
        for(Map.Entry<Float, ArrayList<Integer>> entry : FlipZone.discount.entrySet()){
            if(Integer.parseInt(FlipZone.product.get(entry.getKey()).get(3)) > 0){
                System.out.println("Product: "+entry.getKey()+"\t"+FlipZone.product.get(entry.getKey()).get(1));
                System.out.println("Discount for Elite: "+entry.getValue().get(0));
                System.out.println("Discount for Prime: "+entry.getValue().get(1));
                System.out.println("Discount for Regular: "+entry.getValue().get(2)+"\n");
            }
        }
        System.out.println("____________________________");
        System.out.println("Giveaways available on:\n");
        for(Map.Entry<ArrayList<Float>, Integer> entry : FlipZone.giveaway.entrySet()){
            if(Integer.parseInt(FlipZone.product.get(entry.getKey().get(0)).get(3)) > 0 && Integer.parseInt(FlipZone.product.get(entry.getKey().get(1)).get(3)) > 0){
                System.out.println("Product: "+entry.getKey().get(0)+"\t"+FlipZone.product.get(entry.getKey().get(0)).get(1));
                System.out.println("Product: "+entry.getKey().get(1)+"\t"+FlipZone.product.get(entry.getKey().get(1)).get(1));
                System.out.println("Combined price: "+entry.getValue()+"\n");
            }
        }
    }
}
interface cartFunctionalities{      // INTERFACE
    void addtoCart();
    void addtocartDeal();
    void emptyCart();
    void viewCart();
    void checkoutCart();
}
class Cart implements cartFunctionalities{
    Scanner sc = new Scanner(System.in);
    String username;
    public Cart(String username){
        this.username = username;
    }
    public void addtoCart(){
        System.out.println("Enter the product ID you want to add to cart");
        float id = sc.nextFloat();
        if(FlipZone.product.containsKey(id)){
            if(Integer.parseInt(FlipZone.product.get(id).get(3)) > 0){
                if(FlipZone.cart.containsKey(username)){
                    FlipZone.cart.get(username).add(id);
                }
                else{
                    ArrayList<Float> temp = new ArrayList<>();
                    temp.add(id);
                    FlipZone.cart.put(username, temp);
                }
                System.out.println("Product added to cart successfully!");
            }
            else{
                System.out.println("Product out of stock!");
            }
        }
        else{
            System.out.println("Product doesn't exist!");
        }
    }
    public void addtocartDeal(){
        System.out.println("Enter the product ID you want to check deal for: ");
        float id = sc.nextFloat();
        if(FlipZone.product.containsKey(id)){
            if(Integer.parseInt(FlipZone.product.get(id).get(3)) > 0){
                if(FlipZone.discount.containsKey(id)){
                    if(!FlipZone.cartfordeal.containsKey(username)){
                        ArrayList<Float> temp = new ArrayList<>();
                        temp.add(id);
                        FlipZone.cart.put(username, temp);
                        System.out.println("Product added to cart successfully!");
                    }
                    else{
                        FlipZone.cart.get(username).add(id);
                        System.out.println("Product added to cart successfully!");
                    }
                }
                else{
                    for(Map.Entry<ArrayList<Float>, Integer> entry : FlipZone.giveaway.entrySet()){
                        if(entry.getKey().get(0) == id || entry.getKey().get(1) == id){
                            if(!FlipZone.cartfordeal.containsKey(username)){
                                ArrayList<Float> temp = new ArrayList<>();
                                temp.add(entry.getKey().get(0));
                                temp.add(entry.getKey().get(1));
                                FlipZone.cartfordeal.put(username, temp);
                                System.out.println("Product added to cart successfully!");
                            }
                            else{
                                FlipZone.cartfordeal.get(username).add(entry.getKey().get(0));
                                FlipZone.cartfordeal.get(username).add(entry.getKey().get(1));
                                System.out.println("Product added to cart successfully!");
                            }
                        }
                    }
                    System.out.println("No deal available for this product!");
                }
            }
            else{
                System.out.println("Product out of stock!");
            }
        }
        else{
            System.out.println("Product doesn't exist!");
        }
    }
    public void viewCart(){
        if(FlipZone.cart.containsKey(username)){
            System.out.println("Product ID\tProduct Name\tProduct Price\tProduct Quantity");
            for(int i=0; i<FlipZone.cart.get(username).size(); i++){
                System.out.println(FlipZone.cart.get(username).get(i)+"\t"+FlipZone.product.get(FlipZone.cart.get(username).get(i)).get(1)+"\t"+FlipZone.product.get(FlipZone.cart.get(username).get(i)).get(2)+"\t"+FlipZone.product.get(FlipZone.cart.get(username).get(i)).get(3));
            }
        }
        else{
            System.out.println("Cart is empty!");
        }
        if(FlipZone.cartfordeal.containsKey(username)){
            for(int i=0; i<FlipZone.cartfordeal.get(username).size(); i++){
                System.out.println(FlipZone.cartfordeal.get(username).get(i)+"\t"+FlipZone.product.get(FlipZone.cartfordeal.get(username).get(i)).get(1)+"\t"+FlipZone.product.get(FlipZone.cartfordeal.get(username).get(i)).get(2)+"\t"+FlipZone.product.get(FlipZone.cartfordeal.get(username).get(i)).get(3));
            }
        }
        else{
            System.out.println("Cart is empty!");
        }
        System.out.println("____________________________");
    }
    public void emptyCart(){
        if(FlipZone.cart.containsKey(username)){
            FlipZone.cart.remove(username);
            System.out.println("Cart emptied successfully!");
        }
        else{
            System.out.println("Cart is already empty!");
        }
        if(FlipZone.cartfordeal.containsKey(username)){
            FlipZone.cartfordeal.remove(username);
            System.out.println("Cart emptied successfully!");
        }
        else{
            System.out.println("Cart is already empty!");
        }
    }
    public void checkoutCart() {
        viewCart();
        float total = 0;
        for(Float i : FlipZone.cart.get(username)){
            int price = Integer.parseInt(FlipZone.product.get(i).get(2));
            if(FlipZone.discount.containsKey(i)){
                if(FlipZone.details.get(username).get(0).equals("Elite")){
                    price = price - (price * FlipZone.discount.get(i).get(0) / 100);
                }
                else if(FlipZone.details.get(username).get(0).equals("Prime")){
                    price = price - (price * FlipZone.discount.get(i).get(1) / 100);
                }
                else{
                    price = price - (price * FlipZone.discount.get(i).get(2) / 100);
                }
            }
            total += price;
        }
        for(Map.Entry<ArrayList<Float>, Integer> entry : FlipZone.giveaway.entrySet()){
            for(int i = 0; i < entry.getKey().size()-1; i+=2){
                if(FlipZone.cartfordeal.get(username).contains(entry.getKey().get(i)) && FlipZone.cartfordeal.get(username).contains(entry.getKey().get(i+1))){
                    total += entry.getValue();
                }
            }
        }
        if(FlipZone.details.get(username).get(0).equals("Elite")){
            total = total - (total * 10 / 100);
            float delivery = 100;
            total += delivery;
            System.out.println("Total amount to be paid: "+total);
            if(total <= Integer.parseInt(FlipZone.details.get(username).get(1))){
                FlipZone.details.get(username).set(1, Integer.toString(Integer.parseInt(FlipZone.details.get(username).get(1)) - (int) total));
                System.out.println("Your order has been placed successfully!");
                System.out.println("Expected delivery date: "+ LocalDate.now().plusDays(2));
                if(FlipZone.cart.size() != 0){
                    for(Float i : FlipZone.cart.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                if(FlipZone.cartfordeal.size() != 0){
                    for(Float i : FlipZone.cartfordeal.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                FlipZone.cart.remove(username);
                FlipZone.cartfordeal.remove(username);
            }
            else{
                System.out.println("Insufficient balance!");
            }
        }
        else if(FlipZone.details.get(username).get(0).equals("Prime")){
            total = total - (total * 5 / 100);
            float delivery = 100 + (total * 2 / 100);
            total += delivery;
            System.out.println("Total amount to be paid: "+total);
            if(total <= Integer.parseInt(FlipZone.details.get(username).get(1))){
                FlipZone.details.get(username).set(1, Integer.toString(Integer.parseInt(FlipZone.details.get(username).get(1)) - (int) total));
                System.out.println("Your order has been placed successfully!");
                System.out.println("Expected delivery date: "+ LocalDate.now().plusDays(4));
                if(FlipZone.cart.size() != 0){
                    for(Float i : FlipZone.cart.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                if(FlipZone.cartfordeal.size() != 0){
                    for(Float i : FlipZone.cartfordeal.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                FlipZone.cart.remove(username);
                FlipZone.cartfordeal.remove(username);
            }
            else{
                System.out.println("Insufficient balance!");
            }
        }
        else{
            float delivery = 100 + (total * 5 / 100);
            total += delivery;
            System.out.println("Total amount to be paid: "+total);
            if(total <= Integer.parseInt(FlipZone.details.get(username).get(1))){
                FlipZone.details.get(username).set(1, Integer.toString(Integer.parseInt(FlipZone.details.get(username).get(1)) - (int) total));
                System.out.println("Your order has been placed successfully!");
                System.out.println("Expected delivery date: "+ LocalDate.now().plusDays(7));
                System.out.println(FlipZone.cart);
                if(FlipZone.cart.size() != 0){
                    for(Float i : FlipZone.cart.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                if(FlipZone.cartfordeal.size() != 0){
                    for(Float i : FlipZone.cartfordeal.get(username)){
                        FlipZone.product.get(i).set(3, Integer.toString(Integer.parseInt(FlipZone.product.get(i).get(3)) - 1));
                    }
                }
                FlipZone.cart.remove(username);
                FlipZone.cartfordeal.remove(username);
            }
            else{
                System.out.println("Insufficient balance!");
            }
        }
    }

}
public class FlipZone {
    static HashMap<Integer, String> category = new HashMap<>();
    static HashMap<Float, ArrayList<String>> product = new HashMap<>();
    static HashMap<Float, ArrayList<Integer>> discount = new HashMap<>();
    static HashMap<ArrayList<Float>, Integer> giveaway = new HashMap<>();
    static HashMap<String, String> customer = new HashMap<>();
    static HashMap<String, ArrayList<String>> details = new HashMap<>();
    static HashMap<String, ArrayList<Integer>> coupons = new HashMap<>();
    static HashMap<String, ArrayList<Float>> cart = new HashMap<>();
    static HashMap<String, ArrayList<Float>> cartfordeal = new HashMap<>();
    static void welcome(){
        System.out.println("1. Enter as Admin");
        System.out.println("2. Explore the product catalog");
        System.out.println("3. Show available deals");
        System.out.println("4. Enter as Customer");
        System.out.println("5. Exit the application");
    }
    static void admin(){
        System.out.println("1. Add a category");
        System.out.println("2. Delete a category");
        System.out.println("3. Add a product");
        System.out.println("4. Delete a product");
        System.out.println("5. Set discount on product");
        System.out.println("6. Add giveaway deal");
        System.out.println("7. Back");
    }
    static void login(){
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Back");
    }
    static void customer(){
        System.out.println("1. browse products");
        System.out.println("2. browse deals");
        System.out.println("3. add a product to cart");
        System.out.println("4. add products in deal to cart");
        System.out.println("5. view coupons");
        System.out.println("6. check account balance");
        System.out.println("7. view cart");
        System.out.println("8. empty cart");
        System.out.println("9. checkout cart");
        System.out.println("10. upgrade customer status");
        System.out.println("11. Add amount to wallet");
        System.out.println("12. back");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        Offers offer = new Offers();
        Display display = new Display();
        Customer customer = new Customer();
        Browse browse = new Browse();
        int choice;
        System.out.println("WELCOME TO FLIPZONE!");
        do{
            welcome();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    admin.loginAdmin();
                    int choice2;
                    do {
                        admin();
                        System.out.println("Enter your choice: ");
                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1 -> admin.addCategory();
                            case 2 -> admin.deleteCategory();
                            case 3 -> admin.addProduct();
                            case 4 -> admin.deleteProduct();
                            case 5 -> offer.discount();
                            case 6 -> offer.giveaway();
                            case 7 -> System.out.println("Back to main menu");
                            default -> System.out.println("Invalid choice!");
                        }
                    } while (choice2 != 7);
                }
                case 2 -> display.catalog();
                case 3 -> display.deals();
                case 4 -> {
                    int choice3;
                    do {
                        login();
                        System.out.println("Enter your choice: ");
                        choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1 -> customer.signUp();
                            case 2 -> {
                                customer.logIn();
                                Cart cart = new Cart(customer.getUsername());
                                int choice4;
                                do {
                                    customer();
                                    System.out.println("Enter your choice: ");
                                    choice4 = sc.nextInt();
                                    switch (choice4) {
                                        case 1 -> browse.catalog();
                                        case 2 -> browse.deals();
                                        case 3 -> cart.addtoCart();
                                        case 4 -> cart.addtocartDeal();
                                        case 5 -> customer.viewCoupon();
                                        case 6 -> customer.checkBalance();
                                        case 7 -> cart.viewCart();
                                        case 8 -> cart.emptyCart();
                                        case 9 -> cart.checkoutCart();
                                        case 10 -> customer.upgradeStatus();
                                        case 11 -> customer.addBalance();
                                        case 12 -> System.out.println("Back to login menu");
                                        default -> System.out.println("Invalid choice!");
                                    }
                                } while (choice4 != 12);
                            }
                            case 3 -> System.out.println("Back to main menu");
                            default -> System.out.println("Invalid choice!");
                        }
                    } while (choice3 != 3);
                }
                case 5 -> System.out.println("Exiting the application");
                default -> System.out.println("Invalid choice");
            }
        }while(choice != 5);
    }
}
