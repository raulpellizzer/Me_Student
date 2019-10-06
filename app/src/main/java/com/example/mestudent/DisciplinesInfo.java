package com.example.mestudent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DisciplinesInfo extends AppCompatActivity {
    private String discName;
    private String discTeacherName;
    private String discClassroom;
    private String discDay;

    public DisciplinesInfo(String discName, String discTeacherName, String discClassroom, String discDay) {
        this.discName = discName;
        this.discTeacherName = discTeacherName;
        this.discClassroom = discClassroom;
        this.discDay = discDay;
    }

    public static List<DisciplinesInfo> listAll(){
        List<DisciplinesInfo> disciplines = new ArrayList<>();
        disciplines.add(new DisciplinesInfo("Nome1", "Nome1", "Nome1", "Nome1"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));
        disciplines.add(new DisciplinesInfo("Nome2", "Nome2", "Nome2", "Nome2"));

        return disciplines;
    }

    public String getDiscName(){
        return this.discName;
    }

    public String getdiscTeacherName(){
        return this.discTeacherName;
    }

    public String getdiscClassroom(){
        return this.discClassroom;
    }

    public String getdiscDay(){
        return this.discDay;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public void setdiscTeacherName(String discTeacherName) {
        this.discTeacherName = discTeacherName;
    }

    public void setdiscClassroom(String discClassroom) {
        this.discClassroom = discClassroom;
    }

    public void setdiscDay(String discDay) {
        this.discDay = discDay;
    }
}
