import java.sql.Date;
import java.util.Scanner;

public class PatientFrontend {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBconnection ob=new DBconnection();
        System.out.println("Welcome to the Patient Management System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register a new patient");
            System.out.println("2. Make an appointment");
            System.out.println("3. Delete an appointment");
            System.out.println("4. Get patient's balance");
            System.out.println("5. Generate patient's invoice");
            System.out.println("6. Pay patient's balance");
            System.out.println("7. View available services");
            System.out.println("8. View upcoming appointments");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 0:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                    case 1:
                        registerNewPatient(scanner,ob);
                        break;
                    case 2:
                        makeAppointment(scanner,ob);
                        break;
                    case 3:
                        deleteAppointment(scanner,ob);
                        break;
                    case 4:
                        getPatientBalance(scanner,ob);
                        break;
                    case 5:
                        generatePatientInvoice(scanner,ob);
                        break;
                    case 6:
                        payPatientBalance(scanner,ob);
                        break;
                    case 7:
                        viewAvailableServices(ob);
                        break;
                    case 8:
                        viewUpcoming(scanner,ob);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void viewUpcoming(Scanner scanner, DBconnection ob) throws Exception
    {
        System.out.println("enter your patient_id");
        int pid=scanner.nextInt();
        System.out.println(ob.viewAppointments(pid));
    }
    private static void registerNewPatient(Scanner scanner, DBconnection db) throws Exception {
        System.out.println("Enter patient's name:");
        String name = scanner.next();

        System.out.println("Enter patient's address:");
        String addr = scanner.next();

        System.out.println("Enter patient's date of birth (YYYY-MM-DD):");
        String dobStr = scanner.next();
        Date dob = Date.valueOf(dobStr);

        System.out.println("Enter patient's gender (m/f):");
        char gen = scanner.next().charAt(0);

        Patient newPatient = new Patient(name, addr, dob, gen);
       System.out.println("Patient registered successfully with ID: " + newPatient.patient_id);
       
    }

    // make appointment
    private static void makeAppointment(Scanner scanner, DBconnection db) throws Exception {
        System.out.println("Enter patient id:");
        int pid = scanner.nextInt();

        System.out.println("Enter the service_id that you desire:");
        int sid = scanner.nextInt();

        System.out.println("Enter date of appointment (yyy-mm-dd):");
        String dobStr = scanner.next();
        Date dob = Date.valueOf(dobStr);
        System.out.println("Appointment registered successfully with ID: " + Patient.make_appointment(pid, sid, dob));
    }

    private static void deleteAppointment(Scanner scanner, DBconnection db) throws Exception {

        System.out.println("Enter the patient_id:");
        int pid = scanner.nextInt();
        System.out.println("Enter the request_id");
        int rid = scanner.nextInt();
        Patient.delete_appointment(pid, rid);
        System.out.println("Appointment deleted successfuly ");
    }

    private static void getPatientBalance(Scanner scanner, DBconnection db) throws Exception {
        System.out.println("Enter the patient_id:");
        int pid = scanner.nextInt();
        System.out.println("The patient has a balance of:" + Patient.get_balance(pid));
    }

    private static void generatePatientInvoice(Scanner scanner, DBconnection db) throws Exception {
        System.out.println("Enter the patient_id:");
        int pid = scanner.nextInt();
        System.out.println(Patient.get_invoice(pid));
    }

    private static void payPatientBalance(Scanner scanner, DBconnection db) throws Exception {
        System.out.println("Enter the patient_id:");
        int pid = scanner.nextInt();
        System.out.println("enter the order_number:");
        int rid = scanner.nextInt();
        Patient.pay_balance(pid, rid);
        System.out.println("The balance has been settled successfuly");
    }

    private static void viewAvailableServices( DBconnection db) throws Exception {
        System.out.println(Patient.get_offerings());
    }
    

}
