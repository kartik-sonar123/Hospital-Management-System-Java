package HospitalManagementSystem;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.TreeMap;

public class hospitalManagement {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";  // Change as per your DB
    private static final String user = "Add your username here";
    public static final String password = "Add your password here";
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");    
//          System.out.println("driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);
            Patient patient = new Patient();
            Doctor doctor = new Doctor();
            Appointment appointment = new Appointment();
            while (true) {
                System.out.println("===========Hospital Management System===========");
                System.out.println("1.Add patient");
                System.out.println("2.Delete patient");
                System.out.println("3.View All Patients");
                System.out.println("4.displayPatientDetails");
                System.out.println("5.View All Doctors");
                System.out.println("6.Book Appointment");
                System.out.println("7.View All Appointments");
                System.out.println("8.Exit");
                System.out.println("====================================================");
                System.out.println("Enter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        patient.addPatient(con,sc);
                        System.out.println("");
                        break;
                    case 2:
                        patient.deletePatient(con,sc);
                        System.out.println("");
                        break;
                    case 3:
                        patient.viewPatients(con);
                        System.out.println("");
                        break;
                    case 4:
                        patient.displayPatientDetails(con,sc);
                        System.out.println("");
                        break;
                    case 5:
                        doctor.viewDoctors(con);
                        System.out.println("");
                        break;
                    case 6:
                        appointment.bookAppointment(con,sc,patient,doctor);
                        System.out.println("");
                        break;
                    case 7:
                        appointment.viewAllAppointments(con);
                        break;
                    case 8:
                        exit();
                        return;
                    default:
                        System.out.println("Invalid choice try again!");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void exit()
    {
        try{
            System.out.print("Exiting System");
            for (int i=5;i>=0;i--)
            {
                System.out.print(".");
                Thread.sleep(1000);
            }
            System.out.println("");
            System.out.println("Thanks for using our hotel management system...");

        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
