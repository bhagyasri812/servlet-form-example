package genre;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Second")


public class Second extends GenericServlet {
	
	public void init()
	{
		System.out.println("init");
	}

    /**
     * Default constructor. 
     */
    public void service(ServletRequest req, ServletResponse res)throws ServletException,IOException {
        PrintWriter pw=res.getWriter();
        res.setContentType("text/html");
        String a=req.getParameter("t1");
        String b=req.getParameter("t2");
        String c=req.getParameter("t3");
        String d=req.getParameter("t4");
        String e=req.getParameter("t5");
        String f=req.getParameter("b1");
        pw.println("This name is"+a);
        pw.println("This address is"+b);
        pw.println("This phn no is"+c);
        pw.println("This email is"+d);
        pw.println("This empno is"+e);
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456789");
        	if(f.equals("Insert")) {
        		PreparedStatement st=con.prepareStatement("insert into mphasii values(?,?,?,?,?)");

        		st.setString(1, a);
        		st.setString(2, b);
        		st.setString(3, c);
        		st.setString(4, d);
        		st.setString(5, e);
        		st.execute();
        		pw.println("row inserted");
        		}
        	if(f.equals("Update")) {
        		PreparedStatement st=con.prepareStatement("update mphasii set address=? where empno=?");
        		st.setString(1, b);
        		st.setString(2, e);
        		st.execute();
        		pw.println("updated");
        	}
        	if(f.equals("Delete")) {
        		PreparedStatement st=con.prepareStatement("delete from mphasii where empno=?");
        		st.setString(1, e);
        		st.execute();
        		
        		pw.println("deleted");
        	}
        	if(f.equals("Select")) {
        		Statement st1=con.createStatement();
        		ResultSet rs=st1.executeQuery("select * from mphasii");  
        		
        		while(rs.next())  
        		System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
        		pw.println("row displayed");
        	}
        	con.close();
        }
        catch(Exception ae) {
        	ae.printStackTrace();
        }
       
    }
    public void destroy() {
    	System.out.println("destroy");
    }

}
