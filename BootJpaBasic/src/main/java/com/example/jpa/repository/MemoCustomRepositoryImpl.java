package com.example.jpa.repository;


import com.example.jpa.entity.Member;
import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

public class MemoCustomRepositoryImpl implements MemoCustomRepository {

    @PersistenceContext // 엔티티매니저를 주입받을 떄 사용하는 어노테이션
    private EntityManager entityManager;

    @Transactional // update, delete구문일 경우 부착
    @Override
    public int updateTest(String writer, Long mno) {

        //JPQL
        String sql = "update Memo m set m.writer = :a where mno = :b";

        Query query = entityManager.createQuery(sql);
        query.setParameter("a", writer);
        query.setParameter("b", mno);

        // update, delete문장은 executeUpdate로 실행
        // select문장은 getResultList, getSingleResult로 실행
        int result = query.executeUpdate();


        return result;
    }/**/

    @Override
    public List<Memo> mtoJoin1(Long mno) {
        // inner조인 - 연결되는 데이터가 없으면 안나옴


        //String sql = "select m from Memo m inner join m.member x where m.mno >= :mno";
        String sql = "select m from Memo m left join m.member x where m.mno >= :mno";
        TypedQuery<Memo> query = entityManager.createQuery(sql,Memo.class);
        query.setParameter("mno", mno);

        return query.getResultList();
    }

    @Override
    public List<Memo> mtoJoin3(String name) {

        // 값을 매핑 시킬떄는 Memo로 받을 수 있고, Object[]로 받을 수 있음
        String sql = "select m from Memo m inner join Member x on m.writer = x.name where x.name = :name";

        TypedQuery<Memo> query = entityManager.createQuery(sql,Memo.class);
        query.setParameter("name",name);

        return query.getResultList();
    }

    @Override
    public Member otmJoin1(String id) {
        String sql = "select m from Member m inner join m.list x where m.id = :id";
        TypedQuery<Member> query = entityManager.createQuery(sql,Member.class);
        query.setParameter("id",id);


        return query.getSingleResult();
    }

    @Override
    public List<MemberMemoDTO> getList(String id) {
        String sql = "select new com.example.jpa.entity.MemberMemoDTO(x.id, x.name, x.signDate, m.mno, m.writer, m.text)"+
                "from Memo m inner join m.member x where x.id = :id";

        TypedQuery<MemberMemoDTO> query = entityManager.createQuery(sql,MemberMemoDTO.class);
        query.setParameter("id",id);

        return query.getResultList();
    }

    @Override
    public List<MemberMemoDTO> quiz2(String text) {
        String sql = "select new com.example.jpa.entity.MemberMemoDTO(m.id,m.name,m.signDate,x.mno,x.writer,x.text)"
        + "from Member m left join m.list x where x.text like :text";

        TypedQuery<MemberMemoDTO> query = entityManager.createQuery(sql,MemberMemoDTO.class);
        query.setParameter("text",text);
        return query.getResultList();
    }

}
