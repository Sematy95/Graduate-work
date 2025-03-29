package ru.skypro.homework.service.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdMapperTest {

    private AdMapper mapper = Mappers.getMapper(AdMapper.class);
    Ad ad = new Ad(1,"title","d","i",2,new User(1,"123456","Oleg","Smagin","+79215600890","email@mail.ru", Role.USER,"112"));


    @Test
    void adToAdDto() {
        AdDto adDto = mapper.adToAdDto(ad);
        System.out.println("adDto = " + adDto);
        assertEquals(ad.getAuthor().getId(), adDto.getAuthor());
        assertEquals(ad.getImage(), adDto.getImage());
        assertEquals(ad.getPk(), adDto.getPk());
        assertEquals(ad.getTitle(), adDto.getTitle());
        assertEquals(ad.getPrice(), adDto.getPrice());



    }

    @Test
    void adToExtendedAd() {
        ExtendedAd extendedAd = mapper.adToExtendedAd(ad);
        System.out.println("extendedAd = " + extendedAd);
        assertEquals(ad.getAuthor().getFirstName(), extendedAd.getAuthorFirstName());
        assertEquals(ad.getAuthor().getLastName(), extendedAd.getAuthorLastName());
        assertEquals(ad.getAuthor().getEmail(), extendedAd.getEmail());
        assertEquals(ad.getAuthor().getPhone(), extendedAd.getPhone());
        assertEquals(ad.getImage(), extendedAd.getImage());
        assertEquals(ad.getPk(), extendedAd.getPk());
        assertEquals(ad.getTitle(), extendedAd.getTitle());
        assertEquals(ad.getPrice(), extendedAd.getPrice());
        assertEquals(ad.getDescription(), extendedAd.getDescription());
    }

    @Test
    void adToCreateOrUpdateAd() {
        CreateOrUpdateAdDto adDto = mapper.adToCreateOrUpdateAd(ad);
        System.out.println("adDto = " + adDto);
        assertEquals(ad.getTitle(), adDto.getTitle());
        assertEquals(ad.getPrice(), adDto.getPrice());
        assertEquals(ad.getDescription(), adDto.getDescription());
    }
}