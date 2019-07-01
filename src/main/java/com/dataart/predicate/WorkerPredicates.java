package com.dataart.predicate;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerPredicates {

    public static Predicate<Worker> isAdult() {
        return worker -> worker.getAge() >= 21;
    }

    public static Predicate<Worker> isAgeMoreThan(int age) {
        return worker -> worker.getAge() > age;
    }

    public static Predicate<Worker> isAgeMoreThanAndProfession(int age, Profession profession) {
        return worker -> worker.getAge() > age && Profession.PM.equals(worker.getProfession());
    }

    public static Predicate<Worker> isAgeMoreThanOrProfession(int age, Profession profession) {
        return worker -> worker.getAge() > age || Profession.QA.equals(worker.getProfession());
    }

    public static List<Worker> filterWorkers(List<Worker> workerList, Predicate<Worker> predicate) {
        return workerList.stream().filter(predicate).collect(Collectors.toList());
    }
}
