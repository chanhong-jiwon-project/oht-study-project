package oht.club.service.notification;



import oht.club.dto.notification.NotificationDTO;
import oht.club.repository.notification.entity.Notification;

import java.util.List;

public interface NotificationService {

    String join(NotificationDTO notificationDTO);

    String update(Long notificationId, NotificationDTO notificationDTO);

    List<Notification> getList();

    Notification getDetail(Long notificationId);

    boolean delete(Long notificationId);
}
