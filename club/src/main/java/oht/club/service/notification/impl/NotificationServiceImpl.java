package oht.club.service.notification.impl;


import jakarta.transaction.Transactional;
import oht.club.domain.notification.dto.NotificationListRequest;
import oht.club.repository.notification.NotificationRepository;
import oht.club.repository.notification.entity.Notification;
import oht.club.service.notification.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @Override
    public String join(NotificationListRequest notificationListRequest){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Notification notification = Notification.builder()
                .title(notificationListRequest.getTitle())
                .content(notificationListRequest.getContent())
                .createdAt(formattedDateTime)
                .build();

        notificationRepository.save(notification);

        return "success";

    }


    @Override
    @Transactional
    public String update(Long notificationId, NotificationListRequest notificationListRequest){

        // 나중에 업데이트 시간으로 사용
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = now.format(formatter);

        Notification notification = notificationRepository.findById(notificationId).orElseThrow(RuntimeException::new);

        notification.setTitle(notificationListRequest.getTitle());
        notification.setContent(notificationListRequest.getContent());

        notificationRepository.save(notification);

        return "success";

    }


}
