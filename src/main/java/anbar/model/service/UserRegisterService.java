package anbar.model.service;

import anbar.model.entity.User;





import anbar.model.repository.UserRegisterRepository;

public class UserRegisterService {
    public static void save(User user) throws Exception {
        try (UserRegisterRepository userRegisterRepository = new UserRegisterRepository()) {
            userRegisterRepository.save(user);
        }
    }
}
