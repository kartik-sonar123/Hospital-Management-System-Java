package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Doctor
{
    public void viewDoctors(Connection con)
    {
        try
        {
            String Query="SELECT * FROM doctors";
            PreparedStatement ps = con.prepareStatement(Query);
            ResultSet rs=ps.executeQuery();

            System.out.println("+-----------------+-------------------+----------------------------+");
            System.out.println("| Doctor id       | Name              | Specialization             |");
            System.out.println("+-----------------+-------------------+----------------------------+");
            while(rs.next())
            {
                int doctor_id=rs.getInt("id");
                String name=rs.getString("name");
                String specialization =rs.getString("specilization");
                System.out.printf("| %-15s | %-17s | %-26s |\n", doctor_id, name, specialization);
                System.out.println("+-----------------+-------------------+----------------------------+");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public boolean getDoctorById(Connection con,int id)
    {
        try
        {
            String Query="SELECT * FROM doctors where id=?";
            PreparedStatement ps= con.prepareStatement(Query);
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
