package HospitalManagementSystem;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class hospitalManagement {
    public static final String url = "jdbc:mysql://localhost:3306/hospital";
    public static final String user = "root";
    public static final String password = "9370";


    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("driver loaded successfully!");
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
                System.out.println("=========== Hospital Management System ===========");
                System.out.println("1.Add patient");
                System.out.println("2.View All Patients");
                System.out.println("3.displayPatientDetails");
                System.out.println("4.View All Doctors");
                System.out.println("5.Book Appointment");
                System.out.println("6.View All Appointments");
                System.out.println("7.Exit");
                System.out.println("==================================================");
                System.out.println("Enter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        patient.addPatient(con,sc);
                        System.out.println("");
                        break;
                    case 2:
                        patient.viewPatients(con);
                        System.out.println("");
                        break;
                    case 3:
                        patient.displayPatientDetails(con,sc);
                        System.out.println("");
                        break;
                    case 4:
                        doctor.viewDoctors(con);
                        System.out.println("");
                        break;
                    case 5:
                        appointment.bookAppointment(con,sc,patient,doctor);
                        System.out.println("");
                        break;
                    case 6:
                        appointment.viewAllAppointments(con);
                        break;
                    case 7:
                        System.out.println("Thanks for using our hotel management system...");
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

}
