package com.example.jpa.repository;

import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//<엔티티, id타입>
public interface MemoRepository extends JpaRepository<Memo, Long>,
        MemoCustomRepository /*커스텀 레파지토리*/{

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


    // JPQL - sql과 유사하나 엔티티를 사용해서 구문 작성
    // select, update, delete 구문을 제공하고, insert는 없음
    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getList();

    @Query("select m from Memo m where m.mno >= :num order by m.text desc")
    List<Memo> getList2(@Param("num") Long num);

    @Query("select m.mno, m.writer from Memo m where m.writer like %:search%")
    List<Object[]> getList3(@Param("search") String param);


    //JPQL - update구문
    // update구문 테이블명 set값 value 값
    @Transactional
    @Modifying
    @Query("update Memo m set m.writer = :a where m.mno = :b")
    int updateMemo(@Param("a") String writer, @Param("b") Long mno);

    @Transactional
    @Modifying
    @Query("update Memo m set m.writer = :#{#a.writer}, m.text= :#{#a.text} where m.mno = :#{#a.mno }")
    int updateMemo2(@Param("a") Memo memo);


    // delete
    @Transactional
    @Modifying
    @Query("delete from Memo m where m.mno= :a")
    int deleteMemo(@Param("a") Long mno);


    //JPQL구문의 맨 마지막에 pagaable을 넣으면 페이지 처리를 함
    @Query("select m from Memo m where m.mno >= :a")
    Page<Memo> getListPage(@Param("a") Long mno, Pageable pageable);


    // 네이티브쿼리 - jpql이 아닌 sql문을 직접 날리는 방법
    @Query(value = "select * from memo where mno = ?",nativeQuery = true)
    Memo getNative(Long a);

    //jpql로 조인
//    @Query("select m from Memo m inner join m.member x where m.mno >= :mno")
//    List<Memo> mtoJoin2(@Param("mno") long mno);

//    @Query("select m from Member m left join m.list x where x.text like %?%")
//    List<MemberMemoDTO> getList2(String text);
}
