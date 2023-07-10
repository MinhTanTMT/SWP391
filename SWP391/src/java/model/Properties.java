/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class Properties {

    public int Index;
    public String Name;
    public double Translation_X;
    public double Translation_Y;
    public double Translation_Z;
    public double Rotation_X;
    public double Rotation_Y;
    public double Rotation_Z;
    public double Scale_X;
    public double Scale_Y;
    public double Scale_Z;

    public Properties() {
    }

    public Properties(int Index, String Name, double Translation_X, double Translation_Y, double Translation_Z, double Rotation_X, double Rotation_Y, double Rotation_Z, double Scale_X, double Scale_Y, double Scale_Z) {
        this.Index = Index;
        this.Name = Name;
        this.Translation_X = Translation_X;
        this.Translation_Y = Translation_Y;
        this.Translation_Z = Translation_Z;
        this.Rotation_X = Rotation_X;
        this.Rotation_Y = Rotation_Y;
        this.Rotation_Z = Rotation_Z;
        this.Scale_X = Scale_X;
        this.Scale_Y = Scale_Y;
        this.Scale_Z = Scale_Z;
    }

}
