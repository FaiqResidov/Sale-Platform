public class Seller {

    public Seller(String fullName,String username,String password,int age,boolean isAdmin){
        this.fullName=fullName;
        this.age=age;
        this.isAdmin=isAdmin;
        this.username=username;
        this.password=password;
    }

    @Override
public String toString(){
        return "Name: %s username: %s password: %s age: %d Admin: %b".formatted(fullName,username,password,age,isAdmin);
}


    public String fullName;
    public String username;
    public int age;
    public String experience;
    public Double salary;
    public String gender;
    public String password;
    public boolean isAdmin;
}
