package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
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
    List<Memo> findByTextContainingOrWriterContainingOrderByMnoDesc(String text, String writer);

}