package com.mapproject.codexist.Controller;


import com.mapproject.codexist.Core.results.ErrorResult;
import com.mapproject.codexist.Core.results.Result;
import com.mapproject.codexist.Entities.Dto.MapDto;
import com.mapproject.codexist.Business.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/map/")
@CrossOrigin
@Slf4j
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }


    @GetMapping("/position")
    public Result getPosition (
            @RequestParam(name = "longitude", required = true) Double longitude,
            @RequestParam(name = "latitude", required = true) Double latitude,
            @RequestParam(name = "radius", defaultValue = "0") int radius
            ) {

        try {
            MapDto mapDto = new MapDto(longitude, latitude, radius);
            return this.mapService.MapInfoService(mapDto);
        }
        catch (RuntimeException e) {
            log.error("Beklenmeyen bir hata oluştu: {}", e.getMessage());
            return new ErrorResult(e.getMessage());
        }
        catch (Exception e) {
            log.error("Beklenmeyen bir hata oluştu: {}", e.getMessage());
            return new ErrorResult("Beklenmeyen bir hata oluştu: "+e.getMessage());
        }
    }

}
