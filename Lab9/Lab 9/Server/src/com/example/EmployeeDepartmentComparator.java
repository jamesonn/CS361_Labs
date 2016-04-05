package com.example;

import java.util.Comparator;

/**
 * Created by MSDK on 4/4/16.
 */
public class EmployeeDepartmentComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Employee && o2 instanceof Employee) {
            Employee e1 = (Employee) o1;
            Employee e2 = (Employee) o2;
            return e1.getDepartment().compareToIgnoreCase(e2.getDepartment());
        }
        return 0;
    }


}
