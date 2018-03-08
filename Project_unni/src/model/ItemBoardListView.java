package model;

import java.util.List;

public class ItemBoardListView {

   private int startPage; //네비게이션 시작페이지 계산
   private int endPage; //네비게이션 끝페이지 계산
   private int pageTotalCount; //총 페이지 개수 계산
   private int boardCountPerPage; //페이지당 게시글 수 파라미터
   private int boardTotalCount; //총 게시글 개수 파라미터
   private int currentPageNumber; // 현재 페이지 번호 파라미터
   private List<ItemBoard> boardList; //화면에 표시할 게시글 리스트 파라미터

   public ItemBoardListView(int currentPageNumber, int boardCountPerPage, int boardTotalCount, List<ItemBoard> boardList) {
       this.currentPageNumber = currentPageNumber;
       this.boardCountPerPage = boardCountPerPage;
       this.boardTotalCount = boardTotalCount;
       this.boardList = boardList;

       init();
   }

   private void init() {
       //데이터 셋팅 메서드 
       calPageTotalCount();
       getStartPage();
       getEndPage();
   }

   //총 페이지 수 구하기, 페이지수 구해서 변수에 셋팅하면 됨
   public void calPageTotalCount() {
       //전체 페이지 수 
       if(boardTotalCount ==0) {
           pageTotalCount =0;

       }else {            
           pageTotalCount = boardTotalCount / boardCountPerPage;

           if(boardTotalCount % boardCountPerPage > 0) {
               pageTotalCount++;
           }
       }
   }

   public int getStartPage() {
       //  6       >>1 , 10 
       // 16      >>11 , 20
       //  10     >>1 , 10
       //24   >> 21  30

       startPage = (currentPageNumber-1)/boardCountPerPage *10 +1;
       return startPage ;
   }

   public int getEndPage() {
       //  6       >>1 , 10 
       // 16      >>11 , 20
       //  10     >>1 , 10
       //24   >> 21  30

   endPage = ((currentPageNumber-1)/boardCountPerPage+1)*10;
   return endPage;
   }

   public int getPageTotalCount() {
       return pageTotalCount;
   }

   public void setPageTotalCount(int pageTotalCount) {
       this.pageTotalCount = pageTotalCount;
   }

   public int getBoardCountPerPage() {
       return boardCountPerPage;
   }

   public void setBoardCountPerPage(int boardCountPerPage) {
       this.boardCountPerPage = boardCountPerPage;
   }

   public int getBoardTotalCount() {
       return boardTotalCount;
   }

   public void setBoardTotalCount(int boardTotalCount) {
       this.boardTotalCount = boardTotalCount;
   }

   public int getCurrentPageNumber() {
       return currentPageNumber;
   }

   public void setCurrentPageNumber(int currentPageNumber) {
       this.currentPageNumber = currentPageNumber;
   }

   public List<ItemBoard> getBoardList() {
       return boardList;
   }

   public void setBoardList(List<ItemBoard> boardList) {
       this.boardList = boardList;
   }

@Override
public String toString() {
	return "ItemBoardListView [startPage=" + startPage + ", endPage=" + endPage + ", pageTotalCount=" + pageTotalCount
			+ ", boardCountPerPage=" + boardCountPerPage + ", boardTotalCount=" + boardTotalCount
			+ ", currentPageNumber=" + currentPageNumber + ", boardList=" + boardList + "]";
}
   
   
}