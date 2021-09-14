package com.project.mreview.controller;

import com.project.mreview.service.MovieService;
import com.project.mreview.web.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String register(MovieDto movieDto, RedirectAttributes redirectAttributes){
        log.info("movieDTO:"+movieDto);
        Long mno = movieService.register(movieDto);

        redirectAttributes.addFlashAttribute("msg",mno);

        return "redirect:/movie/list";
    }
}
