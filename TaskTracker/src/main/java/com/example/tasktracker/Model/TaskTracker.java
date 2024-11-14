package com.example.tasktracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskTracker {
    private String taskID,title,description,taskStatus;

}
