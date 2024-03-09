package Utils;

import Domain.Doctor;

import java.util.Comparator;

public class SortByGrade implements Comparator<Doctor> {

    public int compare(Doctor a,Doctor b)
    {
        return a.getGrade().compareTo(b.getGrade());
    }
}