/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Student;
import be.Class;
import data.MockData;

/**
 *
 * @author alexl
 */
public class MainModel {
    
    private MockData data;

    public void createMockData() {
        data = new MockData();
        data.createMockData();
    }
    
}
