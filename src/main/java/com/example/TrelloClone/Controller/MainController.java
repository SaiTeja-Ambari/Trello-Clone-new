package com.example.TrelloClone.Controller;

import com.example.TrelloClone.Helpers.State;
import com.example.TrelloClone.Repo.HistoryRepository;
import com.example.TrelloClone.Helpers.Task;
import com.example.TrelloClone.Helpers.History;
import com.example.TrelloClone.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import static java.time.temporal.ChronoUnit.MINUTES;


@Controller
@RequestMapping(path = "/demo")
@CrossOrigin
public class MainController {

    
	@Autowired
    private MainService mainService;

    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Creates a task with the provided description. 
     * User assignment and comments are optional.
     * 
     * @param suid - Optional user ID
     * @param desc - Description of the task
     * @param comment - Optional comments
     * @return task ID or -1 in case of an error
     */
    @PostMapping(path = "/add")
    public @ResponseBody Long createTask(@RequestParam(required = false) Long suid, String desc, @RequestParam(required = false) List<String> comment) {
        Task task = new Task();
        History historyEntry = new History();
        
        if (suid != null) {
            String name = mainService.findByName(suid);
            if (name == null) {
                return -1L;
            }
            task.setSuid(suid);
            task.setName(name);
            
            historyEntry.setSuid(suid);
            historyEntry.setName(name);
        }

        if (comment != null) {
            task.setComments(comment);
        }

        task.setDescription(desc);
        task.setState(State.TODO);
        task.setTimestamp();

        historyEntry.setDescription(desc);
        historyEntry.setState(State.TODO);

        mainService.save(task);
        historyEntry.setTaskID(task.getTaskID());
        historyRepository.save(historyEntry);

        return task.getTaskID();
    }

    /**
     * Retrieves all tasks.
     * 
     * @return list of all tasks
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks() {
        return mainService.findAll();
    }

    /**
     * Deletes a task based on task ID.
     * 
     * @param taskID - ID of the task to be deleted
     * @return "success" if the task was deleted, "failure" otherwise
     */
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteTask(@RequestParam Long taskID) {
        if (taskID.equals(mainService.findtaskID(taskID))) {
            mainService.deleteCommentById(taskID);
            mainService.deleteById(taskID);
            return "success";
        }
        return "failure";
    }

    /**
     * Modifies an existing task. Allows updating the state, user, description, and comments.
     * 
     * @param id - ID of the task to be modified
     * @param state - New state of the task
     * @param suid - User ID
     * @param desc - Description update
     * @param comment - Comment to be added
     * @return "success" if the task was modified, error message otherwise
     */

    //Modifies existing task. Can add or change the user assigned, can change state of task, can add comments.
    //If state of task has been changed to done, total time taken for the task to move from to-do to done is calculated(in minutes) and shown.
    @PostMapping(path = "/modify")
    public @ResponseBody String modifyTask(@RequestParam Long id, @RequestParam(required = false) String state, @RequestParam Long suid, @RequestParam(required = false, defaultValue = "") String desc, @RequestParam(required = false, defaultValue = "") String comment) {

        History h =new History();
        h.setTaskID(id);

        if (state != null) {

            State state_num=mainService.getCurrentState(id);
            if (State.valueOf(state) != State.TODO && state_num.TODO.getNumVal() == State.TODO.getNumVal()) {

                LocalTime timestamp = mainService.fetchTime(id);
                Long timeToComplete = timestamp.until(LocalTime.now(), MINUTES);
                Long prevtimeInTodo = mainService.fetchTimeInTodo(id);
                Long finaltime;
                if(prevtimeInTodo!=null) { finaltime = prevtimeInTodo + timeToComplete;}
                else finaltime=timeToComplete;
                mainService.timeInTodo(id, finaltime);
                h.setTimeInTodo(finaltime);
                mainService.updateTimestamp(id, Time.valueOf(LocalTime.now()));

            }
            if (State.valueOf(state) != State.DOING && state_num.DOING.getNumVal() == State.DOING.getNumVal()) {

                LocalTime timestamp = mainService.fetchTime(id);
                Long timeToComplete = timestamp.until(LocalTime.now(), MINUTES);
                Long prevtimeInDoing = mainService.fetchTimeInDoing(id);
                Long finaltime;
                if(prevtimeInDoing!=null) { finaltime = prevtimeInDoing + timeToComplete;}
                else finaltime =timeToComplete;

                mainService.timeInDoing(id, finaltime);
                h.setTimeInTodo(finaltime);
                mainService.updateTimestamp(id, Time.valueOf(LocalTime.now()));
            }
            if (State.valueOf(state) != State.DONE && state_num.DONE.getNumVal() == State.DONE.getNumVal()) {
                LocalTime timestamp = mainService.fetchTime(id);
                Long timeToComplete = timestamp.until(LocalTime.now(), MINUTES);
                Long prevtimeInDone = mainService.fetchTimeInDone(id);
                Long finaltime;
                if(prevtimeInDone!=null) { finaltime = prevtimeInDone + timeToComplete;}
                else finaltime=timeToComplete;
                mainService.timeInDone(id, finaltime);
                h.setTimeInTodo(finaltime);
                mainService.updateTimestamp(id, Time.valueOf(LocalTime.now()));
            }
            h.setState(State.valueOf(state));
            mainService.changestate(id, State.valueOf(state).getNumVal());
        }
        if (suid != null) {
            mainService.changeusername(id, suid);
            h.setSuid(suid);
            String name = mainService.findByName(suid);
            if (name != null) {
                h.setName(name);
                mainService.changename(id, name);
            } else return "enter a valid suid";
        }
        if (desc != null) {
            mainService.changedesc(id, desc);
            h.setDescription(desc);
        }

        if (comment != null) {
            mainService.addComment(id, comment);
        }
        historyRepository.save(h);
        return "success";
    }

    /**
     * Retrieves the history of task modifications.
     * 
     * @return list of all history entries
     */
    @GetMapping(path = "/history")
    public @ResponseBody Iterable<History> getHistory() {
        return historyRepository.findAll();
    }
}
