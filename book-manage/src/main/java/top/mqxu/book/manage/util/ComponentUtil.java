package top.mqxu.book.manage.util;

import javafx.scene.control.Button;

/**
 * 组件工具类
 * @author mqxu
 */
public class ComponentUtil {

    /**
     * 根据传入的文字和主题返回一个按钮
     *
     * @param text  文字
     * @param theme 主题
     * @return 按钮
     */
    public static Button getButton(String text, String theme) {
        Button button = new Button(text);
        button.getStyleClass().add(theme);
        return button;
    }
}
