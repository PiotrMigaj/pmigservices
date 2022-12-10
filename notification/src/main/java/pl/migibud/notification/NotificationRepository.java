package pl.migibud.notification;

import org.springframework.data.jpa.repository.JpaRepository;

interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
