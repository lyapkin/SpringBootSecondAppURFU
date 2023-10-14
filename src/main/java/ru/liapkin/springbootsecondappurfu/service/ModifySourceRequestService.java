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
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSource("Source: Service 1");
        log.info("Поле request.source изменилось на \"Source: Service 1\"");

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
