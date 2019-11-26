package org.vincent.springmvc.controller.springpure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vincent.springmvc.dto.outgoing.OutputEntity;
import org.vincent.springmvc.dto.outgoing.Staff;
import org.vincent.springmvc.system.SystemConstant;

import java.util.ArrayList;
import java.util.List;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping(SystemConstant.FE_SPRING_PURE + "datatable")
public class DatatableController {

    @GetMapping
    public String datatablePage() {
        return SystemConstant.FE_SPRING_PURE + "datatable/index";
    }

    @GetMapping("data")
    @ResponseBody
    public OutputEntity generate() {
        List<Staff> staffs = new ArrayList<>();
        for(int index = 0; index < 300; index++) {
            staffs.add(generateStaff("data_" + index));
        }

        OutputEntity entity = new OutputEntity();
        entity.setData(staffs);

        return entity;
    }

    private Staff generateStaff(String basicData) {
        Staff staff = new Staff();
        staff.setExtn(basicData + "_setExtn");
        staff.setName(basicData + "_setName");
        staff.setOffice(basicData + "_setOffice");
        staff.setPosition(basicData + "_setPosition");
        staff.setSalary(basicData + "_setSalary");
        staff.setStart_date(basicData + "_setStart_date");
        return staff;
    }
}
