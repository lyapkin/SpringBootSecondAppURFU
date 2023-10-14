package ru.liapkin.springbootsecondappurfu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.liapkin.springbootsecondappurfu.model.Request;
import ru.liapkin.springbootsecondappurfu.model.Systems;

@Slf4j
@Service
@Qualifier("ModifySystemNameRequestService")
public class ModifySystemNameRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.SYSTEM_1);
        log.info("Поле request.systemName изменилось на {}", Systems.SYSTEM_1);


        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });

    }
}
