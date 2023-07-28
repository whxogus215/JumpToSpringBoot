package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;

    @Test
    void makeQuestionTest() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }

    @Test
    void findQuestionListTest() {
        List<Question> questionList = this.questionRepository.findAll();
        assertEquals(2, questionList.size());

        Question question = questionList.get(0);
        assertEquals("sbb가 무엇인가요?", question.getSubject());
    }

    @Test
    void findQuestionByIdTest() {
        Optional<Question> questionById = this.questionRepository.findById(1);
        if (questionById.isPresent()) {
            Question question = questionById.get();
            assertEquals("sbb가 무엇인가요?", question.getSubject());
        }
    }

    @Test
    void findQuestionBySubjectTest() {
        Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, question.getId());
    }

    @Test
    void findQuestionBySubjectAndContentTest() {
        Question question = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, question.getId());
    }

    @Test
    void findQuestionBySubjectLikeTest() {
        List<Question> questions = this.questionRepository.findBySubjectLike("sbb%");
        Question question = questions.get(0);
        assertEquals("sbb가 무엇인가요?", question.getSubject());
    }

    @Test
    void modifyQuestionByIdTest() {
        Optional<Question> questionById = this.questionRepository.findById(1);
        assertTrue(questionById.isPresent());
        Question question = questionById.get();
        question.setSubject("수정된 제목");
        this.questionRepository.save(question);
    }

    @Test
    void removeQuestionByIdTest() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> questionId = this.questionRepository.findById(1);
        assertTrue(questionId.isPresent());
        Question question = questionId.get();
        this.questionRepository.delete(question);
        assertEquals(1, this.questionRepository.count());
    }

    // 대용량 데이터 테스트 코드
    @Test
    void testJpa() {
        for (int i = 1; i<= 300; i++){
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용 무";
            this.questionService.create(subject, content, null);
        }
    }




}
