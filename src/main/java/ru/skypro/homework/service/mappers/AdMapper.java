package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ad.AdDto;

import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring", uses = AdMapper.class)
public interface AdMapper {

    @Mapping(target = "author",expression ="java(ad.getAuthor().getId())" )
    @Mapping(target = "image",expression ="java(path + ad.getPk())" )
    AdDto adToAdDto(Ad ad, String path);

    @Mapping(target = "authorFirstName",expression ="java(ad.getAuthor().getFirstName())" )
    @Mapping(target = "authorLastName",expression ="java(ad.getAuthor().getLastName())" )
    @Mapping(target = "email",expression ="java(ad.getAuthor().getEmail())" )
    @Mapping(target = "phone",expression ="java(ad.getAuthor().getPhone())" )
    @Mapping(target = "image",expression ="java(path + ad.getPk())" )
    ExtendedAd adToExtendedAd(Ad ad, String path);

    CreateOrUpdateAdDto adToCreateOrUpdateAd(Ad ad);








}