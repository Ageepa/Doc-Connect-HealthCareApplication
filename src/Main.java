import java.util.*;

public class Main {
    public static
    void adminMenu(){
        boolean adminMenu = true;
        while(adminMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to add a doctor, press 2 to add a doctor availability, and press 3 to exit");
            int userObjective = scanner.nextInt();
            if (userObjective == 1) {
                Controller.addDoctor();
                for(Doctor doc: Controller.allDoctors){
                    System.out.println(doc.getDoctorId());
                }
                System.out.println("Doctor Added Successfully!!!");

            } else if (userObjective == 2) {
                Controller.addAvailabilityForDoctor();

            } else if (userObjective == 3) {
                adminMenu = false;
            } else {
                System.out.println("Invalid");
            }
        }
    }
    public static void patientMenu(){
        boolean patientMenu = true;
        while(patientMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to view doctors, press 2 to book an appointment, press 3 to view a selected doctor’s bookings, press 4 to exit and press 5 to add a patient");
            int userObjective = scanner.nextInt();
            if (userObjective == 1) {
                Controller.viewAllDoctors();
            } else if (userObjective == 2) {
                Controller.bookAppointmtnt();
            } else if (userObjective == 3) {
                System.out.println("View a selected Doctor");
            } else if (userObjective == 4) {
                patientMenu = false;
            } else if (userObjective == 5) {
                Controller.addPatient();
            } else {
                System.out.println("Invalid");
            }
        }
    }

    public static void run(){
        boolean mainMenu = true;

        while(mainMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you are a hospital administrator please press 1, If you are a patient please press 2, Press 3 to exit");
            int userType = scanner.nextInt();

            if (userType == 1) {
                adminMenu();

            } else if (userType == 2) {
                patientMenu();

            } else if (userType == 3) {
                mainMenu = false;
                System.out.println("Exit");

            } else {
                System.out.println("Invalid Input");
            }
        }
    }


    public static void main(String[] args) {

        Doctor sampleDoc = new Doctor(223,"Saman Kumara","22.05.1987","Gynocologist","077-333-9900");
        Patient samplePatient = new Patient("T-12", "Alice Johnson",  "555-123-4567");
        Controller.allDoctors.add(sampleDoc);
        Controller.allPatients.add(samplePatient);

        run();

    }
}
