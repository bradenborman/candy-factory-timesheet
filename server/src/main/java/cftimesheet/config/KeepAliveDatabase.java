package cftimesheet.config;

import cftimesheet.daos.EmployeeDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KeepAliveDatabase {

    private EmployeeDao employeeDao;

    public KeepAliveDatabase(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //Hopefully runs to keep database alive
    @Scheduled(cron = "0/15 * * * * ?")
    public void updateNextTeamPlayingAndOddsQuick() {
        employeeDao.fetchAllActiveEmployees();
    }

}