package oht.club.dto.notification;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class NotificationDTO {

    private Long id;

    private String title;

    private String content;

    private String createdAt;

}
