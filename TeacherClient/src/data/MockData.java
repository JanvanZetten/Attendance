/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import be.Student;
import be.Class;
import java.util.List;

/**
 *
 * @author alexl
 */
public class MockData {
    
    private List<Class> listOne;
    private List<Class> listTwo;
    private List<Class> listThree;
    
    private Class SCO;
    private Class SDE;
    private Class ITO;
    private Class Sløjd;
    
    private Student Alex;
    private Student Asbjørn;
    private Student Jan;
    
    public void createMockData() {
        SCO = new Class(1, "SCO");
        SDE = new Class(2, "SDE");
        ITO = new Class(3, "ITO");
        Sløjd = new Class(4, "Sløjd");
        
        listOne.add(SCO);
        listOne.add(ITO);
        
        listTwo.add(SCO);
        listTwo.add(SDE);
        listTwo.add(ITO);
        
        listThree.add(SDE);
        listThree.add(Sløjd);
        
        Alex = new Student(1, "Alex Tygesen", listOne);
        Asbjørn = new Student(2, "Asbjørn Mansa EtEllerAndet", listTwo);
        Jan = new Student(3, "JanvanZetten", listThree);
    }

    public List<Class> getListOne() {
        return listOne;
    }

    public List<Class> getListTwo() {
        return listTwo;
    }

    public List<Class> getListThree() {
        return listThree;
    }

    public Class getSCO() {
        return SCO;
    }

    public Class getSDE() {
        return SDE;
    }

    public Class getITO() {
        return ITO;
    }

    public Class getSløjd() {
        return Sløjd;
    }

    public Student getAlex() {
        return Alex;
    }

    public Student getAsbjørn() {
        return Asbjørn;
    }

    public Student getJan() {
        return Jan;
    }
    
    
    
}
