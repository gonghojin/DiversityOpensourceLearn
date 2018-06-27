package com.gongdel.springbootjpa.repository.note;

import com.gongdel.springbootjpa.domain.note.Note;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteTest {

    @Autowired
    private NoteRepository noteRepository;

    @After
    public void cleanup() {
        noteRepository.deleteAll();
    }

    @Test
    public void note_등록() {
        // given
        Note note = new Note();
        note.setTitle("테스트 타이틀");
        note.setContent("테스트 내용");

        // when
        noteRepository.save(note);

        // then
        Note testEntity = noteRepository.findAll().get(0);
        assertThat(testEntity.getTitle()).isEqualTo(note.getTitle());
        assertThat(testEntity.getContent()).isEqualTo(note.getContent());

    }
}
