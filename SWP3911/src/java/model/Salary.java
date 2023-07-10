/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class Salary {

    private int id;
    private int id_acc;
    private String month_starts;
    private String month_ends;
    private int wage;

    public Salary() {
    }

    public Salary(int id, int id_acc, String month_starts, String month_ends, int wage) {
        this.id = id;
        this.id_acc = id_acc;
        this.month_starts = month_starts;
        this.month_ends = month_ends;
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
    }

    public String getMonth_starts() {
        return month_starts;
    }

    public void setMonth_starts(String month_starts) {
        this.month_starts = month_starts;
    }

    public String getMonth_ends() {
        return month_ends;
    }

    public void setMonth_ends(String month_ends) {
        this.month_ends = month_ends;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
    

}
