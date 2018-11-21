package com.best.service.interfaces;

import com.best.bean.Employee;

/**
 * Created by EDZ on 2018/11/21.
 */
public interface WorkServiceI {
    Employee getEmpByRedis(Long id);
}
