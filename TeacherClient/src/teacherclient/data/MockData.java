/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.data;

import teacherclient.be.Student;
import teacherclient.be.Class;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexl
 */
public class MockData {
    
    private ArrayList<Class> listAllClasses;
    private ArrayList<Class> listOne;
    private ArrayList<Class> listTwo;
    private ArrayList<Class> listThree;
    
    private Class CS2017A;
    private Class CS2017B;
    private Class CS2016A;
    private Class CS2016B;
    
    private ArrayList<Student> listAllStudents;
    
    private Student Alex;
    private Student Asbjørn;
    private Student Jan;
    
    public void createMockData() {
        CS2017A = new Class(1, "CS2017A");
        CS2017B = new Class(2, "CS2017B");
        CS2016A = new Class(3, "CS2016A");
        CS2016B = new Class(4, "CS2016B");
        
        listAllClasses = new ArrayList<Class>();
        listAllClasses.add(CS2017A);
        listAllClasses.add(CS2017B);
        listAllClasses.add(CS2016A);
        listAllClasses.add(CS2016B);
        
        listOne = new ArrayList<Class>();
        listOne.add(CS2017A);
        listOne.add(CS2016A);
        
        listTwo = new ArrayList<Class>();
        listTwo.add(CS2017A);
        listTwo.add(CS2017B);
        listTwo.add(CS2016A);
        
        listThree = new ArrayList<Class>();
        listThree.add(CS2017B);
        listThree.add(CS2016B);
        
        Alex = new Student(1, "Alex Tygesen", listOne);
        Asbjørn = new Student(2, "Asbjørn Mansa EtEllerAndet", listTwo);
        Jan = new Student(3, "JanvanZetten", listThree);
        
        listAllStudents = new ArrayList<Student>();
        listAllStudents.add(Alex);
        listAllStudents.add(Asbjørn);
        listAllStudents.add(Jan);
    }

    public List<Class> getListAllClasses() {
        return listAllClasses;
    }

    public List<Student> getListAllStudents() {
        return listAllStudents;
    } 
}
