/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.bll;

import java.util.List;
import studentclient.be.ScheduleItem;
import studentclient.dal.MockDAO;

/**
 *
 * @author janvanzetten
 */
public class BllManager {
    private MockDAO mock = new MockDAO();

    public List<ScheduleItem> getScheduleItems() {
        return mock.getSchedueleItems();
    }
    
}
