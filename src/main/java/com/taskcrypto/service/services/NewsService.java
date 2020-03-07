package com.taskcrypto.service.services;

import com.taskcrypto.service.models.NewsServiceModel;

import java.util.List;

public interface NewsService {
    void insertNews(NewsServiceModel newsServiceModel);

    List<NewsServiceModel> getAllNews();
}
