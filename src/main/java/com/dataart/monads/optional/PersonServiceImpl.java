package com.dataart.monads.optional;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonServiceImpl {

    public static List<Worker> getWorkersByCompanyAndProf(MapNpeProtection<String, Company> companyMap, String companyName, Profession profession) {
        return companyMap.find(companyName)
                .flatMap(Company::getWorkers)
                .orElse(Collections.emptyList())
                .stream().filter(worker -> profession.equals(worker.getProfession()))
                .collect(Collectors.toList());
    }
}
