package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.exception.*;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.mappers.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Transactional
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AdMapper adMapper;
    private final ImageServiceImpl imageService;

    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, CommentRepository commentRepository, AdMapper adMapper, ImageServiceImpl imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    /**
     * Find all
     *
     * @return Ads
     */
    @Override
    public Ads getAllAds() {
        List<AdDto> ads = adRepository.findAll().stream()
                .map(ad -> adMapper.adToAdDto(ad, "/ads/images/"))
                .toList();
        if (ads.isEmpty()) {
            throw new NoAdsExistException();
        }

        return new Ads(
                ads.size(),
                ads
        );
    }

    /**
     * Get ad by id from DB.
     *
     * @param adId ad ID.
     * @return ExtendedAd.
     */
    @Override
    public ExtendedAd getAd(Integer adId) {
        return adMapper.adToExtendedAd(adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId)), "/ads/images/");
    }

    /**
     * Get ads from an authorized user.
     *
     * @return Ads.
     */
    @Override
    public Ads getAdsMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()));
            List<AdDto> ads = adRepository.findAllByAuthorId(user.getId()).orElseThrow(() -> new UserNotFoundException(authentication.getName())).stream()
                    .map(ad -> adMapper.adToAdDto(ad, "/ads/images/"))
                    .toList();
            if (ads.isEmpty()) {
                throw new NoAdsExistException();
            }

            return new Ads(
                    ads.size(),
                    ads
            );
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Add
     *
     * @param properties CreateOrUpdateAdDto.
     * @param file      file of image.
     * @return AdDTO Dto-объект объявления.
     */
    @Override
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Ad ad = new Ad(
                    properties.getTitle(),
                    properties.getDescription(),
                    imageService.updateImage(file),
                    properties.getPrice(),
                    userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()))
            );

            adRepository.save(ad);
            log.info("Ad {} {} saved", ad.getPk(), ad.getTitle());
            return adMapper.adToAdDto(ad, "/ads/images/");
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Update
     *
     * @param adId       Id.
     * @param properties CreateOrUpdateAdDto.
     * @return AdDTO Dto-объект объявления.
     */
    @Override
    public AdDto updateAd(Integer adId, CreateOrUpdateAdDto properties) {
        if (!adRepository.existsById(adId)) {
            throw new AdNotFoundException(adId);
        }
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId));
        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setDescription(properties.getDescription());
        adRepository.save(ad);
        return adMapper.adToAdDto(ad, "/ads/images/");
    }

    /**
     * Change
     *
     * @param image file
     * @throws ImageNotFoundException if it is not found
     */
    @Override
    public void updateImage(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        if (!ad.getImage().equals("no_image.png")) {
          imageService.deleteAdImage(ad);
        }
        String path = imageService.updateImage(image);
        ad.setImage(path);
        adRepository.save(ad);
    }

    /**
     * Delete
     *
     * @param adId ad ID.
     * @throws AdNotFoundException if it is not found.
     */
    @Override
    public void deleteAd(Integer adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId));
        commentRepository.deleteAllByAdPk(ad.getPk());
        imageService.deleteAdImage(ad);
        adRepository.delete(ad);
    }

    @Override
    public byte[] getAdImage(int id) throws IOException {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        return imageService.getAdImage(ad);
    }

    /**
     * Get
     *
     * @param adId Ad ID.
     * @return String.
     * @throws AdNotFoundException if it is not found.
     */
    public Ad getAdByUsername(Integer adId) {
        return adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId));
    }

}
