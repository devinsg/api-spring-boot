package com.demo.airline.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class ProfessorCollection {
    private Collection<Professor> professors;
}
