package ru.liapkin.springbootsecondappurfu.service;

import org.springframework.stereotype.Service;
import ru.liapkin.springbootsecondappurfu.model.Positions;
import ru.liapkin.springbootsecondappurfu.model.Request;

@Service
public interface BonusService {

    double calculateAnnual(Positions position, double salary, double bonus, int workDays, int year);

    double calculateQuarterly(Positions position, double salary, double bonus, int workDays);

    double calculate(Request request);

}
