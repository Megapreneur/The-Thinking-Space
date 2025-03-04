package com.uncledemy.the_thinking_space.service;

import java.util.List;

public interface NotificationService {
    void sendWelcomeNotification(String email);
    void sendNewsletterNotification();
    void sendNotification(List<String> emails);
}
