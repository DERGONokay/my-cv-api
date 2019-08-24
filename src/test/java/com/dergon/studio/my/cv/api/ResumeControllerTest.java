package com.dergon.studio.my.cv.api;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Damian L. Lisas on 2019-08-24
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(value={"classpath:application-local.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResumeControllerTest {
}
