package top.putongren.dxcblog.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: IpInfoBO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpInfoBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private Info data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private String ip;
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
        @JsonProperty(value = "country_id")
        private String countryId;
        @JsonProperty(value = "area_id")
        private String areaId;
        @JsonProperty(value = "region_id")
        private String regionId;
        @JsonProperty(value = "city_id")
        private String cityId;
        @JsonProperty(value = "county_id")
        private String countyId;
        @JsonProperty(value = "isp_id")
        private String ispId;
    }
}
