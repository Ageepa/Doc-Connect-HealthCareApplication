import java.util.*;

public class Controller {

    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    public static ArrayList<Patient> allPatients = new ArrayList<>();

    public static int NUMBER_OF_SLOTS = 2;
    public static void addDoctor(){

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Birthday: ");
        String birthday = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = sc.nextLine();

        Random random = new Random();
        Doctor tempDoctor = new Doctor(random.nextInt(),name,birthday,specialization,contactNumber);
        allDoctors.add(tempDoctor);

    }

    public static void addAvailabilityForDoctor(){

        Scanner scanner =new Scanner(System.in);
        System.out.print("Enter the Doctor id you want to add availability: ");
        int docID = scanner.nextInt();
        Doctor selectedDoctor = null;
        //we need to fetch the doctor from the array list
        for(Doctor doc: allDoctors){
            if(doc.getDoctorId() == docID){
                selectedDoctor = doc;
            }
        }


        if(selectedDoctor == null){
            System.out.println("No Doctor Found!!!");
            return;
        }
        //if the doctor is existing take the data
        System.out.print("Enter the Day you want to add the Availability: ");
        int day = scanner.nextInt();
        System.out.print("Enter the Month you want to add the Availability: ");
        int month = scanner.nextInt();
        System.out.print("Enter the Year you want to add the Availability: ");
        int year = scanner.nextInt();

        Date bookingDate = new Date(year,month,day);
        selectedDoctor.setAvailability(bookingDate);

    }

    public static void viewAllDoctors(){

        for(Doctor doc: Controller.allDoctors){
            System.out.println("Doctor " + doc.getName() + " has a specialization of " + doc.getSpecialization() + " has a id of " + doc.getDoctorId() + " and has a availability of " + doc.getAvailabilities().toString());
        }
    }

    public static void addPatient(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient's Id: ");
        String id = scanner.nextLine();
        System.out.print("Enter patient's Contact Information: ");
        String contact = scanner.nextLine();
        Patient tempPatient = new Patient(id,name,contact);

        allPatients.add(tempPatient);
        System.out.println("Patient Added Successfully!!!");
        System.out.println(allPatients.toString());

    }


    public static void bookAppointmtnt(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Doctor's Id you want to make an appointment: ");
        int docId = scanner.nextInt();
        System.out.print("Enter your patient's Id: ");
        String patientId = scanner.next();

        System.out.print("Enter the Day you want to add Availability: ");
        String day = scanner.next();
        System.out.print("Enter the Month you want to add Availability: ");
        String month = scanner.next();
        System.out.print("Enter the Year you want to add Availability: ");
        String year = scanner.next();


        Patient selectedPatient = getPatientById(patientId);
        Doctor selectedDoc = getDoctorById(docId);
        if(selectedDoc == null || selectedPatient == null){
            System.out.println("Invalid Doc or Patient ID");
            return;
        }


        Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        //Check the availability
        Boolean isAvailable  = checkAvailability(selectedDoc,appointmentDate);
        if (!isAvailable) {
            System.out.println("Doctor is not available on the selected Date");
            return;
        }

        System.out.print("Is this a General or Referral Appointment? (G/R): ");
        String appointmentType = scanner.next();

        if (appointmentType.equals("G")) {
            String appTime = getTimeForBooking(selectedDoc, appointmentDate);
            if (appTime != null) {
                System.out.print("Enter patient notes: ");
                scanner.nextLine(); // consume newline
                String patientNotes = scanner.nextLine();
                GeneralAppointment appointment = new GeneralAppointment(selectedDoc, selectedPatient, appointmentDate, appTime, patientNotes);
                selectedDoc.setAppAppointment(appointment, appointmentDate);
                System.out.println(selectedDoc.getAllAppointments().toString());
            } else {
                System.out.println("All the slots are filled!!!");
            }
        } else if (appointmentType.equals("R")) {
            System.out.print("Enter Referral Doctor's Id: ");
            int referralDocId = scanner.nextInt();
            Doctor referralDoctor = getDoctorById(referralDocId);
            if (referralDoctor == null) {
                System.out.println("Invalid Referral Doctor ID");
                return;
            }

            String appTime = getTimeForBooking(selectedDoc, appointmentDate);
            if (appTime != null) {
                System.out.print("Enter notes: ");
                scanner.nextLine(); // consume newline
                String notes = scanner.nextLine();
                ReferralAppointment appointment = new ReferralAppointment(selectedDoc, selectedPatient, appointmentDate, appTime, referralDoctor, notes);

                System.out.print("Enter Referral Doctor's notes: ");
                String referralDoctorNotes = scanner.nextLine();
                appointment.setReferralDoctorNotes(referralDoctorNotes);

                selectedDoc.setAppAppointment(appointment, appointmentDate);
                System.out.println(selectedDoc.getAllAppointments().toString());
            } else {
                System.out.println("All the slots are filled!!!");
            }
        } else {
            System.out.println("Invalid appointment type!");
        }

    }

    private static String getTimeForBooking(Doctor selectedDoctor, Date dateOfBooking){
        for(Map.Entry<Date,ArrayList<Appointment>> appointment: selectedDoctor.getAllAppointments().entrySet()){
            if(appointment.getKey().equals(dateOfBooking)){
                int numberOfSlots = appointment.getValue().size();
                if(numberOfSlots > NUMBER_OF_SLOTS-1){
                    return null;
                }
                System.out.println("We can make a booking");
                int time = 17 + appointment.getValue().size();
                String strTime = time + " : 00";
                return strTime;
            }
        }
        return "17 : 00";
    }

    private static boolean checkAvailability(Doctor selectedDoctor, Date datOfBooking){
        for(Date day: selectedDoctor.getAvailabilities()){
            if(day.equals(datOfBooking)){
                return true;
            }

        }
        return false;
    }


    public static Patient getPatientById(String id){
        for(Patient patient : allPatients){
            if(patient.getPatientID().equals(id)){
                return patient;
            }
        }
        return null;
    }

    public static Doctor getDoctorById(int id){
        for(Doctor doc : allDoctors){
            if(doc.getDoctorId() == id){
                return doc;
            }
        }
        System.out.println("No doctor Found");
        return null;
    }


}
