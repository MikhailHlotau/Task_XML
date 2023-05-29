package com.glotov.xml.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Type {

    @XmlElementWrapper(name = "Ports")
    @XmlElement(name = "Port")
    private String[] ports;

    @XmlAttribute(name = "Peripheral")
    private boolean isPeripheral;

    @XmlAttribute(name = "PowerConsumption")
    private int powerConsumption;

    @XmlAttribute(name = "Cooler")
    private Boolean hasCooler;

    @XmlAttribute(name = "ComponentGroup")
    private String componentGroup;

    public String[] getPorts() {
        return ports;
    }

    public void setPorts(String[] ports) {
        this.ports = ports;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public Boolean getHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(Boolean hasCooler) {
        this.hasCooler = hasCooler;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(String componentGroup) {
        this.componentGroup = componentGroup;
    }
}

