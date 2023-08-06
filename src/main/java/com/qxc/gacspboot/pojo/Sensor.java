package com.qxc.gacspboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_sensor")
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    private String sensorname;
}
