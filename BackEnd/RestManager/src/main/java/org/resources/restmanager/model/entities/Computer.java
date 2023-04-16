package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

@Entity
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Computer extends Resource {
    @NonNull
    private String CPU;
    @NonNull
    private String disk;
    @NonNull
    private int RAM;
    @NonNull
    private String screen;


    @Override
    public String getResourceType(){
        return "Computer";
    }

    @NonNull
    public String getCPU() {
        return CPU;
    }

    public void setCPU(@NonNull String CPU) {
        this.CPU = CPU;
    }

    @NonNull
    public String getDisk() {
        return disk;
    }

    public void setDisk(@NonNull String disk) {
        this.disk = disk;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    @NonNull
    public String getScreen() {
        return screen;
    }

    public void setScreen(@NonNull String screen) {
        this.screen = screen;
    }
}
