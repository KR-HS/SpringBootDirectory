package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<엔티티, id타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {

    //쿼리 메서드 - 메서드의 유형을 보고 JPA가 select문을 실행
    // where mno between ? and ?
    List<Memo> findByMnoBetween(Long start,Long end);
    // where text=? order by mno desc
    List<Memo> findByTextOrderByMnoDesc(String text);
    // where writer in (?,?,?) order by mno asc
    List<Memo> findByWriterIsInOrderByMnoAsc(List<String> list);
    // where mno between ? and ? order by mno desc
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long start,Long end);
    // where text like ? or writer like ? order by mno desc
    List<Memo> findByTextContainingOrWriterContainingOrderByMnoDesc(String text, String writer); // 포함되있는 문자를 매개변수로 받음 ex = admin
    List<Memo> findByTextLikeOrWriterLikeOrderByMnoDesc(String text, String writer); // Like는 매개변수로 패턴자체를 받음 ex = %admin%44


    // 마지막 매개변수에 Pageable을 넣게되면 페이지를 처리한다
    // where text like ? or writer like ?
    Page<Memo> findByTextLikeOrWriterLike(String text, String writer, Pageable pageable);

}