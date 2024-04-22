package ru.hogwarts.school.conrollers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@Profile({"prod","test"})
public class InfoController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port-info")
    public String getServerPort() {
        return "This server is runnin' by port: " + serverPort;
    }
}
