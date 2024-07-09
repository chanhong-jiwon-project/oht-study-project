package oht.club.service.notification.impl;


import jakarta.transaction.Transactional;
import oht.club.dto.notification.NotificationDTO;
import oht.club.repository.notification.NotificationRepository;
import oht.club.repository.notification.entity.Notification;
import oht.club.service.notification.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @Override
    @Transactional
    public String join(NotificationDTO notificationDTO){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Notification notification = Notification.builder()
                .title(notificationDTO.getTitle())
                .content(notificationDTO.getContent())
                .createdAt(formattedDateTime)
                .build();

        notificationRepository.save(notification);

        return "success";

    }


    @Override
    @Transactional
    public String update(Long notificationId, NotificationDTO notificationDTO){

        // 나중에 업데이트 시간으로 사용
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = now.format(formatter);

        Notification notification = notificationRepository.findById(notificationId).orElseThrow(RuntimeException::new);

        notification.setTitle(notificationDTO.getTitle());
        notification.setContent(notificationDTO.getContent());

        notificationRepository.save(notification);

        return "success";

    }

    @Override
    public List<NotificationDTO> getList() {

        List<Notification> notificationList = notificationRepository.findAll();

        return notificationList.stream()
                .map(notification -> new NotificationDTO(notification.getId(), notification.getTitle(), notification.getContent(), notification.getCreatedAt()))
                .collect(Collectors.toList());

    }

    @Override
    public NotificationDTO getDetail(Long notificationId) {

        Optional<Notification> findNotification = notificationRepository.findById(notificationId);

        return findNotification.map(notification -> new NotificationDTO(notification.getId(), notification.getTitle(), notification.getContent(), notification.getCreatedAt())).orElse(null);

    }

    @Override
    @Transactional
    public boolean delete(Long notificationId) {

        // 해당 id 를 삭제
        notificationRepository.deleteById(notificationId);

        Optional<Notification> deletedNotification = notificationRepository.findById(notificationId);

        if(deletedNotification.isEmpty()){
            return true;
        }else{
            return false;
        }

    }


}
