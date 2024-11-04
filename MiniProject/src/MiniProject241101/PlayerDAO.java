package MiniProject241101;

//PlayerDAO.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
	private String user = "scott"; // 사용자 이름
	private String password = "tiger"; // 비밀번호

	public void insertPlayer(Player player3) throws SQLException {
		String query = "INSERT INTO players3 (id, name, nickname, age, team) VALUES (player_id_seq.NEXTVAL, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, player3.getName());
			pstmt.setString(2, player3.getNickname());
			pstmt.setInt(3, player3.getAge());
			pstmt.setString(4, player3.getTeam());
			pstmt.executeUpdate();
		}
	}

	public List<Player> getAllPlayers() throws SQLException {
		List<Player> players3 = new ArrayList<>();
		String query = "SELECT * FROM players3";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				Player player3 = new Player(rs.getString("name"), rs.getString("nickname"), rs.getInt("age"),
						rs.getString("team"));
				players3.add(player3);
			}
		}
		return players3;
	}
}
