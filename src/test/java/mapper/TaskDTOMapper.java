package mapper;


import models.TaskDTO;

import java.util.Hashtable;

import static java.lang.Integer.parseInt;

public class TaskDTOMapper {

    public static TaskDTO mapToTaskDTO(Hashtable<String, String> data) {
        TaskDTO task = new TaskDTO();
        task.setTaskName(data.get("taskName"));
        task.setHourlyRate(data.get("hourlyRate"));
        task.setStartDate(data.get("startDate"));
        task.setDueDate(data.get("dueDate"));
        task.setPriority(data.get("priority"));
        task.setRepeatEvery(data.get("repeatEvery"));
        task.setTotalCycles(data.get("totalCycles"));
        task.setRelatedTo(data.get("relatedTo"));
        task.setTypeRelatedTo(data.get("typeRelatedTo"));
        task.setAssignee(data.get("assignee"));
        task.setFollower(data.get("follower"));
        task.setTag(data.get("tag"));
        task.setDescription(data.get("description"));

        return task;
    }
}
