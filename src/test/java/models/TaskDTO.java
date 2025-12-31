package models;

public class TaskDTO {
    private int isCheckedCheckbox;
    private String taskName;
    private String hourlyRate;
    private String startDate;
    private String dueDate;
    private String priority;
    private String repeatEvery;
    private String totalCycles;
    private String relatedTo;
    private String typeRelatedTo;
    private String assignee;
    private String follower;
    private String tag;
    private String description;

    public TaskDTO(){}

    public TaskDTO(TaskDTO taskData) {
        this.isCheckedCheckbox = taskData.isCheckedCheckbox;
        this.taskName = taskData.taskName;
        this.hourlyRate = taskData.hourlyRate;
        this.startDate = taskData.startDate;
        this.dueDate = taskData.dueDate;
        this.priority = taskData.priority;
        this.repeatEvery = taskData.repeatEvery;
        this.totalCycles = taskData.totalCycles;
        this.relatedTo = taskData.relatedTo;
        this.typeRelatedTo = taskData.typeRelatedTo;
        this.assignee = taskData.assignee;
        this.follower = taskData.follower;
        this.tag = taskData.tag;
        this.description = taskData.description;
    }

    public int getCheckedCheckbox() {
        return isCheckedCheckbox;
    }

    public void setIsCheckedCheckbox(int isCheckedCheckbox) {
        this.isCheckedCheckbox = isCheckedCheckbox;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRepeatEvery() {
        return repeatEvery;
    }

    public void setRepeatEvery(String repeatEvery) {
        this.repeatEvery = repeatEvery;
    }

    public String getTotalCycles() {
        return totalCycles;
    }

    public void setTotalCycles(String totalCycles) {
        this.totalCycles = totalCycles;
    }

    public String getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getTypeRelatedTo() {
        return typeRelatedTo;
    }

    public void setTypeRelatedTo(String typeRelatedTo) {
        this.typeRelatedTo = typeRelatedTo;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
