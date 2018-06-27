package com.gongdel.springbootjpa.repository.note;

import com.gongdel.springbootjpa.domain.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
/**
    JpaRepository interface(SimpleJpaRepository라고도 불림)는 Entity의 CRUD 작동을 위한 모든 method들을 정의한다
    따라서 save(), findOne(), findAll(), count(), delete() 등을 사용할 수 있다.

 **/