package com.gongdel.springsecurity.controller;

import com.gongdel.springsecurity.model.User;
import com.gongdel.springsecurity.model.UserProfile;
import com.gongdel.springsecurity.service.UserProfileService;
import com.gongdel.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/app")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/", "list"}, method = RequestMethod.GET)
    public String listUser(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "userList";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);

        return "newuser";
    }

    // 등록
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveUsre(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newuser";
        }

        /**
         * field[sso]의 유일성을 지키기 위해 선호되는 방법은  Model class[User]의 field[sso] 위에 custom @Unique annotation을 구현하고 적용하는 것이다.
         * 아래에 언급된 code 구간[if 구간]은 custom 에러를 외부의 validation framework로 채우는 것과 동시에 내부화된 messages 사용을 보여준다.
         */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}
                    , Locale.getDefault()));
            result.addError(ssoError);
            return "registration";

        }

        userService.save(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        return "registrationsuccess";
    }

    // 수정
    @RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySso(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);

        return "newuser";
    }
    @RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newuser";
        }
        /**
         * 만약 UI에서 User의 유일 키인 SSO_ID의 변경을 가능하게 한다면 아래의 코드를 사용
         *
         *  if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
                FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId",
                  new String[]{user.getSsoId()}, Locale.getDefault()));

         result.addError(ssoError);
         return "registration";

         */
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");

        return "registrationsuccess";
    }

    // 삭제
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/app/list";
    }

    /**
     * UserProfile list를 views에 제공
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

}
