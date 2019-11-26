package org.vincent.springmvc.dto.outgoing;

import java.util.List;

public class OutputEntity {

    private List<Staff> data;

    public List<Staff> getData() {
        return data;
    }

    public void setData(List<Staff> data) {
        this.data = data;
    }
}
