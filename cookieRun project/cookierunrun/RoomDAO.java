package cookierunrun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO {
   private String driver = "oracle.jdbc.driver.OracleDriver";
   //private String url = "jdbc:oracle:thin:@192.168.0.20:1521:xe";
   private String url = "jdbc:oracle:thin:@192.168.0.159:1521:xe";
   private String username = "c##java";
   private String password = "bit";
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   public RoomDAO() {
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   public ArrayList<RoomDTO> selectAllRoom() {
      ArrayList<RoomDTO> ar = new ArrayList<RoomDTO>();
      getConnection();
      String sql = "select * from roomTable";
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomNumber(rs.getInt("roomNumber"));
            roomDTO.setRoomName(rs.getString("roomName"));
            roomDTO.setRoomPw(rs.getString("roomPw"));
            roomDTO.setSecretRoom(rs.getInt("secretRoom"));
            roomDTO.setUser1(rs.getString("user1"));
            roomDTO.setUser2(rs.getString("user2"));
            roomDTO.setUserCount(rs.getInt("userCount"));
            roomDTO.setRoomState(rs.getInt("roomState"));

            ar.add(roomDTO);
         }
      } catch (SQLException e) {
         e.printStackTrace();
         ar = null;
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return ar;
   }
   
   public void updateRoomInf(RoomDTO roomDTO) {
	   getConnection();
	   String sql = "update roomtable set roomname=?,roompw=?,secretroom=?,user1=?,user2=?,usercount=?,roomstate=? where roomNumber = ?";
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,roomDTO.getRoomName());
		pstmt.setString(2,roomDTO.getRoomPw());
		pstmt.setInt(3,roomDTO.getSecretRoom());
		pstmt.setString(4,roomDTO.getUser1());
		pstmt.setString(5,roomDTO.getUser2());
		pstmt.setInt(6,roomDTO.getUserCount());
		pstmt.setInt(7, roomDTO.getRoomState());
		pstmt.setInt(8, roomDTO.getRoomNumber());
		pstmt.executeUpdate();
		
	} catch (SQLException e) {		
		e.printStackTrace();
	}finally {
		try {
			if (pstmt != null)
				pstmt.close();// 나중에 만든 것 먼저 끊기
			if (conn != null)
				conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	   
   }
}
   
   public void updateUser2(String user2, int roomNum) {
	   getConnection();
	   String sql = "update roomtable set user2 = ?, usercount = 2 where roomNumber = ?";
	   try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user2);
		pstmt.setInt(2, roomNum);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   finally {
			try {
				if (pstmt != null)
					pstmt.close();// 나중에 만든 것 먼저 끊기
				if (conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	   }
   }
}