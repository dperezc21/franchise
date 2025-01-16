import com.prueba.franquicia.application.BranchService;
import com.prueba.franquicia.application.FranchiseService;
import com.prueba.franquicia.services.ProductService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ProductService productBean() { return new ProductService(); }

    @Bean
    public BranchService branchBean() { return new BranchService(); }

    @Bean
    public FranchiseService franchiseBean() { return new FranchiseService(); }
}
