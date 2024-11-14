package com.example.tasktracker.controller;

import com.example.tasktracker.ApiResopnse.ApiResponse;
import com.example.tasktracker.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task/tracker")
public class TaskTrackerController {
    ArrayList<TaskTracker> taskTrackers=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<TaskTracker> getTask(){
        return taskTrackers;
    }
    @GetMapping("/search/{key}")
    public TaskTracker search(@PathVariable String key){
        for (TaskTracker taskTracker : taskTrackers) {
           if(taskTracker.getTitle().contains(key)) {
            return taskTracker;
           }
        }
        System.out.println("Not found");
        return null;
    }

    @PostMapping("/post")
    public ApiResponse addtask(@RequestBody  TaskTracker taskTracker){
        taskTrackers.add(taskTracker);
        return new ApiResponse( "added successfully");
    }
    @PutMapping("/status/{index}")
    public ApiResponse doneStatus(@PathVariable int index){
        if (taskTrackers.get(index).getTaskStatus()=="done"){
        taskTrackers.get(index).setTaskStatus("not done");}else
            taskTrackers.get(index).setTaskStatus("done");

        return new ApiResponse( "status updated successfully");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody TaskTracker taskTracker){
        taskTrackers.set(index,taskTracker);
        return new ApiResponse( "update successfully");
    }
    @DeleteMapping ("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        taskTrackers.remove(index);
        return new ApiResponse( "deleted successfully");
    }


}
