/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model.HBoxCellComparator;

import java.util.Comparator;
import teacherclient.dal.HBoxCell;

/**
 *
 * @author Asbamz
 */
public class AbsenceComparator extends HBoxCellComparator
{
    public AbsenceComparator()
    {
        super.name = "Absence";
        super.comparator = new Comparator<HBoxCell>()
        {
            @Override
            public int compare(HBoxCell o1, HBoxCell o2)
            {
                Double name1 = o1.getAbsence();
                Double name2 = o2.getAbsence();

                return name2.compareTo(name1);
            }
        };
    }
}
