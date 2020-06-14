package co.com.jvirtualstore.configuration;

import co.com.jvirtualstore.model.Product;
import co.com.jvirtualstore.model.User;
import co.com.jvirtualstore.repository.ProductRepository;
import co.com.jvirtualstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AppInitializer implements SmartInitializingSingleton {

    private static Logger LOG = LoggerFactory.getLogger(AppInitializer.class);


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterSingletonsInstantiated() {
        LOG.info("App initialization ----->>>> afterSingletonsInstantiated <<<<<--------");
        Product product = new Product("AA Name", "CC Description");
        this.productRepository.save(product);
        product = new Product("CC Name", "BB Description");
        this.productRepository.save(product);
        product = new Product("BB Name", "AA Description");
        this.productRepository.save(product);

        String psw = passwordEncoder.encode("leon");

        User leon = new User("leon", psw, true);
        leon.addAuthority("READ");
        LOG.info("leon [{}]", leon);
        userRepository.save(leon);
        psw = passwordEncoder.encode("leon");
        User luna = new User("luna", psw, true);
        luna.addAuthority("READ");
        luna.addAuthority("WRITE");
        LOG.info("luna [{}]", luna);
        userRepository.save(luna);
    }
}
