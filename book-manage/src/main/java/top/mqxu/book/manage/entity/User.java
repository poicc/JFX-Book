package top.mqxu.book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * User实体类
 *
 * @author mqxu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String mobile;
    private String email;
    private String password;
    private String username;
    private String avatar;
    private String identity;
    private String department;
    private LocalDate createDate;

}
