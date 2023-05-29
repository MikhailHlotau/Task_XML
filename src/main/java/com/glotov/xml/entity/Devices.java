package com.glotov.xml.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class Devices {

    @XmlElement(name = "Device")
    private List<Device> deviceList;

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
