package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Patient
{
    public void addPatient(Connection con,Scanner sc) {
        try {
            String Query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
            System.out.println("Enter patient name:");
            String name = sc.next();
            System.out.println("Enter patient age:");
            sc.nextLine();
            int age = sc.nextInt();
            System.out.println("Enter patient Gender:");
            String gender = sc.next();

            PreparedStatement ps = con.prepareStatement(Query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("patient admitted successfully");
            } else {
                System.out.println("Patient admission field due to some reason");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePatient(Connection con,Scanner sc)
    {
        System.out.println("Enter patient id:");
        int patient_id=sc.nextInt();
        System.out.println("Enter patient name:");
        String Name=sc.next();
        sc.nextLine();
        if(!getPatientById(con,patient_id))
        {
            System.out.println("there is no patient on this id and name");
            return;
        }
        try
        {
            String Query="DELETE FROM patients WHERE id= ? AND name=?";
            PreparedStatement ps=con.prepareStatement(Query);
            ps.setInt(1,patient_id);
            ps.setString(2,Name);
            int affectedRow=ps.executeUpdate();
            if(affectedRow > 0)
            {
                System.out.println("patient "+ patient_id + " " + Name +" is Deleted successfully!");
            }
            else {
                System.out.println("pai");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void viewPatients(Connection con) {
        try {
            String Query = "SELECT * FROM patients";
            PreparedStatement ps = con.prepareStatement(Query);
            ResultSet rs = ps.executeQuery();

            System.out.println("+------------------+--------------+------------+------------+");
            System.out.println("| Patient id       |   Name       |   Age      | Gender     |");
            System.out.println("+------------------+--------------+------------+------------+");
            while (rs.next()) {
                int patient_id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.printf("| %-16s | %-12s | %-10s | %-10s |\n", patient_id, name, age, gender);
                System.out.println("+------------------+--------------+------------+------------+");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //    Purpose: This is used to verify if a patient exists by their ID.
    public boolean getPatientById(Connection con, int id) {
        try {
            String Query = "SELECT * FROM patients WHERE id=?";
            PreparedStatement ps = con.prepareStatement(Query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void displayPatientDetails(Connection con,Scanner sc) {
        try
        {
            System.out.println("Enter patient Id:");
            int patient_id = sc.nextInt();
            if (!getPatientById(con, patient_id)) {
                System.out.println("there is no patient on this id");
                return;
            }
            System.out.println("Patient Found! Here are the details:");
            String query = "SELECT * FROM patients WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patient_id);
            ResultSet rs = ps.executeQuery();
            System.out.println("+------------------+--------------+------------+------------+");
            System.out.println("| Patient id       |   Name       |   Age      | Gender     |");
            System.out.println("+------------------+--------------+------------+------------+");
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.printf("| %-16s | %-12s | %-10s | %-10s |\n", patient_id, name, age, gender);
                System.out.println("+------------------+--------------+------------+------------+");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient details: " + e.getMessage());
        }
    }
}
