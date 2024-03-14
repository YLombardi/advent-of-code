package fr.ylombardi.adventofcode.y2018.d7;

public class Worker {

    public Worker(int id) {
        this.id = id;
    }

    int id;
    String nodeWorkingOn = ".";
    int remainingWorkingTime = 0;

    boolean isWorking() {
        return remainingWorkingTime > 0;
    }

    void startWorking(String nodeLabel, int workingTime) {
        nodeWorkingOn = nodeLabel;
        remainingWorkingTime = workingTime;
    }

    void work1second() {
        if (isWorking()) remainingWorkingTime--;
    }

    String nodeDone() {
        if (!isWorking() && !".".equals(nodeWorkingOn)) {
            String nodeDone = nodeWorkingOn;
            nodeWorkingOn =  ".";
            return nodeDone;
        }
        return null;
    }

    String workOn() {
        return nodeWorkingOn;
    }
}
