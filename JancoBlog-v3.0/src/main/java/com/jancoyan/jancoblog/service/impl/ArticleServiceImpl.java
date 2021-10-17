package com.jancoyan.jancoblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.jancoblog.pojo.Article;
import com.jancoyan.jancoblog.mapper.ArticleMapper;
import com.jancoyan.jancoblog.pojo.ArticleImage;
import com.jancoyan.jancoblog.pojo.DeletedComment;
import com.jancoyan.jancoblog.pojo.PageArticle;
import com.jancoyan.jancoblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jancoyan.jancoblog.utils.ArticleUtils;
import com.jancoyan.jancoblog.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-09-14
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> getIndexList(Integer pn, Integer limit, String condition) {

        //        分页查询
        IPage<Article> iPage = new Page<>(pn, limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        String[] split = condition.split("--");
        for (String item : split) {
            String[] split2 = item.split("=");
            if(split2.length < 2){
                continue;
            }

            if("article_author_name".equals(split2[0])){
                wrapper.like("article_author_name", split2[1]);
            }else if("article_title".equals(split2[0])){
                wrapper.like("article_title", split2[1]);
            }else if("type".equals(split2[0])){
                wrapper.eq("article_type", split2[1]);
            }else if("start".equals(split2[0])){
                wrapper.gt("article_post_time", split2[1]);
            }else if("end".equals(split2[0])){
                wrapper.lt("article_post_time", split2[1]);
            }else if("rank_view".equals(split2[0])){
                if ("1".equals(split2[1])) {
                    wrapper.orderByAsc("article_view_count");
                } else {
                    wrapper.orderByDesc("article_view_count");
                }
            }else if("rank_like".equals(split2[0])){
                if ("1".equals(split2[1])) {
                    wrapper.orderByAsc("article_like_count");
                } else {
                    wrapper.orderByDesc("article_like_count");
                }
            }else if("rank_comment".equals(split2[0])){
                if ("1".equals(split2[1])) {
                    wrapper.orderByAsc("article_comment_count");
                } else {
                    wrapper.orderByDesc("article_comment_count");
                }
            }
        }

        return baseMapper.getIndexList(iPage, wrapper);
    }

    @Override
    public IPage<Article> getManageList(Integer userId,
                                        Integer pn,
                                        Integer limit,
                                        String condition) {
        //        分页查询
        IPage<Article> iPage = new Page<>(pn, limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        // 单一用户的文章获取
        if(null != userId) {
            wrapper.eq("user_id", userId);
        }

        wrapper = ArticleUtils.generateManageArticleWrapperByCondition(wrapper, condition);

        return baseMapper.getManageList(iPage, wrapper);
    }

    @Override
    public Article getSingleArticle(String articleId) {
        return baseMapper.getSingleArticle(articleId);
    }

    @Override
    public Article getSingleArticleDeleted(String articleId) {
        return baseMapper.getSingleArticleDeleted(articleId);
    }

    @Override
    public IPage<Article> getDeletedList(Integer userId, Integer pn, Integer limit,
                                         String condition) {
        //        分页查询
        IPage<Article> iPage = new Page<>(pn, limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        // 单一用户的文章获取
        if(null != userId) {
            wrapper.eq("user_id", userId);
        }

        wrapper = ArticleUtils.generateManageArticleWrapperByCondition(wrapper, condition);

        return baseMapper.getDeletedList(iPage, wrapper);
    }

    @Override
    public boolean deleteCompletely(String ids) {
        QueryWrapper<ArticleImage> wrapper = new QueryWrapper<>();
        ArticleImage articleImage = new ArticleImage();
        QueryWrapper<DeletedComment> deletedCommentQueryWrapper = new QueryWrapper<>();
        DeletedComment deletedComment = new DeletedComment();

        if(!ids.contains("&")){
            wrapper.eq("article_id", ids);
            deletedCommentQueryWrapper.eq("comment_article_id", ids);
            List<ArticleImage> articleImages = articleImage.selectList(wrapper);
            for (ArticleImage image : articleImages) {
                // 依次删除图片文件
                FileUtils.deleteImageIfExists(image.getInsertDate(), image.getFilename());
            }
            // 从表中删除
            articleImage.delete(wrapper);
            deletedComment.delete(deletedCommentQueryWrapper);
            // 删除所有已删除的评论
            baseMapper.deleteCompletely(ids);
        } else {
            String[] id = ids.split("&");
            for (String item : id) {
                // 删除图片文件
                wrapper.eq("article_id", item);
                deletedCommentQueryWrapper.eq("comment_article_id", item);
                List<ArticleImage> articleImages = articleImage.selectList(wrapper);
                for (ArticleImage image : articleImages) {
                    // 依次删除图片文件
                    FileUtils.deleteImageIfExists(image.getInsertDate(), image.getFilename());
                }
                // 从表中删除
                articleImage.delete(wrapper);
                deletedComment.delete(deletedCommentQueryWrapper);
                // 从文章表中删除文章
                baseMapper.deleteCompletely(item);
            }
        }
        return true;
    }

    @Override
    public boolean batchRecoverDeletedArticle(String ids) {
        if(!ids.contains("&")){
            baseMapper.batchRecover(ids);
        } else {
            String[] id = ids.split("&");
            for (String item : id) {
                baseMapper.batchRecover(item);
            }
        }
        return true;
    }

    @Override
    public IPage<PageArticle> getArticleByUserRecently(String id, Integer pn, Integer limit) {
        IPage<PageArticle> iPage = new Page<>(pn, limit);
        QueryWrapper<PageArticle> wrapper = new QueryWrapper<>();
        wrapper.eq("article_author", id);
        wrapper.orderByDesc("article_post_time");
        return baseMapper.getArticleByUserRecently(iPage, wrapper);
    }


}
