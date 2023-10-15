package ru.liapkin.springbootsecondappurfu.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.liapkin.springbootsecondappurfu.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@Data
@Builder
public class Response {

    /***
     * Уникальный идентификатор сообщения
     */
    private String uid;


    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /***
     * Время создания сообщения
     */
    private String systemTime;

    /***
     * Код
     */
    private Codes code;

    /***
     * Бонус
     */
    private double bonus;

    /***
     * Код ошибки
     */
    private ErrorCodes errorCode;

    /***
     * Сообщение ошибки
     */
    private ErrorMessages errorMessage;

    @Override
    public String toString() {
        return  '{' +
                "uid='" + uid + '\'' +
                "operationUid='" + operationUid + '\'' +
                "systemTime='" + systemTime + '\'' +
                "code='" + code + '\'' +
                "errorCode='" + errorCode + '\'' +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public static Response create(Request request, double bonus) {

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .bonus(bonus)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Создан базовый response: {}", response);

        return response;
    }

    public static Response create(Request request) {

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Создан базовый response: {}", response);

        return response;
    }

    public void setValidationException() {
        this.setCode(Codes.FAILED);
        this.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        this.setErrorMessage(ErrorMessages.VALIDATION);

        log.info("Поле response.code изменилось на {}", this.getCode());
        log.info("Поле response.errorCode изменилось на {}", this.getErrorCode());
        log.info("Поле response.errorMessage изменилось на {}", this.getErrorMessage());
    }

    public void setUnsupportedException() {
        this.setCode(Codes.FAILED);
        this.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
        this.setErrorMessage(ErrorMessages.UNSUPPORTED);

        log.info("Поле response.code изменилось на {}", this.getCode());
        log.info("Поле response.errorCode изменилось на {}", this.getErrorCode());
        log.info("Поле response.errorMessage изменилось на {}", this.getErrorMessage());
    }

    public void setUnknownException() {
        this.setCode(Codes.FAILED);
        this.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        this.setErrorMessage(ErrorMessages.UNKNOWN);

        log.info("Поле response.code изменилось на {}", this.getCode());
        log.info("Поле response.errorCode изменилось на {}", this.getErrorCode());
        log.info("Поле response.errorMessage изменилось на {}", this.getErrorMessage());
    }

}
