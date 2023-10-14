package ru.liapkin.springbootsecondappurfu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.liapkin.springbootsecondappurfu.model.Response;

import java.util.UUID;

@Slf4j
@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {

        UUID uuid = UUID.randomUUID();

        response.setOperationUid(uuid.toString());
        log.info("Поле response.operationUid изменилось на {}", response.getOperationUid());

        return response;

    }

}
