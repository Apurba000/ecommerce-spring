package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import org.springframework.ui.Model;
public interface UserStatus {
    Model loginStatus(Model model);
}
