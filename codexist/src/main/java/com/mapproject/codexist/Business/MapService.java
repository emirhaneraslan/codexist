package com.mapproject.codexist.Business;

import com.mapproject.codexist.Core.exception.InvalidCoordinateException;
import com.mapproject.codexist.Core.exception.InvalidRadiusException;
import com.mapproject.codexist.Core.results.Result;
import com.mapproject.codexist.Core.results.SuccessDataResult;
import com.mapproject.codexist.Entities.Dto.MapDto;
import com.mapproject.codexist.Entities.MapInfo;
import com.mapproject.codexist.DataAccess.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MapService {

    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public Result MapInfoService (MapDto mapDto){

        if(mapDto.getRadius()<0) {
            throw new InvalidRadiusException("Yarıçap negatif olamaz.");
        }

        if (mapDto.getLongitude() < -180 || mapDto.getLongitude() > 180 || mapDto.getLatitude() < -90 || mapDto.getLatitude() > 90) {
            throw new InvalidCoordinateException("Geçersiz koordinat değerleri girdiniz.");
        }

        Optional<MapInfo> existingMapInfo = mapRepository.findByLongitudeAndLatitudeAndRadius(mapDto.getLongitude(), mapDto.getLatitude(), mapDto.getRadius());

        if (existingMapInfo.isPresent()) {
            return new SuccessDataResult<>(existingMapInfo.get(), "Mevcut kayıt bulundu.");
        }

        MapInfo mapInfo = new MapInfo();
        mapInfo.setLatitude(mapDto.getLatitude());
        mapInfo.setLongitude(mapDto.getLongitude());
        mapInfo.setRadius(mapDto.getRadius());
        mapRepository.save(mapInfo);

        return new SuccessDataResult<MapInfo>(mapInfo,"Adres Başarıyla Eklendi.");
    }
}
