package oht.club.controller.board.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import oht.club.dto.notification.NotificationDTO;
import oht.club.repository.notification.NotificationRepository;
import oht.club.repository.notification.entity.Notification;
import oht.club.service.notification.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/notification")
//@RestController
@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    // 공지사항 리스트
    @GetMapping
    public String notification(Model model) {
        List<Notification> notificationList = notificationService.getList();
        model.addAttribute("notificationList", notificationList);
        return "notification/list";
    }

    // 공지사항 상세
    @GetMapping("/{notificationId}")
    public String notificationView(@PathVariable("notificationId") Long notificationId, Model model) {
        Notification notification = notificationService.getDetail(notificationId);
        model.addAttribute("notification", notification);
        return "notification/view";
    }

    // 공지사항 insert 뷰
    @GetMapping("/edit")
    public String notificationInsertView(Model model) {

        model.addAttribute("notification", new NotificationDTO());

        return "notification/edit";
    }

    // 공지사항 insert
    @PostMapping("/edit")
    public String notificationInsert(Model model, @ModelAttribute NotificationDTO notificationDTO) {


        log.info("title = {}, content = {}", notificationDTO.getTitle(), notificationDTO.getContent());

        String insert = notificationService.join(notificationDTO);

        model.addAttribute("notification", notificationDTO);

        // 뷰페이지로 리다이렉트
        // 아직 뷰페이지가 없음
        if(insert.equalsIgnoreCase("success")){
            return "notification/view";
        }else{
            return "redirect:/notification";
        }
    }

    // 공지사항 업데이트 뷰
    @GetMapping("/edit/{notificationId}")
    public String notificationUpdateView(Model model, @PathVariable("notificationId") Long notificationId) {

        Notification notification = notificationService.getDetail(notificationId);

        model.addAttribute("notification", notification);

        return "notification/edit";
    }

    // 공지사항 업데이트
    @PostMapping("/edit/{notificationId}")
    public String notificationUpdate(Model model, @PathVariable("notificationId") Long notificationId, @ModelAttribute NotificationDTO notificationDTO) {

        String update = notificationService.update(notificationId, notificationDTO);

        model.addAttribute("notification", notificationDTO);
        // 뷰페이지로 리다이렉트
        // 아직 뷰페이지가 없음
        if(update.equalsIgnoreCase("success")){
            return "/notification/view";
        }else{
            return "redirect:/notification";
        }


    }

    // 공지사항 삭제
    @PostMapping("/delete/{notificationId}")
    public String notificationDelete(@PathVariable("notificationId") Long notificationId) {
        boolean deleteNotification = notificationService.delete(notificationId);
        if(deleteNotification){
            return "redirect:/notification";
        }else{
            return "redirect:/notification";
        }
    }
}
