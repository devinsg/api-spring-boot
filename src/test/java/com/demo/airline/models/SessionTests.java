package com.demo.airline.models;

import com.demo.airline.repositories.ISessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SessionTests {
    @Autowired
    private ISessionRepository iRepository;

    @Test
    public void testFind() throws Exception {
        Session session = iRepository.getById(1L);

        assertNotNull(session);
        assertEquals(1, session.getSessionId());
    }

    @Test
    public void testList() throws Exception {
        List<Session> sessions = iRepository.findAll();

        assertNotNull(sessions);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testSessionsThatHaveName() throws Exception {
        List<Session> sessions = iRepository.findBySessionNameContains("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testDslQueryNot() throws Exception {
        List<Session> sessions = iRepository.findBySessionLengthNot(45);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testDslQueryNotLike() throws Exception {
        List<Session> sessions = iRepository.findBySessionNameNotLike("Java");
        assertTrue(sessions.size() > 0);

        Session s1 = sessions.get(0);
        assert (s1.getSessionName().toLowerCase().lastIndexOf("Java")) < 0; // not have java
    }

    @Test
    public void testDslQueryLessThan() throws Exception {
        List<Session> sessions = iRepository.findBySessionLengthLessThanEqual(30);
        assertTrue(sessions.size() > 0);

        Session s1 = sessions.get(0);
        assert (s1.getSessionLength()) <= 30;
    }

    @Test
    public void testFindBySessionName() throws Exception {
        List<Session> sessions = iRepository.findBySessionNameContains("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testQuerySessionsWithName() throws Exception {
        List<Session> sessions = iRepository.querySessionsWithName("Java");
        assertTrue(sessions.size() > 0);
        assertTrue(sessions.get(0).getSessionName().contains("Java"));
    }

    @Test
    public void testQuerySessionsWithNamePaging() throws Exception {
        /* query condition: OFFSET 5 ROWS FETCH NEXT 10 ROWS ONLY
         *  it only runs on Oracle 12
         * */
        //Page<Session> page = iRepository.querySessionsWithNamePaging("n", PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "sessionLength")));

        List<Session> sessions = iRepository.querySessionsWithName("n");
        assertTrue(sessions.size() > 0);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Session s1 = new Session();
        s1.setSessionName("Meeting 2022");
        s1.setSessionDescription("Preparation Meeting in 2022");
        s1.setSessionLength(65);
        s1 = iRepository.saveAndFlush(s1);

        Session s2 = iRepository.getById(s1.getSessionId());
        assertEquals("Meeting 2022", s2.getSessionName());

        iRepository.delete(iRepository.getById(s2.getSessionId()));
    }

    @Test
    public void testUpdateDslQuery() throws Exception {
        iRepository.updateSessionName((long) 2,"Meeting 2022-06-01", "Meeting for AngularJs");

        List<Session> sessionList = iRepository.findBySessionNameContains("Meeting 2022-06-01");
        assertNotNull(sessionList);
        assert (sessionList.size() > 0);
        assert (sessionList.get(0).getSessionName().equals("Meeting 2022-06-01"));
        assert (sessionList.get(0).getSessionDescription().equals("Meeting for AngularJs"));
    }
}
