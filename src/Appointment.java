package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Appointment
{
    public static void bookAppointment(Connection con,Scanner sc,Patient patient,Doctor doctor) {
        System.out.println("Enter patient id:");
        int patient_id = sc.nextInt();
        System.out.println("Enter doctor id:");
        int doctor_id = sc.nextInt();
        System.out.println("Enter appointment date (yyy-mm-dd):");
        String appointment_date = sc.next();
        if (patient.getPatientById(con, patient_id) && doctor.getDoctorById(con, doctor_id)) {
            if (doctorAvailability(doctor_id, appointment_date, con)) {
                String query = "INSERT INTO appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, patient_id);
                    ps.setInt(2, doctor_id);
                    ps.setString(3, appointment_date);

                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("your appointment booked!");
                    } else {
                        System.out.println("your appointment not booked due to some reason");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("doctor not available on this date");
            }
        } else {
            System.out.println("Either doctor not available or patient");
        }
    }

    public static boolean doctorAvailability(int doctor_id, String appointment_date, Connection con) {
        String query = "SELECT COUNT(*) FROM appointments where id=? AND appointment_date=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctor_id);
            ps.setString(2, appointment_date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void viewAllAppointments(Connection con) {
        try
        {
            String query ="SELECT * FROM appointments";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("+-----------------+----------------+----------------+---------------------+");
            System.out.println("| Appointment ID  | Patient Name   | Doctor Name    | Appointment Date    |");
            System.out.println("+-----------------+----------------+----------------+---------------------+");
            while (rs.next()) {
                int appointment_id = rs.getInt("id");
                int patient_id = rs.getInt("patient_id");
                int doctor_id = rs.getInt("doctor_id");
                String date = rs.getString("appointment_date");
                System.out.printf("| %-15s | %-14s | %-14s | %-19s |\n", appointment_id, patient_id, doctor_id, date);
                System.out.println("+-----------------+----------------+----------------+---------------------+");
            }
        }
        catch (SQLException e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }
    }

}
