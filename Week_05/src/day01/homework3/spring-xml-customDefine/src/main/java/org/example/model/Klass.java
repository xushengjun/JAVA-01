package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Klass { 
    
    private List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
