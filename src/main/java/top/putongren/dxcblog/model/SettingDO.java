package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: SettingDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String value;
    private String remark;
}
