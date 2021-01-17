package chocolate.chocho.controller;

import chocolate.chocho.service.jobpost.command.JobPostServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
public class Controller {

    private final JobPostServiceImpl jobPostService;

    @PostMapping("/post/create/")
    public String bobo(Model model) {

    }

    static class postForm {
        private String namei;
    }

}
