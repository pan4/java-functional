package com.dataart.predicate;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerPredicates {

    public static Predicate<Worker> isAdult() {
        return isAgeMoreThan(21);
    }

    public static Predicate<Worker> isAgeMoreThan(int age) {
        return worker -> worker.getAge() > age;
    }

    public static Predicate<Worker> isAgeMoreThanAndProfession(int age, Profession profession) {
        return isAgeMoreThan(age).and(isProfession(profession));
    }

    public static Predicate<Worker> isAgeMoreThanOrProfession(int age, Profession profession) {
        return isAgeMoreThan(age).or(isProfession(profession));
    }

    public static List<Worker> filterWorkers(List<Worker> workerList, Predicate<Worker> predicate) {
        return workerList.stream().filter(predicate).collect(Collectors.toList());
    }

    private static Predicate<Worker> isProfession(Profession profession){
        return worker -> profession.equals(worker.getProfession());
    }
}
