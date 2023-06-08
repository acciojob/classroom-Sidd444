package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String,Student> studentMap;
    private HashMap<String,Teacher> teacherMap;
    private HashMap<String, List<String>> studentTeacherMapping;
    public StudentRepository(){
        this.studentMap=new HashMap<>();
        this.teacherMap=new HashMap<>();
        this.studentTeacherMapping=new HashMap<>();
    }
    public void saveStudent(Student student){
        studentMap.put(student.getName(),student);
    }
    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }
    public void saveStudentTeacherPair(String student,String teacher){
        if(studentMap.containsKey(student)&&teacherMap.containsKey(teacher)) {

            List<String> currentStudentByTeacher = new ArrayList<>();

            if (studentTeacherMapping.containsKey(teacher))
                currentStudentByTeacher = studentTeacherMapping.get(teacher);

            currentStudentByTeacher.add(student);

            studentTeacherMapping.put(student, currentStudentByTeacher);
        }
        }
        public Student findStudent(String student){
        return studentMap.get(student);
    }
    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }
    public List<String> getStudentsFromTeacher(String teacher){
        return studentTeacherMapping.get(teacher);
    }
    public void deleteTeacher(String teacher){
        List<String> students = new ArrayList<String>();
        if(studentTeacherMapping.containsKey(teacher)){
            //1. Find the movie names by director from the pair
            students = studentTeacherMapping.get(teacher);

            //2. Deleting all the movies from movieDb by using movieName
            for(String movie: students){
                if(studentMap.containsKey(movie)){
                   studentMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            studentTeacherMapping.remove(teacher);
        }
        if(studentTeacherMapping.containsKey(teacher)){
            studentTeacherMapping.remove(teacher);
        }
    }
    public void deleteAll(){
        HashSet<String> studentSet = new HashSet<>();
        teacherMap = new HashMap<>();
        for(String teacher: studentTeacherMapping.keySet()){
            for(String movie: studentTeacherMapping.get(teacher)){
                studentSet.add(movie);
            }
        }
        for(String movie: studentSet){
            if(studentMap.containsKey(movie)){
                studentMap.remove(movie);
            }
        }
        studentTeacherMapping = new HashMap<>();
    }
    public List<String> getAllStudents(){
        List<String> students=new ArrayList<>();
        for(String s:studentMap.keySet()){
            students.add(s);
        }
        return students;
    }
}
