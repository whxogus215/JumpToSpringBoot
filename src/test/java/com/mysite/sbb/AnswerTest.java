package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerTest {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Test
    void answerSaveTest() {
        Optional<Question> questionById = this.questionRepository.findById(2);
        assertTrue(questionById.isPresent());
        Question question = questionById.get();

        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    @Test
    void findAnswerById() {
        Optional<Answer> answerById = this.answerRepository.findById(1);
        assertTrue(answerById.isPresent());
        Answer answer = answerById.get();
        assertEquals(2, answer.getQuestion().getId());
    }

    @Transactional
    @Test
    void findAnswerListByQuestionId() {
        Optional<Question> questionById = this.questionRepository.findById(2);
        assertTrue(questionById.isPresent());
        Question question = questionById.get();

        List<Answer> answerList = question.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }
}
