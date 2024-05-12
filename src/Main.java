import java.util.Scanner;

public class Main {
public static Scanner sc=new Scanner(System.in);
    public static Seller[] sellers=new Seller[2];
    public static Product[] products=new Product[10];
    public static Seller loggedInSeller=null;
    public static double totalAmount=0;
    public static double profit=0;


    public static void main(String[] args) {
        sellers[0] = new Seller("Admin admin", "Admin", "Admin123", 23, true);
          startApp();


        //Admin
        //1.yeni satici elave et
        //2.saticini sil
        //3.product elave et
        //4.productu sil
        //5.satis et


        //satici
        //1.satis et
    }

    public static void authenticate(){
        System.out.print("Enter your username: ");
        String username = sc.next();
        System.out.print("enter your password: ");
        String password=sc.next();
        for(Seller seller:sellers){
            if(seller!=null && seller.username.equals(username) && seller.password.equals(password)){
                loggedInSeller=seller;
            }
        }
        if(loggedInSeller==null){
            System.out.println("invalid credential! try again!");
            authenticate();
        }
    }


    public static void showMenu(){
      if(loggedInSeller.isAdmin){
     processAdminMenu();
      }
      else {
       processSellerMenu();
      }
    }
public static  void processAdminMenu(){
    System.out.println("Today's profit is: " + profit);
    System.out.println("Today's total selling amount: " + totalAmount);
    System.out.println();
    System.out.println("1. Add a new seller");
    System.out.println("2. See all sellers");
    System.out.println("3. Delete selller");
    System.out.println("4. Add a new product");
    System.out.println("5. See all products");
    System.out.println("6. Delete product");
    System.out.println("7. Make a sale");
    System.out.println("8. Log out");

    System.out.print("Make a choice: ");
    int choice =sc.nextInt();
    switch (choice){
        case 1->addNewSeller();
        case 2->getAllSellers();
        case 3->deleteSeller();
        case 4->addProduct();
        case 5->getAllProduct();
        case 6->deleteProduct();
        case 7->sellProduct();
        case 8->{loggedInSeller=null;
            startApp();}
        default->{ System.out.println("Incorrect choice! select one of the choices from below menu!");
            processAdminMenu();}

    }
    processAdminMenu();
}

public static void startApp(){
authenticate();
showMenu();
}
    public static  void processSellerMenu(){
        System.out.println("Today's profit is: " + profit);
        System.out.println("Today's total selling amount: " + totalAmount);
        System.out.println();
        System.out.println("1. Make a sale");
        System.out.println("2. See all products");
        System.out.println("3. Log out");

        System.out.println("Make a choice: ");
        int choice =sc.nextInt();
        switch (choice){
            case 1 ->sellProduct();
            case 2 ->getAllProduct();
            case 3->{loggedInSeller=null;
                startApp();}
            default->{ System.out.println("Incorrect choice! select one of the choices from below menu!");
                processSellerMenu();}
        }
    }
    public static void sellProduct(){
        System.out.print("Enter the code of the product: ");
        String code = sc.next();

        Product product = null;
        for (Product p : products) {
            if (p != null && p.code.equals(code) && p.count > 0) {
                product = p;
            }
        }

        if (product == null) {
            System.out.println("There is not any product with given code!");
                if(loggedInSeller.isAdmin){
                    processAdminMenu();
                }
                else {
                    processSellerMenu();
                }


        }

        System.out.println("The amount of the product is: " + product.amount);
        System.out.print("Enter the count of the product to sell: ");
        int count = sc.nextInt();
        if (count > product.count) {
            System.out.println("Unfortunately! we only have " + product.count + " " + product.name);
            System.out.print("Do you want to change the desired count to " + product.count);
            String answer = sc.next();
            if (answer.equals("yes")) {
                count = product.count;
            } else {
                    if(loggedInSeller.isAdmin){
                        processAdminMenu();
                    }
                    else {
                        processSellerMenu();
                    }

            }
        }
        System.out.println("You should get " + count * product.amount + " manats from customer");

        System.out.print("Do you confirm the sell? (yes or not): ");
        String answer = sc.next();

        if (answer.equals("yes")) {
            totalAmount += count * product.amount;
            profit += count * (product.amount - product.price);
            product.count -= count;
        }

        System.out.print("Would u like to make another sell? (yes or not) ");
        String answ = sc.next();

        if (answ.equals("yes")) {
            if(loggedInSeller.isAdmin){
                processAdminMenu();
            }
            else {
                processSellerMenu();
            }
        } else {
            showMenu();
        }
    }

    public static void addNewSeller(){
        System.out.println("Enter the seller's name: ");
        String name=sc.next();
        System.out.println("Enter the seller's username: ");
        String username=sc.next();
        System.out.println("Enter the seller's password");
        String password=sc.next();
        System.out.println("Enter the seller's age");
        int age=sc.nextInt();
        System.out.println("Enter 1(yes) or 0(no) to submit if the seller is admin or not: ");
        int isAdminInput=sc.nextInt();

        boolean isAdmin =isAdminInput==1;

        Seller seller=new Seller(name,username,password,age,isAdmin);
        boolean isFull=true;
        for(int i=0;i<sellers.length;i++){
            if(sellers[i]==null){
                sellers[i]=seller;
                isFull=false;
                break;
            }
        }
        if(isFull){
            Seller [] newArr=new Seller[sellers.length*2];
            for(int i=0;i<sellers.length;i++){
                newArr[i]=sellers[i];
            }
            newArr[sellers.length]=seller;
            sellers = newArr;
        }
        System.out.println("Would you like to add another seller? (yes or not)");
        String answer=sc.next();
        System.out.println("The seller has been successfully added :)");
        if(answer.equalsIgnoreCase("yes")){
            addNewSeller();
        }else {
            processAdminMenu();
        }



    }

    public static void deleteSeller(){
        System.out.println("Enter the seller index you want to Delete");
        int index=sc.nextInt();
        if(index==0){
            System.out.println("default admin cannot be deleted :(");
        }else{
            System.out.println("The seller does not exist in this index");
        }

        for(int i=0;i< sellers.length;i++){
            if(index!=0 && i==index && sellers[i]!=null){
                sellers[i]=null;
                System.out.println("Seller deleted successfully :)");
                break;
            }
        }
    }
public static void getAllProduct(){
        boolean isFull=true;
    for(int i=0;i< products.length;i++){
        if(products[i]!=null){
            System.out.println(products[i].toString());
            isFull=false;
        }
    }
    if(isFull){
        System.out.println("product is not available :( Please create product");
    }

        if(loggedInSeller.isAdmin){
            processAdminMenu();
        }
        else{
            processSellerMenu();
        }


}



    public static void getAllSellers(){
        for(int i=0;i< sellers.length;i++){
            if(sellers[i]!=null){
                System.out.println(sellers[i].toString());
            }
        }
    }

    public static void addProduct(){
        System.out.println("Enter the product's name: ");
        String name=sc.next();
        System.out.println("Enter the product's code: ");
        String code=sc.next();
        System.out.println("Enter the product's count");
        int count=sc.nextInt();
        System.out.println("Enter the product's price");
        int price=sc.nextInt();
        System.out.println("Enter the product's amount");
        int amount=sc.nextInt();

        Product product =new Product(name,code,count,price,amount);
        boolean isFull=true;
        for(int i=0;i<products.length;i++){
            if(products[i]==null){
                products[i]=product;
                isFull=false;
                break;
            }
        }
        if(isFull){
            Product[] newarr= new Product[products.length*2];
            for(int i=0;i<products.length;i++){
                newarr[i]=products[i];
            }
            newarr[products.length]=product;
            products=newarr;
        }
        System.out.println("Would you like to add another product? (yes or not)");
        String answer=sc.next();

        if(answer.equalsIgnoreCase("yes")){
            addProduct();
        }else {
            processAdminMenu();
        }

    }
    public static void deleteProduct(){
        System.out.println("Enter the product index you want to Delete");
        int index=sc.nextInt();
        for(int i=0;i< products.length;i++){
            if(i==index && products[i]!=null){
                products[i]=null;
                System.out.println("Product deleted successfully :)");
            }
            else{
                System.out.println("The product does not exist in this index");
            }
            break;
        }
    }





}