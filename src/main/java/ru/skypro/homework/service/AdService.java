package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;

import java.io.IOException;

public interface AdService {
    Ads getAllAds();
    ExtendedAd getAd(Integer adId);
    Ads getAdsMe();
//    AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image);

    AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) throws IOException;

    AdDto updateAd(Integer adId, CreateOrUpdateAdDto properties);
    void updateImage(Integer adId, MultipartFile image);
    void deleteAd(Integer adId);
    byte[] getAdImage(int id) throws IOException;

}
