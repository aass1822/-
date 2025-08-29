package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dtos.MemberDto;

public class DbUtils {
	// 속성 (DB 접속에 필요한 정보)
	private static String id = "root";
	private static String pw = "1234";
	private static String url = "jdbc:mysql://localhost:3306/opendatadb";

	// JDBC 관련 객체
	private static Connection conn = null;        // DB와 연결하는 객체
	private static PreparedStatement pstmt = null; // SQL 실행을 위한 객체
	private static ResultSet rs = null;           // SELECT 결과를 저장하는 객체

	// 연결
	public static void conn() throws Exception {
		// 드라이버 로드 및 DB 연결
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loading Success");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("DB Conn");
	}

	public static void disConn() throws Exception {
		// rs / pstmt / conn 닫기 처리
		if(rs!=null) {
			rs.close();
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}

	public static int insertMember(MemberDto memberDto) throws Exception {
		// tbl_member 테이블에 dto 값 저장 후 int 반환
		pstmt = conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		pstmt.setString(1, memberDto.getUserid());
		pstmt.setString(2, memberDto.getPassword());
		pstmt.setString(3, memberDto.getUsername());
		pstmt.setString(4, memberDto.getZipcode());
		pstmt.setString(5, memberDto.getAddr1());
		pstmt.setString(6, memberDto.getAddr2());
		pstmt.setString(7, memberDto.getPhone1());
		pstmt.setString(8, memberDto.getPhone2());
		pstmt.setString(9, memberDto.getPhone3());
		pstmt.setString(10, memberDto.getTel1());
		pstmt.setString(11, memberDto.getTel2());
		pstmt.setString(12, memberDto.getTel3());
		pstmt.setString(13, memberDto.getEmail());
		pstmt.setString(14, memberDto.getYear());
		pstmt.setString(15, memberDto.getMonth());
		pstmt.setString(16, memberDto.getDay());
	
		int result = pstmt.executeUpdate();
		
		return result;
	}

	public static MemberDto selectMember(String userid) throws Exception {
		// tbl_member 테이블에서 userid에 해당하는 데이터 조회 후 MemberDto 반환
		pstmt = conn.prepareStatement("select * from tbl_member where userid=?");
		pstmt.setString(1,userid);
		rs = pstmt.executeQuery();
		MemberDto dto = null;
		if(rs != null && rs.next()) {

				dto = new MemberDto();
				dto.setUserid(rs.getString("userid"));
				dto.setUserid(rs.getString("password"));
				dto.setUserid(rs.getString("username"));
				dto.setUserid(rs.getString("zipcode"));
				dto.setUserid(rs.getString("addr1"));
				dto.setUserid(rs.getString("addr2"));
				dto.setUserid(rs.getString("phone1"));
				dto.setUserid(rs.getString("phone2"));
				dto.setUserid(rs.getString("phone3"));
				dto.setUserid(rs.getString("tel1"));
				dto.setUserid(rs.getString("tel2"));
				dto.setUserid(rs.getString("tel3"));
				dto.setUserid(rs.getString("email"));
				dto.setUserid(rs.getString("year"));
				dto.setUserid(rs.getString("month"));
				dto.setUserid(rs.getString("day"));
			
		}
		return dto;
	}

	public static void TxStart() throws Exception {
		if (conn != null)
			conn.setAutoCommit(false);
	}

	public static void TxEnd() throws Exception {
		if (conn != null)
			conn.commit();
	}

	public static void RollBack() throws Exception {
		if (conn != null)
			conn.rollback();
	}
}
