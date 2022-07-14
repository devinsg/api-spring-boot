package com.demo.airline.controllers;

import com.demo.airline.models.Session;
import com.demo.airline.repositories.ISessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    @Autowired
    private ISessionRepository repository;

    @GetMapping
    public List<Session> list(@RequestParam(required = false) String name) {
        if(name != null) {
            return repository.findBySessionNameContains(name);
        } else {
            return repository.findAll();
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return repository.saveAndFlush(session);
    }

    //@DeleteMapping
    //public void delete(@PathVariable Long id) {
        //repository.delete(id);
    //}

    @PutMapping
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = repository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return repository.saveAndFlush(session);
    }
}
