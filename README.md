# TIL_JDBC

### JDBC
> ### JDBC 예시
  ```SQL
  package testJDBC;

  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;

  public class JDBCTest {
    public static void main(String[] args) {
      Connection conn = null;
      PreparedStatement pstmt = null; 
      try {
  //                    공퉁부분      로컬호스트 포트번호 SID
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "KH"; // 접속할 ID
        String pw = "KH"; // 접속할 PW

        Class.forName("testJDBC.JDBCTest"); // 연결할 Class 경로
        conn = DriverManager.getConnection(url, id, pw);

        System.out.println("연결성공");
        String sql = "select * from EMPLOYEE where DEPT_CODE = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "D1");

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
          System.out.print(rs.getString("EMP_ID") + " ");
          System.out.print(rs.getString("EMP_NAME")+ " ");
          System.out.println(rs.getString("DEPT_CODE"));
          System.out.println();
        }
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        if (rs != null) try { rs.close();} catch (SQLException e) {}
        if (pstmt != null) try { pstmt.close();} catch (SQLException e) {}
        if (conn != null) try { conn.close();} catch (SQLException e) {}
      }
    }
  }

  ```
