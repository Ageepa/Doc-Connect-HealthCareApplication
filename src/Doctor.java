import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Doctor extends Person{

    private int doctorID;
    private String name;
    private String birthday;
    private String specialization;
    private String contactNumber;
    private ArrayList<Date> availabilities;
    private HashMap<Date,ArrayList<Appointment>> allAppointments = new HashMap<>();

    public Doctor(int doctorID,String name,String birthday,String specialization,String contactNumber){
        super(name, contactNumber);
        this.doctorID = doctorID;
        this.birthday = birthday;
        this.specialization = specialization;
        availabilities = new ArrayList<>();

    }
    public boolean isPhysician(){
        boolean nameEndsWith = this.specialization.endsWith("Physician");
        return nameEndsWith;
    }
    public void setAvailability(Date availableDate) {
        this.availabilities.add(availableDate);
    }

    public void setAppAppointment(Appointment appointment, Date date){
        ArrayList<Appointment> currentAppointments = this.allAppointments.get(date);
        if(currentAppointments == null){
            ArrayList<Appointment> tempArrayList = new ArrayList<>();
            tempArrayList.add(appointment);
            this.allAppointments.put(date,tempArrayList);

        }else{
            currentAppointments.add(appointment);
            this.allAppointments.put(date,currentAppointments);

        }


    }

    public ArrayList<Date> getAvailabilities() {

        return availabilities;
    }

    public HashMap<Date, ArrayList<Appointment>> getAllAppointments() {

        return allAppointments;
    }

    public int getDoctorId() {

        return doctorID;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setAvailabilities(ArrayList<Date> availabilities) {
        this.availabilities = availabilities;
    }

    public void setAllAppointments(HashMap<Date, ArrayList<Appointment>> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



}
