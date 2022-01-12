package com.guili.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guili.eduservice.entity.EduChapter;
import com.guili.eduservice.entity.EduVideo;
import com.guili.eduservice.entity.vo.ChapterVo;
import com.guili.eduservice.entity.vo.VideoVo;
import com.guili.eduservice.mapper.EduChapterMapper;
import com.guili.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guili.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);

        List<String> idList = new ArrayList<>();
        for (EduChapter chapter : chapters) {
            idList.add(chapter.getId());
        }

        System.out.println("++++++++"+idList);
        //获取课时信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.in("chapter_id",idList);

        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> videos = eduVideoService.list(queryWrapper2);

        System.out.println("+++++++++++++++++"+videos);

        //填充章节vo数据
        int count1 = chapters.size();
        for (int i = 0; i < count1; i++) {
            EduChapter chapter = chapters.get(i);

            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoArrayList.add(chapterVo);

            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            int count2 = videos.size();
            for (int j = 0; j < count2; j++) {

                EduVideo video = videos.get(j);
                if(chapter.getId().equals(video.getChapterId())){

                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }

        return chapterVoArrayList;
    }
}
