public class Patient  extends Person {


    private String patientID;

    public Patient(String patientID, String name, String contactNumber){
        super(name, contactNumber);
        this.patientID = patientID;

    }

    public String getPatientID(){
        return this.patientID;
    }

    public void setPatientID(String pid) {
        this.patientID = pid;
    }

    public char getPatientType(){
        char firstLetter = this.patientID.charAt(0);
        return firstLetter;

    }


}
