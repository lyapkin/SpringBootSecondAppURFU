package ru.liapkin.springbootsecondappurfu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /***
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "Пусто поле UID")
    @Size(max=32, message = "Длина значения поля UID превышает 32 символа")
    private String uid;

    /***
     * Уникальный идентификатор операции
     */
    @NotBlank(message = "Пустое поле operationUID")
    @Size(max=32, message = "Длина значения поля operationUID превышает 32 символа")
    private String operationUid;

    /***
     * Имя системы отправителя
     */
    private Systems systemName;

    /***
     * Время создания сообщения
     */
    @NotBlank(message = "Пустое поле systemTime")
    @Pattern(regexp = "(([0-1][0-9])|(2[0-4])):[0-5][0-9]", message = "Значение поля systemTime не соответствует шаблону 'hh:mm'")
    private String systemTime;

    /***
     * Время получения запроса
     */
    private String receivedTime;

    /***
     * Наименование рессурса
     */
    private String source;

    /***
     * Должность
     */
    private Positions position;

    /***
     * Зарплата
     */
    private double salary;

    /***
     * Бонус
     */
    private double bonus;

    /***
     * Количество отработанных дней
     */
    private int workDays;

    /***
     * Рабочий год
     */
    @Min(value = 1900, message = "Значение поля communicationID меньше 1900")
    @Max(value = 3000, message = "Значение поля communicationID больше 3000")
    private int workYear;

    /***
     * Уникальный идентификатор коммуникации
     */
    @Min(value = 1, message = "Значение поля communicationID меньше 1")
    @Max(value = 100000, message = "Значение поля communicationID больше 100000")
    private int communicationId;

    /***
     * Уникальный идентификатор шаблона
     */
    private int templateId;

    /***
     * Код продукта
     */
    private int productCode;

    /***
     * Код СМС
     */
    private int smsCode;

    @Override
    public String toString() {
        return  '{' +
                "uid='" + uid + '\'' +
                "operationUid='" + operationUid + '\'' +
                "systemName='" + systemName + '\'' +
                "systemTime='" + systemTime + '\'' +
                "source='" + source + '\'' +
                "communicationId='" + communicationId + '\'' +
                "templateId='" + templateId + '\'' +
                "productCode='" + productCode + '\'' +
                "smsCode='" + smsCode + '\'' +
                '}';
    }

}
