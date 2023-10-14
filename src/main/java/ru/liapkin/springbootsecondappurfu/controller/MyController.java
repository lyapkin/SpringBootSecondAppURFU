package ru.liapkin.springbootsecondappurfu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liapkin.springbootsecondappurfu.exception.UnsupportedCodeException;
import ru.liapkin.springbootsecondappurfu.exception.ValidationFailedException;
import ru.liapkin.springbootsecondappurfu.model.*;
import ru.liapkin.springbootsecondappurfu.service.ModifyRequestService;
import ru.liapkin.springbootsecondappurfu.service.ModifyResponseService;
import ru.liapkin.springbootsecondappurfu.service.ValidationService;
import ru.liapkin.springbootsecondappurfu.util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        request.setReceivedTime(DateTimeUtil.getCustomFormat().format(new Date()));

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Создан базовый response: {}", response);

        try {
            if (request.getUid().equals("123")) {
                log.error("uid = 123");
                throw new UnsupportedCodeException("uid = 123");
            }
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.info("Поле response.code изменилось на {}", response.getCode());
            log.info("Поле response.errorCode изменилось на {}", response.getErrorCode());
            log.info("Поле response.errorMessage изменилось на {}", response.getErrorMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.error("Поле response.code изменилось на {}", response.getCode());
            log.error("Поле response.errorCode изменилось на {}", response.getErrorCode());
            log.error("Поле response.errorMessage изменилось на {}", response.getErrorMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.error("Поле response.code изменилось на {}", response.getCode());
            log.error("Поле response.errorCode изменилось на {}", response.getErrorCode());
            log.error("Поле response.errorMessage изменилось на {}", response.getErrorMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
