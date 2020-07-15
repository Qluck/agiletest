package agiletest.controller;

import agiletest.entity.Token;
import agiletest.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/v1")
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("token")
    public ResponseEntity<Token> getToken() {
        return new ResponseEntity<>(tokenService.getToken(), HttpStatus.OK);
    }
}
