package com.glotov.xml.entity;

import java.util.Objects;

public class Device {
    private String id;
    private String name;
    private String origin;
    private double price;
    private boolean isCritical;

    private Device(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.origin = builder.origin;
        this.price = builder.price;
        this.isCritical = builder.isCritical;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public boolean isCritical() {
        return isCritical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Double.compare(device.price, price) == 0 && isCritical == device.isCritical && Objects.equals(id, device.id) && Objects.equals(name, device.name) && Objects.equals(origin, device.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, price, isCritical);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", isCritical=" + isCritical +
                '}';
    }

    public static class Builder {
        private String id;
        private String name;
        private String origin;
        private double price;
        private boolean isCritical;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setCritical(boolean isCritical) {
            this.isCritical = isCritical;
            return this;
        }

        public Device build() {
            return new Device(this);
        }
    }
}
