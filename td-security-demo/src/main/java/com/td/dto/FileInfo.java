package com.td.dto;

/**
 * @className: FileInfo
 * @description:
 * @author: cyd
 * @date: 2019/12/1 下午4:42
 **/
public class FileInfo {
    private String path;

    public FileInfo(String path){
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
