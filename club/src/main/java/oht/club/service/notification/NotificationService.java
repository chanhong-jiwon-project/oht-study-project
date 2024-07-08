package oht.club.service.notification;



import oht.club.domain.notification.dto.NotificationListRequest;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {

    String join(NotificationListRequest notificationListRequest);

    String update(Long notificationId, NotificationListRequest notificationListRequest);


}
