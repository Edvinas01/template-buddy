package com.edd.rest.integration;

import com.edd.rest.TemplateBuddy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Resource
    private TemplateBuddy templateBuddy;

    @Test
    public void test() {
        templateBuddy.fromUrl("http://localhost:{port}/config", port)
                .get();
    }
}