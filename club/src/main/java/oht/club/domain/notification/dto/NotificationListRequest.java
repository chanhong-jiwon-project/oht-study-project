package oht.club.domain.notification.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationListRequest {

    private Long id;

    private String title;

    private String content;

    private String createdAt;

}
