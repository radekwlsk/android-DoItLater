package com.afrometal.radoslaw.doitlater;

/**
 * Created by radoslaw on 30.04.17.
 */

public class ToDoListItem {
    Long id;
    String title;
    String date;
    String due;

    public ToDoListItem(Long id, String title, String date, String due) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.due = due;
    }
}
