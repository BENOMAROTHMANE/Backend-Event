package org.sid.backend.services.impl;
import org.sid.backend.dao.ActiviteRepository;
import org.sid.backend.dao.NotificationRepository;
import org.sid.backend.domaine.NotificationVo;
import org.sid.backend.domaine.converter.NotificationConverter;
import org.sid.backend.model.Activite;
import org.sid.backend.model.Notification;
import org.sid.backend.sec.repo.AppUserRepository;
import org.sid.backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Date;



@Service

public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public List<NotificationVo> getAllNotifications() {
        List<Notification> notification = notificationRepository.findAll();
        return NotificationConverter.toVo(notification);
    }

    @Override
    public NotificationVo getNotificationById(Long id) {
          Notification notification = notificationRepository.findById(String.valueOf(id)).get();
          return NotificationConverter.toVo(notification);

    }

    int i = 0;
    @Override
    @Scheduled(fixedRate = 50000)
    public void saveNotification() {
        for (Activite activite : activiteRepository.findAll(Sort.by(Sort.Direction.ASC, "dateDebut")) ){
          //  if (activite.getDateDebut().getTime() - new Date().getTime() <= 900000) {
            if(activite.getDateDebut().before(new Date())){
        //    assertThat(firstDate.isEqual(secondDate), is(false));
                Notification notification = new Notification();
                notification.setActivite(activite);
                notification.setConfirmed(false);
                notification.setDate(new Date());
                notification.setName("l' activite a commence" + i++);
                notificationRepository.save(notification);
                System.out.println("notification saved");
           }
        }
    }

    //

    //get notification
    @Override
    @Scheduled(fixedRate = 50000)
    public List<NotificationVo> getNotification(){
       List<Notification> notifications = notificationRepository.findAll();
       if (notifications.size() == 0){
           System.out.println("aucune notification");
       }
       else {
              System.out.println("notification getted");
       }

        //System.out.println("notification getted");
        return NotificationConverter.toVo(notifications);
    }




@Override
//@Scheduled(fixedRate = 50002)
public void addNotificationToUsers(String nameNotification, Long idActivite) {
    Notification notification = notificationRepository.findByName(nameNotification);
    Activite activite = activiteRepository.findById(idActivite).get();
    activite.getUsers().forEach(user -> {
        notification.getUsers().add(user);
        notificationRepository.save(notification);
    });
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Override
//    public void saveNotification(NotificationVo notificationVo) {
//        notificationRepository.save(NotificationConverter.toBo(notificationVo));
//    }
//
//
//    @Override
//    public NotificationVo updateNotification(NotificationVo notificationVo) {
//        //update notification
//        Notification notification = NotificationConverter.toBo(notificationVo);
//        notificationRepository.save(notification);
//        return NotificationConverter.toVo(notification);
//    }
//
//    @Override
//    public void deleteNotification(Long id) {
//        notificationRepository.deleteById(String.valueOf(id));
//    }
}
