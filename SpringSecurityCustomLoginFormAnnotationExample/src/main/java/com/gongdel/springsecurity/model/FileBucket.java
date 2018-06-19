package com.gongdel.springsecurity.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

    MultipartFile file;
    String description;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
/**
 * Spring은  multipart request에서 받아진 uploaded file의 표현인 'org.springframework.web.multipart.MultipartFile'를 제공한다.
 * 이는 getName(), getContentType(), getBytes(), getInputStream() 등과 같은 유용한 methods를 제공한다.
 * 이는 loadeded 되고 있는 file에 대한 정보를 탐색하는 동안 life bit를 더 쉽게 만든다.
 **/