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

    @NotBlank(message = "Пусто поле UID")
    @Size(max=32, message = "Длина значения поля UID превышает 32 символа")
    private String uid;
    @NotBlank(message = "Пустое поле operationUID")
    @Size(max=32, message = "Дляна значения поля operationUID превышает 32 символа")
    private String operationUid;
    private Systems systemName;
    @NotBlank(message = "Пустое поле systemTime")
    @Pattern(regexp = "(([0-1][0-9])|(2[0-4])):[0-5][0-9]", message = "Значение поля systemTime не соответствует шаблону 'hh:mm'")
    private String systemTime;
    private String receivedTime;
    private String source;
    @Min(value = 1, message = "Значение поля communicationID меньше 1")
    @Max(value = 100000, message = "Значение поля communicationID больше 100000")
    private int communicationId;
    private int templateId;
    private int productCode;
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
