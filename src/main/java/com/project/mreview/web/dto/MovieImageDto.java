package com.project.mreview.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieImageDto {

    private String uuid;

    private String imgName;

    private String path;

    private String getImageURL(){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+imgName,"UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    private String getThumbnailURL(){
        try{
            return URLEncoder.encode(path+"/s_"+uuid+"_"+imgName,"UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
