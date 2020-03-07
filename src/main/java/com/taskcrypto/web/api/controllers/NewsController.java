package com.taskcrypto.web.api.controllers;


import com.taskcrypto.constants.Messages;
import com.taskcrypto.service.models.NewsServiceModel;
import com.taskcrypto.service.services.NewsService;
import com.taskcrypto.web.api.models.AddNewsModel;
import com.taskcrypto.web.controllers.BaseController;
import com.taskcrypto.web.models.NewsViewModel;
import org.aspectj.bridge.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController extends BaseController {
    private final NewsService newsService;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/api/add-news", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
            , produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ModelAndView addNews(ModelAndView modelAndView, AddNewsModel model) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDate());

        NewsServiceModel newsServiceModel = new NewsServiceModel();
        newsServiceModel.setDate(date);
        newsServiceModel.setTitle(model.getTitle());
        newsServiceModel.setShortDescription(model.getShortDescription());
        newsServiceModel.setText(model.getText());

        this.newsService.insertNews(newsServiceModel);

        modelAndView.addObject("successAddedNewsMessage", Messages.ADDED_NEWS);

        return super.view("index.html", modelAndView);
    }

    @GetMapping("/api/all-news")
    List<NewsViewModel> allNews() {
        return this.newsService.getAllNews()
                .stream()
                .map(n -> {
                    NewsViewModel newsViewModel = this.modelMapper.map(n, NewsViewModel.class);

                    Date date = n.getDate();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(date);
                    newsViewModel.setDate(strDate);

                    return newsViewModel;
                })
                .collect(Collectors.toList());
    }

}
