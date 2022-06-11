package com.demo.airline.repositories;

import com.demo.airline.models.Session;

import java.util.List;

public interface ICustomRepository {
    List<Session> customQuerySessions();
    String customHelloWord();
}
