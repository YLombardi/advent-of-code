package fr.ylombardi.adventofcode.y2018.d7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Workers {

    List<Worker> workers;

    public Workers(int nbWorkers) {
        this.workers = new ArrayList<>();
        this.initWorkerList(nbWorkers);
    }

    void initWorkerList(int nbWorkers) {
        for (int i = 1; i <= nbWorkers; i++) {
            workers.add(new Worker(i));
        }
    }

    boolean hasFreeWorker() {
        var freeWorker = workers.stream().filter(w -> !w.isWorking()).findAny();
        return freeWorker.isPresent();
    }

    /**
     * Assigne une tâche à un worker.
     * @param nodeLabel node
     * @param timeOffset time offset to calculate the working duration
     */
    void assignTask(String nodeLabel, int timeOffset) {
        var freeWorker = workers.stream().filter(w -> !w.isWorking()).findAny();
        if (freeWorker.isPresent()) {
            int time = getTime(nodeLabel);
            freeWorker.get().startWorking(nodeLabel, timeOffset + time);
        }
    }

    /**
     * Permet de mapper le libellé d'un noeud vers un temps de travail en utilisant
     * la position de la lette dans l'alphabet
     * @param nodeLabel Alphabet letter
     * @return Time equivalent for this letter
     */
    static int getTime(String nodeLabel) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(nodeLabel) + 1;
    }

    /**
     * Fait travailler les workers pendant 1 seconde
     */
    void work() {
        workers.forEach(Worker::work1second);
    }

    /**
     * Sert à l'affichage des noeuds sur lesquels les workers travaillents
     * @return List of nodes formatted as String
     */
    String workOn() {
        List<String> workOnList = workers.stream().map(Worker::workOn).toList();
        return String.join(" ", workOnList);
    }

    /**
     * Retourne la liste des noeuds que les workers viennent de finir
     * @return List of nodes
     */
    List<String> nodeDone() {
        return workers.stream().map(Worker::nodeDone).filter(Objects::nonNull).toList();
    }
}
