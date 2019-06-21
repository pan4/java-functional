package com.dataart.streams;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamServiceImpl {

    public static Long wordsCounter(List<String> words, String word) {
        return words.stream()
                .map(w -> w.replaceAll("[^a-zA-Z ]", ""))
                .filter(test -> test.equals(word))
                .count();
    }

    public static Integer getSum(int... i) {
        IntStream stream = IntStream.of(i);
        return stream.sum();
    }

    public static Long getCountWordsLongerThan(List<String> words, int count) {
        return words.stream().filter(w -> w.length() > count).count();
    }

    public static Long getCountWordsStartWith(List<String> words, String startWith) {
        return words.stream().filter(w -> w.startsWith(startWith)).count();
    }

    public static String getFirstWordStartWith(List<String> words, String startWith) {
        return words.stream().filter(w -> w.startsWith(startWith)).findFirst().orElse("null");
    }

    public static Double getAverageAge(List<Worker> workers) {
        return workers.stream().mapToInt(worker -> worker.getAge()).summaryStatistics().getAverage();
    }

    public static List<String> capitalLetter(List<String> words) {
        return words.stream()
                .map(StreamServiceImpl::capitalize)
                .collect(Collectors.toList());
    }

    private static String capitalize(String str){
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static List<String> concatenationAndToUpperCase(List<String> words1, List<String> words2) {
        return Stream.of(words1, words2)
                .flatMap(words -> words.stream())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> sortWordsByLength(List<String> words) {
        return words.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
    }

    public static List<String> excludeDuplicateAndAlphabeticalSort(List<String> words) {
        return words.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Worker> searchWorkers(List<Company> companies, Profession profession) {
        return companies.stream()
                .flatMap(company -> company.getWorkers().orElse(Collections.emptyList()).stream())
                .filter(worker -> profession.equals(worker.getProfession()))
                .collect(Collectors.toList());
    }

}
