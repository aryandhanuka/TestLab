import java.util.*;
public class AdminFrontend {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBconnection ob=new DBconnection();
        System.out.println("Welcome to the Patient Management System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a service");
            System.out.println("2. Change Service Prices");
            System.out.println("3. Remove a service");
            System.out.println("4. Adjust Patient Balance");
            System.out.println("5. view Services");
            System.out.println("6. Deliver appointment service ");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 0:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    case 6:
                    //deliver_appointment
                    delivApp(scanner,ob);
                    break;
                    case 5:
                    //view services 
                    System.out.println(ob.getServices());
                    break;
                    case 4:
                    //adjust patient balance
                    adjBalance(scanner,ob);
                    break;
                    case 3:
                    //remove service 
                    rmvService(scanner, ob);
                    break;
                    case 2:
                    chgPrice(scanner, ob);
                    break;
                    case 1:
                    addService(scanner, ob);
                    break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void adjBalance(Scanner sc, DBconnection ob) throws Exception
    {
        System.out.println("Enter the patient id: ");
        int pid=sc.nextInt();
        ob.clearDues(pid);
    } 
    private static void delivApp(Scanner sc, DBconnection ob) throws Exception
    {
        System.out.println("Enter the patient id: ");
        int pid=sc.nextInt();
        System.out.println("Enter the appointment number");
        int rid=sc.nextInt();
        ob.complete_appointment(pid,rid);
    } 
    private static void rmvService(Scanner sc, DBconnection ob) throws Exception
    {
        System.out.println("Enter the service id: ");
        int sid=sc.nextInt();
        ob.removeService(sid);
    }
    private static void chgPrice(Scanner sc, DBconnection ob) throws Exception
    {
        System.out.println("Enter the service id: ");
        int sid=sc.nextInt();
        System.out.println("Enter the new Price ");
        int price=sc.nextInt();
        ob.changeUprice(sid, price);
    }
    private static void addService(Scanner sc, DBconnection ob) throws Exception
    {
        System.out.println("Enter the service name: ");
        String sid=sc.next();
        System.out.println("Enter the Price ");
        int price=sc.nextInt();
        ob.addService(sid, price);
    }


}
