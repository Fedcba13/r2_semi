package com.r2.board.model.dao;






import static com.r2.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.r2.admin.model.dao.AdminMemberDAO;
import com.r2.board.model.vo.BoardComment;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;
import com.r2.member.model.dao.MemberDAO;

public class BoardDAO {
	
	private Properties prop = new Properties();

	public BoardDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public static List<FreeBoard> selectBoardListAll(Connection conn, int cPage, int numPerPage) {
		List<FreeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String query = prop.getProperty("selectBoardCount");
		
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement("SELECT * FROM ( SELECT ROWNUM AS RNUM, V.* FROM( SELECT * FROM free_BOARD where free_board_enabled = 1 ORDER BY free_BOARD_DATE DESC) V ) V WHERE RNUM BETWEEN ? AND ? ");
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			
			
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				FreeBoard fb = new FreeBoard();
				//컬럼명은 대소문자 구분이 없다.
				fb.setFree_Board_No(rset.getString("FREE_BOARD_NO"));
				fb.setFree_Board_Title(rset.getString("FREE_BOARD_TITLE"));
				fb.setFree_Board_Writer(rset.getString("FREE_BOARD_WRTIER"));
				fb.setFree_Board_Content(rset.getString("free_Board_Content"));
				fb.setFree_Board_Date(rset.getDate("FREE_BOARD_DATE"));
				fb.setFree_Board_ReadCount(rset.getInt("free_Board_ReadCount"));
				fb.setFree_Board_Category(rset.getInt("FREE_BOARD_CATEGORY"));
				fb.setFree_Board_Enabled(rset.getInt("FREE_BOARD_ENABLED"));
				fb.setFree_Board_Ref(rset.getInt("FREE_BOARD_REF"));
				
				list.add(fb);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}

		return totalMember;
	}



	public int increaseReadCount(Connection conn, String freeBoardNo) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, freeBoardNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int board_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, board_no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public FreeBoard selectOne(Connection conn, String freeBoardNo) {
		FreeBoard fb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, freeBoardNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				fb = new FreeBoard();
				fb.setFree_Board_No(rset.getString(1));
				fb.setFree_Board_Title(rset.getString(2));
				fb.setFree_Board_Writer(rset.getString(3));
				fb.setFree_Board_Content(rset.getString(4));
				fb.setFree_Board_Date(rset.getDate(5));
				fb.setFree_Board_ReadCount(rset.getInt(6));
				fb.setFree_Board_Category(0);
				fb.setFree_Board_Enabled(rset.getInt(8));
				fb.setFree_Board_Ref(rset.getInt(9));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return fb;
	}



	public int insertFreeBoard(Connection conn, FreeBoard fb) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertFreeBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, fb.getFree_Board_Title());
			pstmt.setString(2, fb.getFree_Board_Writer());
			pstmt.setString(3, fb.getFree_Board_Content());

			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int selectLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}



	public List<FreeBoard> srchBoard(Connection conn, String srchType, String keyword, int cPage, int numPerPage) {
		List<FreeBoard> flist = new ArrayList<FreeBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		
		if("writer".equals(srchType)) {				
			query = prop.getProperty("srchBoardbyWriter");

		} else if("title".equals(srchType)) {				
			query = prop.getProperty("srchBoardbyTitle");

			
			
		} else if("uniSrch".equals(srchType)) {
			query = prop.getProperty("srchBoardbUniSrch");


			
			
		} else {
			System.out.println("srchType 오류");

		}
		

		
		

		
		try{
			
			
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			
			if("uniSrch".equals(srchType)) {				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, (cPage-1)*numPerPage+1);
				pstmt.setInt(4, cPage*numPerPage);
				
			} else {			
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				
			} 

			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				
				FreeBoard fb = new FreeBoard();
				fb.setFree_Board_No(rset.getString(2));
				fb.setFree_Board_Title(rset.getString(3));
				fb.setFree_Board_Writer(rset.getString(4));
				fb.setFree_Board_Content(rset.getString(5));
				fb.setFree_Board_Date(rset.getDate(6));
				fb.setFree_Board_ReadCount(rset.getInt(7));
				fb.setFree_Board_Category(8);
				fb.setFree_Board_Enabled(rset.getInt(9));
				fb.setFree_Board_Ref(rset.getInt(10));
				flist.add(fb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		

		return flist;
	}



	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardComment");
 
//		comment writer(1), 
//		comment level(2), 
//		content(3), 
//		board ref(4), 
//		comment ref(5), 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bc.getBoard_Comment_Writer());
			pstmt.setInt(2, bc.getBoard_Comment_Level());
			pstmt.setString(3, bc.getBoard_Comment_Content());
			pstmt.setString(4, bc.getBoard_Ref());
			//java - int: null을 허용하지 않음. 
			//oracle - number: null 
			pstmt.setString(5, bc.getBoard_Comment_Ref());

			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}



	public List<BoardComment> selectCommentList(Connection conn, String freeBoardNo) {
        List<BoardComment> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectCommentList");
        
        try{
            //미완성쿼리문을 가지고 객체생성.
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, freeBoardNo);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
                BoardComment bc = new BoardComment();
                //컬럼명은 대소문자 구분이 없다.
                bc.setBoard_Comment_No(rset.getString(2));
                bc.setBoard_Comment_Writer(rset.getString(3));
                bc.setBoard_Comment_Level(rset.getInt(4));
                bc.setBoard_Comment_Content(rset.getString(1));
                bc.setBoard_Ref(rset.getString(6));
                bc.setBoard_Comment_Ref(rset.getString(7));
                bc.setBoard_Comment_Date(rset.getDate(8));
                bc.setBoard_Comment_Enabled(rset.getInt(9));
                
                list.add(bc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
    }



	public int updateBoard(Connection conn, FreeBoard fb) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateFreeBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, fb.getFree_Board_Title());
			pstmt.setString(2, fb.getFree_Board_Content());
			pstmt.setString(3, fb.getFree_Board_No());

			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int deleteBoard(Connection conn, String boardNo) {
		int result = 0;		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteFreeBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, boardNo);


			System.out.println(query);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}



	public int srchBoardCountBy(Connection conn, String srchType, String keyword) {
		PreparedStatement pstmt = null;
		int totalPage = 0;
		ResultSet rset = null;
		String query = null;
		
		
		if("writer".equals(srchType)) {				
			query = prop.getProperty("srchBoardCountByWriter");
			
		} else if("title".equals(srchType)) {				
			query = prop.getProperty("srchBoardCountByTitle");
			
			
		} else if("uniSrch".equals(srchType)) {
			query = prop.getProperty("srchBoardCountByUnisrch");
			System.out.println("실행 unisrch");
			
			
		} else {
			System.out.println("srchType 오류");
			System.out.println("실행 error");
		}
		
		System.out.println(srchType);
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			if("uniSrch".equals(srchType)) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			} else {
				pstmt.setString(1, "%"+keyword+"%");
				
			}
			
			
			
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			
			
			
			
			
			while(rset.next()){
				totalPage = rset.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}

		System.out.println("srchcount@dao(totalPage)"+totalPage);
		return totalPage;
	}



	public int insertScenarioBoard(Connection conn, Scenario s) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertScenarioBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, s.getMember_Id());
			pstmt.setString(2, s.getScenario_Content());
			pstmt.setString(3, s.getTitle());

			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int selectLastScenarioSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int LastSeq = 0;
		String sql = prop.getProperty("selectScenarioLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				LastSeq = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return LastSeq;
	}



	public static List<Scenario> selectScenarioBoardListAll(Connection conn, int cPage, int numPerPage) {
		List<Scenario> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String query = prop.getProperty("selectBoardCount");
		
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement("SELECT * FROM ( SELECT ROWNUM AS RNUM, V.* FROM( SELECT * FROM scenario where scenario_enabled = 1 ORDER BY scenario_no DESC) V ) V WHERE RNUM BETWEEN ? AND ?");
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			
			
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Scenario s = new Scenario();
				//컬럼명은 대소문자 구분이 없다.
				s.setScenario_No(rset.getString("SCENARIO_NO"));
				s.setMember_Id(rset.getString("MEMBER_ID"));
				s.setScenario_Content(rset.getString("SCENARIO_CONTENT"));
				s.setScenario_Date(rset.getDate("SCENARIO_DATE"));
				s.setScenario_Enabled(rset.getInt("SCENARIO_ENABLED"));
				s.setTitle(rset.getString("TITLE"));
				s.setReadCount(rset.getInt("READCOUNT"));
				list.add(s);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}



	public int selectScenarioBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement("select count(*) from scenario where scenario_enabled = 1");
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}

		return totalMember;
	}



	public int increaseScenarioReadCount(Connection conn, String scenarioNo) {
		// TODO Auto-generated method stub
				int result = 0;
				PreparedStatement pstmt = null;
				
				try {
					//미완성쿼리문을 가지고 객체생성.
					pstmt = conn.prepareStatement("update scenario set READCOUNT = READCOUNT + 1 where SCENARIO_NO = ?");
					pstmt.setString(1, scenarioNo);
					
					//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
					//DML은 executeUpdate()
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
				
				return result;
	}



	public Scenario selectScenarioOne(Connection conn, String scenarioNo) {
		Scenario s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectScenarioOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, scenarioNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				s = new Scenario();
				s.setScenario_No(rset.getString("SCENARIO_NO"));
				s.setMember_Id(rset.getString("MEMBER_ID"));
				s.setScenario_Content(rset.getString("SCENARIO_CONTENT"));
				s.setScenario_Date(rset.getDate("SCENARIO_DATE"));
				s.setScenario_Enabled(rset.getInt("SCENARIO_ENABLED"));
				s.setTitle(rset.getString("TITLE"));
				s.setReadCount(rset.getInt("READCOUNT"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return s;
	}



	public int updateScenarioBoard(Connection conn, Scenario s) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateScenarioBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, s.getTitle());
			pstmt.setString(2, s.getScenario_Content());
			pstmt.setString(3, s.getScenario_No());

			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int deleteScenario(Connection conn, String boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteScenario"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, boardNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public List<Scenario> srchScenario(Connection conn, String srchType, String keyword, int cPage, int numPerPage) {
		List<Scenario> s = new ArrayList<Scenario>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		
		if("writer".equals(srchType)) {				
			query = prop.getProperty("srchScenariobyWriter");

		} else if("title".equals(srchType)) {				
			query = prop.getProperty("srchScenariobyTitle");

			
			
		} else if("uniSrch".equals(srchType)) {
			query = prop.getProperty("srchScenarioUniSrch");


			
			
		} else {
			System.out.println("srchType 오류");

		}
		

		
		

		
		try{
			
			
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			
			if("uniSrch".equals(srchType)) {				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, (cPage-1)*numPerPage+1);
				pstmt.setInt(4, cPage*numPerPage);
				
			} else {			
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				
			} 

			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				
				Scenario ss = new Scenario();
				ss.setScenario_No(rset.getString(2));
				ss.setMember_Id(rset.getString(3));
				ss.setScenario_Content(rset.getString(4));
				ss.setScenario_Date(rset.getDate(5));
				ss.setScenario_Enabled(rset.getInt(6));
				ss.setTitle(rset.getString(7));
				ss.setReadCount(rset.getInt(8));
				s.add(ss);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		

		return s;
	}



	public int srchScenarioCountBy(Connection conn, String srchType, String keyword) {
		PreparedStatement pstmt = null;
		int totalPage = 0;
		ResultSet rset = null;
		String query = null;
		
		
		if("writer".equals(srchType)) {				
			query = prop.getProperty("srchScenarioCountByWriter");
			
		} else if("title".equals(srchType)) {				
			query = prop.getProperty("srchScenarioCountByTitle");
			
			
		} else if("uniSrch".equals(srchType)) {
			query = prop.getProperty("srchScenarioCountByUnisrch");
			
			
		} else {
			System.out.println("srchType 오류");
			System.out.println("실행 error");
		}
		
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			if("uniSrch".equals(srchType)) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			} else {
				pstmt.setString(1, "%"+keyword+"%");
				
			}
			
			
			
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			
			
			
			
			
			while(rset.next()){
				totalPage = rset.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}

		return totalPage;
	}



	public int deleteBoardComment(Connection conn, String boardCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoardComment"); 
		
		System.out.println("boardCommentNo"+boardCommentNo);
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, boardCommentNo);
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



	public int insertScenarioBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertScenarioBoardComment");
 
//		comment writer(1), 
//		comment level(2), 
//		content(3), 
//		board ref(4), 
//		comment ref(5), 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bc.getBoard_Comment_Writer());
			pstmt.setInt(2, bc.getBoard_Comment_Level());
			pstmt.setString(3, bc.getBoard_Comment_Content());
			pstmt.setString(4, bc.getBoard_Ref());
			//java - int: null을 허용하지 않음. 
			//oracle - number: null 
			pstmt.setString(5, bc.getBoard_Comment_Ref());

			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}
	
	

}
