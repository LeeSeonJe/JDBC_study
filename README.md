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

        Class.forName("oracle.jdbc.driver.OracleDriver"); // 연결할 Class 경로
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

### eclipse git push Error - "rejected -non-fast-forword"
+ 원인
  + Repositories를 생성후에 JDBC파일을 push하는 과정에서 오류가 난 것 같다.  
  무엇을 잘못했는지 정확히는 파악하지 못하였다.
+ 이유 
  + 현재 작업하고 있는 Repository가 서버에 있는 Repository보다 오래된 내용이기 때문에 오류를 뱉고있다.
  + Push를 하게되면 서버에 있는 최신 내용은 날라가고 현재 작업한 내용만 적용되는 문제로 인해 에러가 발생하는 것 같다.
+ 해결
  + 강제적으로 push를 할려고 했지만 좋은 방법이지 않은 것 같다.
  + 또한 eclipse가 아닌 git bash로 branch를 확인하였을 때 알 수 없는 branch가 생겨있는 것을 확인하였다.
  + 그리고 push 과정에서 rejected - non-fast-forward 키워드가 발생하는 것을 보고 검색을 통해 해결을 하였다.
  + [rejected - non-fast-forward](https://m.blog.naver.com/sim4858/220924984480)
+ 추가로 확인해야할 것
  + 우선 이전 잘못된 push로 생긴 잘못된 시점의 master brach와 현재 시점의 master의 marge를 통해 해결하였다.
  + git에 대해 정리된 책을 하나 구입해서 읽어봐야할거 같다.
  + 기본적으로 branch의 통상적인 개념은 알고 있으나 좀더 찾아봐야할 거 같다.
