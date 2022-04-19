package com.guli.edustatistic.controller;


import com.commonutils.Result;
import com.guli.edustatistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/edustatistic/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    @PostMapping("{day}")
    public Result createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return Result.ok();
    }
    @GetMapping("show-chart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return Result.ok().data(map);
    }
}

