package carveo_portal.carveoManagement.utility;

import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitorScheduler {

    @Autowired
    private VisitorRepository visitorRepository;

    // Used Schedular to get daily report

    @Scheduled(cron = "0 * 21 * * ?") // every day at 11 PM
    public void generateDailyVisitorReport() {
        List<Visitor> visitors = visitorRepository.findAll();

        try {
            ExcelGenerator.generateVisitorReport(visitors);
            System.out.println("Visitor report generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
