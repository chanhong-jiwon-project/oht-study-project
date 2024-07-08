package oht.club.controller.board.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import oht.club.domain.notification.dto.NotificationListRequest;
import oht.club.service.notification.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequestMapping("/notification")
//@RestController
@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // 공지사항 리스트
    @GetMapping
    public String notification(Model model) {
        return "notification/list";
    }

    // 공지사항 상세
    @GetMapping("/{notificationId}")
    public String notificationView(@PathVariable("notificationId") Long notificationId, Model model) {

        return "notification/view";
    }

    // 공지사항 insert 뷰
    @GetMapping("/edit")
    public String notificationInsertView() {
        return "notification/edit";
    }

    // 공지사항 insert 뷰
    @PostMapping("/edit")
    public String notificationInsert(@ModelAttribute NotificationListRequest notificationListRequest) {


        log.info("title = {}, content = {}", notificationListRequest.getTitle(), notificationListRequest.getContent());

        String insert = notificationService.join(notificationListRequest);


        // 뷰페이지로 리다이렉트
        // 아직 뷰페이지가 없음
        if(insert.equalsIgnoreCase("success")){
            return "redirect:/notification/edit";
        }else{
            return "redirect:/notification/edit";
        }
    }

    // 공지사항 업데이트 뷰
    @GetMapping("/edit/{notificationId}")
    public String notificationUpdateView(Model model) {

        return "notification/edit";
    }

    // 공지사항 업데이트
    @PostMapping("/edit/{notificationId}")
    public String notificationUpdate(@PathVariable("notificationId") Long notificationId, @ModelAttribute NotificationListRequest notificationListRequest) {

        String update = notificationService.update(notificationId, notificationListRequest);

        // 뷰페이지로 리다이렉트
        // 아직 뷰페이지가 없음
        if(update.equalsIgnoreCase("success")){
            return "redirect:/notification/edit";
        }else{
            return "redirect:/notification/edit";
        }


    }

    // 공지사항 삭제
    @PostMapping("/delete/{notificationId}")
    public void notificationDelete() {
        System.out.println(123);
    }
}
