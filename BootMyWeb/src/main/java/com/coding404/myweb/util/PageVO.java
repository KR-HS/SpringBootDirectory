package com.coding404.myweb.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageVO {
    
    // 페이지네이션을 계산하기 위한 클래스
    private int start; // 시작페이지 번호
    private int end; // 끝페이지 번호
    private boolean prev; // 이전버튼 활성화 여부
    private boolean next; // 다음버튼 활성화 여부


    private int page; // 현재 조회하고 있는 페이지(cri객체와 연관)
    private int realEnd; // 맨 마지막 페이지에서 보이는 실제 끝번호값

    private Criteria cri; // 페이지 기준객체
    private List<Integer> pageList; // 페이지번호 리스트(타임리프에서 향상된 for문)

    // 생성될 때 cri객체와, 전체게시글 수 필요
    public PageVO(Criteria cri,int total){
        this.cri=cri;
        this.page = cri.getPage();

        // 끝페이지
        // 현재 조회하는 페이지 11-> 끝페이지 20번
        // 현재 조회하는 페이지 5 -> 끝페이지 10번
        this.end = (int)(Math.ceil(this.page/10.0))*10;

        // 페이지 시작번호 게산
        // 끝페이지 - 페이지네이션 개수 + 1
        this.start = this.end-10+1;

        // 실제 끝번호
        // 데이터가 53개라면 -> 실제 마지막 페이지 번호는 6
        // 데이터가 165개라면 -> 실제 마지막 페이지 번호는 17
        this.realEnd = (int)Math.ceil(total*1.0/cri.getAmount());

        // 실제 마지막번호로 다시 계산
        // 데이터가 165개일때
        // 1~10 페이지 조회시, end= 10 , realEnd = 17
        // 11~20 페이지 조회시, end = 20 , realEnd 17;

        if(this.end>this.realEnd){
            this.end=this.realEnd;
        }

        this.prev=this.start>10;
        this.next=this.end<this.realEnd;

        // 페이지 리스트 초기화
        this.pageList=new ArrayList<Integer>();
        for(int i=this.start;i<=this.end;i++){
            this.pageList.add(i);
        }
    }
}
