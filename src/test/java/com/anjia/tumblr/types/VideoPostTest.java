package com.anjia.tumblr.types;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

/**
 * VideoPost tests
 * @author jc
 */
public class VideoPostTest extends TypeTest {

    private int thumbnailHeight = 2;
    private int thumbnailWidth = 2;
    private String permalinkUrl = "permaUrl";
    private String thumbnailUrl = "url";
    private String caption = "hello";
    private String videos = "[{\"width\":300,\"embed_code\":\"embed\"}]";
    private VideoPost post;

    @Before
    public void setup() {
        Map<String, Object> flat = new HashMap<String, Object>();
        flat.put("type", "video");
        flat.put("caption", caption);
        flat.put("player", JSONArray.parse(videos));
        flat.put("thumbnail_url", thumbnailUrl);
        flat.put("thumbnail_width", thumbnailWidth);
        flat.put("thumbnail_height", thumbnailHeight);
        flat.put("permalink_url", permalinkUrl);
        post=JSON.parseObject(JSON.toJSONString(flat), new TypeReference<VideoPost>(){});
    }

    @Test
    public void testReaders() {
        assertEquals(caption, post.getCaption());
        assertEquals(thumbnailUrl, post.getThumbnailUrl());
        assertEquals(thumbnailHeight, post.getThumbnailHeight());
        assertEquals(thumbnailWidth, post.getThumbnailWidth());
        assertEquals(permalinkUrl, post.getPermalinkUrl());

        Video video = post.getVideos().get(0);
        assertEquals(new Integer(300), video.getWidth());
        assertEquals("embed", video.getEmbedCode());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setDataWithEmbedCode() {
        post.setData(new File("some_path"));
        post.setEmbedCode("something");
    }

    @Test
    public void setDataWithoutEmbedCode() {
        File file = new File("some_path");
        post.setData(file);
        Map<String, Object> detail = post.detail();
        assertEquals(file, detail.get("data"));
        // clear
        post.setData(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setEmbedCodeWithData() {
        post.setEmbedCode("something");
        post.setData(new File("some_path"));
    }

    @Test
    public void setEmbedCodeWithoutData() {
        String embedCode = "external";
        post.setEmbedCode(embedCode);
        Map<String, Object> detail = post.detail();
        assertEquals(embedCode, detail.get("embed"));
        // clear
        post.setEmbedCode(null);
    }

    @Test
    public void testOtherDetail() {
        post.setCaption("test_caption");

        Map<String, Object> detail = post.detail();
        assertEquals("test_caption", detail.get("caption"));
        assertEquals("video", detail.get("type"));
    }

}
