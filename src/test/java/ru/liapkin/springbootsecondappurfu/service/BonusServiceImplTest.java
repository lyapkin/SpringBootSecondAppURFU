package ru.liapkin.springbootsecondappurfu.service;

import org.junit.jupiter.api.Test;
import ru.liapkin.springbootsecondappurfu.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BonusServiceImplTest {

    @Test
    void calculateAnnual() {

        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        int year = 2023;

        double result = new BonusServiceImpl().calculateAnnual(position, salary, bonus, workDays, year);

        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyForManager() {

        Positions position = Positions.TL;
        double bonus = 2.0;
        double salary = 100000;
        int workDays = 58;

        double result = new BonusServiceImpl().calculateQuarterly(position, salary, bonus, workDays);

        double expected = 8965.51724137931;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyForNotManager() {

        Positions position = Positions.HR;
        double bonus = 2.0;
        double salary = 100000;
        int workDays = 200;

        double result = new BonusServiceImpl().calculateQuarterly(position, salary, bonus, workDays);

        double expected = 0;
        assertThat(result).isEqualTo(expected);
    }
}