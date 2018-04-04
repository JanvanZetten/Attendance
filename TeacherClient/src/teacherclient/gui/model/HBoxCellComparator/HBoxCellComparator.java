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
public abstract class HBoxCellComparator
{
    protected String name;
    protected Comparator<HBoxCell> comparator;

    public String getName()
    {
        return name;
    }

    public Comparator<HBoxCell> getComparator()
    {
        return comparator;
    }
}
