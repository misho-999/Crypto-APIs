package com.taskcrypto.service.services.implementations;

import com.taskcrypto.data.models.News;
import com.taskcrypto.data.repositories.NewsRepository;
import com.taskcrypto.service.models.NewsServiceModel;
import com.taskcrypto.service.services.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insertNews(NewsServiceModel newsServiceModel) {
        this.newsRepository.save(this.modelMapper.map(newsServiceModel, News.class));
    }

    @Override
    public List<NewsServiceModel> getAllNews() {
        return this.newsRepository.findAll().stream()
                .map(n -> this.modelMapper.map(n, NewsServiceModel.class))
                .collect(Collectors.toList());
    }
}
