package com.gongdel.springsecurity.controller;

import com.gongdel.springsecurity.model.FileBucket;
import com.gongdel.springsecurity.model.User;
import com.gongdel.springsecurity.model.UserDocument;
import com.gongdel.springsecurity.service.UserDocumentService;
import com.gongdel.springsecurity.service.UserService;
import com.gongdel.springsecurity.utill.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    UserService userService;

    @Autowired
    FileValidator fileValidator;

    /**
     *  @InitBinder를 이용해서 Validator에 대한 직접적인 호출없이 spring이 유효성 검사 코드를 실행
     *  :폼과 커맨드 객체 사이의 매핑을 처리해주는 WebDataBinder를 초기화할 수 있도록 하고 있다.
     *  :이 메서드에서 WebDataBinder.setValidator() 메서드를 이용해서 커맨드 객체의 유효성 여부를 검사할 때 사용할 Validator를 설정하게 됨
     */
    @InitBinder("fileBucket") // InitBinder method가 적용되는 객체를 더 구체화하기 위해서 value 요소 사용, value는 command/form의 속성 이름
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    // 등록
    @RequestMapping(value = "/add-document-{userId}", method = RequestMethod.GET)
    public String addDocuments(@PathVariable int userId, ModelMap model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<UserDocument> documents = userDocumentService.findAllById(userId);
        model.addAttribute("document", documents);

        return "managedocuments";
    }
}
