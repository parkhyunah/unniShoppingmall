package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Cart;
import model.Comments;
import model.Delivery;
import model.EtcBoard;
import model.Item;
import model.ItemBoard;
import model.Member;
import model.NoticeBoard;
import model.OrderItem;

public class UnniDao {
	private static UnniDao instance;
	private UnniDao() {
	}
	public static UnniDao getInstance() {
		if (instance == null) {
			instance = new UnniDao();
		}
		return instance;
	}

	/*******************************************************************************************************/
	/* * CART/장바구니**************************************************************************************/
	/*******************************************************************************************************/

	//1. memberId가 구매할 총 가격 검색
	public int SelectCartPriceByMemberId(String memberId) {
		String sql = "select sum(itemtotal) from cart where memberid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("sum(itemtotal)");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//2. insertCart() : 장바구니 넣기
	public void insertCart(Cart cart) {
		String sql = "insert into cart(itemCode,itemName,itemMainPic,itemColor,memberID,itemQty,itemTotal,itemPrice) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getItemCode());
			pstmt.setString(2, cart.getItemName());
			pstmt.setString(3, cart.getItemMainPic());
			pstmt.setString(4, cart.getItemColor());
			pstmt.setString(5, cart.getMemberID());
			pstmt.setInt(6, cart.getItemQty());
			pstmt.setInt(7, cart.getItemTotal());
			pstmt.setInt(8, cart.getItemPrice());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 3. selectAllByMemberId : memberId로 장바구니 검색
	public List<Cart> selectAllByMemberId(String memberId) {
		String sql = "select * from cart where memberId =?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Cart> result = new ArrayList<Cart>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setItemCode(rs.getInt("itemcode"));
				cart.setItemMainPic(rs.getString("itemMainPic"));
				cart.setItemQty(rs.getInt("itemQty"));
				cart.setItemName(rs.getString("itemName"));
				cart.setItemTotal(rs.getInt("itemTotal"));
				cart.setItemColor(rs.getString("itemColor"));
				cart.setMemberID(rs.getString("memberId"));
				cart.setItemPrice(rs.getInt("itemPrice"));
				result.add(cart);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 4. updateCart() : cart 수정
	public void updateCart(Cart cart) {
		String sql = "update cart set itemQty=?, itemTotal=? where itemcode=? and itemColor=? and memberId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getItemQty());
			pstmt.setInt(2, cart.getItemQty() * cart.getItemPrice());
			pstmt.setInt(3, cart.getItemCode());
			pstmt.setString(4, cart.getItemColor());
			pstmt.setString(5, cart.getMemberID());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 5. deleteCart() : Cart 삭제
	public Boolean deleteCart(Cart cart) {
		String sql = "delete from cart where itemcode=? and itemColor=? and memberId=? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getItemCode());
			pstmt.setString(2, cart.getItemColor());
			pstmt.setString(3, cart.getMemberID());
			pstmt.executeUpdate();
			result = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 6. deleteCart() : item코드로 삭제하기
	public void deleteCart(int itemcode) {
		String sql = "delete from cart where itemcode=? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);
			pstmt.executeUpdate();
			result = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//7. SelectOneCart() : cart 검색하기
	public boolean SelectOneCart(String memberId, String itemColor, int itemCode) {
		String sql = "select * from cart where memberId = ? and itemcolor=? and itemcode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, itemColor);
			pstmt.setInt(3, itemCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/***************************************************************************************************/
	/***************************************** NoticeBoard/공지사항*************************************/
	/***************************************************************************************************/
	// 1. selectNoticeByTitle() : 공지사항 제목으로 검색
	public List<NoticeBoard> selectNoticeByTitle(String title) {
		String sql = "select * from noticeboard where noticetitle like ('%' || ? || '%') order by noticenum desc";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<NoticeBoard> result = new ArrayList<NoticeBoard>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				noticeBoard.setNoticeNum(rs.getInt("noticeNum"));
				noticeBoard.setAdminId(rs.getString("adminId"));
				noticeBoard.setNoticeTitle(rs.getString("noticeTitle"));
				noticeBoard.setNoticeContent(rs.getString("noticeContent"));
				noticeBoard.setNoticeReadCount(rs.getInt("noticeReadCount"));
				result.add(noticeBoard);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 2. selectNoticeByContent() : 공지사항 내용으로 검색
	public List<NoticeBoard> selectNoticeByContent(String content) {
		String sql = "select * from noticeboard where noticecontent like ('%' || ? || '%') order by noticenum desc";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<NoticeBoard> result = new ArrayList<NoticeBoard>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				noticeBoard.setNoticeNum(rs.getInt("noticeNum"));
				noticeBoard.setAdminId(rs.getString("adminId"));
				noticeBoard.setNoticeTitle(rs.getString("noticeTitle"));
				noticeBoard.setNoticeContent(rs.getString("noticeContent"));
				noticeBoard.setNoticeReadCount(rs.getInt("noticeReadCount"));
				result.add(noticeBoard);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 3. selectAllNoticeBoard() : 공지사항 전체검색
	public List<NoticeBoard> selectAllNoticeBoard() {
		String sql = "select * from noticeboard order by noticenum desc";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<NoticeBoard> result = new ArrayList<NoticeBoard>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				noticeBoard.setNoticeNum(rs.getInt("noticeNum"));
				noticeBoard.setAdminId(rs.getString("adminId"));
				noticeBoard.setNoticeTitle(rs.getString("noticeTitle"));
				noticeBoard.setNoticeContent(rs.getString("noticeContent"));
				noticeBoard.setNoticeReadCount(rs.getInt("noticeReadCount"));
				result.add(noticeBoard);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 4. insertNoticeBoard() : 공지사항 글 추가하기
	public void insertNoticeBoard(NoticeBoard noticeBoard) {
		String sql = "insert into noticeboard values(NOTICEBOARD_SEQ.nextval, ?,?,?,0)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeBoard.getAdminId());
			pstmt.setString(2, noticeBoard.getNoticeTitle());
			pstmt.setString(3, noticeBoard.getNoticeContent());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//5. updateNoticeBoard() : 공지 사항 글 제목/내용 수정
	public void updateNoticeBoard(NoticeBoard noticeBoard) {
		String sql = "update noticeboard" + " set noticeTitle = ?," + "     noticeContent =?" + " where noticeNum= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeBoard.getNoticeTitle());
			pstmt.setString(2, noticeBoard.getNoticeContent());
			pstmt.setInt(3, noticeBoard.getNoticeNum());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 6. deleteNoticeBoard() : 공지사항 글 번호로 삭제
	public void deleteNoticeBoard(int noticeNum) {
		String sql = "delete from noticeboard where noticeNum = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//7. updateReadCount() : 공지사항 조회수 카운트
	public void updateReadCount(int noticeReadCount) {
		String sql = "update noticeboard set noticereadCount=noticereadCount+1 where noticenum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeReadCount);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/***************************************************************************************************/
	/**************************************EtcBoard/Q&A게시판*******************************************/
	/***************************************************************************************************/
	// 1. selectCountEtcBoardForSearch() : 검색을 위해 EtcBoard 글 개수
	   public int selectCountEtcBoardForSearch(int bType, String keyword) {
	         int count = 0;
	         String sql = "select count(*) from etcboard where bType =? and memberid like ('%' || ? || '%')";
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            conn = ConnectionProvider.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, bType);
	            pstmt.setString(2, keyword);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	               count = rs.getInt(1);
	            }
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            try {
	               if (conn != null) conn.close();
	               if (rs != null) rs.close();
	               if (pstmt != null) pstmt.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         return count;
	      }
	   
	
	//2. selectEtcBoardByIdForPaging() : 페이징처리를 위한 기타게시판 ID로 검색
	   public List<EtcBoard> selectEtcBoardByIdForPaging(int firstRow, int endRow, int bType, String keyword) {
	         Connection connection = null;
	         String sql = "select * from (select rownum as rnum, m1.boardnum,m1.memberid,m1.itemInfo,m1.regdate, m1.boardtitle,m1.boardcontent,m1.btype,m1.bnum from (select boardnum,memberid,itemInfo,regdate,boardtitle,boardcontent,btype,bnum from etcboard order by boardnum desc) m1) where (rnum between ? and ?) and bType=? and memberid like ('%' || ? || '%')  ";
	         ResultSet rs = null;
	         PreparedStatement pstmt = null;
	         List<EtcBoard> result = new ArrayList<EtcBoard>();
	         try {
	            connection = ConnectionProvider.getConnection();
	            pstmt = connection.prepareStatement(sql);
	            pstmt.setInt(1, firstRow);
	            pstmt.setInt(2, endRow);
	            pstmt.setInt(3, bType);
	            pstmt.setString(4, keyword);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	               EtcBoard etcBoard = new EtcBoard();
	               etcBoard.setBoardNum(rs.getInt("boardnum"));
	               etcBoard.setbNum(rs.getInt("bnum"));
	               etcBoard.setMemberId(rs.getString("memberid"));
	               etcBoard.setItemInfo(rs.getString("itemInfo"));
	               etcBoard.setRegDate(rs.getDate("regdate"));
	               etcBoard.setBoardTitle(rs.getString("boardtitle"));
	               etcBoard.setBoardContent(rs.getString("boardcontent"));
	               etcBoard.setbType(1);
	               result.add(etcBoard);
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } finally {
	            try {
	               if (connection != null) connection.close();
	               if (pstmt != null) pstmt.close();
	               if (rs != null) rs.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         return result;
	      }
	
	//3. selectEtcList() : 기타게시판 검색(페이징)
	public List<EtcBoard> selectEtcList(int firstRow,int endRow,int bType){
		String sql = "select * from (select rownum as rnum, m1.boardnum,m1.memberid,m1.iteminfo,m1.regdate, m1.boardtitle,m1.boardcontent,m1.btype,m1.bnum from (select boardnum,memberid,iteminfo,regdate,boardtitle,boardcontent,btype,bnum from etcboard order by boardnum desc) m1) where (rnum between ? and ?) and bType=?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<EtcBoard> result = new ArrayList<EtcBoard>();
		    try {
		        conn = ConnectionProvider.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, firstRow);
		        pstmt.setInt(2, endRow);
		        pstmt.setInt(3, bType);
		        rs = pstmt.executeQuery();
		        while(rs.next()) {
		            EtcBoard etcBoard = new EtcBoard();
		            etcBoard.setBoardNum(rs.getInt("boardnum"));
		            etcBoard.setMemberId(rs.getString("memberid"));
		            etcBoard.setItemInfo(rs.getString("itemInfo"));
		            etcBoard.setRegDate(rs.getDate("regdate"));
		            etcBoard.setBoardTitle(rs.getString("boardtitle"));
		            etcBoard.setBoardContent(rs.getString("boardcontent"));
		            etcBoard.setbType(rs.getInt("bType"));
		            etcBoard.setbNum(rs.getInt("bNum"));
		            result.add(etcBoard);
		        }
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try{
		            if(conn !=null) conn.close();
		            if(rs !=null) rs.close();
		            if(pstmt !=null) pstmt.close();
		        }catch(SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return result;
		}

		//4. selectCountEtcBoard() : 기타게시판 타입별로 글 수 세기
		public int selectCountEtcBoard(int bType) {
		    int count = 0;
		    String sql = "select count(*) from etcboard where bType =?";
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    try {
		        conn = ConnectionProvider.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, bType);
		        rs = pstmt.executeQuery();
		        if(rs.next()) {
		            count = rs.getInt(1);	
		        }    
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try{
		            if(conn !=null) conn.close();
		            if(rs !=null) rs.close();
		            if(pstmt !=null) pstmt.close();
		        }catch(SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return count;
		}
	
	// 5. selectEtcBoardById() : 기타게시판 아이디로 게시글 검색
	public List<EtcBoard> selectEtcBoardById(EtcBoard etcboard) {
		Connection connection = null;
		String sql = "select * from etcboard where btype = ? and (memberid like ('%' || ? || '%')) order by boardnum desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null; 
		List<EtcBoard> result = new ArrayList<EtcBoard>(); 
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, etcboard.getbType());
			pstmt.setString(2, etcboard.getMemberId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtcBoard etcBoard = new EtcBoard();
				etcBoard.setbType(rs.getInt("btype"));
				etcBoard.setBoardNum(rs.getInt("boardnum"));
				etcBoard.setbNum(rs.getInt("bnum"));
				etcBoard.setMemberId(rs.getString("memberid"));
				etcBoard.setItemInfo(rs.getString("itemInfo"));
				etcBoard.setRegDate(rs.getDate("regdate"));
				etcBoard.setBoardTitle(rs.getString("boardtitle"));
				etcBoard.setBoardContent(rs.getString("boardcontent"));
				result.add(etcBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 6. selectEtcBoardByContent() : 기타게시판 내용으로 게시글 찾기
	public List<EtcBoard> selectEtcBoardByContent(EtcBoard etcboard) {
		Connection connection = null;
		String sql = "select * from etcboard where btype = ? and (boardcontent like ('%' || ? || '%')) order by boardnum desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null; 
		List<EtcBoard> result = new ArrayList<EtcBoard>(); 
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, etcboard.getbType());
			pstmt.setString(2, etcboard.getBoardContent());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtcBoard etcBoard = new EtcBoard();
				etcBoard.setbType(rs.getInt("btype"));
				etcBoard.setBoardNum(rs.getInt("boardnum"));
				etcBoard.setbNum(rs.getInt("bnum"));
				etcBoard.setMemberId(rs.getString("memberid"));
				etcBoard.setItemInfo(rs.getString("itemInfo"));
				etcBoard.setRegDate(rs.getDate("regdate"));
				etcBoard.setBoardTitle(rs.getString("boardtitle"));
				etcBoard.setBoardContent(rs.getString("boardcontent"));
				result.add(etcBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 7. insertEtcBoardByQna() :  기타게시판(Q&A)에 게시글 추가하기
	public void insertEtcBoardByQna(EtcBoard etcBoard) {
		String sql = "insert into etcboard(boardnum, memberid, iteminfo, regdate, boardtitle, boardcontent, btype, bnum) "
				+ "values (ETCBOARD_SEQ.nextval, ?, ?, sysdate, ?, ?, 1, QNA_SEQ.nextval)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, etcBoard.getMemberId());
			pstmt.setString(2, etcBoard.getItemInfo());
			pstmt.setString(3, etcBoard.getBoardTitle());
			pstmt.setString(4, etcBoard.getBoardContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 8. insertEtcBoardByReview() : 기타게시판(Review)에 게시글 추가하기
	public void insertEtcBoardByReview(EtcBoard etcBoard) {
		String sql = "insert into etcboard(boardnum, memberid, iteminfo, regdate, boardtitle, boardcontent, btype, bnum) "
				+ "values (ETCBOARD_SEQ.nextval, ?, ?, sysdate, ?, ?, 2, review_SEQ.nextval)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, etcBoard.getMemberId());
			pstmt.setString(2, etcBoard.getItemInfo());
			pstmt.setString(3, etcBoard.getBoardTitle());
			pstmt.setString(4, etcBoard.getBoardContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 9. selectOneByTypeAndNum() : 기타게시판을 타입, 글번호로 하나만 조회
	public EtcBoard selectOneByTypeAndNum(int quabNum, int quabType) {
		// 커넥션 얻어오고,sql 작성하고, 구문객체 얻어오고, 쿼리 실행
		Connection connection = null;
		String sql = "select * from etcboard where btype = ? and bNum = ?";
		ResultSet rs = null; // 결과 받을 준비
		PreparedStatement pstmt = null; // sql 전송 을 위한 구문객체
		EtcBoard result = null;
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, quabType);
			pstmt.setInt(2, quabNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new EtcBoard();
				result.setbNum(rs.getInt("bNum"));
				result.setbType(rs.getInt("bType"));
				result.setItemInfo(rs.getString("itemInfo"));
				result.setMemberId(rs.getString("memberId"));
				result.setBoardTitle(rs.getString("boardTitle"));
				result.setBoardContent(rs.getString("boardContent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 10 selectOneByType() : 기타게시판을 타입별로 모든 게시글 검색
	public List<EtcBoard> selectOneByType(int bType) {
		Connection connection = null;
		String sql = "select * from etcboard where btype = ? order by bnum desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<EtcBoard> result = new ArrayList<EtcBoard>();
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtcBoard etcBoard = new EtcBoard();
				etcBoard.setbNum(rs.getInt("bnum"));
				etcBoard.setMemberId(rs.getString("memberId"));
				etcBoard.setItemInfo(rs.getString("itemInfo"));
				etcBoard.setRegDate(rs.getDate("regdate"));
				etcBoard.setBoardTitle(rs.getString("boardtitle"));
				etcBoard.setBoardContent(rs.getString("boardcontent"));
				etcBoard.setbType(rs.getInt("bType"));
				result.add(etcBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 11. selectEtcBoardByTitle() : 기타게시판 제목으로 게시글 찾기
	public List<EtcBoard> selectEtcBoardByTitle(EtcBoard etcboard) {
		Connection connection = null;
		String sql = "select * from etcboard where btype = ? and (boardtitle like ('%' || ? || '%')) order by boardnum desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<EtcBoard> result = new ArrayList<EtcBoard>(); 
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, etcboard.getbType());
			pstmt.setString(2, etcboard.getBoardTitle());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtcBoard etcBoard = new EtcBoard();
				etcBoard.setbNum(rs.getInt("bNum"));
				etcBoard.setBoardNum(rs.getInt("boardnum"));
				etcBoard.setMemberId(rs.getString("memberid"));
				etcBoard.setItemInfo(rs.getString("itemInfo"));
				etcBoard.setRegDate(rs.getDate("regdate"));
				etcBoard.setBoardTitle(rs.getString("boardtitle"));
				etcBoard.setBoardContent(rs.getString("boardcontent"));
				result.add(etcBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//12. selectQnaBoardById() : 기타게시판(Q&A)을 Id로 검색
	public List<EtcBoard> selectQnaBoardById(EtcBoard etcboard) {
		Connection connection = null;
		String sql = "select * from etcboard where memberid = ? and btype = ? order by boardnum desc";
		ResultSet rs = null; 
		PreparedStatement pstmt = null; 
		List<EtcBoard> result = new ArrayList<EtcBoard>();
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, etcboard.getMemberId());
			pstmt.setInt(2, etcboard.getbType());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtcBoard etcBoard = new EtcBoard();
				etcBoard.setBoardNum(rs.getInt("boardnum"));
				etcBoard.setbNum(rs.getInt("bnum"));
				etcBoard.setMemberId(rs.getString("memberid"));
				etcBoard.setItemInfo(rs.getString("itemInfo"));
				etcBoard.setRegDate(rs.getDate("regdate"));
				etcBoard.setBoardTitle(rs.getString("boardtitle"));
				etcBoard.setBoardContent(rs.getString("boardcontent"));
				etcBoard.setbType(1);
				result.add(etcBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//13. deleteEtcBoard() : 기타게시판 글 삭제
	public int deleteEtcBoard(int bType, int bNum) {
		int result = 0;
		String sql = "delete from etcboard where btype = ? and bnum = ?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bType);
			pstmt.setInt(2, bNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//14. updateEtcBoard() : 기타 게시판 제목/내용 수정하기
	public void updateEtcBoard(EtcBoard etcBoard) {
		String sql = "update etcboard set " + "boardtitle = ? ," + "boardcontent = ?" + " where bnum = ? and btype = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etcBoard.getBoardTitle());
			pstmt.setString(2, etcBoard.getBoardContent());
			pstmt.setInt(3, etcBoard.getbNum());
			pstmt.setInt(4, etcBoard.getbType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 15. deleteEtcBoard() : 기타게시판 작성자와 아이디가 동일하면 글 삭제
	public void deleteEtcBoard(String memberId) {
		int result = 0;
		String sql = "delete from etcboard where memberId = ?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = ConnectionProvider.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/***************************************************************************************************/
	/*********************************************Member/회원*******************************************/
	/***************************************************************************************************/

	// 1. modifyMember() : 회원 수정 (관리자가 회원 정보 수정할 때)
	public void modifyMember(Member member) {
		String sql = "update member set memberPass = ?, memberName = ?, memberAddress = ?, "
				+ "memberPhoneNum = ?, memberPoint = ? where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPass());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setString(4, member.getMemberPhoneNum());
			pstmt.setInt(5, member.getMemberPoint());
			pstmt.setString(6, member.getMemberId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 2. insertMember() : 회원 추가
	public void insertMember(Member member) {
		String sql = "insert into member values (?, ?, ?, ?, ?, ?, 0)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPass());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberAddress());
			pstmt.setString(5, member.getMemberPhoneNum());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 3. deleteMember : 회원 삭제
	public void deleteMember(String id) {
		String sql = "delete member where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 4. updateMember() : 회원정보 수정
	public void updateMember(Member member) {
		String sql = "update member set memberPass = ?, memberName = ?, memberAddress = ?, "
				+ "memberPhoneNum = ? where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPass());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setString(4, member.getMemberPhoneNum());
			pstmt.setString(5, member.getMemberId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 5. updateMemberPoint() : 회원의 적립금 수정
	public void updateMemberPoint(Member member) {
		String sql = "update member set memberPoint = ? where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberPoint());
			pstmt.setString(2, member.getMemberId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 6. SelectOneMember() : Id로 회원 검색
	public Member SelectOneMember(String memberId) {
		String sql = "select * from member where memberId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member result = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Member();
				result.setMemberId(rs.getString("memberId"));
				result.setMemberPass(rs.getString("memberPass"));
				result.setMemberName(rs.getString("memberName"));
				result.setMemberAddress(rs.getString("memberAddress"));
				result.setMemberPhoneNum(rs.getString("memberPhoneNum"));
				result.setMemberEmail(rs.getString("memberEmail"));
				result.setMemberPoint(rs.getInt("memberPoint"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 7. SelectAllMember() : 회원 전체 검색
	public List<Member> SelectAllMember() {
		List<Member> result = new ArrayList<Member>();
		String sql = "select * from member";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setMemberPass(rs.getString("memberPass"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberAddress(rs.getString("memberAddress"));
				member.setMemberPhoneNum(rs.getString("memberPhoneNum"));
				member.setMemberEmail(rs.getString("memberEmail"));
				member.setMemberPoint(rs.getInt("memberPoint"));
				result.add(member);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 8.selectOneMemberByEmail() : 이메일로 멤버 검색
		public Member selectOneMemberByEmail(String memberEmail) {
			String sql = "select * from member where memberEmail = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Member result = null;

			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberEmail);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					result = new Member();

					result.setMemberId(rs.getString("memberId"));
					result.setMemberPass(rs.getString("memberPass"));
					result.setMemberName(rs.getString("memberName"));
					result.setMemberAddress(rs.getString("memberAddress"));
					result.setMemberPhoneNum(rs.getString("memberPhoneNum"));
					result.setMemberEmail(rs.getString("memberEmail"));
					result.setMemberPoint(rs.getInt("memberPoint"));
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;

		}

	/***************************************************************************************************/
	/******************************************delivery/배송********************************************/
	/***************************************************************************************************/
	// 1. insertDelivery() : delivery 추가
	public void insertDelivery(Delivery delivery) {
		String sql = "insert into Delivery(deliveryNum, deliveryPrice,orderNum) " + "values (?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delivery.getDeliveryNum());
			pstmt.setInt(2, delivery.getDeliveryPrice());
			pstmt.setInt(3, delivery.getOrderNum());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 2. selectOneDelivery() : 배송하나 가져오기
	public Delivery selectOneDelivery(int deliveryNum) {
		String sql = "select * from Delivery where deliveryNum = ?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Delivery result = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deliveryNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Delivery();
				result.setDeliveryNum(rs.getInt("deliveryNum"));
				result.setDeliveryPrice(rs.getInt("deliveryPrice"));
				result.setDeliveryDate(rs.getDate("deliveryDate"));
				result.setDeliveryState(rs.getString("deliveryState"));
				result.setOrderNum(rs.getInt("orderNum"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 3. selectAllDelivery : 목록 가져오기
	public List<Delivery> selectAllDelivery() {
		String sql = "select * from Delivery order by deliveryNum desc";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Delivery> result = new ArrayList<Delivery>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Delivery delivery = new Delivery();
				delivery.setDeliveryNum(rs.getInt("deliveryNum"));
				delivery.setDeliveryPrice(rs.getInt("deliveryPrice"));
				delivery.setDeliveryDate(rs.getDate("deliveryDate"));
				delivery.setDeliveryState(rs.getString("deliveryState"));
				delivery.setOrderNum(rs.getInt("orderNum"));
				result.add(delivery);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 4. updateDelivery() : Delivery 수정
	public void updateDelivery(Delivery delivery) {
		String sql = "update Delivery set deliveryDate=?, deliveryState=? " + "where deliveryNum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, delivery.getDeliveryDate());
			pstmt.setString(2, delivery.getDeliveryState());
			pstmt.setInt(3, delivery.getDeliveryNum());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 5. deleteDelivery() : 삭제
	public void deleteDelivery(int deliveryNum) {
		String sql = "delete from Delivery where deliveryNum = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deliveryNum);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/***************************************************************************************************/
	/***************************************** OrderItem 주문*******************************************/
	/***************************************************************************************************/
	// 1. insertOrder() : OrderItem 추가
	   public void insertOrder(OrderItem orderItem) {
	      String sql = 
	   "insert into orderitem(memberId, orderNum, orderDate, orderAddress, orderPhoneNum, memberName, orderTotalPrice,"
	   + "paymentName,itemColor,itemQty,itemCode,orderItemNum, deliveryState, itemMainPic, itemName, itemPrice) "
	   + "values (?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, orderitem_seq.nextval, '배송전', ?, ?, ?)";
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = ConnectionProvider.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, orderItem.getMemberId());
	         pstmt.setInt(2, orderItem.getOrderNum());
	         pstmt.setString(3, orderItem.getOrderAddress());
	         pstmt.setString(4, orderItem.getOrderPhoneNum());
	         pstmt.setString(5,orderItem.getMemberName());
	         pstmt.setInt(6, orderItem.getOrderTotalPrice());
	         pstmt.setString(7, orderItem.getPaymentName());
	         pstmt.setString(8, orderItem.getItemColor());
	         pstmt.setInt(9, orderItem.getItemQty());
	         pstmt.setInt(10, orderItem.getItemCode());
	         pstmt.setString(11, orderItem.getItemMainPic());
	         pstmt.setString(12, orderItem.getItemName());
	         pstmt.setInt(13, orderItem.getItemPrice());
	         pstmt.executeUpdate();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (conn != null) conn.close();
	            if (pstmt != null) pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }

	   // 2. selectAllOrder() : orderItem 목록 가져오기
	   public List<OrderItem> selectAllOrderItem() {
	      String sql = "select * from OrderItem order by orderItemNum desc";
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      List<OrderItem> result = new ArrayList<OrderItem>();
	      try {
	         conn = ConnectionProvider.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            OrderItem item = new OrderItem();
	            item.setMemberId(rs.getString("memberId"));
	            item.setOrderNum(rs.getInt("orderNum"));
	            item.setOrderDate(rs.getDate("orderDate"));
	            item.setOrderAddress(rs.getString("orderAddress"));
	            item.setOrderPhoneNum(rs.getString("orderPhoneNum"));
	            item.setMemberName(rs.getString("memberName"));
	            item.setOrderTotalPrice(rs.getInt("orderTotalPrice"));
	            item.setPaymentName(rs.getString("paymentName"));
	            item.setItemColor(rs.getString("itemColor"));
	            item.setItemQty(rs.getInt("itemQty"));
	            item.setItemCode(rs.getInt("itemCode"));
	            item.setOrderItemNum(rs.getInt("orderitemnum"));
	            item.setDeliveryState(rs.getString("deliveryState"));
	            item.setItemMainPic(rs.getString("itemMainPic"));
	            item.setItemName(rs.getString("itemName"));
	            item.setItemPrice(rs.getInt("itemPrice"));
	            result.add(item);
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (conn != null) conn.close();
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	   }

	   // 3. selectAllOrderByOrderNum() : 동일한 orderNum 목록 가져오기
	   public List<OrderItem> selectAllOrderByOrderNum(int orderNum) {
	         String sql = "select * from OrderItem where ordernum=? order by orderItemNum desc";
	         PreparedStatement pstmt = null;
	         Connection conn = null;
	         ResultSet rs = null;
	         List<OrderItem> result = new ArrayList<OrderItem>();
	         try {
	            conn = ConnectionProvider.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, orderNum);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	               OrderItem item = new OrderItem();
	               item.setMemberId(rs.getString("memberId"));
	               item.setOrderNum(rs.getInt("orderNum"));
	               item.setOrderDate(rs.getDate("orderDate"));
	               item.setOrderAddress(rs.getString("orderAddress"));
	               item.setOrderPhoneNum(rs.getString("orderPhoneNum"));
	               item.setMemberName(rs.getString("memberName"));
	               item.setOrderTotalPrice(rs.getInt("orderTotalPrice"));
	               item.setPaymentName(rs.getString("paymentName"));
	               item.setItemColor(rs.getString("itemColor"));
	               item.setItemQty(rs.getInt("itemQty"));
	               item.setItemCode(rs.getInt("itemCode"));
	               item.setOrderItemNum(rs.getInt("orderitemnum"));
	               item.setDeliveryState(rs.getString("deliveryState"));
	               item.setItemMainPic(rs.getString("itemMainPic"));
	               item.setItemName(rs.getString("itemName"));
	               item.setItemPrice(rs.getInt("itemPrice"));
	               result.add(item);
	            }
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            try {
	               if (conn != null) conn.close();
	               if (rs != null) rs.close();
	               if (pstmt != null) pstmt.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         return result;
	      }

		// 4. updateDeliveryState() : ItemBoard 수정
		public int updateDeliveryState(String deliveryState, int orderNum) {
			String sql = "update OrderItem set deliveryState=? where orderNum=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deliveryState);
				pstmt.setInt(2, orderNum);
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 5. selectOrderList() : orderlist에 출력할 리스트
		public List<OrderItem> selectOrderList() {
			String sql = "select distinct o1.ordernum,o2.memberid, o2.membername,o2.orderphonenum,o2.orderaddress,o2.ordertotalprice,o2.deliverystate from orderitem o1, orderitem o2 where  o1.ordernum=o2.ordernum and o1.memberid=o2.memberid and o1.membername=o2.membername and o1.orderphonenum = o2.orderphonenum and o1.orderaddress=o2.orderaddress and o1.ordertotalprice=o2.ordertotalprice and o1.deliverystate=o2.deliverystate order by ordernum desc";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<OrderItem> result = new ArrayList<OrderItem>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					OrderItem item = new OrderItem();
					item.setOrderNum(rs.getInt("orderNum"));
					item.setMemberId(rs.getString("memberId"));
					item.setMemberName(rs.getString("memberName"));
					item.setOrderPhoneNum(rs.getString("orderPhoneNum"));
					item.setOrderAddress(rs.getString("orderAddress"));
					item.setOrderTotalPrice(rs.getInt("orderTotalPrice"));
					item.setDeliveryState(rs.getString("deliveryState"));
					result.add(item);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
	   // 6. selectMyOrderList() : 회원의 개인 orderlist에 출력할 리스트
	   public List<OrderItem> selectMyOrderList(String memberId) {
	      String sql = "select distinct o1.ordernum,o2.memberid, o2.membername,o2.orderphonenum,o2.orderaddress,o2.ordertotalprice,o2.deliverystate from orderitem o1, orderitem o2 where  o1.ordernum=o2.ordernum and o1.memberid=o2.memberid and o1.membername=o2.membername and o1.orderphonenum = o2.orderphonenum and o1.orderaddress=o2.orderaddress and o1.ordertotalprice=o2.ordertotalprice and o1.deliverystate=o2.deliverystate and o1.memberid=? order by ordernum desc";
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      List<OrderItem> result = new ArrayList<OrderItem>();
	      try {
	         conn = ConnectionProvider.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memberId);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            OrderItem item = new OrderItem();
	            item.setOrderNum(rs.getInt("orderNum"));
	            item.setMemberId(rs.getString("memberId"));
	            item.setMemberName(rs.getString("memberName"));
	            item.setOrderPhoneNum(rs.getString("orderPhoneNum"));
	            item.setOrderAddress(rs.getString("orderAddress"));
	            item.setOrderTotalPrice(rs.getInt("orderTotalPrice"));
	            item.setDeliveryState(rs.getString("deliveryState"));
	            result.add(item);
	         }
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (conn != null) conn.close();
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	   }
		  
		// 7. selectAllOrde() : orderItem 목록 가져오기
		public List<OrderItem> selectAllOrder() {
			String sql = "select * from OrderItem order by orderNum desc";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<OrderItem> result = new ArrayList<OrderItem>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					OrderItem item = new OrderItem();
					item.setMemberId(rs.getString("memberId"));
					item.setOrderNum(rs.getInt("orderNum"));
					item.setOrderDate(rs.getDate("orderDate"));
					item.setOrderAddress(rs.getString("orderAddress"));
					item.setOrderPhoneNum(rs.getString("orderPhoneNum"));
					result.add(item);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 8. updateOrder() : OrderItem 수정
		public void updateOrder(OrderItem orderItem) {
			String sql = "update OrderItem set memberId=?, orderAddress=?, orderPhoneNum=? where orderNum=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, orderItem.getMemberId());
				pstmt.setString(2, orderItem.getOrderAddress());
				pstmt.setString(3, orderItem.getOrderPhoneNum());
				pstmt.setInt(4, orderItem.getOrderNum());
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 9. deleteOrder() : OrderItem 삭제 - 주문번호로
		public void deleteOrder(int orderNum) {
			String sql = "delete from OrderItem where orderNum = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, orderNum);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		// 10. deleteOrder() : OrderItem 삭제 - 멤버 탈퇴 시 주문내역도 함께 삭제
		public void deleteOrder(String memberId) {
			String sql = "delete from OrderItem where memberId = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

		/***************************************************************************************************/
		/****************************************Itemboard/상품게시판***************************************/
		/***************************************************************************************************/
		// 1. insertItemBoard() : ItemBoard 추가
		public int insertItemBoard(ItemBoard itemBoard) {
			String sql = "insert into ItemBoard(itemCode,itemBoardNum, itemName,itemMainPic, itemDetailPic, itemPrice, itemStock, itemType) "
					+ "values (?,itemboard_seq.nextval,?,?,?,?,?,?)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemBoard.getItemCode());
				pstmt.setString(2, itemBoard.getItemName());
				pstmt.setString(3, itemBoard.getItemMainPic());
				pstmt.setString(4, itemBoard.getItemDetailPic());
				pstmt.setInt(5, itemBoard.getItemPrice());
				pstmt.setString(6, itemBoard.getItemStock());
				pstmt.setInt(7, itemBoard.getItemType());
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 2. selectOneItemBoard() : ItemBoard 하나 가져오기
		public ItemBoard selectOneItemBoard(int itemCode) {
			String sql = "select * from ItemBoard where itemCode = ?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			ItemBoard result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new ItemBoard();
					result.setItemCode(rs.getInt("itemCode"));
					result.setItemBoardNum(rs.getInt("itemBoardNum"));
					result.setItemName(rs.getString("itemName"));
					result.setItemMainPic(rs.getString("itemMainPic"));
					result.setItemDetailPic(rs.getString("itemDetailPic"));
					result.setItemPrice(rs.getInt("itemPrice"));
					result.setItemStock(rs.getString("itemStock"));
					result.setItemType(rs.getInt("itemType"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 3. selectAllItemBoard() : ItemBoard 목록 가져오기
		public List<ItemBoard> selectAllItemBoard() {
			String sql = "select * from ItemBoard order by ItemBoardNum desc";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<ItemBoard> result = new ArrayList<ItemBoard>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemBoard itemBoard = new ItemBoard();
					itemBoard.setItemCode(rs.getInt("itemCode"));
					itemBoard.setItemBoardNum(rs.getInt("itemBoardNum"));
					itemBoard.setItemName(rs.getString("itemName"));
					itemBoard.setItemMainPic(rs.getString("itemMainPic"));
					itemBoard.setItemDetailPic(rs.getString("itemDetailPic"));
					itemBoard.setItemPrice(rs.getInt("itemPrice"));
					itemBoard.setItemStock(rs.getString("itemStock"));
					itemBoard.setItemType(rs.getInt("itemType"));
					result.add(itemBoard);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 4. updateItemBoard() : ItemBoard 수정
		public int updateItemBoard(ItemBoard itemBoard) {
			String sql = "update ItemBoard set itemName=?, itemMainPic=?,itemDetailPic=?,itemPrice=?,itemStock=?,itemType=? where itemCode=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, itemBoard.getItemName());
				pstmt.setString(2, itemBoard.getItemMainPic());
				pstmt.setString(3, itemBoard.getItemDetailPic());
				pstmt.setInt(4, itemBoard.getItemPrice());
				pstmt.setString(5, itemBoard.getItemStock());
				pstmt.setInt(6, itemBoard.getItemType());
				pstmt.setInt(7, itemBoard.getItemCode());
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 5. deleteItemBoard() : ItemBoard 삭제
		public int deleteItemBoard(int itemCode) {
			String sql = "delete from ItemBoard where itemCode = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 6. selectTypeItemBoard() : ItemBoard 종류별 목록 가져오기
		public List<ItemBoard> selectTypeItemBoard(int itemType) {
			String sql = "select * from ItemBoard where itemtype=? order by ItemBoardNum desc";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<ItemBoard> result = new ArrayList<ItemBoard>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemType);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemBoard itemBoard = new ItemBoard();
					itemBoard.setItemCode(rs.getInt("itemCode"));
					itemBoard.setItemBoardNum(rs.getInt("itemBoardNum"));
					itemBoard.setItemName(rs.getString("itemName"));
					itemBoard.setItemMainPic(rs.getString("itemMainPic"));
					itemBoard.setItemDetailPic(rs.getString("itemDetailPic"));
					itemBoard.setItemPrice(rs.getInt("itemPrice"));
					itemBoard.setItemStock(rs.getString("itemStock"));
					itemBoard.setItemType(rs.getInt("itemType"));
					result.add(itemBoard);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 7. selectItemBoardByName() : 상품 검색 결과 목록 가져오기 / 상품 이름
		public List<ItemBoard> selectItemBoardByName(String search) {
			Connection connection = null;
			String sql = "select * from itemboard where (itemName like ('%' || ? || '%')) order by itemboardnum desc";
			ResultSet rs = null;
			PreparedStatement pstmt = null; 
			List<ItemBoard> result = new ArrayList<ItemBoard>(); 
			try {
				connection = ConnectionProvider.getConnection();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemBoard itemBoard = new ItemBoard();
					itemBoard.setItemCode(rs.getInt("itemCode"));
					itemBoard.setItemBoardNum(rs.getInt("itemBoardNum"));
					itemBoard.setItemName(rs.getString("itemName"));
					itemBoard.setItemMainPic(rs.getString("itemMainPic"));
					itemBoard.setItemDetailPic(rs.getString("itemDetailPic"));
					itemBoard.setItemPrice(rs.getInt("itemPrice"));
					itemBoard.setItemStock(rs.getString("itemStock"));
					itemBoard.setItemType(rs.getInt("itemType"));
					result.add(itemBoard);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null)
						connection.close();
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 8. updateItemBoardByOrder() 재고량, 판매량 수정
		public void updateItemBoard(int itemCode, String newStock) {
			System.out.println("itemboard 재고량 수정하러 들어왔어염");
			System.out.println(newStock);
			String sql = "update ItemBoard set itemStock=? where itemCode=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newStock);
				pstmt.setInt(2, itemCode);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//9. selectOneItemBoardForSequence() : itemBoard의 Sequence를 얻어옴
		public int selectOneItemBoardForSequence() {
			String sql = "select ITEMBOARD_SEQ.nextval from dual";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);	
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		
		
		//1. selectCountItemBoard() : 상품 게시판의 개수 검색
		   public int selectCountItemBoard() {
		      int count = 0;
		      String sql = "select count(*) from Itemboard";
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         conn = ConnectionProvider.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         rs = pstmt.executeQuery();
		         if(rs.next()) {
		            count = rs.getInt(1);
		         }    
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         try{
		            if(conn !=null) conn.close();
		            if(rs !=null) rs.close();
		            if(pstmt !=null) pstmt.close();
		         }catch(SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      return count;
		   }
		   
		   //2. selectItemList : 상품 게시판 검색(페이징)
		   public List<ItemBoard> selectItemList(int firstRow, int endRow){
		      String sql = "select * from (select rownum as rnum, m1.itemBoardnum,m1.itemcode,m1.itemname,m1.itemmainpic, m1.itemdetailPic, m1.itemprice, m1.itemstock, m1.itemtype from (select itemboardnum,itemcode,itemname,itemmainpic,itemdetailpic,itemprice,itemstock,itemtype from itemboard order by itemboardnum desc) m1) where rnum between ? and ?";
		      PreparedStatement pstmt = null;
		      Connection conn = null;
		      ResultSet rs = null;
		      List<ItemBoard> result = new ArrayList<ItemBoard>();
		      try {
		         conn = ConnectionProvider.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, firstRow);
		         pstmt.setInt(2, endRow);
		         rs = pstmt.executeQuery();
		         while(rs.next()) {
		            ItemBoard board = new ItemBoard();
		            board.setItemBoardNum(rs.getInt("itemboardnum"));
		            board.setItemCode(rs.getInt("itemcode"));
		            board.setItemName(rs.getString("itemName"));
		            board.setItemMainPic(rs.getString("itemMainPic"));
		            board.setItemDetailPic(rs.getString("itemdetailpic"));
		            board.setItemPrice(rs.getInt("itemPrice"));
		            board.setItemStock(rs.getString("itemStock"));
		            board.setItemType(rs.getInt("itemType"));
		            result.add(board);
		         }
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         try{
		            if(conn !=null) conn.close();
		            if(rs !=null) rs.close();
		            if(pstmt !=null) pstmt.close();
		         }catch(SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      return result;
		   }
		   
		
		/******************************************************************************************/
		/*****************************************Item/상품****************************************/
		/******************************************************************************************/
		// 1. insertItem() : Item 추가
		public void insertItem(Item item) {
			String sql = "insert into item(itemCode, itemName, itemMainPic,itemDetailPic, itemPrice, itemColor, itemStock, itemType,itemSales) "
					+ "values (?,?,?,?,?,?,?,?,0)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, item.getItemCode());
				pstmt.setString(2, item.getItemName());
				pstmt.setString(3, item.getItemMainPic());
				pstmt.setString(4, item.getItemDetailPic());
				pstmt.setInt(5, item.getItemPrice());
				pstmt.setString(6, item.getItemColor());
				pstmt.setInt(7, item.getItemStock());
				pstmt.setInt(8, item.getItemType());
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 2. selectOneItem() : Item 하나 가져오기
		public Item selectOneItem(int itemCode) {
			String sql = "select * from Item where itemCode = ?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			Item result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				// 결과를 얻어와야하기 때문에 executeQuery
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new Item();
					result.setItemCode(rs.getInt("itemCode"));
					result.setItemName(rs.getString("itemName"));
					result.setItemMainPic(rs.getString("itemMainPic"));
					result.setItemDetailPic(rs.getString("itemDetailPic"));
					result.setItemPrice(rs.getInt("itemPrice"));
					result.setItemColor(rs.getString("itemColor"));
					result.setItemStock(rs.getInt("itemStock"));
					result.setItemType(rs.getInt("itemType"));
					result.setItemType(rs.getInt("itemSales"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		//3. selectOneItemByCodeNColor() : 상품 코드와 색상으로 검색하기
		public Item selectOneItemByCodeNColor(int itemCode, String itemColor) {
			String sql = "select * from Item where itemCode = ? and itemColor =?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			Item result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				pstmt.setString(2, itemColor);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new Item();
					result.setItemCode(rs.getInt("itemCode"));
					result.setItemName(rs.getString("itemName"));
					result.setItemMainPic(rs.getString("itemMainPic"));
					result.setItemDetailPic(rs.getString("itemDetailPic"));
					result.setItemPrice(rs.getInt("itemPrice"));
					result.setItemColor(rs.getString("itemColor"));
					result.setItemStock(rs.getInt("itemStock"));
					result.setItemType(rs.getInt("itemType"));
					result.setItemSales(rs.getInt("itemSales"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 4. selectAllItem() : Item 목록 가져오기
		public List<Item> selectAllItem() {
			String sql = "select * from Item";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<Item> result = new ArrayList<Item>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Item item = new Item();
					item.setItemCode(rs.getInt("itemCode"));
					item.setItemName(rs.getString("itemName"));
					item.setItemMainPic(rs.getString("itemMainPic"));
					item.setItemDetailPic(rs.getString("itemDetailPic"));
					item.setItemPrice(rs.getInt("itemPrice"));
					item.setItemColor(rs.getString("itemColor"));
					item.setItemStock(rs.getInt("itemStock"));
					item.setItemType(rs.getInt("itemType"));
					item.setItemType(rs.getInt("itemSales"));
					item.setItemSales(rs.getInt("itemSales"));
					result.add(item);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 5. selectAllByItemCode() : 같은 코드의 아이템 전부 가져오기
		public List<Item> selectAllByItemCode(int itemCode) {
			String sql = "select * from Item where itemcode=?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<Item> result = new ArrayList<Item>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Item item = new Item();
					item.setItemCode(rs.getInt("itemCode"));
					item.setItemName(rs.getString("itemName"));
					item.setItemMainPic(rs.getString("itemMainPic"));
					item.setItemDetailPic(rs.getString("itemDetailPic"));
					item.setItemPrice(rs.getInt("itemPrice"));
					item.setItemColor(rs.getString("itemColor"));
					item.setItemStock(rs.getInt("itemStock"));
					item.setItemType(rs.getInt("itemType"));
					item.setItemSales(rs.getInt("itemSales"));
					result.add(item);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 6. updateItem() : Item 수정
		public int updateItem(Item item) {
			String sql = "update Item set itemName=?, itemMainPic=?,itemDetailPic=?,itemPrice=?,itemColor=?, itemStock=?,itemType=? where itemCode=? and itemColor=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, item.getItemName());
				pstmt.setString(2, item.getItemMainPic());
				pstmt.setString(3, item.getItemDetailPic());
				pstmt.setInt(4, item.getItemPrice());
				pstmt.setString(5, item.getItemColor());
				pstmt.setInt(6, item.getItemStock());
				pstmt.setInt(7, item.getItemType());
				pstmt.setInt(8, item.getItemCode());
				pstmt.setString(9, item.getItemColor());
				pstmt.executeUpdate();
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 7. deleteItem() : Item 삭제
		public void deleteItem(int itemCode) {
			String sql = "delete from Item where itemCode = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 8. UpdateSales : 판매량 증가, 재고량 감소시키는 기능
		// 상품 코드, 상품 색상, 판매수를 받아와서 판매한 수 만큼 증감하기
		public int updateItemByOrder(int itemCode, String itemColor, int newSales, int newStock) {
			System.out.println("재고량 수정하러 들어왔어염");
			String sql = "update Item set itemSales=?, itemStock=? where itemCode=? and itemColor=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, newSales);
				pstmt.setInt(2, newStock);
				pstmt.setInt(3, itemCode);
				pstmt.setString(4, itemColor);
				pstmt.executeUpdate();
				result = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 9. selectOneItemForStock() : 재고량 검색하기
		public int selectOneItemForStock(int itemCode, String itemColor) {
			String sql = "select itemStock from Item where itemCode = ? and itemColor =?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, itemCode);
				pstmt.setString(2, itemColor);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("itemStock");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//10. selectBestItem() : Best6
		public List<Item> selectBestItem() {
	         String sql = "select * from Item where itemsales>0 and rownum<=6 order by itemsales desc";
	         PreparedStatement pstmt = null;
	         Connection conn = null;
	         ResultSet rs = null;
	         List<Item> result = new ArrayList<Item>();
	         try {
	            conn = ConnectionProvider.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	               Item item = new Item();
	               item.setItemCode(rs.getInt("itemCode"));
	               item.setItemName(rs.getString("itemName"));
	               item.setItemMainPic(rs.getString("itemMainPic"));
	               item.setItemDetailPic(rs.getString("itemDetailPic"));
	               item.setItemPrice(rs.getInt("itemPrice"));
	               item.setItemColor(rs.getString("itemColor"));
	               item.setItemStock(rs.getInt("itemStock"));
	               item.setItemType(rs.getInt("itemType"));
	               item.setItemType(rs.getInt("itemSales"));
	               item.setItemSales(rs.getInt("itemSales"));
	               result.add(item);
	            }
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            try {
	               if (conn != null) conn.close();
	               if (rs != null) rs.close();
	               if (pstmt != null) pstmt.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         return result;
	      }

		/********************************************************************************************/
		/*****************************************admin/관리자***************************************/
		/********************************************************************************************/
		public Admin selectOneAdmin(String adminId) {
			String sql = "select * from admin where adminid = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Admin result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, adminId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new Admin();
					result.setAdminId(rs.getString("adminId"));
					result.setAdminPass(rs.getString("adminPass"));
					result.setAdminName(rs.getString("adminName"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
					if (rs != null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		/******************************************************************************************/
		/*************************************NoticeBoard/num**************************************/
		/******************************************************************************************/
		
		//1. selectCountNoticeBoard() : 전체 게시판의 수 검색
		public int selectCountNoticeBoard() {
			int count = 0;
			String sql = "select count(*) from noticeboard";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}    
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try{
					if(conn !=null) conn.close();
					if(rs !=null) rs.close();
					if(pstmt !=null) pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
		}
		
		//2. selectNoticeList : 공지사항 게시판 검색(페이징)
		public List<NoticeBoard> selectNoticeList(int firstRow, int endRow){
			String sql = "select * from (select rownum as rnum, m1.noticenum,m1.adminid,m1.noticetitle,m1.noticecontent, m1.noticereadcount from (select noticenum,adminid,noticetitle,noticecontent,noticereadcount from noticeboard order by noticenum desc) m1) where rnum between ? and ?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<NoticeBoard> result = new ArrayList<NoticeBoard>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, firstRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					NoticeBoard noticeBoard = new NoticeBoard();
					noticeBoard.setNoticeNum(rs.getInt("noticenum"));
					noticeBoard.setAdminId(rs.getString("adminid"));
					noticeBoard.setNoticeTitle(rs.getString("noticetitle"));
					noticeBoard.setNoticeContent(rs.getString("noticecontent"));
					noticeBoard.setNoticeReadCount(rs.getInt("noticereadcount"));
					result.add(noticeBoard);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try{
					if(conn !=null) conn.close();
					if(rs !=null) rs.close();
					if(pstmt !=null) pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//3. selectOneNumber() : 게시글 번호(num)으로 멤버 검색
		public NoticeBoard selectOneNumber(int noticeNum) {
			String sql = "select * from noticeboard where noticenum = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NoticeBoard result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, noticeNum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new NoticeBoard();
					result.setNoticeNum(rs.getInt("noticeNum"));
					result.setNoticeReadCount(rs.getInt("noticeReadCount"));
					result.setNoticeTitle(rs.getString("noticeTitle"));
					result.setNoticeContent(rs.getString("noticeContent"));

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
					if (rs != null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		/******************************************************************************************/
		/*****************************************File/파일****************************************/
		/******************************************************************************************/

		//1. selectAllfile() : 전체 파일 검색
		public List<String> selectAllfile() {
			String sql = "select * from uploadFile";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<String> result = new ArrayList<String>();

			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					result.add(rs.getString("filename"));
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		//2. insertFile() : 파일 추가(삽입)
		public int insertFile(String filename) {
			String sql = "insert into uploadfile(filename) values (?)";
			Connection connection = null;
			PreparedStatement pstmt = null;
			int result = 0;
			try {
				connection = ConnectionProvider.getConnection();
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, filename);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		/*********************************************************************************************/
		/*************************************comments/댓글*******************************************/
		/*********************************************************************************************/
		
		// 1. insertComments() : Comment 추가
		public void insertComments(Comments comments) {
			String sql = "insert into comments(commentsboardnum, bnum, btype,writer, content) "
					+ "values (comments_seq.nextval,?,?,?,?)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, comments.getbNum());
				pstmt.setInt(2, comments.getbType());
				pstmt.setString(3, comments.getWriter());
				pstmt.setString(4, comments.getContent());
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 2. deleteComments() : Comments 삭제
		public void deleteComments(int commentsboardnum) {
			String sql = "delete from comments where commentsboardnum = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, commentsboardnum);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void deleteComments(int bType, int bNum)  {
			String sql = "delete from comments where bType = ? and bNum=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bType);
				pstmt.setInt(2, bNum);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 3. selectOneComments() : Comments 하나 가져오기
		public Comments selectOneComments(int commentsboardnum) {
			String sql = "select * from comments where commentsboardnum = ?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			Comments result = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, commentsboardnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new Comments();
					result.setCommentsBoardNum(rs.getInt("commentsboardnum"));
					result.setbNum(rs.getInt("bnum"));
					result.setbType(rs.getInt("btype"));
					result.setWriter(rs.getString("writer"));
					result.setContent(rs.getString("content"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		// 4.updateComments() : 댓글 수정 (content(댓글내용)만 수정가능)
		public void updateComments(int bNum, int bType) {
			String sql = "update Item set content=? where bNum=? and bType=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bNum);
				pstmt.setInt(1, bType);
				pstmt.executeUpdate();
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// selectAllCommentsByBoard() : 댓글 검색
		public List<Comments> selectAllCommentsByBoard(int bNum, int bType) {
			String sql = "select * from comments where bNum =? and bType=?";
			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs = null;
			List<Comments> result = new ArrayList<Comments>();
			try {
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bNum);
				pstmt.setInt(2, bType);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Comments comm = new Comments();
					comm.setbNum(rs.getInt("bNum"));
					comm.setbType(rs.getInt("bType"));
					comm.setCommentsBoardNum(rs.getInt("commentsBoardnum"));
					comm.setContent(rs.getString("content"));
					comm.setWriter(rs.getString("writer"));
					result.add(comm);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	}