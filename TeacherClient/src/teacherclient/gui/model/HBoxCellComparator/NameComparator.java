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
public class NameComparator extends HBoxCellComparator
{
    public NameComparator()
    {
        super.name = "Name";
        super.comparator = new Comparator<HBoxCell>()
        {
            @Override
            public int compare(HBoxCell o1, HBoxCell o2)
            {
                String name1 = o1.getStudent().getName();
                String name2 = o2.getStudent().getName();

                return name1.compareTo(name2);
            }
        };
    }
}
