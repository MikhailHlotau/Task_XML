package com.glotov.xml.entity;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devices devices = (Devices) o;
        return Objects.equals(deviceList, devices.deviceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceList);
    }

    @Override
    public String toString() {
        return "Devices{" +
                "deviceList=" + deviceList +
                '}';
    }
}
