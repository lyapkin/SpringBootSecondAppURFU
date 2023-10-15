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
import ru.liapkin.springbootsecondappurfu.service.BonusService;
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
    private final BonusService bonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService,
                        BonusService bonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.bonusService = bonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        request.setReceivedTime(DateTimeUtil.getCustomFormat().format(new Date()));

        double bonus = bonusService.calculate(request);

        Response response = Response.create(request, bonus);

        try {
            if (request.getUid().equals("123")) {
                log.error("uid = 123");
                throw new UnsupportedCodeException("uid = 123");
            }
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setValidationException();

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setUnsupportedException();

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setUnknownException();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
//        modifyRequestService.modify(request);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
