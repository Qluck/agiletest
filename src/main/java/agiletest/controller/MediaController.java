package agiletest.controller;

import agiletest.entity.Media;
import agiletest.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/v1")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @GetMapping("images")
    public ResponseEntity<List<Media>> getMediaByPage(@RequestParam Integer pageNumber) {
        return new ResponseEntity<>(mediaService.getMediaByPage(pageNumber), HttpStatus.OK);
    }
}
