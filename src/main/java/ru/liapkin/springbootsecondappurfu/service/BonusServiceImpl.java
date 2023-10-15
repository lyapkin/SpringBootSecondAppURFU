package ru.liapkin.springbootsecondappurfu.service;

import org.springframework.stereotype.Service;
import ru.liapkin.springbootsecondappurfu.model.Positions;
import ru.liapkin.springbootsecondappurfu.model.Request;
import ru.liapkin.springbootsecondappurfu.util.DateTimeUtil;

@Service
public class BonusServiceImpl implements BonusService {

    @Override
    public double calculateAnnual(Positions position, double salary, double bonus, int workDays, int year) {
        return salary * bonus * DateTimeUtil.getDaysInYear(year) * position.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterly(Positions position, double salary, double bonus, int workDays) {

        if (!position.isManager()) return 0;

        return salary * bonus * position.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculate(Request request) {
        double bonus;
        if (request.getPosition().isManager()) {
            bonus = calculateQuarterly(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays()
            );
        } else {
            bonus = calculateAnnual(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays(),
                    request.getWorkYear()
            );
        }

        return bonus;
    }

}
