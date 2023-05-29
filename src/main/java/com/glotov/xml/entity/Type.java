package com.glotov.xml.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ports", "isPeripheral", "powerConsumption", "hasCooler", "componentGroup"})
public class Type {
    @XmlElementWrapper(name = "Ports")
    @XmlElement(name = "Port")
    private List<Port> ports;

    @XmlAttribute(name = "Peripheral")
    private boolean isPeripheral;

    @XmlAttribute(name = "PowerConsumption")
    private int powerConsumption;

    @XmlAttribute(name = "Cooler")
    private Boolean hasCooler;

    @XmlAttribute(name = "ComponentGroup")
    private String componentGroup;

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
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

    @Override
    public String toString() {
        return "Type{" +
                "ports=" + ports +
                ", isPeripheral=" + isPeripheral +
                ", powerConsumption=" + powerConsumption +
                ", hasCooler=" + hasCooler +
                ", componentGroup='" + componentGroup + '\'' +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Port {
        @XmlValue
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Port{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}

