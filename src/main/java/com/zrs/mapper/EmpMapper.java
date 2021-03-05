package com.zrs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrs.bean.Emp;

import java.util.List;


public interface EmpMapper extends BaseMapper<Emp> {
    List<Emp> getEmps();
    int addEmp(Emp emp);
}
