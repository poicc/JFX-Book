package top.mqxu.book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @description:
 * @author: mqxu
 * @date: 2021-12-10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    private Long id;
    private String account;
    private String password;
    private LocalDateTime createTime;
}
