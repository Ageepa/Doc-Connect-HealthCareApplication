import java.util.Date;

public class GeneralAppointment extends Appointment {
    private String patientNotes;

    public GeneralAppointment(Doctor doctor, Patient patient, Date date, String time, String patientNotes) {
        super(doctor, patient, date, time);
        this.patientNotes = patientNotes;
    }

    public String getPatientNotes() {
        return patientNotes;
    }

    public void setPatientNotes(String patientNotes) {
        this.patientNotes = patientNotes;
    }
}
