import com.prueba.franquicia.services.ProductService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ProductService productBean() {
        return new ProductService();
    }
}
