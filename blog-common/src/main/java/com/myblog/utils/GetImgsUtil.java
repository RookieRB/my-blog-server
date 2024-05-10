package com.myblog.utils;

import com.myblog.constant.MessageConstant;
import com.myblog.exception.FailedToLoadFolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Component
public class GetImgsUtil {
    private final ResourceLoader resourceLoader;
    public GetImgsUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String[] getImgsNamesHandler(){
        Resource resource = resourceLoader.getResource("classpath:/static/face");
        File folder;
        try{
            folder = resource.getFile();
        }catch (IOException e){
            throw new FailedToLoadFolder(MessageConstant.FAILED_LOAD_FOLDER);
        }

        String[] imageExtensions = {"png","jpg","jpeg","gif","bmp"};
        return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(file -> {
                    String fileName = file.getName().toLowerCase();
                    return Arrays.stream(imageExtensions).anyMatch(fileName::endsWith);
                })
                .map(File::getName)
                .toArray(String[]::new);
    }

}
