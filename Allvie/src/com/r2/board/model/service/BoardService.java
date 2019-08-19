package com.r2.board.model.service;





import static com.r2.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.r2.board.model.dao.BoardDAO;
import com.r2.board.model.vo.BoardComment;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;

public class BoardService {

	public List<FreeBoard> selectBoardListAll(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FreeBoard> list = BoardDAO.selectBoardListAll(conn, cPage, numPerPage);
		
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		
		Connection conn = getConnection();
		int totalBoardCount = new BoardDAO().selectBoardCount(conn);
		close(conn);
		return totalBoardCount;
		
		
	}

	public FreeBoard selectOne(String freeBoardNo, boolean hasRead) {
		Connection conn = getConnection();
		
		//조회수 증가
		int result = 0;
		if(!hasRead) {
			result = new BoardDAO().increaseReadCount(conn, freeBoardNo);			
		}
		
		//2.게시글 조회
		FreeBoard fb = new BoardDAO().selectOne(conn, freeBoardNo);
		
		//트랜잭션 처리
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return fb;
	}
	
	public FreeBoard selectOne(String boardNo) {
		Connection conn = getConnection();
		FreeBoard board = new BoardDAO().selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	public int insertBoard(FreeBoard fb) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertFreeBoard(conn, fb);

		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new BoardDAO().selectLastSeq(conn);
			System.out.println("확인할것= "+result);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<FreeBoard> srchBoard(String srchType, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FreeBoard> flist = new BoardDAO().srchBoard(conn, srchType, keyword, cPage, numPerPage);
		

		
		close(conn);
		return flist;
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoardComment(conn, bc);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public List<BoardComment> selectCommentListByBoardNo(String freeBoardNo) {
		Connection conn = getConnection();
		List<BoardComment> list= new BoardDAO().selectCommentList(conn, freeBoardNo);
		close(conn);
		return list;
	}

	public int updateFreeBoard(FreeBoard fb) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateBoard(conn, fb);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteFreeBoard(String boardNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoard(conn, boardNo);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int srchBoardCountBy(String srchType, String keyword) {
		
		Connection conn = getConnection();
		int totalBoardCount = new BoardDAO().srchBoardCountBy(conn, srchType, keyword);
		close(conn);
		return totalBoardCount;
	}

	public int insertScenarioBoard(Scenario s) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertScenarioBoard(conn, s);
		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new BoardDAO().selectLastScenarioSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		return result;
		
		
		
		
	}

	public List<Scenario> selectScenarioBoardListAll(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Scenario> list = BoardDAO.selectScenarioBoardListAll(conn, cPage, numPerPage);
		
		close(conn);
		return list;
	}

	public int selectScenarioBoardCount() {
		Connection conn = getConnection();
		int totalBoardCount = new BoardDAO().selectScenarioBoardCount(conn);
		close(conn);
		return totalBoardCount;
	}

	public Scenario selectScenarioOne(String scenarioNo, boolean hasRead) {
		Connection conn = getConnection();
		
		//조회수 증가
		int result = 0;
		if(!hasRead) {
			result = new BoardDAO().increaseScenarioReadCount(conn, scenarioNo);			
		}
		
		//2.게시글 조회
		Scenario s = new BoardDAO().selectScenarioOne(conn, scenarioNo);
		
		//트랜잭션 처리
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return s;
	}

	public int updateScenarioBoard(Scenario s) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateScenarioBoard(conn, s);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteScenario(String boardNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteScenario(conn, boardNo);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Scenario> srchScenario(String srchType, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Scenario> s = new BoardDAO().srchScenario(conn, srchType, keyword, cPage, numPerPage);
		

		
		close(conn);
		return s;
	}

	public int srchScenarioCountBy(String srchType, String keyword) {
		Connection conn = getConnection();
		int totalBoardCount = new BoardDAO().srchScenarioCountBy(conn, srchType, keyword);
		close(conn);
		return totalBoardCount;
	}

	public int deleteBoardComment(String boardCommentNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoardComment(conn, boardCommentNo);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertScenarioBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertScenarioBoardComment(conn, bc);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}






	
	
}
