/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Student;
import be.Class;
import data.Data;

/**
 *
 * @author alexl
 */
public class MainModel {
    
    private Data data;

    public void createMockData() {
        data = new Data();
        data.createMockData();
        
        new Class(1, "SCO");
        new Class(2, "SDE");
        new Class(3, "ITO");
        new Class(4, "Hjemkunstskab");
        
        new Student(1, "Alex Tygesen", )
    }
    
}
